<%-- 
    Document   : profile
    Created on : Jul 11, 2011, 1:14:43 AM
    Author     : atefahmed
--%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquerymobile-1.0b1/jqm-datebox.css" /> --%>

<div data-role="page" data-theme="a" id="profileView" >
    <div data-role="header">
        <h1>Zytoon</h1>
        <div data-role="navbar">
            <ul>
                <li><a href="/home/index#new-programs" data-icon="home" rel="external" data-prefetch>Programs <span style="font-size:60%"> (Step 1: Enroll)</span></a></li>
                <li><a href="/home/index#due-reports" data-icon="grid" rel="external" data-prefetch>Reports <span style="font-size:60%"> (Step 2: Track)</span></a></li>
                <li><a href="/home/family" data-icon="plus" rel="external" data-prefetch>My Family<span style="font-size:60%"></span></a></li>
                <li><a href="/home/profile" data-icon="gear" rel="external" data-theme="b">Profile</a></li>
                <li><a href="/logout" data-icon="delete" rel="external" >Logout</a></li>
            </ul>
        </div>
    </div>
    <div data-role="content" >

        <div class="container" style="width: 300px">
            <fieldset>
                <div class="input-div">
                    <label class="grey">Name: </label>&nbsp;&nbsp;&nbsp;&nbsp; <span id="firstnameV" ></span>
                </div>
                <div class="input-div">
                    <label class="grey">Points: </label>&nbsp;&nbsp;&nbsp; <span id="pointsV"></span>
                </div>
                <div class="input-div">
                    <label class="grey">Geder: </label>&nbsp;&nbsp;&nbsp; <span id="genderV" ></span>
                </div>
                <div class="input-div">
                    <label class="grey">City: </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="cityV" ></span>
                </div>

                <div class="input-div">
                    <label class="grey">Zipcode: </label>&nbsp;<span id="zipcodeV" ></span>
                </div>

                <div class="input-div">
                    <label class="grey">Country: </label>&nbsp;<span id="countryV"></span>
                </div>
                
                <div class="input-div">
                    <label class="grey">Email Alert due reports: </label>&nbsp;<span id="notifyReportDueV"></span>
                </div>

                <div class="input-div">
                    <a href="#main-page" data-role="button" data-theme="a" data-transition="fade">Edit</a>
                </div>
            </fieldset>
        </div>

        <%--
        <div data-role="footer" >
            <div data-role="navbar" >
                <ul>
                    <li><a href="">Zytoon.me Beta</a></li>
                </ul>
            </div>
        </div>
        --%>


    </div>

</div>

        
<div data-role="page" data-theme="a" id="main-page" >
    <div data-role="header">
        <h1>Zytoon</h1>
        <%--
        <h1>Me</h1>
        <a data-icon="home" href="/home/index" rel="external" style="width:100px">Home</a>
        --%>
        <div data-role="navbar">
            <ul>
                <li><a href="/home/index#new-programs" data-icon="home" rel="external" data-prefetch>Programs <span style="font-size:60%"> (Step 1: Enroll)</span></a></li>
                <li><a href="/home/index#due-reports" data-icon="grid" rel="external" data-prefetch>Reports <span style="font-size:60%"> (Step 2: Track)</span></a></li>
                <li><a href="/home/family" data-icon="plus" rel="external" data-prefetch>My Family<span style="font-size:60%"></span></a></li>
                <li><a href="/home/profile" data-icon="gear" rel="external" data-theme="b">Profile</a></li>
                <li><a href="/logout" data-icon="delete" rel="external" >Logout</a></li>
            </ul>
        </div>
    </div>
    <div data-role="content" >

        <div class="container" style="width: 300px">
            <fieldset>
                <div class="input-div">
                    <input id="firstname" type="text" placeholder="  Full Name">
                </div>
                
                <%--
                <tr>
                    <td>
                        <label>Date of birth:</label>
                    </td>
                    <td>
                        <div data-role="fieldcontain" data-inline="true" >

                                <input name="dob" id="dob" type="date" data-role="datebox" data-theme="a"
                                       data-options='{"mode": "datebox","disableManualInput": "true"}'>
                            </div>
                        </td>
                    </tr>
                --%>
                <div class="input-div">
                    <select id="gender" >
                        <option value="">Gender</option>
                        <option value="male">Male</option><br/>
                        <option value="female">Female</option><br/>
                    </select>
                </div>
                <div class="input-div">
                    <input id="city" type="text" placeholder="  City">
                </div>

                <div class="input-div">
                    <input id="zipcode" type="text" placeholder="  Zipcode">
                </div>

                <div class="input-div">
                    <select name="select-choice-1" id="country" data-placeholder="true">
                        <option value="">Country</option>
                        <option value="USA">USA</option>
                        <option value="Canada">Canada</option>
                        <option value="India">India</option>
                        <option value="Turkey">Turkey</option>
                        <option value="Australia">Australia</option>
                        <option value="UK">UK</option>
                        <option value="KSA">KSA</option>
                        <option value="UAE">UAE</option>
                        <option value="Malaysia">Malaysia</option>
                        <option value="Pakistan">Pakistan</option>
                        <option value="Other">Other</option>
                    </select>
                </div>

                <div class="input-div">
                    <select id="notifyReportDue" >
                        <option value="false">Email Alert Off </option><br/>
                        <option value="true">Email Alert ON</option><br/>
                    </select>
                </div>
                
                <div class="input-div">
                    <a data-role="button" data-theme="b" data-icon="check" data-inline="true" class="full" id="updateBtn" href="Javascript:void(0);">Save</a>
                </div>

            </fieldset>
        </div>

        <%--
        <div data-role="footer" >
            <div data-role="navbar" >
                <ul>
                    <li><a href="">Zytoon.me Beta</a></li>
                </ul>
            </div>
        </div>
        --%>


    </div>

</div>
        
<script type='text/javascript' src='${pageContext.request.contextPath}/static/home/engine.js'></script>    
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/ProgramAjaxService.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/static/home/profile.js'></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquerymobile-1.0b1/jqm-datebox.js"></script> --%>


