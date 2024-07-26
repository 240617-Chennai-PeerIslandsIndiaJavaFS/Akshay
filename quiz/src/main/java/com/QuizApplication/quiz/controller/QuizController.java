package com.QuizApplication.quiz.controller;

import com.QuizApplication.quiz.Models.Quiz;
import com.QuizApplication.quiz.Services.QuizService;
import com.QuizApplication.quiz.responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    public QuizService quizService;

    @PostMapping
    public BaseResponse<List<Quiz>> addQuestions(@RequestBody List<Quiz> quizzes){
        BaseResponse<List<Quiz>> response =new BaseResponse<>();
        response.setData(quizService.addQuestions(quizzes));
        response.setMessage("Questions added sucessfully");
        response.setStatus(HttpStatus.CREATED.value());
        return response;
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Quiz>> getQuestionById(@PathVariable int id){
        BaseResponse<Quiz> response=new BaseResponse<>();
        response.setData(quizService.getQuestionById(id).get());
        response.setMessage("Question"+id+" has been fetched successfully");
        response.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
