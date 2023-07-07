package com.rogueanovi.knowledgetestingsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "questionary")
public class Questionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionaryId;
    @Column(length = 5000)
    private String ask;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    @Transient
    private String selectedAnswer;
    private String answer;
    private String image;
    @ManyToOne(fetch = FetchType.EAGER)
    private Test test;

}
