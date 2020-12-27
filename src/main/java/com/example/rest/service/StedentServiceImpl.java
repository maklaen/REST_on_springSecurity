package com.example.rest.service;

import com.example.rest.dto.auth.request.StudRequestDto;
import com.example.rest.entity.Student;
import com.example.rest.mapper.StudentMapper;
import com.example.rest.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StedentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    public StedentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public ResponseEntity<?> create(StudRequestDto studRequestDto) {
        Student student = studentMapper.convertToStudentFromRegisterRequest(studRequestDto);
        studentRepository.save(student);
        return null;
    }

    @Override
    public List<Student> readAll() {
        List<Student> list = studentRepository.findAll();
        return list;
    }

    @Override
    public Optional<Student> read(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public boolean update(Student student, int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Student> readSubject(String subject) {
        return null;
    }

    @Override
    public List<Student> readTopic(List<Student> list, String topic) {
        return null;
    }
}
