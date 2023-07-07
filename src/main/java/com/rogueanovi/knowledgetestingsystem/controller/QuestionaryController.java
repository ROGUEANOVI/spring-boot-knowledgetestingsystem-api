package com.rogueanovi.knowledgetestingsystem.controller;

import com.rogueanovi.knowledgetestingsystem.dto.questionary.QuestionaryAddRequestDto;
import com.rogueanovi.knowledgetestingsystem.dto.questionary.QuestionaryUpdateRequestDto;
import com.rogueanovi.knowledgetestingsystem.model.Questionary;
import com.rogueanovi.knowledgetestingsystem.model.Test;
import com.rogueanovi.knowledgetestingsystem.service.QuestionaryService;
import com.rogueanovi.knowledgetestingsystem.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/questionary")
public class QuestionaryController {
    @Autowired
    private QuestionaryService questionaryService;

    @Autowired
    private TestService testService;

    @GetMapping("/")
    public ResponseEntity<Set<Questionary>> getQuestionnairesList(){
        Set<Questionary> questionnairesList = questionaryService.getQuestionnairesList();
        return new ResponseEntity<>(questionnairesList, HttpStatus.OK);
    }

    @GetMapping("/{questionaryId}")
    public ResponseEntity<Questionary> getQuestionaryById(@PathVariable("questionaryId") Long questionaryId){
        try {
            Questionary questionary = questionaryService.getQuestionaryById(questionaryId);
            return new ResponseEntity<>(questionary, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/test/evaluate-test")
    public  ResponseEntity<?> evaluateQuestionnaires(@RequestBody List<Questionary> questionaries){
        double maximumPoints = 0;
        double correctAnswer = 0;
        Integer attempts = 0;

        for(Questionary questionary: questionaries){
            Questionary questionaryFound = questionaryService.getOneCuestionary(questionary.getQuestionaryId());
            if (questionaryFound.getAnswer().equals(questionary.getSelectedAnswer())){
                correctAnswer++;
                Long points = questionaries.get(0).getTest().getMaximumPoints()/questionaries.size();
                maximumPoints += points;
            }
            if (questionary.getSelectedAnswer() != null){
                attempts++;
            }
        }
        Map<String, Object> answers = new HashMap<>();
        answers.put("Maximum Points", maximumPoints);
        answers.put("Correct Answer", correctAnswer);
        answers.put("Attempts", attempts);
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @GetMapping("/test/{testId}")
    public ResponseEntity<?> getQuestionnairesByTest(@PathVariable("testId") Long testId){
        try {
            Test test = testService.getTestById(testId);
            Set<Questionary> questionnaires = test.getQuestionnaires();

            ArrayList tests = new ArrayList(questionnaires);
            if (tests.size() > test.getNumberQuestionnaires()) {
                tests = (ArrayList) tests.subList(0, test.getNumberQuestionnaires() + 1);
            }
            Collections.shuffle(tests);
            return new ResponseEntity<>(tests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Questionary> addQuestionary(@RequestBody QuestionaryAddRequestDto questionaryAddRequestDto){
        Questionary questionaryEntity = questionaryAddRequestDto.convertToEntity();
        Questionary questionarySaved = questionaryService.AddQuestionary(questionaryEntity);
        return new ResponseEntity<>(questionarySaved, HttpStatus.CREATED);
    }

    @PutMapping("/{questionaryId}")
    public ResponseEntity<Questionary> updateQuestionary(@PathVariable("questionaryId") Long questionaryId, @RequestBody QuestionaryUpdateRequestDto questionaryUpdateRequestDto){
        Questionary questionaryEntity = questionaryUpdateRequestDto.convertToEntity();
        try {
            Questionary  questionaryFound = questionaryService.getQuestionaryById(questionaryId);
            questionaryFound.setAsk(questionaryEntity.getAsk());
            questionaryFound.setOption1(questionaryEntity.getOption1());
            questionaryFound.setOption2(questionaryEntity.getOption2());
            questionaryFound.setOption3(questionaryEntity.getOption3());
            questionaryFound.setOption4(questionaryEntity.getOption4());
            questionaryFound.setOption4(questionaryEntity.getOption4());
            questionaryFound.setAnswer(questionaryEntity.getAnswer());
            questionaryFound.setImage(questionaryEntity.getImage());
            questionaryFound.setTest(questionaryEntity.getTest());

            Questionary questionaryUpdated = questionaryService.updateQuestionary(questionaryFound);
            return  new ResponseEntity<>(questionaryUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{questionaryId}")
    public ResponseEntity<Questionary> deleteQuestionary(@PathVariable("questionaryId") Long questionaryId){
        try {
            Questionary questionaryFound = questionaryService.getQuestionaryById(questionaryId);
            questionaryService.deleteQuestionary(questionaryId);
            return new ResponseEntity<>(questionaryFound, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
