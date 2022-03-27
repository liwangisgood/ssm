package cn.ahpu.controller;

import cn.ahpu.entity.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author:liwang
 * @date: 2022/3/24 2:54
 * @Description:TODO
 * @Algorithm Design：TODO
 */
@Controller
//value默认为所有请求的公共前缀
/*@RequestMapping("/test")*//*搞不起*/
public class MyController {

	/*1.不同地址可以访问同一个方法
	* 2.没有method属性，两种访问方GET POST都可以  405请求方式不支持*/
	@RequestMapping( value = "/some.do",method = RequestMethod.GET)/*加/ 代表项目的根:localhost:8080/myWeb*/
	public ModelAndView doSome(HttpServletRequest request, HttpServletResponse response,String name,Integer age){

		System.out.println("doSome()方法开始执行……");
		ModelAndView mv = new ModelAndView("show");
		//response.setContentType("text/html;charset=utf-8");
		mv.addObject("msg","欢迎用springmvc");//框架
		mv.addObject("fun","执行的是doSome()");

		//mv.setViewName("show");/*该视图如果可以解析 那么鼠标点击可以直接跳转*/
		return mv;

		//		String name = request.getParameter("name");
//		if (name==null){
//			System.out.println("name==null");//name==null 当没有name这个属性
//		}

	}


	@RequestMapping( value = "/some01.do",method = RequestMethod.POST)/*加/ 代表项目的根:localhost:8080/myWeb*/
	                           //名字对应和位置无关
	public ModelAndView doSome01(String name,Integer age){//其中Integer age对应的值 Integer.valueOf(age),不能为其他 不然会抛出400的异常
		//POST请求方式乱码 原本用request.setCharacterEncoding("utf-8") 可解决 现在用过滤器

		ModelAndView mv = new ModelAndView("show");//mv.setViewName("show") 和构造器中加入效果一样

		mv.addObject("name",name);//在.jsp文件，可以通过 ${name}直接获取name对应的数据
		mv.addObject("age",age);
		return mv;
	}


	@RequestMapping( value = "/some02.do",method = RequestMethod.POST)/*加/ 代表项目的根:localhost:8080/myWeb*/
	                            //名字对应和请求路径位置无关 解决请求地址参数名与方法变量名不一致的问题
								//@RequestParam默认必须有参数 没有400错误
	public ModelAndView doSome02(@RequestParam(value = "name",required = false) String name, @RequestParam Integer age){//其中Integer age对应的值 Integer.valueOf(age),不能为其他 不然会抛出400的异常
		//POST请求方式乱码 原本用request.setCharacterEncoding("utf-8") 可解决 现在用过滤器

		ModelAndView mv = new ModelAndView("show");//mv.setViewName("show") 和构造器中加入效果一样

		mv.addObject("name",name);//在.jsp文件，可以通过 ${name}直接获取name对应的数据
		mv.addObject("age",age);
		return mv;
	}


	@RequestMapping( value = "/some03.do" )/*加/ 代表项目的根:localhost:8080/myWeb*/
	/*请求方式POST，使用浏览器默认GET请求方式不支持，会报45异常*/
	public ModelAndView doSome03(Student student){
		//其中Integer age对应的值 Integer.valueOf(age),不能为其他 不然会抛出400的异常
		//POST请求方式乱码 原本用request.setCharacterEncoding("utf-8") 可解决 现在用过滤器

		ModelAndView mv = new ModelAndView("show");//mv.setViewName("show") 和构造器中加入效果一样

		mv.addObject("name",student.getName());//在.jsp文件，可以通过 ${name}直接获取name对应的数据
		mv.addObject("age",student.getAge());
		mv.addObject("student",student);
		return mv;
	}

	@RequestMapping("/returnView.do")
	public String returnView(HttpServletRequest request,String name,Integer age){
		System.out.println("name:"+name+" "+"age:"+age);
		/*重新开启服务器才能够访问到视图show.jsp页面访问到相应的name age数据，之前似乎有缓存，更新代码实现热部署
		* 不能到达最新访问状态*/

/*		String为方法返回值 该种方法不行
		ModelAndView mv = new ModelAndView("show");//加不加”view视图名称参数都无法无法进行访问“，没有返回视图对象
		mv.addObject("name",name);
		mv.addObject("age",age);*/


//		request实现数据传到.jsp页面，${},转发(客户端数据)
		request.setAttribute("name",name);
		request.setAttribute("age",age);

/*		request获取session可以将数据传达到.jsp也买你${}
		HttpSession session = request.getSession();
		session.setAttribute("name",name);
		session.setAttribute("age",age); 理论是转发(客户端数据) 重定向都可以(服务器数据session)*/

		//用完整路径如/WEB-INF/view/show.jsp直接转发访问到该资源 不能有视图解析器
		return "show";//逻辑视图名称，配置了视图解析器 该返回字符串会自动加上前缀与后缀
	}


	@RequestMapping("/returnView01.do")
	public void returnView01(HttpServletResponse response,String name,Integer age){
		System.out.println("name:"+name+" "+"age:"+age);

//		request实现数据传到.jsp页面，${},转发(客户端数据)
//		request.setAttribute("name",name);
//		request.setAttribute("age",age);
		Student student = new Student();
		student.setName(name);
		student.setAge(age);
		String json="";
		if (student!=null){
			ObjectMapper om = new ObjectMapper();
			try {
				json = om.writeValueAsString(student);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		//application/json  text/html

		response.setContentType("application/json");//已经有过滤器对响应体，和post请求方式请求体编码设置
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.print(json);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (pw != null) {
				pw.close();
			}
		}

	}

}
