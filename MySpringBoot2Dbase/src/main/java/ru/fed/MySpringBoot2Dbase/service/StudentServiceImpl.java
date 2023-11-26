package ru.fed.MySpringBoot2Dbase.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fed.MySpringBoot2Dbase.dao.StudentDAO;
import ru.fed.MySpringBoot2Dbase.entity.Student;
import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentDAO studentDAO;
    @Override
    @Transactional
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
    @Override
    @Transactional
    public Student getStudent(int id) {
        return studentDAO.getStudent(id);
    }
    @Override
    @Transactional
    public Student saveStudent(Student student) {
        return studentDAO.saveStudent(student);
    }
    @Override
    @Transactional
    public void deleteStudent(int id) {
        studentDAO.deleteStudent(id);
    }

}
