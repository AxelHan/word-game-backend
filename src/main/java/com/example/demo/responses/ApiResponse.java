package com.example.demo.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponse<T> {

    protected String status;
    protected T data;

    public void set(T data){
        this.status = "success";
        this.data = data;
    }

}
