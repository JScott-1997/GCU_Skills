<%-- 
    Document   : accountdetails
    Created on : 5 Mar 2021, 18:20:51
    Author     : seanl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@ page import="bean.Student"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../../../../../OneDrive/Desktop/ProjectPublic-sean-s-branch/ProjectPublic-sean-s-branch/web/css/style.css">
        <link rel="icon" type="image/png" href="../../../../../OneDrive/Desktop/ProjectPublic-sean-s-branch/ProjectPublic-sean-s-branch/web/images/favicon32.png" sizes="32x32"/>
        <link rel="icon" type="image/png" href="../../../../../OneDrive/Desktop/ProjectPublic-sean-s-branch/ProjectPublic-sean-s-branch/web/images/favicon16.png" sizes="16x16">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="../../../../../OneDrive/Desktop/ProjectPublic-sean-s-branch/ProjectPublic-sean-s-branch/web/js/toggle.js"></script>
        <title>Account details | GCU_Skills</title>
        <% if(session.getAttribute("student")!=null){
        }
            else{
            response.sendRedirect("login.jsp");}%>
    </head>
    <body>
     <%@ include file="navbar.jsp" %>  
        <div id="section-container">
           <section class="image-section" id="accountdetails">
                <div class="image-box-content" id="detailsformbox">
                    <h2>Account details</h2>
                    <p>View and amend your account details below.<br>
                        To view your lessons and timetable <a href="timetable.jsp">click here.</a></p>
                    <form class="detailsform" id="detailsform">
                        <table style="width:100%">
                            <tr>
                              <th>Student ID:</th>
                              <td id="studentid">${student.studentId}</td>
                              <td id="studentidedit">*</td>
                            </tr>
                            <tr>
                              <th>Full name:</th>
                              <td><input type="text" class="input" name="fullname" id="fullnamefield" value="${student.firstName} ${student.lastName}" readonly /></td>
                              <td><small>*</small></td>
                            </tr>
                            <tr>
                              <th>Date of birth:</th>
                              <td><input type="text" class="input" name="dob" id="dobfield" value="${student.dob}" readonly /></td>
                              <td><small>*</small></td>
                            </tr>
                            <tr>
                              <th>Contact no:</th>
                              <td><input type="text" class="input" name="phonenumber" id="phonenofield" value="${student.phoneNumber}" readonly /></td>
                              <td><button class="detailsbutton" id="phonenoedit">Edit</button><small class="editconfirm">&check; updated.</small></td>
                            </tr>
                            <tr>
                              <th>Email:</th>
                              <td><input type="text" class="input" name="emailaddress" id="emailfield" value="${student.email}" readonly /></td>
                              <td><button class="detailsbutton" id="emailedit">Edit</button><small class="editconfirm">&check; updated.</small></td>
                            </tr>
                            <tr>
                              <th>Password:</th>
                              <td><input type="password" class="input" name="password" id="passwordfield" value="${student.password}" readonly /></td>
                              <td><button class="detailsbutton" id="passwordedit">Edit</button><small class="editconfirm">&check; updated.</small></td>
                            </tr>                            
                        </table>                          
                    </form>
                    <small>* - Not editable. Please <a href="index.jsp#contact">contact support</a> to request a change.</small>
                </div>
            </section>
        </div>
    </body>
</html>
