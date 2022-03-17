package com.example.demoproject.student.service;

import com.example.demoproject.student.repository.StudentRepository;
import com.example.demoproject.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        boolean existsEmail = studentRepository.selectExistsEmail(student.getEmail());
        if (existsEmail) {
            throw new IllegalStateException("Email " + student.getEmail() + "taken");
        }
        studentRepository.save(student);
    }


}
