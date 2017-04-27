<%-- 
    Document   : patientDoctorProfile
    Created on : Mar 2, 2017, 5:03:34 PM
    Author     : ramakrishna
--%>

<%@page import="java.util.Iterator"%>
<%@page import="com.Database.FormBeans"%>
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

                                    String username = (String) session.getAttribute("userName");
                                    if (username != null) {
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



        <div class="row">
            <div class="large-12 columns">
                
                
                  <p class="text-left" style="font-weight: bold;">Doctors Profile Here</p>
                    <hr style="border: 1px solid green;">
                
                <table>
                    <tbody>
                        <tr  ><th width="250">Doctor Name</th><th width="250">City</th><th width="150">Experience</th><th width="250">Department</th><th width="150"></th></tr>


                        <%
                            ArrayList<FormBeans> search = (ArrayList<FormBeans>) request.getAttribute("profile");
                            Iterator<FormBeans> it = search.iterator();
                            while (it.hasNext()) {

                                FormBeans rfb = (FormBeans) it.next();

                        %>

                        <tr>
                            <td><%=rfb.getDoctorName()%></td>
                            <td> <%=rfb.getDoctorCity()%></td>
                            <td><%=rfb.getExp()%></td>
                            <td><%=rfb.getDepartment()%></td>

                            <td>
                                <a href="bookAppointment.jsp?username=<%=rfb.getDoctorUserName()%>" class="button ">BOOK</a>
                             
                              </td>
<!--                            <td>
                                
                                  <a href="sendMessage.jsp?username=<%=rfb.getDoctorUserName()%>" class="button success">MESSAGE</a>
                            </td>-->


                        </tr>
                        <%}%>
                    </tbody>
                </table> 

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