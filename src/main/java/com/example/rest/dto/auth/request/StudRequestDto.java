package com.example.rest.dto.auth.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudRequestDto {
    private String name;
    private String subject;
    private String topic;
    private String type;
    private int point;

    @Override
    public String toString() {
        return "StudRequestDto{" +
                "name='" + name + '\'' +
                "subject='" + subject + '\'' +
                "topic='" + topic + '\'' +
                "type='" + type + '\'' +
                "point='" + point + '\'' +
                +'}';
    }
}
