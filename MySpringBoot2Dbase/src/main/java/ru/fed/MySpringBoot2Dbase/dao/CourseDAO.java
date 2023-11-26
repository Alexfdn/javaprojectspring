package ru.fed.MySpringBoot2Dbase.dao;
import org.springframework.stereotype.Repository;
import ru.fed.MySpringBoot2Dbase.entity.Course;
import java.util.List;
public interface CourseDAO {
    List<Course> getAllCourses();
    Course saveCourse(Course course);
    Course getCourse(int id);
    void deleteCourse(int id);

}

