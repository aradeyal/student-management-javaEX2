package org.example.stage1.service;

import org.example.stage1.entity.Student;
import org.example.stage1.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final StudentRepository studentRepository;

    public DataInitializer(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) {
        if (studentRepository.count() == 0) {
            studentRepository.save(new Student(null, "John", "Doe", 21.5, "john.doe@example.com"));
            studentRepository.save(new Student(null, "Jane", "Smith", 22.3, "jane.smith@example.com"));
            System.out.println("Data initialized.");
        }
    }
}