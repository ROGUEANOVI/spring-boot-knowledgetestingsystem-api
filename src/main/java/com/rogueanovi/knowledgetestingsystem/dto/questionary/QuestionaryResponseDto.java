package com.rogueanovi.knowledgetestingsystem.dto.questionary;

import com.rogueanovi.knowledgetestingsystem.model.Questionary;
import com.rogueanovi.knowledgetestingsystem.model.Test;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class QuestionaryResponseDto {
    private Long questionId;
    private String ask;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private String image;
    private Test test;

    public static QuestionaryResponseDto convertToDto(Questionary question){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(question, QuestionaryResponseDto.class);
    }
}
