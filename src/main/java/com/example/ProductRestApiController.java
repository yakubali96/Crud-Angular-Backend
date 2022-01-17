package com.example;

import com.example.model.APIResponse;
import com.example.model.Product;
import com.example.Dao.DatabaseConnector;
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

@WebServlet(name = "ProductRestApiController", urlPatterns = {"/api/add_product"})
public class ProductRestApiController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //-------start:--get request json data to java object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        StringBuffer jb = Utility.requestBodyToStr(request);
        Product product = gson.fromJson(jb.toString(), Product.class);
        System.out.println("Product: " + gson.toJson(product));

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("product", product);
        APIResponse res = new APIResponse("Save Successful", data, 200);

        try {
            if (save(product)) {
                res.setMessage("Save successfully");
            } else {
                res.setMessage("Save Failed");
            }
        } catch (SQLException ex) {
            res.setMessage("Data conn error " + ex.getLocalizedMessage());
        } catch (ClassNotFoundException ex) {
            res.setMessage("Data conn error " + ex.getLocalizedMessage());
        }
        //===========response======================
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        out.write(gson.toJson(res));
        out.flush();
        out.close();

    }

    public boolean save(Product product) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnector.getConnect();
        String sql = "insert into products(name, quantity, price, remarks) values(?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, product.getName());
        pst.setInt(2, product.getQuantity());
        pst.setDouble(3, product.getPrice());
        pst.setString(4, product.getRemarks());
        int rs = pst.executeUpdate();
        if (rs > 0) {
            return true;
        }

        return false;
    }

}
