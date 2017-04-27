<%-- 
    Document   : patientRecieved
    Created on : Mar 3, 2017, 12:00:59 PM
    Author     : ramakrishna
--%>

<%@page import="com.Database.FormBeans"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online Medical Portal Management</title>
    <link href="css/foundation.css" rel="stylesheet" type="text/css">
    <link href="css/foundation.min.css" rel="stylesheet" type="text/css">
    <link href="css/normalize.css" rel="stylesheet" type="text/css">
    <link href="foundation-icons/foundation-icons.css" rel="stylesheet" type="text/css">
    <link href="css/base.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="owlcarousel/assets/owl.carousel.css" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <script src="js/vendor/jquery.js" type="application/javascript"></script>
    <script src="js/foundation/foundation.js" type="application/javascript"></script>
    <script src="js/foundation.min.js" type="application/javascript"></script>
    <script src="js/foundation/foundation.topbar.js" type="application/javascript"></script>
    <script src="js/foundation/foundation.orbit.js" type="application/javascript"></script>
    <script src="js/foundation/foundation.reveal.js" type="application/javascript"></script>
    <script src="js/foundation/foundation.abide.js" type="application/javascript"></script>
    <script src="owlcarousel/owl.carousel.min.js"></script>

    <style>
        .owl-item{
            width: 1000px;
        }
        .owl-item > img{
            height: 350px;
            
        }
    </style>
</head>
<body>

<div class="row" style="border-bottom: 1px solid #e31e24;background-color: red">
    <div class="large-12 columns">
        <div class="sticky">
            <nav class="top-bar" data-topbar="" role="navigation" data-options="sticky_on: large">
                <ul class="title-area">
                    <li class="name">
                    </li>
                    <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
                    <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
                </ul>

                <section class="top-bar-section">
                    <!-- Right Nav Section -->
                    <ul class="right" id="right-navigation">
                        
                          <% 
                       // HttpSession session=request.getSession();
                        
                       String username=(String)session.getAttribute("userName");
                        if(username!=null){
                       %>
                        <li class="active"><a href="#"><%=username%></a></li>
                        <%}
                        %>
                        
                        <li class="active"><a href="patientHome.jsp">HOME</a></li>
                        <li class="active"><a href="./PatientRequests">BOOKED</a></li>
                        <li class="active"><a href="./PatientReceived">MESSAGES</a></li>
                        <li class="active"><a href="./PatientProfile">VIEW PROFILE</a></li>
                         <li class="active"><a href="patientHome.jsp">SEARCH DOCTORS</a></li>
                           <li class="active"><a href="./PatientViewDoctors">VIEW DOCTORS</a></li>
                             <li class="active"><a href="./LogoutAction">LOGOUT</a></li>
                          <li class="active"><a href="#">CONTACT US</a></li>

                              
                    </ul>

                    <!-- Left Nav Section -->
                    <ul class="left" id="left-navigation">
                        <li><a href="index.jsp"><i class="fi-home" style="font-size: 30px;color: rgba(227,29,36,.8);"></i></a></li>
                    </ul>
                </section>
            </nav>
        </div>
    </div>
</div> 
      <div class="row">
          <div class="large-6 medium-6 small-4 columns large-centered" style="margin-top: 50px;">
                 <p class="text-center" style="font-size:25px;color: red;font-weight: bold">Welcome to Online Medical Portal Management</p>
          </div>
      </div>
   
                        <%String touser=request.getParameter("username");%>
              
        <div class="row"> 
          <div class="large-8 medium-6 small-4 columns large-centered" style="margin-top: 50px;">
               <form action="./PatientGetMessages" method="POST">

        <p class="text-left" style="font-weight: bold;">Received Messages Here</p>
                    <hr style="border: 1px solid green;">
        
      <div class="row collapse">
        <div class="small-10 columns">
         <select name="username">
                                <%ArrayList<FormBeans> messagesResent=(ArrayList<FormBeans>) request.getAttribute("recent");
                            Iterator<FormBeans> it = messagesResent.iterator();
                            while (it.hasNext()) {

                                FormBeans rfb = (FormBeans) it.next();%>
                                <option name="username"value="<%=rfb.getUserName()%>"><%=rfb.getUserName()%></option>
                                <%}%>
                            </select>
        </div>
        <div class="small-2 columns">
            <button type="submit"  class="button postfix">Go</button>
        </div>
      
      </div>
       </form>
              <form data-abide action="./PatientReply"method="POST">
      <p  class="text-center" > <% String status=(String)request.getAttribute("status");
        if(status!=null){
      %><%=status%>
      <%} %></p>

                  
     <%ArrayList<FormBeans> messagesResent1=(ArrayList<FormBeans>) request.getAttribute("message");
                            Iterator<FormBeans> it1 = messagesResent1.iterator();
                   //         while (it1.hasNext()) {

                                FormBeans rfb = (FormBeans) it1.next();%>

                     <div class="name-field">
                        <label>From User Name <small>required</small>
                            <input type="text" name="fromuser" value="<%=rfb.getDoctorUserName()%>"required pattern="[a-zA-Z]+" readonly="true">
                        </label>
                        <small class="error">Name is required and must be a string.</small>
                     </div>
                   <div class="name-field">
                        <label>To User Name <small>required</small>
                            <input type="text" name="touser" value="<%=rfb.getUserName()%>"required pattern="[a-zA-Z]+" readonly="true">
                        </label>
                        <small class="error">Enter User name is required.</small>
                    </div>
                         <div class="name-field">
                        <label>Time <small></small>
                            <input type="text" name="time" value="<%=rfb.getDate()%>" readonly="true">
                        </label>
                        
                    </div>
                        <div class="name-field">
                        <label>Received Message <small>required</small>
                            <input class="error"rows="4"required value="<%=rfb.getDoctorName()%>"name="message1" readonly="true"placeholder="Enter Message...">

                        </label>
                       
                    </div>
                      <div class="name-field">
                        <label>Type Message <small>required</small>
                            <textarea class="error"rows="4"required name="message" placeholder="Enter Message..."></textarea>

                        </label>
                        <small class="error">Enter Message is required.</small>
                    </div>
                  
                    
                    <div class="button-field" style="padding-top: 10px">

                            <div class="row">
                                <div class="large-12a columns">

                                </div>

                                <div class="large-12 columns">

                                    <button type="submit" class="button radius expand" >REPLY</button>

                                </div>
                            </div>
                        </div>



                </form>     
          </div>
    
       
              
              
          </div>
      </div>
  </div>

              
  
   
             <div class="row"> 
          <div class="large-12 medium-6 small-4 columns large-centered" style="margin-top: 50px;">
              <p class="text-center" style="font-size:20px;color: green;font-weight: bold"></p>


  
        
     
         </div>
     </div>

 <div class="row footer-section">
    
    <p class="text-center" style="font-size: 12px;color: red">
        Copyright © 2017. Privacy Policy. All Rights Reserved
    </p>
</div>
    

<script type="text/javascript">
    $(document).ready(function(){
        $(document).foundation();
        $('.owl-carousel').owlCarousel({
            items:1,
            loop:true,
            margin:10,
            autoplay:true,
            autoplayTimeout:2000,
            autoplayHoverPause:true,
            responsiveClass:true
        });
    });
</script>


</body>
</html>