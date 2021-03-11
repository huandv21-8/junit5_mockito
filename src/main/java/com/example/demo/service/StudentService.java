package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getStudent() {
//       boolean a = studentRepository.getAll();
//        System.out.println(a);
        return studentRepository.findAll();
    }

    public Student save(Student student) {
       Student std = studentRepository.save(student);
      return std;
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public Student update(Student student) {
        Student std = studentRepository.findByStudentId(student.getStudentId());
        std.setAge(student.getAge());
        std.setFullName(student.getFullName());
        std.setEmail(student.getEmail());
        studentRepository.save(std);
        return std;
    }
}
