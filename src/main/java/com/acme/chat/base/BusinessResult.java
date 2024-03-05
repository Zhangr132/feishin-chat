package com.acme.chat.base;

import com.acme.chat.exception.BusinessException;
import lombok.Data;

/**
 * @Description TODO
 * @Author
 * @Date 2024/2/29 23:56
 */
@Data
public class BusinessResult<T> {

    private T data;
    private Integer code = 0;
    private String message = "success";

    public BusinessResult(BusinessException businessException){
        this.code = businessException.getCode();
        this.message = businessException.getMessage();
    }

    public BusinessResult(){

    }

    public BusinessResult(T t){
        data = t;
    }
}
