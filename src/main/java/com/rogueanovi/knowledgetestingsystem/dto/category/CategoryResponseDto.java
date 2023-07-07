package com.rogueanovi.knowledgetestingsystem.dto.category;

import com.rogueanovi.knowledgetestingsystem.model.Category;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CategoryResponseDto {
    private Long categoryId;
    private String title;
    private String description;

    public static CategoryResponseDto convertToDto(Category category){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(category, CategoryResponseDto.class);
    }
}
