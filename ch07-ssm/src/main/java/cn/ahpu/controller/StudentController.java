package cn.ahpu.controller;

import cn.ahpu.entity.Student;
import cn.ahpu.exception.AgeException;
import cn.ahpu.exception.MyUserException;
import cn.ahpu.exception.NameException;
import cn.ahpu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:liwang
 * @date: 2022/3/27 3:34
 * @Description:TODO
 * @Algorithm Design：TODO
 */
@Controller
@RequestMapping("/student")
public class StudentController {

	@Resource
	private StudentService studentService;

	@RequestMapping("/addStudent.do")
	public ModelAndView addStudent(Student student){
		int result=studentService.addStudent(student);
		if (result>0){
			System.out.println("注册成功");
		}else {
			System.out.println("注册失败");
		}
		//ModelAndView mv = new ModelAndView("result");
		ModelAndView mv = new ModelAndView();
		/*可以转发到视图解析器以外的路径资源 forward关键字相当于屏蔽视图解析器（有没有无所谓） 寻找自己的路径在target/ch07-ssm-1.0.0*/
		mv.setViewName("forward:/result.jsp");//
		mv.addObject("result",result);
		return mv;
	}

	/*不加ResponseBody会自动与视图解析器一起转发请求组合 前缀与后缀*/
	@RequestMapping("/queryStudent.do")
	@ResponseBody  //springmvc中返回值为Object对象类型的 加该注解返回该对象数据 不加返回逻辑视图页面可能404
	public List<Student> queryStudent(){
		List<Student> students=studentService.findStudent();
		return students;
	}

	@RequestMapping("/some.do")//把异常交给框架
	public ModelAndView doSome(String name,Integer age) throws MyUserException {
		System.out.println("dosome方法开始执行……");

		//在控制层判断登录的用户信息是否满足条件，可以在拦截器使用来替换
		ModelAndView mv = new ModelAndView();
		if(!"zs".equals(name)){
			throw new NameException("姓名不正确！！！");
		}

		if(age==null||age>80){
			throw new AgeException("没有获取年龄值，活着比较大");
		}
		mv = new ModelAndView("result");
		mv.addObject("myname",name);
		mv.addObject("myage",age);
		return mv;
	}



}
