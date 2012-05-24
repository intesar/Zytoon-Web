<%--
 | (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 | Source code generated by Celerio, a Jaxio product
 | Want to use Celerio within your company? email us at info@jaxio.com
 | Follow us on twitter: @springfuse
 | Template pack-mvc-3:src/main/webapp/WEB-INF/views/index.p.vm.jsp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
    .stream-b{font-size: 7pt;}
</style>

<!-- Start of first page -->
<div data-role="page" data-theme="a" id="home" >

    <div data-role="header">
        <h1>Home</h1>
        <a href="/home/profile" data-icon="gear" rel="external" style="width:100px">Profile</a>
        <a href="/logout" data-icon="delete" rel="external" style="width:100px">Logout</a>
    </div>

    <div data-role="content">
        <div style="width: 450px">
        <ul data-role="listview" data-theme="a" data-inset="true" >
            <li><a href="#new-programs" rel="external">Programs <span style="font-size:60%"> (Step 1: Enroll)</span></a></li>
            <li><a href="#due-reports" rel="external">Reports <span style="font-size:60%"> (Step 2: Track)</span></a></li>
            <%-- <li><a href="#wall" rel="external">Wall</a></li> --%>
        </ul>
        </div>
    </div><!-- /content -->


    <div data-role="footer" data-position="fixed">
        <div data-role="navbar" >
            <ul>
                <li><a href="">Zytoon.me Beta</a></li>
            </ul>
        </div>
    </div><!-- /footer -->
</div><!-- /page --> 

<div data-role="page" data-theme="a" id="due-reports" >

    <div data-role="header">
        <h1>Reports</h1>
        <a href="#home" data-icon="home" rel="external" style="width:100px">Home</a>
    </div>


    <div data-role="content" >
        <div style="width: 450px">
        <div id="programs">
            <div data-collapsed="true" id="report"></div>
            
            <div data-collapsed="true" id="active-programs"></div>
        </div>
        </div>
    </div>
    <div data-role="footer" data-position="fixed">
        <div data-role="navbar" >
            <ul>
                <li><a href="">Zytoon.me Beta</a></li>
            </ul>
        </div>
    </div>
</div>

<div data-role="page" data-theme="a" id="new-programs" >

    <div data-role="header">
        <h1>Programs</h1>
        <a href="#home" data-icon="home" rel="external" style="width:100px">Home</a>
    </div>


    <div data-role="content" >
        <div style="width: 450px">
        <p style="font-size:77%">First thing to do is to enroll in a program that suites you the most.
            &nbsp;After passing, either take the next level program or join the longer version of the same program.
            </p>
        <div data-collapsed="true" id="upcoming-programs"></div>
        </div>
    </div>
    <%--
    <div data-role="footer" data-position="fixed">
        <div data-role="navbar" data-position="fixed">
            <ul>
                <li><a href="">Zytoon.me Beta</a></li>
            </ul>
        </div>
    </div>
    --%>
</div>

<div data-role="page" data-theme="a" id="wall" >
    <div data-role="header">
        <h1>Wall</h1>
        <a href="#home" data-icon="home" >Home</a>
    </div>
    <div data-role="content" >
        <div style="padding-left:5%">
            <a href="#share" id="showShareDiv" data-role="button" data-theme="a" data-icon="plus" data-iconpos="left" style="width:200px">Share</a>
        </div>
        <br/>
        <div style="padding-left:5%" id="streamDiv"></div>
    </div>
    <div data-role="footer" data-position="fixed" >
        <div data-role="navbar" data-position="fixed" data-role="controlgroup">
            <ul>
                <li><a href="">Zytoon.me Beta</a></li>
            </ul>
        </div>
    </div>
</div>

<div data-role="page" data-theme="a" id="share" >
    <div data-role="header">
        <h1>Share</h1>
        <a href="#wall" data-icon="info" >Wall</a>
    </div>
    <div data-role="content">
        <div style="padding-left: 5%;float:left;" id="shareDiv">
            <div data-role="fieldcontain" data-inline="true" style="width: 350px;">
                <label>Title <span class="stream-b"> Book, Article </span></label><br/>
                <input id="title" type="text" style="width:350px">
            </div>
            <div data-role="fieldcontain" data-inline="true" style="width: 350px;">
                <label>URL</label><br/>
                <input id="url" type="text" style="width:350px">
            </div>

            <div data-inline="true" style="width: 250px;">
                <a data-role="button" data-theme="b" data-icon="plus" id="postBtn"> Save</a>
                <%--input type="button" data-role="button" data-theme="c" data-icon="delete" data-inline="true" class="postCancelBtn" value="Cancel" /--%>
            </div>
        </div>
    </div>
    <div data-role="footer" data-position="fixed" >
        <div data-role="navbar" data-position="fixed" data-role="controlgroup">
            <ul>
                <li><a href="">Zytoon.me Beta</a></li>
            </ul>
        </div>
    </div>
</div>




<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>    
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/ProgramAjaxService.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/static/home/main.js'></script>
