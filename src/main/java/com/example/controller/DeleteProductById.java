/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.controller;

import com.example.Dao.DatabaseConnector;
import com.example.Utility;
import com.example.model.APIResponse;
import com.example.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "DeleteProductById", urlPatterns = {"/delete_product_by_id"})
public class DeleteProductById extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String id =  req.getParameter("product_id");
        System.out.println(id);      
        Gson gson = new Gson();
         APIResponse res = new APIResponse("Delete Successful", null, 200);
        try {
            boolean isDeleted = deleteProsuct(id);
            if (isDeleted) {
                res.setMessage("Delete Successfully");
            } else {
                 res.setMessage("Delete faield");
            }
        } catch (ClassNotFoundException ex) {
            res.setMessage(ex.getLocalizedMessage());
        } catch (SQLException ex) {
             res.setMessage(ex.getLocalizedMessage());
        }
        resp.getWriter().print(gson.toJson(res));  
    }

    
    public static boolean deleteProsuct(String id) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseConnector.getConnect();
        String sql = "delete from products where id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);       
        pst.setInt(1, Integer.parseInt(id));
        int rs = pst.executeUpdate();
        if (rs > 0) {
            return true;
        }
        return false;
    }


}




