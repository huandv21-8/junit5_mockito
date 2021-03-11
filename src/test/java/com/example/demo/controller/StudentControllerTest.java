package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StudentController.class)
class StudentControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Test
    void getAllStudent() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(new Student((long) 3, "nguyen cong phuong", 12, "phuong@gmail.com"));
        students.add(new Student((long) 4, "nguyen cong phuong", 12, "phuong@gmail.com"));
        Mockito.when(studentService.getStudent()).thenReturn(students);

        String url = "/api/getStudent";

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url);
        MvcResult mvcResult = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println("abc " + actualJsonResponse);

        String expectedJsonResponse = new ObjectMapper().writeValueAsString(students);
        System.out.println("xyz " + expectedJsonResponse);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

    }


    @Test
    void addStudent() throws Exception {
        Student newStd = new Student(null, "nguyen cong phuong", 12, "phuong@gmail.com");
        Student stdResponse = new Student((long) 1, "nguyen cong phuong", 12, "phuong@gmail.com");
        Mockito.when(studentService.save(newStd)).thenReturn(stdResponse);
        String url = "/api/addStudent";

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url);

        mockMvc.perform(builder
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(newStd))
                .with(csrf())
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(stdResponse)));
    }

    @Test
    void updateStudentById() throws Exception {
        Student existStd = new Student((long) 1, "van toan", 12, "toan@gmail.com");
        Student stdResponse = new Student((long) 1, "van toan", 12, "toan@gmail.com");
        Mockito.when(studentService.update(existStd)).thenReturn(stdResponse);
        String url = "/api/updateStudent";

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url);
        mockMvc.perform(builder
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(existStd))
                .with(csrf())
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(stdResponse)));
    }


    @Test
    void deleteStudent() throws Exception {
        long studentId = 1;
        Mockito.doNothing().when(studentService).delete(studentId);
        String url = "/api/deleteStudentById/" + studentId;

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url);
        mockMvc.perform(builder).andExpect(status().isOk());
        Mockito.verify(studentService, Mockito.times(1)).delete(studentId);
    }
}