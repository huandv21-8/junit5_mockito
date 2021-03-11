package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
//import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStudent() {
    }

    @Test
    void save() {

//        Student std = new Student((long) 3,"nguyen cong phuong",12,"phuong@gmail.com");
//        Mockito.when(studentRepository.save(std))
//                .thenReturn(std);
//
//        // Use mock in test
//        Assert.assertEquals("FAILED", studentService.save(std));
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}