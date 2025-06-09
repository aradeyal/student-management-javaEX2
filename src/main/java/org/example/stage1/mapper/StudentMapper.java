package org.example.stage1.mapper;

import org.example.stage1.dto.StudentDto;
import org.example.stage1.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student toEntity(StudentDto dto) {
        return new Student(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getAge(), dto.getEmail());
    }

    public StudentDto toDto(Student entity) {
        return new StudentDto(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getEmail());
    }

    public void updateEntityFromDto(Student entity, StudentDto dto) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
    }
}
