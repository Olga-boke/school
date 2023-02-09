package ru.hogwarts.school.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import static java.util.Collections.singletonList;

@Service
public class StudentService {

    private static StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public int count = 0;

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
    public static List<String> getNameStarWithA(String letter) {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(Student::getName)
                .filter(s -> s.startsWith(letter))
                .sorted(String::compareTo)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

    }
    public static Double getAverageAgeByStreamApi() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .mapToDouble(student -> student.getAge())
                .average()
                .getAsDouble();
    }

    public void getThreadStudentNames() {
        List<String> studentsNames = studentRepository.findAll().stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println(studentsNames.get(0));
        System.out.println(studentsNames.get(1));


        new Thread(() -> {
            System.out.println(studentsNames.get(2));
            System.out.println(studentsNames.get(3));
        }).start();
        new Thread(() -> {
            System.out.println(studentsNames.get(4));
            System.out.println(studentsNames.get(5));
        }).start();
    }

        public void threadAllStudentNamesSync() {
            List<String> studentsNames = studentRepository.findAll().stream()
                    .map(Student::getName)
                    .collect(Collectors.toList());

            printToConsoleSync(studentsNames);
            printToConsoleSync(studentsNames);


            new Thread(() -> {
                printToConsoleSync(studentsNames);
                printToConsoleSync(studentsNames);
            }).start();

            new Thread(() -> {
                printToConsoleSync(studentsNames);
                printToConsoleSync(studentsNames);
            }).start();
        }

        public synchronized void printToConsoleSync(List<String> studentsNames) {
            System.out.println(studentsNames.get(count));
            count++;
        }
}

