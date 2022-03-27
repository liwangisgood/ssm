package cn.ahpu.handler;

import cn.ahpu.exception.AgeException;
import cn.ahpu.exception.NameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author:liwang
 * @date: 2022/3/27 15:41
 * @Description:TODO
 * @Algorithm Design：TODO
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = NameException.class)
	public ModelAndView doNameException(Exception ex){
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg","姓名必须是zs，其他用户不能访问");
		mv.addObject("ex",ex);
		mv.setViewName("nameError");
		return mv;
	}

	@ExceptionHandler(value = AgeException.class)
	public ModelAndView doAgeException(Exception ex){
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg","你的年龄不能大于80");
		mv.addObject("ex",ex);
		mv.setViewName("ageError");
		return mv;
	}

	@ExceptionHandler
	public ModelAndView doOtherException(Exception ex){
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg","其他发生的异常");
		mv.addObject("ex",ex);
		mv.setViewName("otherError");
		return mv;
	}



}
