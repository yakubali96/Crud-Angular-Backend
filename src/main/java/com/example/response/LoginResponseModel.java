/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.response;

import java.util.Map;

/**
 *
 * @author User
 */
public class LoginResponseModel {
      private String message;
   
    private Map<String, Object> data;
   

    public LoginResponseModel(String message) {
        this.message = message;
       
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

  
}
