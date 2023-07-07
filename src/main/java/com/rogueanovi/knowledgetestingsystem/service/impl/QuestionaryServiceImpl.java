package com.rogueanovi.knowledgetestingsystem.service.impl;

import com.rogueanovi.knowledgetestingsystem.model.Questionary;
import com.rogueanovi.knowledgetestingsystem.model.Test;
import com.rogueanovi.knowledgetestingsystem.repository.QuestionaryRepository;
import com.rogueanovi.knowledgetestingsystem.service.QuestionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class QuestionaryServiceImpl implements QuestionaryService {

    @Autowired
    private QuestionaryRepository questionaryRepository;
    @Override
    public Set<Questionary> getQuestionnairesList() {
        return new LinkedHashSet<>(questionaryRepository.findAll());
    }

    @Override
    public Questionary getQuestionaryById(Long questionaryId) {
        return questionaryRepository.findById(questionaryId).orElseThrow();
    }

    @Override
    public Questionary getOneCuestionary(Long questionaryId) {
        return questionaryRepository.getOne(questionaryId);
    }

    @Override
    public Set<Questionary> getTestQuestionnaires(Test test) {
        return new LinkedHashSet<>(questionaryRepository.findByTest(test));
    }

    @Override
    public Questionary AddQuestionary(Questionary questionary) {
        return questionaryRepository.save(questionary);
    }

    @Override
    public Questionary updateQuestionary(Questionary questionary) {
        return questionaryRepository.save(questionary);
    }

    @Override
    public void deleteQuestionary(Long questionaryId) {
        questionaryRepository.deleteById(questionaryId);
    }
}
