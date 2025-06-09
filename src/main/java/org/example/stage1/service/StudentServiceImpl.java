package org.example.stage1.service;

import org.example.stage1.dto.StudentDto;
import org.example.stage1.entity.Student;
import org.example.stage1.exception.*;
import org.example.stage1.mapper.StudentMapper;
import org.example.stage1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotExists("Student with id " + id + " does not exist"));
        return studentMapper.toDto(student);
    }

    @Override
    @Transactional
    public StudentDto addStudent(StudentDto studentDto) {
        studentRepository.findByEmail(studentDto.getEmail()).ifPresent(s -> {
            throw new AlreadyExists("Student with email " + studentDto.getEmail() + " already exists");
        });
        Student student = studentMapper.toEntity(studentDto);
        return studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    @Transactional
    public StudentDto updateStudent(StudentDto studentDto, Long id) {
        if (studentDto.getId() != null && !studentDto.getId().equals(id)) {
            throw new StudentIdAndIdMismatch("Path ID does not match body ID");
        }

        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new NotExists("Student with id " + id + " does not exist"));

        studentRepository.findByEmail(studentDto.getEmail()).ifPresent(other -> {
            if (!other.getId().equals(id)) {
                throw new AlreadyExists("Email already in use by another student");
            }
        });

        studentMapper.updateEntityFromDto(existing, studentDto);
        return studentMapper.toDto(studentRepository.save(existing));
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new NotExists("Student with id " + id + " does not exist");
        }
        studentRepository.deleteById(id);
    }
}
