package com.rogueanovi.knowledgetestingsystem.dto.category;

import com.rogueanovi.knowledgetestingsystem.model.Category;
import org.modelmapper.ModelMapper;
import lombok.Data;

@Data
public class CategoryAddRequestDto {
    private String title;
    private String description;

    public Category convertToEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Category.class);
    }
}
