package ru.fed.MyUiRestDbLr71.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fed.MyUiRestDbLr71.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
