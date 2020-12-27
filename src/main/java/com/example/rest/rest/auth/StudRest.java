package com.example.rest.rest.auth;


import com.example.rest.dto.auth.request.AuthRequestDto;
import com.example.rest.dto.auth.request.StudRequestDto;
import com.example.rest.entity.Student;
import com.example.rest.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/")
public class StudRest {

    private final StudentService studentService;

    StudRest(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudent() {
        return studentService.readAll();
    }

    @PostMapping("/students")
    public ResponseEntity<?> newStudent(
            @RequestBody StudRequestDto studRequestDto
    ) {
        return studentService.create(studRequestDto);
    }
}
