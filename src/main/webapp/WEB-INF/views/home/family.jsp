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

<div data-role="page" data-theme="a" id="family" >
    <div data-role="header">
        <h1>Zytoon <span class="salam" style="font-size: 60%"></span></h1>
        <div data-role="navbar">
            <ul>
                <li><a href="/home/index#new-programs" data-icon="home" rel="external" data-prefetch>Programs <span style="font-size:60%"> (Step 1: Enroll)</span></a></li>
                <li><a href="/home/index#due-reports" data-icon="grid" rel="external" data-prefetch>Reports <span style="font-size:60%"> (Step 2: Track)</span></a></li>
                <li><a href="/home/family" data-icon="plus" data-theme="b" data-prefetch>My Family<span style="font-size:60%"></span></a></li>
                <li><a href="/home/profile" data-icon="gear" rel="external" >Profile</a></li>
                <li><a href="/logout" data-icon="delete" rel="external" >Logout</a></li>
            </ul>
        </div>
    </div>
    <div data-role="content">
        <div style="padding-left: 5%;float:left;width:350px" >
            <fieldset>
                <div class="input-div">
                    <input type="search" value="" id="emailSearch" placeholder=" Email" />
                    <a data-role="button" data-theme="b" data-icon="search" class="full" id="searchBtn"> Search </a>
                </div>
            </fieldset>

            <div class="search-result" style="display:none">
                <div data-role="collapsible" data-theme="a" data-content-theme="a" style="font-size:85%" data-collapsed="false">
                    <h3>Found email</h3>
                    <p><span id="ss-n"></span> <span style="font-size:80%; padding-left: 10px" class="grey"> (name)</span> </p>
                    <p><span id="ss-nn"></span><span style="font-size:80%; padding-left: 10px" class="grey">(nickname)</span></p>
                    <p><span id="ss-em"></span></p>
                    <p><span id="ss-a"></span></p>
                    <div>
                        <a data-role="button" data-theme="b" data-icon="star" class="full" id="joinBtn" data-inline="true" > Send invite to join your family network! </a>
                    </div>
                </div>
            </div>
            <div class="search-result-no" style="display:none">
                <div data-role="collapsible" data-theme="a" data-content-theme="a" style="font-size:85%" data-collapsed="false">
                    <h3>No match found</h3>
                    <p><span id="ssn-em"></span></p>
                    <p class="grey">Would you like to send email invitation to this user to join Zytoon.me?</p>
                    <div>
                        <a data-role="button" data-theme="b" data-icon="star" id="inviteBtn" class="full" data-inline="true" > Send invite to join Zytoon!</a>
                    </div>

                </div>
            </div>
            <div class="join-request" style="display:none">
                <div data-role="collapsible" data-theme="a" data-content-theme="a" style="font-size:85%" data-collapsed="false">
                    <h3>Networking Invitations</h3>
                    <div class="join-request-c">

                    </div>
                </div>
            </div>
            <br><br>
            <div class="my-net-div" style="display:none" data-role="collapsible" data-theme="a" data-content-theme="a" style="font-size:85%" data-collapsed="false">
                <h3>My Family Network</h3>
                <div class="my-network" >
                </div>
            </div>
        </div>
    </div>
</div>
<script type='text/javascript' src='${pageContext.request.contextPath}/static/home/engine.js'></script>    
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/ProgramAjaxService.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/static/home/family.js'></script>
