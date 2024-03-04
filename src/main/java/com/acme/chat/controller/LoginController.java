package com.acme.chat.controller;

import com.acme.chat.base.BusinessResult;
import com.acme.chat.annotation.LoginToken;
import com.acme.chat.annotation.PassToken;
import com.acme.chat.po.User;
import com.acme.chat.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@Controller
@Api("用户登录模块")
public class LoginController {
	private Logger logger= LoggerFactory.getLogger(getClass());
	@Autowired
	UserService userService;


	/**
	 * 用户名密码登录
	 * @param userName
	 * @param passWord
	 * @param httpSession
	 * @return
	 */
	@ApiOperation("用户名密码登录")
	@RequestMapping("/login")
	@ResponseBody
	@LoginToken
	public BusinessResult login(@RequestParam("username") String userName,@RequestParam("password") String passWord,HttpSession httpSession){
		logger.info("正在进行密码登录");
		User user = userService.checkUserLoginByUserName(userName, passWord);
		BusinessResult<User> businessResult = new BusinessResult<>();
		businessResult.setData(user);
		return businessResult;
	}

	/**
	 * 快速登录，手机号+验证码实现一键注册+登录
	 * @param tel
	 * @param checkNum
	 * @param httpSession
	 * @return
	 */
	@ApiOperation("快速登录")
	@RequestMapping("/fastlogin")
	@ResponseBody
	@LoginToken
	public BusinessResult fastlogin(@RequestParam("tel") String tel,@RequestParam("checkNum") String checkNum,HttpSession httpSession){
		logger.info("正在进行快速登录");
		User user = userService.getUserByTel(tel);
		BusinessResult<User> businessResult = new BusinessResult<>();
		businessResult.setData(user);
		return businessResult;
	}

	/**
	 * 获取验证码
	 * @param tel
	 * @return
	 */
	@ApiOperation("获取验证码")
	@RequestMapping("/getcheckcode")
	@ResponseBody
	@LoginToken
	public BusinessResult fastlogin(@RequestParam("tel") String tel){
		logger.info("正在通过验证码登录");
		return userService.generateAndSendCode(tel);
	}

	/**
	 * 主界面跳转
	 * *登录成功后执行
	 * @return
	 */
	@ApiOperation("主界面跳转")
	@PassToken
	@RequestMapping("/chatroom")
	public String login(){
		logger.info("正在进行主界面跳转");
		return "chatmain";
	}


	/**
	 * 登出
	 * @param userName
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(@RequestParam("username") String userName,HttpSession httpSession){
		logger.info("正在登出");
		userService.userLogOut(userName);
		return "index";
	}

  }
