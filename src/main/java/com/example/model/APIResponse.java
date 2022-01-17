
package com.example.model;

import java.util.Map;


public class APIResponse {
    
    private String message;
    private Map<String, Object> data;
    private int statusCode;

    public APIResponse() {
    }


    public APIResponse(String message, Map<String, Object> data, int statusCode) {
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
        
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

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}