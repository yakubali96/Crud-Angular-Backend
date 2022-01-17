
package com.example.controller;

import com.example.Utility;
import com.example.model.APIResponse;
import com.example.request.LoginRequestModel;
import com.example.response.LoginResponseModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuffer strJson = Utility.requestBodyToStr(request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        LoginRequestModel payload = gson.fromJson(strJson.toString(), LoginRequestModel.class);
        
        if(payload.getUsername().equals("yakub") && payload.getPassword().equals("123")){
            LoginResponseModel res = new LoginResponseModel("Login Success");
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(res));
            out.flush();
            out.close();
            
        }else{
            APIResponse res = new APIResponse("Login Failed", null, 401);
            response.setStatus(401);
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(res));
            out.flush();
            out.close();
        }
    }

    
}
