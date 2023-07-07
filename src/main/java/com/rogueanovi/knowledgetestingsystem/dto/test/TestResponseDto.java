package com.rogueanovi.knowledgetestingsystem.dto.test;

import com.rogueanovi.knowledgetestingsystem.model.Category;
import com.rogueanovi.knowledgetestingsystem.model.Test;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class TestResponseDto {
    private Long testId;
    private String title;
    private String description;
    private Long maximumPoints;
    private Integer numberQuestionnaires;
    private Boolean state;
    private Category category;

    public static TestResponseDto convertToDto(Test test){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(test, TestResponseDto.class);
    }
}
