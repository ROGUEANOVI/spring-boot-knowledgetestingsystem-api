package com.rogueanovi.knowledgetestingsystem.controller;

import com.rogueanovi.knowledgetestingsystem.dto.test.TestAddRequestDto;
import com.rogueanovi.knowledgetestingsystem.dto.test.TestUpdateRequestDto;
import com.rogueanovi.knowledgetestingsystem.model.Category;
import com.rogueanovi.knowledgetestingsystem.model.Test;
import com.rogueanovi.knowledgetestingsystem.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/")
    public ResponseEntity<Set<Test>> getTestsList(){
        Set<Test> testsList = testService.getTestsList();
        return new ResponseEntity<>(testsList, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Test>> getActiveTestsList(){
        List<Test> tests = testService.getActiveTestsList();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Test>> getTestsListByCategory(@PathVariable("categoryId") Long categoryId){
        Category category = new Category();
        category.setCategoryId(categoryId);
        try {
            List<Test> tests = testService.getTestsListByCategory(category);
            return new ResponseEntity<>(tests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/active/{categoryId}")
    public ResponseEntity<List<Test>> getTestsListByActiveCategory(@PathVariable("categoryId") Long categoryId){
        Category category = new Category();
        category.setCategoryId(categoryId);
        List<Test> tests = testService.getTestsListByActiveCategory(category);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }


    @GetMapping("/{testId}")
    public ResponseEntity<Test> getTestById(@PathVariable("testId") Long testId){
        try {
            Test test = testService.getTestById(testId);
            return new ResponseEntity<>(test, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Test> addTest(@RequestBody TestAddRequestDto testAddRequestDto){
        Test testEntity = testAddRequestDto.convertToEntity();
        Test testSaved = testService.AddTest(testEntity);
        return new ResponseEntity<>(testSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{testId}")
    public ResponseEntity<Test> updateTest(@PathVariable("testId") Long testId, @RequestBody TestUpdateRequestDto testUpdateRequestDto){
        Test testEntity = testUpdateRequestDto.convertToEntity();
        try {
            Test testFound = testService.getTestById(testId);
            testFound.setTitle(testEntity.getTitle());
            testFound.setDescription(testEntity.getDescription());
            testFound.setMaximumPoints(testEntity.getMaximumPoints());
            testFound.setNumberQuestionnaires(testEntity.getNumberQuestionnaires());
            testFound.setState(testEntity.getState());
            testFound.setCategory(testEntity.getCategory());

            Test testUpdated = testService.updateTest(testFound);
            return  new ResponseEntity<>(testUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{testId}")
    public ResponseEntity<Test> deleteTest(@PathVariable("testId") Long testId){
        try {
            Test testFound= testService.getTestById(testId);
            testService.deleteTest(testId);
            return new ResponseEntity<>(testFound, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
