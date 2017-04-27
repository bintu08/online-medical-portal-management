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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ramakrishna
 */
public class BookAppointment extends HttpServlet {

   
 
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
        String doctorName=request.getParameter("dname");
        String patientName=request.getParameter("patientname");;
        String problem=request.getParameter("problem");
        String date=request.getParameter("date");
        String ciy=request.getParameter("city");
        int age=Integer.parseInt(request.getParameter("age"));
        FormBeans form=new FormBeans();
        
        form.setDoctorName(doctorName);
        form.setPatientName(patientName);
        form.setProblem(problem);
        form.setDate(date);
        form.setCity(ciy);
        form.setAge(age);
        HttpSession session= request.getSession();
        String username=(String)session.getAttribute("userName");
        boolean isBookingSuccess=new PatientDatabase().bookAppointment(form,username);
               String path=""; 
                if(isBookingSuccess){
                    request.setAttribute("username", doctorName);
                    request.setAttribute("status", "Slot Booking is Success");
                    path="./bookAppointment.jsp";
                }else{
                     request.setAttribute("username", doctorName);
                     request.setAttribute("status", "Slot Booking is Failed!!");
                    path="./bookAppointment.jsp";
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
