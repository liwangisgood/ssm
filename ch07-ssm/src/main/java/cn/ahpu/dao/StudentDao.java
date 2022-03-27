package cn.ahpu.dao;

import cn.ahpu.entity.Student;

import java.util.List;

/**
 * @author:liwang
 * @date: 2022/3/27 3:19
 * @Description:TODO
 * @Algorithm Design：TODO
 */
public interface StudentDao {

	int insertStudent(Student student);
	List<Student> selectStudent();
}
