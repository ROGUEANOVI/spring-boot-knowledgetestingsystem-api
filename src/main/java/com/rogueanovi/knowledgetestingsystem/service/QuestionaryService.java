package com.rogueanovi.knowledgetestingsystem.service;

import com.rogueanovi.knowledgetestingsystem.model.Questionary;
import com.rogueanovi.knowledgetestingsystem.model.Test;

import java.util.Set;

public interface QuestionaryService {
    Set<Questionary> getQuestionnairesList();
    Questionary getQuestionaryById(Long questionaryId);
    Questionary getOneCuestionary(Long questionaryId);
    Set<Questionary> getTestQuestionnaires(Test test);
    Questionary AddQuestionary(Questionary questionary);
    Questionary updateQuestionary(Questionary questionary);
    void deleteQuestionary(Long questionaryId);
}
