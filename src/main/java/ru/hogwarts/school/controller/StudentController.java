package ru.hogwarts.school.controller;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

@RestController
@RequestMapping("/student")

public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo (@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if ( student == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/amount-all-student")
    public Long getAmountOfAllStudents(){
        return studentService.getAmountOfAllStudents();
    }

    @GetMapping("/average-age-of-all-students")
    public double getAverageAgeOfAllStudents(){
        return studentService.getAverageAgeOfAllStudents();
    }
    @GetMapping("/get-last-five-students()")
    public Collection<Student> getLastFiveStudents(){
        return studentService.getLastFiveStudents();
    }

    @PostMapping
    public Student createStudent (@RequestBody Student student) {
        return studentService.addStudent(student);
    }
    @PutMapping
    public ResponseEntity<Student> editStudent (@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if ( foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteStudent (@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents (@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
    @GetMapping(params = {"age","age2"})
    public ResponseEntity<Collection<Student>> findStudentByAgeBetween (@RequestParam int age,
                                                                        @RequestParam int age2) {
        if (age > 0 &&  age2 > 0) {
            return ResponseEntity.ok(studentService.findStudentByAgeBetween(age,age2));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
    @GetMapping("/{id}/faculty")
    public Faculty getFacultyByStudent(@PathVariable long id){
        return studentService.getFacultyByStudent(id);}


}
