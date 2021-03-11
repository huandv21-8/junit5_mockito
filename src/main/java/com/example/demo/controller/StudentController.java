package com.example.demo.controller;

import com.example.demo.exceptions.Exception;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student student) {
       String mess = String.valueOf(studentService.save(student));
      return mess;
    }

    @PostMapping("/updateStudent")
    public String updateStudentById(@RequestBody Student student) {
        return String.valueOf(studentService.update(student));
    }

    @GetMapping("/getStudent")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> students = studentService.getStudent();
        if (students.isEmpty()) {
            throw new Exception("No student records were found");
        }
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    @GetMapping("/deleteStudentById/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity<>("Delete Successful", OK);
    }
}
