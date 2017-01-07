<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<% if (pageContext.getAttribute("activeTab") == null) { pageContext.setAttribute("activeTab", "users"); } %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Todo List</title>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources/bootstrap/css/bootstrap-datepicker3.standalone.css'/>">
  <script src="<c:url value='/resources/jquery/js/jquery.min.js'/>"></script>
  <script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js'/>"></script>  
  <script src="<c:url value='/resources/bootstrap/js/bootstrap-datepicker.js'/>"></script>

</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Todo</a>
    </div>
    <ul class="nav navbar-nav">      
      <li id="users" <c:if test='${activeTab eq "users"}'>class="active"</c:if>><a href="<c:url value='/users'/>">Users</a></li>
      <li id="tasks" class='${activeTab eq "tasks" ? "active":"none"}'><a href="<c:url value='/tasks'/>">Tasks</a></li>      
    </ul>
  </div>
</nav>

<div class="container">