package com.rogueanovi.knowledgetestingsystem.service;

import com.rogueanovi.knowledgetestingsystem.model.Category;
import com.rogueanovi.knowledgetestingsystem.model.Test;

import java.util.List;
import java.util.Set;


public interface TestService {
    Set<Test> getTestsList();
    List<Test> getActiveTestsList();
    List<Test> getTestsListByCategory(Category category);
    List<Test> getTestsListByActiveCategory(Category category);
    Test getTestById(Long testId);
    Test AddTest(Test test);
    Test updateTest(Test test);
    void deleteTest(Long testId);

}
