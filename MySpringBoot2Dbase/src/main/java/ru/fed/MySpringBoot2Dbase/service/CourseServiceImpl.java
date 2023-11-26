package ru.fed.MySpringBoot2Dbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fed.MySpringBoot2Dbase.dao.CourseDAO;
import ru.fed.MySpringBoot2Dbase.entity.Course;
import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDAO courseDAO;
    @Override
    @Transactional
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }
    @Override
    @Transactional
    public Course getCourse(int id) {
        return courseDAO.getCourse(id);
    }
    @Override
    @Transactional
    public Course saveCourse(Course course) {
        return courseDAO.saveCourse(course);
    }
    @Override
    @Transactional
    public void deleteCourse(int id) {
        courseDAO.deleteCourse(id);
    }
}

