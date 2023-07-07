package com.rogueanovi.knowledgetestingsystem.repository;

import com.rogueanovi.knowledgetestingsystem.model.Questionary;
import com.rogueanovi.knowledgetestingsystem.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface QuestionaryRepository extends JpaRepository<Questionary, Long> {
    Set<Questionary> findByTest(Test test);
}
