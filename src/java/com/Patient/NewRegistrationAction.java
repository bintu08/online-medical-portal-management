/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Patient;

import com.Database.FormBeans;
import com.Database.PatientDatabase;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ramakrishna
 */
public class NewRegistrationAction extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName=request.getParameter("username");
        String fullName=request.getParameter("fullname");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String city=request.getParameter("city");
        String dob=request.getParameter("dob");
        
        FormBeans form=new FormBeans();
        form.setFullName(fullName);
        form.setUserName(userName);
        form.setPassword(password);
        form.setEmail(email);
        form.setDob(dob);
        form.setCity(city);
        String path="";
        boolean flag=new PatientDatabase().newAccount(form);
        if(flag){
            request.setAttribute("status", "Registration is Successfully Completed...!!");
            path="./patientLogin.jsp";
        }
        else{
             request.setAttribute("status", "Registration is Failed please Try Again...!!");
            path="./newRegistration.jsp";
        }
        RequestDispatcher dis=request.getRequestDispatcher(path);
        dis.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
