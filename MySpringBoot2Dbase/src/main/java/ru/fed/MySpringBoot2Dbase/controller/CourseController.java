package ru.fed.MySpringBoot2Dbase.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.fed.MySpringBoot2Dbase.entity.Course;
import ru.fed.MySpringBoot2Dbase.service.CourseService;
import java.util.List;
@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @GetMapping("/courses")
    public List<Course> showAllCourses() {
        List<Course> allCourses = courseService.getAllCourses();
        return allCourses;
    }
    @GetMapping("/courses/{id}")
    public Course getCourse(@PathVariable("id") int id) {
        return courseService.getCourse(id);
    }
    @PostMapping("/course")
    public Course saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }
    @PutMapping("/courses")
    public Course updateCourse(@RequestBody Course course) {
        courseService.saveCourse(course);
        return course;
    }
    @DeleteMapping("/courses/{id}")
    public void updateCourse(@PathVariable("id") int id) {
        courseService.deleteCourse(id);
    }
}
