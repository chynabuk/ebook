package com.application.electronic_book.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    private T data;
    private long dataAmount;
    private String error;

    public Response(T data){
        this.data = data;
    }

    public Response(String error) {
        this.error = error;
    }

    public Response(T data, long dataAmount){
        this.data = data;
        this.dataAmount = dataAmount;
    }
}