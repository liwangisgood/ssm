package cn.ahpu.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author:liwang
 * @date: 2022/3/27 15:26
 * @Description:TODO
 * @Algorithm Design：TODO
 */
@ControllerAdvice
public class MyUserException extends Exception{

	public MyUserException(){

	}

	public MyUserException(String message){
		super(message);
	}

}
