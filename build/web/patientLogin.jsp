<%-- 
    Document   : patientLogin
    Created on : Feb 27, 2017, 11:05:43 AM
    Author     : ramakrishna
--%>
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
                        <li class="active"><a href="index.jsp">HOME</a></li>
                        <li class="active"><a href="patientLogin.jsp">PATIENT LOGIN</a></li>
                        <li class="active"><a href="adminLogin.jsp">ADMIN LOGIN</a></li>
                        <li class="active"><a href="newRegistration.jsp">NEW REGISTRATION</a></li>
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
          <div class="large-6 medium-6 small-4 columns large-centered" style="margin-top: 50px;">
              <form data-abide action="./LoginAction"method="POST">
      <p  class="text-center" > <% String status=(String)request.getAttribute("status");
        if(status!=null){
      %><%=status%>
      <%} %></p>
                    <p class="text-left" style="font-weight: bold;">Patient Login Here</p>
                    <hr style="border: 1px solid green;">
                  
                     <div class="name-field">
                        <label>User name <small>required</small>
                            <input type="text" name="username" required pattern="[a-zA-Z]+">
                        </label>
                        <small class="error">Name is required and must be a string.</small>
                     </div>
                   <div class="name-field">
                        <label>Password <small>required</small>
                            <input type="password" name="password" required>
                        </label>
                        <small class="error">Enter Password is required.</small>
                    </div>
                  
                    
                   <div class="button-field">

                            <div class="row">
                                <div class="large-12a columns">

                                </div>

                                <div class="large-12 columns">

                                    <button type="submit" class="button radius expand" >LOGIN</button>

                                </div>
                            </div>
                        </div>



                </form>
          </div>
      </div>
<!--<div class="row">
    <div class="large-12 columns medium-6 small-4">-->
   
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