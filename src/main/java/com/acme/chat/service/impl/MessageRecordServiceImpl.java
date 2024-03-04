package com.acme.chat.service.impl;

import com.acme.chat.base.Message;
import com.acme.chat.mapper.MessgeRecordMapper;
import com.acme.chat.po.MessageRecord;
import com.acme.chat.service.MessageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author wk.
 * @Date 2023/9/10 2:32
 */
@Service
public class MessageRecordServiceImpl implements MessageRecordService {

    @Autowired
    MessgeRecordMapper messageRecordMapper;


    /**
     * 获取指定用户所有未读消息并转换为消息对象
     * @param userName
     * @return
     */
    @Override
    public List<Message> getUserAllUnreadMessage4send(String userName) {
        List<MessageRecord> userUnreadMessages = messageRecordMapper.getUserUnreadMessage(userName);
        List<Message> messageList = new ArrayList<>();
        if(userUnreadMessages != null){
            for(MessageRecord messageRecord : userUnreadMessages){
                Message<String> message = recordToMessageBeanTrans(messageRecord);
                messageList.add(message);
            }
        }
        return messageList;
    }

    /**
     * 用于将消息记录对象MessageRecord转换为消息对象Message<String>的过程
     * @param messageRecord
     * @return
     */
    private Message<String> recordToMessageBeanTrans(MessageRecord messageRecord) {
        Message<String> message = new Message<>();
        message.setTo(messageRecord.getToUser());
        message.setFrom(messageRecord.getFromUser());
        message.setContentType(messageRecord.getContentType());
        message.setDate(messageRecord.getDate());
        message.setContent(messageRecord.getContent());
        return message;
    }

    /**
     * 将消息对象转换为消息记录对象并存储到数据库
     * @param msg
     */
    @Override
    public void addMeasgeRecord(Message msg) {
        MessageRecord messageRecord = messageToRecordBeanTrans(msg);
        messageRecordMapper.addMessagerecord(messageRecord);

    }

    /**
     * 根据用户名删除用户消息记录
     * @param userName
     */
    @Override
    public void deleteUserMessagerecord(String userName) {
        messageRecordMapper.deleteUserMessagerecord(userName);
    }

    /**
     * 将消息对象Message转换为消息记录对象MessageRecord的过程
     * @param message
     * @return
     */
    private MessageRecord messageToRecordBeanTrans(Message message) {
        MessageRecord messageRecord = new MessageRecord();
        messageRecord.setFromUser(message.getFrom());
        messageRecord.setToUser(message.getTo());
        if(message.getContent() != null){
            messageRecord.setContent(message.getContent().toString());
        }

        messageRecord.setDate(message.getDate());
        messageRecord.setState(1);
        messageRecord.setContentType(message.getContentType());
        return messageRecord;
    }
}
