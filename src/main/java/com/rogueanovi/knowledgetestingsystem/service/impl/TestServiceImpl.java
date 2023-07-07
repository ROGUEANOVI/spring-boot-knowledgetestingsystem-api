package com.rogueanovi.knowledgetestingsystem.service.impl;

import com.rogueanovi.knowledgetestingsystem.model.Category;
import com.rogueanovi.knowledgetestingsystem.model.Test;
import com.rogueanovi.knowledgetestingsystem.repository.TestRepository;
import com.rogueanovi.knowledgetestingsystem.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

    @Override
    public Set<Test> getTestsList() {
        return new LinkedHashSet<>(testRepository.findAll()) ;
    }

    @Override
    public List<Test> getActiveTestsList() {
        return testRepository.findByState(true);
    }

    @Override
    public List<Test> getTestsListByCategory(Category category) {
        return testRepository.findByCategory(category);
    }

    @Override
    public List<Test> getTestsListByActiveCategory(Category category) {
        return testRepository.findByCategoryAndState(category, true);
    }



    public Test getTestById(Long testId) {
        return testRepository.findById(testId).orElseThrow();
    }

    @Override
    public Test AddTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public Test updateTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public void deleteTest(Long testId) {
        testRepository.deleteById(testId);
    }
}
