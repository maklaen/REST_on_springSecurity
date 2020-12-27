package com.example.rest.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "students")
public class Student {

    public Student () {}


    public Student (String id, String name, String subject, String topic, String type, int point ) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.topic = topic;
        this.type = type;
        this.point = point;
    }

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "subject")
    private String subject;

    @Column(name = "topic")
    private String topic;

    @Column(name = "type")
    private String type;

    @Column(name = "point")
    private int point;
}
