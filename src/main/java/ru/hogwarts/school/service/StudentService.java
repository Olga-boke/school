package ru.hogwarts.school.service;


import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student addStudent(Student student) {
        logger.debug("Calling method addStudent");
        return studentRepository.save(student);
    }
    public Student findStudent(long id) {
        logger.debug("Calling method findStudent(studentId={})",id);
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.debug("Calling method editStudent");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.debug("Calling method deleteStudent (studentId={})",id);
        studentRepository.deleteById(id);
    }
    public Collection<Student> findByAge(int age){
        logger.debug("Calling method findByAge (age = {})", age);

        return studentRepository.findByAge(age);
    }
    public Collection<Student> findStudentByAgeBetween(int age, int age2){
        logger.debug("Calling method findStudentByAgeBetween (age = {}, age2 = {})", age, age2);
        return studentRepository.findStudentByAgeBetween(age,age2);
    }
    public Faculty getFacultyByStudent(long id) {
        logger.debug("Calling method getFacultyByStudent (studentId = {})", id);
        return findStudent(id).getFaculty();
        
    }
    public Long getAmountOfAllStudents(){
        logger.debug("Calling method getAmountOfAllStudents");
        return studentRepository.getAmountOfAllStudents();

    }
    public Double getAverageAgeOfAllStudents(){
        logger.debug("Calling method getAverageAgeOfAllStudents");
        return studentRepository.getAverageAgeOfAllStudents();
    }

    public Collection<Student> getLastFiveStudents(){
        logger.debug("Calling method getLastFiveStudents");
        return  studentRepository.getLastFiveStudents();
    }
}
