package cn.ahpu.service;

import cn.ahpu.entity.Student;

import java.util.List;

/**
 * @author:liwang
 * @date: 2022/3/27 3:28
 * @Description:TODO
 * @Algorithm Design：TODO
 */
public interface StudentService {

	int addStudent(Student student);
	List<Student> findStudent();

}
