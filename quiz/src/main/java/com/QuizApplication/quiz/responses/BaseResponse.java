package com.QuizApplication.quiz.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component

public class BaseResponse<T> {
    private String message;
    private T data;
    private int status;

    public BaseResponse(String message, T data, int status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }
    public BaseResponse(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
