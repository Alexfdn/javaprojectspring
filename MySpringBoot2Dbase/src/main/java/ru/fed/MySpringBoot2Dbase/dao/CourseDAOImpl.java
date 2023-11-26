package ru.fed.MySpringBoot2Dbase.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import ru.fed.MySpringBoot2Dbase.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Slf4j
@Repository
public class CourseDAOImpl implements CourseDAO{
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Course> getAllCourses() {
        Query query = entityManager.createQuery("from Course");
        List<Course> allCourses = query.getResultList();
        log.info("getAllCourses" + allCourses);
        return allCourses;
    }
    @Override
    public Course saveCourse(Course course) {
        return entityManager.merge((course));
    }

    @Override
    public Course getCourse(int id) {
        return entityManager.find(Course.class, id);
    }
    @Override
    public void deleteCourse(int id) {
        Query query = entityManager.createQuery("delete from Course "
                + " where id =:CourseId");
        query.setParameter("CourseId", id);
        query.executeUpdate();
    }
}
