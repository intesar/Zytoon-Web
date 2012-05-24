<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jqplot/jquery.jqplot.min.css" />
<style>
    .stream-b{font-size: 7pt;}
    table.jqplot-table-legend {background-color: #666}
</style>

<!-- Start of first page -->

<div data-role="page" data-theme="a" id="new-programs" >
    <div data-role="header">
        <%--
        <h1>Programs</h1>
        <a href="#home" data-icon="home" rel="external" style="width:100px">Home</a>
        --%>
        <h1>Zytoon <span class="salam" style="font-size: 60%"></span></h1>
        <div data-role="navbar">
            <ul>
                <li><a href="#new-programs" data-icon="home" data-theme="b" >Programs <span style="font-size:60%"> (Step 1: Enroll)</span></a></li>
                <li><a href="#due-reports" data-icon="grid" data-transition="fade">Reports <span style="font-size:60%"> (Step 2: Track)</span></a></li>
                <li><a href="/home/family" data-icon="plus" rel="external" data-transition="fade">My Family<span style="font-size:60%"></span></a></li>
                <li><a href="/home/profile" data-icon="gear" rel="external" data-prefetch>Profile</a></li>
                <li><a href="/logout" data-icon="delete" rel="external" >Logout</a></li>
            </ul>
        </div>
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

<div data-role="page" data-theme="a" id="due-reports" >
    <div data-role="header">
         <h1>Zytoon <span class="salam" style="font-size: 60%"></span></h1>
        <div data-role="navbar">
            <ul>
                <li><a href="#new-programs" data-icon="home" data-transition="fade">Programs <span style="font-size:60%"> (Step 1: Enroll)</span></a></li>
                <li><a href="#due-reports" data-icon="grid" data-theme="b" >Reports <span style="font-size:60%"> (Step 2: Track)</span></a></li>
                <li><a href="/home/family" data-icon="plus" rel="external" data-transition="fade">My Family<span style="font-size:60%"></span></a></li>
                <li><a href="/home/profile" data-icon="gear" rel="external" >Profile</a></li>
                <li><a href="/logout" data-icon="delete" rel="external" >Logout</a></li>
            </ul>
        </div>
    </div>
    <div data-role="content" >
        <div style="width: 450px">
            <div id="programs">
                <div data-collapsed="true" id="report"></div>
                <div data-collapsed="true" id="active-programs"></div>
                
                <div data-role="controlgroup" data-type="horizontal" style="padding-left:35px" id="month_p" >
                    <a href="javascript:void()" data-role="button" data-icon="star" data-theme="b" data-iconpos="notext" id="plus">Salah</a>
                    <a href="javascript:void()" data-role="button" data-icon="info" data-theme="b"  data-iconpos="notext" id="minus">Adaab</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="javascript:void()" data-role="button" data-icon="arrow-l" data-iconpos="notext" id="m_p_p">Prev</a>
                    <a href="javascript:void()" data-role="button" data-icon="arrow-r" data-iconpos="notext" id="m_p_n">Next</a>
                </div>
                <div id="chartLM"> 
                    <div id="chartL" style="height:300px;width:450px;color: #FFFFFF;"></div>
                    <br>
                    <div id="chartF" style="height:300px;width:450px;color: #FFFFFF;"></div>
                </div>
                
            </div>
        </div>
    </div>
    <%--
    <div data-role="footer" data-position="fixed">
        <div data-role="navbar" >
            <ul>
                <li><a href="">Zytoon.me Beta</a></li>
            </ul>
        </div>
    </div>
    --%>
</div>

<%--
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
                <a data-role="button" data-theme="b" data-icon="plus" class="full" id="postBtn"> Save</a>
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
--%>
<%-- 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jqplot/jquery.jqplot.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/jqplot/plugins/jqplot.cursor.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jqplot/plugins/jqplot.dateAxisRenderer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jqplot/plugins/jqplot.logAxisRenderer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jqplot/plugins/jqplot.canvasTextRenderer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jqplot/plugins/jqplot.canvasAxisTickRenderer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jqplot/plugins/jqplot.highlighter.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/jqplot/plugins/jqplot.barRenderer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jqplot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jqplot/plugins/jqplot.pointLabels.min.js"></script>

<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>  --%>

<script type='text/javascript' src='${pageContext.request.contextPath}/static/home/engine.js'></script>    
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/ProgramAjaxService.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/static/home/jqplot.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/static/home/main.js'></script>
