package com.QuizApplication.quiz.dao;

import com.QuizApplication.quiz.Models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<Player,Integer> {
    Player findByEmailid(String emailid);
}
