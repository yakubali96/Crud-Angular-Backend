/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.controller;

import com.example.Dao.DatabaseConnector;
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
import java.util.List;
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
@WebServlet(name = "ShowProductController", urlPatterns = {"/show_products"})
public class ShowProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Product> plist = getProducts();
        String strPlist = gson.toJson(plist);
        PrintWriter out = response.getWriter();
        out.write(strPlist);
        out.flush();
        out.close();
    }

    public List<Product> getProducts() {

        List<Product> products = new ArrayList<>();
        try {
            Connection conn = DatabaseConnector.getConnect();
            String sql = "SELECT * FROM products";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"), rs.getDouble("price"), rs.getString("remarks"));
                products.add(product);
            }
        } catch (SQLException ex) {
            return products;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShowProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

  
}
