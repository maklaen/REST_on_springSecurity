package com.example.rest.mapper;

import com.example.rest.dto.auth.request.RegisterRequestDto;
import com.example.rest.dto.auth.request.StudRequestDto;
import com.example.rest.dto.user.response.ProfileResponseDto;
import com.example.rest.entity.Student;
import com.example.rest.entity.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StudentMapper {

    public StudentMapper() {
    }

    public ProfileResponseDto convertToProfileResponse(User user) {
        return new ProfileResponseDto(
                user.getUsername()
        );
    }

    public Student convertToStudentFromRegisterRequest(StudRequestDto studRequestDto) {
        return new Student(
                UUID.randomUUID().toString(),
                studRequestDto.getName(),
                studRequestDto.getSubject(),
                studRequestDto.getTopic(),
                studRequestDto.getType(),
                studRequestDto.getPoint()
        );
    }
}
