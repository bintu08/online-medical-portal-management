/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Admin;

import com.Database.AdminDatabase;
import com.Database.FormBeans;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ramakrishna
 */
public class AddDoctor extends HttpServlet {

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
        doPost(request, response);    }

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
     String dname=request.getParameter("dname");
     String userName=request.getParameter("username");
     String password=request.getParameter("password");
     String department=request.getParameter("department");
     String city=request.getParameter("city");
     int exp=Integer.parseInt(request.getParameter("exp"));
     
     
     FormBeans form=new FormBeans();
     form.setDoctorName(dname.toUpperCase());
     form.setDoctorUserName(userName);
     form.setDoctorPassword(password);
     form.setDepartment(department.toUpperCase());
     form.setDoctorCity(city);
     form.setExp(exp);
     
     
     boolean addDoctor=new AdminDatabase().addDoctor(form);
     String path="";
     if(addDoctor){
         request.setAttribute("status", "Adding Success!!");
         path="./adminHome.jsp";
     }
     else{
          request.setAttribute("status", "Adding Failed!!");
         path="./adminHome.jsp";
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
