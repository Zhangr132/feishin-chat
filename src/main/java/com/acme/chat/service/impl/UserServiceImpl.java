package com.acme.chat.service.impl;

import com.acme.chat.base.BusinessResult;
import com.acme.chat.cache.LoginUserInfoCahce;
import com.acme.chat.chatenum.ErrorEnum;
import com.acme.chat.exception.BusinessException;
import com.acme.chat.mapper.UserMapper;
import com.acme.chat.po.User;
import com.acme.chat.service.UserService;
import com.acme.chat.utils.SchemaTaskUtils;
import com.acme.chat.utils.SendSms;
import com.acme.chat.vo.UserVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author wk.
 * @Date 2023/9/7 23:41
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 检查用户登录
     * @param userName
     * @param password
     * @return
     */
    @Override
    public User checkUserLoginByUserName(String userName, String password) {
        User user = userMapper.getUserByUserName(userName);
        if(user == null){
            throw new BusinessException(ErrorEnum.NO_USER);
        }
        if(!password.equalsIgnoreCase(user.getPassWord())){
            throw new BusinessException(ErrorEnum.CHECK_USER_FAIL);
        }
        return user;
    }


    /**
     * 获取所有用户并分组
     *  *按用户名分组
     * @return
     */
    @Override
    public Map<String,List<User>> getAllUserMap(){
        List<User> allUser = userMapper.getAllUser();
        Map<String, List<User>> collect = allUser.stream().collect(Collectors.groupingBy(User::getUserName));

//        List<String> userNames = allUser.stream().map(User::getUserName).collect(Collectors.toList());
        return collect;
    }

    /**
     * 获取所有用户的在线状态信息
     * @param onLineUsers
     * @return
     */
    @Override
    public List<UserVO> getAllUserWithState(Set<String> onLineUsers) {
        Map<String,List<User>> allUserMap = getAllUserMap();
        List<String> offlineUsers = new ArrayList<>(allUserMap.keySet()).stream().filter(u-> !onLineUsers.contains(u)).collect(Collectors.toList());
        ArrayList<UserVO> allUsers = new ArrayList<>();
        if(onLineUsers != null){
            for(String userName : onLineUsers){
                UserVO userVO = new UserVO();
                userVO.setUserName(userName);
                userVO.setOnLineState(true);
                userVO.setPicture(allUserMap.get(userName).get(0).getPicture());
                allUsers.add(userVO);
            }
        }

        if(offlineUsers != null || offlineUsers.size() > 0){
            for(String userName : offlineUsers){
                UserVO userVO = new UserVO();
                userVO.setUserName(userName);
                userVO.setOnLineState(false);
                userVO.setPicture(allUserMap.get(userName).get(0).getPicture());
                allUsers.add(userVO);
            }
        }


        return allUsers;
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @Override
    public User getUserByName(String username) {

        return userMapper.getUserByUserName(username);
    }

    /**
     * 根据电话号码获取用户信息
     * @param tel
     * @return
     */
    @Override
    public User getUserByTel(String tel) {
        return userMapper.getUserByTel(tel);
    }

    /**
     * 用户登出
     * @param userName
     */
    @Override
    public void userLogOut(String userName) {
        LoginUserInfoCahce.removeTokenByUserName(userName);
        LoginUserInfoCahce.removeUserInfoByUserName(userName);
    }

    /**
     * 注册用户
     * @param user
     */
    @Override
    public void registerUser(User user) {
        userMapper.addUser(user);
    }

    /**
     * 生成并发送手机验证码
     * @param tel
     * @return
     */
    @Override
    public BusinessResult generateAndSendCode(String tel) {
        Random random = new Random();
        int code = random.nextInt(900000);
        code += 100000;
        LoginUserInfoCahce.putTelCode(tel,code);
        //一分钟后清理手机验证码
        SchemaTaskUtils.clearMapKeyByDelay(LoginUserInfoCahce.getTelCodeMap(),tel,1000*60L);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);

        JSONObject sendResult = SendSms.send(tel, jsonObject.toJSONString());
        BusinessResult businessResult = new BusinessResult();
        if (sendResult.getString("Code").equals("OK")) {
            return businessResult;
        }
        businessResult.setCode(-1);
        businessResult.setMessage(sendResult.getString("Message"));
        return businessResult;
    }
}
