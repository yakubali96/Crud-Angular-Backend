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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "UpdateProduct", urlPatterns = {"/update_product"})
public class UpdateProduct extends HttpServlet {

   

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //-------start:--get request json data to java object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        StringBuffer jb = Utility.requestBodyToStr(req);
        Product product = gson.fromJson(jb.toString(), Product.class);
        System.out.println("Product: " + gson.toJson(product));
        //--------end:-get request json data to java object
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("product", product);
        APIResponse res = new APIResponse("Save Successful", data, 200);
        try {
            // code here to send database=========================

            if (updateProduct(product)) {
                res.setMessage("Update successfully");

            } else {
                res.setMessage("Update Failed");
            }
        } catch (SQLException ex) {
            res.setMessage("Data conn error " + ex.getLocalizedMessage());
        } catch (ClassNotFoundException ex) {
            res.setMessage("Data conn error " + ex.getLocalizedMessage());
        }
        //===========response======================

        resp.setContentType("application/json");

        PrintWriter out = resp.getWriter();

        out.write(gson.toJson(res));
        out.flush();
        out.close();

    }

    public boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnector.getConnect();
        String sql = "update products set name=?, quantity=?, price=?, remarks=? where id=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, product.getName());
        pst.setInt(2, product.getQuantity());
        pst.setDouble(3, product.getPrice());
        pst.setString(4, product.getRemarks());
        pst.setInt(5, product.getId());
        int rs = pst.executeUpdate();
        if (rs > 0) {
            return true;
        }

        return false;

    }

  
  
}
