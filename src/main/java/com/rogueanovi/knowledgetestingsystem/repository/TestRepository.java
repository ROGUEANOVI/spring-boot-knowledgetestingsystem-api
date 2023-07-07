package com.rogueanovi.knowledgetestingsystem.repository;

import com.rogueanovi.knowledgetestingsystem.model.Category;
import com.rogueanovi.knowledgetestingsystem.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByCategory(Category category);
    List<Test> findByState(Boolean state);
    List<Test> findByCategoryAndState(Category category, Boolean state);
}
