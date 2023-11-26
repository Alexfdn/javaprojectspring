package ru.fed.MySpringBoot2Dbase.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.fed.MySpringBoot2Dbase.service.StudentService;
import ru.fed.MySpringBoot2Dbase.entity.Student;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import java.util.List;
@RestController
@RequestMapping("/api")

public class MyController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity allStudents() {
        try {
            List<Student> allStudents = studentService.getAllStudents();
            return new ResponseEntity(allStudents, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity getStudent(@PathVariable("id") int id) {
        try {
            return new ResponseEntity(studentService.getStudent(id), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @PostMapping("/students")
    public ResponseEntity saveStudent(@RequestBody Student student) {
        try {
            return new ResponseEntity(studentService.saveStudent(student), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @PutMapping("/students")
    public ResponseEntity updateStudent(@RequestBody Student student) {
        try {
            studentService.saveStudent(student);
            return new ResponseEntity(student, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") int id) {
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity(HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

}

