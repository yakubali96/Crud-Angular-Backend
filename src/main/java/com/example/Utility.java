/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.io.BufferedReader;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */
public class Utility {

    public static StringBuffer requestBodyToStr(HttpServletRequest request)  {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {e.printStackTrace();}
        return jb;

    }
}
