/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Patient;

import com.Database.FormBeans;
import com.Database.PatientDatabase;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ramakrishna
 */
public class PatientGetMessages extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String doctor=request.getParameter("username");
    
    HttpSession session=request.getSession();
    String uname=(String)session.getAttribute("userName");
    System.out.print(doctor);
    ArrayList<FormBeans> messages=new PatientDatabase().getMessages(doctor,uname);
    String path="";
   if(!messages.isEmpty()){
        ArrayList<FormBeans> recent= new PatientDatabase().RecentDoctors();
     
    
            request.setAttribute("recent", recent);  
        
            request.setAttribute("message", messages);
            path="./patientReceived1.jsp";
   }else{
       ArrayList<FormBeans> recent= new PatientDatabase().RecentDoctors();
     request.setAttribute("status", "Messages is Empty");
    
            request.setAttribute("recent", recent);
            path="./patientRecieved.jsp";
   }
        RequestDispatcher dis=request.getRequestDispatcher(path);
        dis.forward(request, response);
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
