/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Patient;

import com.Database.PatientDatabase;
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
public class PatientSendMessage extends HttpServlet {

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
       String fromUser=request.getParameter("fromuser");
       String toUser=request.getParameter("touser");
       String message=request.getParameter("message");
       boolean isSend=new PatientDatabase().send(fromUser,toUser,message);
       String path="";
       if(isSend){
           request.setAttribute("status", "Message sent Success..");
           path="./patientSendMessage.jsp?username="+toUser;
       }else{
           request.setAttribute("status", "Message Sending Failed..");
           path="./patientSendMessage.jsp?username="+toUser;
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
