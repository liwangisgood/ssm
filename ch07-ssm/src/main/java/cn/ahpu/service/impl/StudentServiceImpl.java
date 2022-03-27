package cn.ahpu.service.impl;

import cn.ahpu.dao.StudentDao;
import cn.ahpu.entity.Student;
import cn.ahpu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:liwang
 * @date: 2022/3/27 3:29
 * @Description:TODO
 * @Algorithm Design：TODO
 */
@Service
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentDao studentDao;

	@Override
	public int addStudent(Student student) {
		return studentDao.insertStudent(student);
	}

	@Override
	public List<Student> findStudent() {

		return studentDao.selectStudent();
	}
}
