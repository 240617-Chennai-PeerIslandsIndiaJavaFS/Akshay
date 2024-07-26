package com.QuizApplication.quiz.Services;

import com.QuizApplication.quiz.Models.Player;
import com.QuizApplication.quiz.Models.Quiz;
import com.QuizApplication.quiz.dao.PlayerRepo;
import com.QuizApplication.quiz.dao.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class PlayerService {
    @Autowired
    PlayerRepo playerRepo;
    @Autowired
    QuizService quizService;
    public Player addPlayers(Player player){
        return playerRepo.save(player);

    }

    public Player login(String email,String password){
        Player player=playerRepo.findByEmailid(email);
        if(player!=null){
            if(player.getPassword().equals(password)){
                return player;
            }
            else{
                return null;
            }
        }
        return null;
    }
    public Player getPlayerById(int id){
        return playerRepo.findById(id).get();
    }

    public Player validateAnswer(int player_id,int question_id,String answer){

        System.out.println("Into service-------------------------");
        Player player=getPlayerById(player_id);
        System.out.println("Player object by Id "+player+"-----------");
        Quiz question=quizService.getQuestionById(question_id).get();
        System.out.println("Question object--------------"+question);
        System.out.println("Answer: "+answer+"---------------------------");
        System.out.println(question.getCorrectanswer().equals(answer));
        if(quizService.getQuestionById(question_id).get().getCorrectanswer().equals(answer)){
            System.out.println("updating score -------------------------------");
            player.setLatestscore(player.getLatestscore()+1);
            playerRepo.save(player);

        }
        if(question_id==5){
            if(player.getLatestscore()>player.getHighScore()){
                player.setHighScore(player.getLatestscore());
            }
            player.setLatestscore(0);
            playerRepo.save(player);
        }

        return player;
    }


}
