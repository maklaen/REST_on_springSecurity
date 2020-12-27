package com.example.rest.service;

import com.example.rest.dto.auth.request.StudRequestDto;
import com.example.rest.entity.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    ResponseEntity<?> create(StudRequestDto studRequestDto);

    List<Student> readAll();

    Optional<Student> read(int id);


    boolean update(Student student, int id);

    boolean delete(int id);


    List<Student> readSubject(String subject);


    List<Student> readTopic(List<Student> list,String topic);
}
