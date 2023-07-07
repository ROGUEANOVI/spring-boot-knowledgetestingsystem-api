package com.rogueanovi.knowledgetestingsystem.dto.category;

import com.rogueanovi.knowledgetestingsystem.model.Category;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CategoryUpdateRequestDto {
    private Long categoryId;
    private String title;
    private String description;

    public Category convertToEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Category.class);
    }
}
