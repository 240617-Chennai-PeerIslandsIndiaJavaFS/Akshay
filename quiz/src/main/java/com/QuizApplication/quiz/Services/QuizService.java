package com.QuizApplication.quiz.Services;

import com.QuizApplication.quiz.Models.Quiz;
import com.QuizApplication.quiz.dao.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepo quizRepo;
    public List<Quiz> addQuestions(List<Quiz> quizzes){
        return quizRepo.saveAll(quizzes);

    }
    public Optional<Quiz> getQuestionById(int id){
        return quizRepo.findById(id);
    }
}
