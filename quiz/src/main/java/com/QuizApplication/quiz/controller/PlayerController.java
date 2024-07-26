package com.QuizApplication.quiz.controller;

import com.QuizApplication.quiz.Models.Player;
import com.QuizApplication.quiz.Services.PlayerService;
import com.QuizApplication.quiz.responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @PostMapping
    public ResponseEntity<BaseResponse<Player>> addPlayers(@RequestBody Player player) {
        BaseResponse<Player> baseResponse = new BaseResponse<>();
        baseResponse.setData(playerService.addPlayers(player));
        baseResponse.setMessage("Player has been registered");
        baseResponse.setStatus(HttpStatus.CREATED.value());
        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<BaseResponse<Player>> login(@RequestParam String emailid,@RequestParam String password){
        Player player=playerService.login(emailid,password);
        BaseResponse<Player> baseResponse=new BaseResponse<>();
        baseResponse.setData(player);
        if(player==null){
            baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            baseResponse.setMessage("Invalid login attempt");
        }
        else{
            baseResponse.setStatus(HttpStatus.OK.value());
            baseResponse.setMessage("Login 👍");
        }
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
    @PostMapping("/play")
    public ResponseEntity<BaseResponse<Player>> validateAnswer(@RequestParam int p_id,@RequestParam int q_id,@RequestParam String answer){
        Player player=playerService.validateAnswer(p_id,q_id,answer);
        BaseResponse<Player> baseResponse=new BaseResponse<>();
        baseResponse.setData(player);
        if(p_id==5){
            baseResponse.setMessage("Thank you "+player.getLatestscore());
            baseResponse.setStatus(HttpStatus.OK.value());
        }
        else{
            baseResponse.setMessage("Updated score: "+player.getLatestscore());
            baseResponse.setStatus(HttpStatus.OK.value());
        }
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);

    }


}
