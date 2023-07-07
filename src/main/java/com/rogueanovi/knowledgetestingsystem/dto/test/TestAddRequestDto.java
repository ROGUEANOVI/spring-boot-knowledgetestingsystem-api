package com.rogueanovi.knowledgetestingsystem.dto.test;

import com.rogueanovi.knowledgetestingsystem.model.Category;
import com.rogueanovi.knowledgetestingsystem.model.Test;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class TestAddRequestDto {
    private String title;
    private String description;
    private Long maximumPoints;
    private Integer numberQuestionnaires;
    private Boolean state;
    private Category category;

    public Test convertToEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Test.class);
    }
}
