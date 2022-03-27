package cn.ahpu.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:liwang
 * @date: 2022/3/27 16:35
 * @Description:TODO
 * @Algorithm Design：TODO
 */

public class MyInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("prehandler拦截器方法执行……");
//		request.getRequestDispatcher("/tips.jsp").forward(request,response);
//		return false;//拦截请求
//		return true;
//		return HandlerInterceptor.super.preHandle(request, response, handler);

		String loginName="";
//		Object attr = request.getSession().getAttribute("name");//湖片区不到浏览器发送的初始name=zs 为""
		Object attr = request.getParameter("name");
		if (attr!=null){
			loginName = (String) attr;
		}

		if (!"zs".equals(loginName)){
			request.getRequestDispatcher("/tips.jsp").forward(request,response);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		System.out.println("posthandler拦截器方法执行……");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
		System.out.println("afterCompletion拦截器方法执行……");
	}
}
