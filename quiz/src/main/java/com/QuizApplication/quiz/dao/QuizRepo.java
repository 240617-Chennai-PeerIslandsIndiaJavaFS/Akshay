package com.QuizApplication.quiz.dao;

import com.QuizApplication.quiz.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo  extends JpaRepository <Quiz,Integer>{


}