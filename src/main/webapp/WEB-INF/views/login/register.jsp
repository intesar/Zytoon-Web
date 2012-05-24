<%-- 
    Document   : register
    Created on : Jul 11, 2011, 1:14:54 AM
    Author     : atefahmed
--%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<div data-role="page" data-theme="a" >

    <div data-role="header">
        <h1>Sign Up</h1>
    </div>
    <div data-role="content" style="padding-left:10%">
        <div class="prepend-4 span-30 container" >
            <form:form action="${loginUrl}" modelAttribute="registerForm" method="post" data-ajax="false">
                <form:errors cssClass="error" />
                <fieldset>

                    <div class="input-div">
                        <input id="fullname" type="text" value="" placeholder="  Full Name">
                    </div>
                    <div class="input-div"></div>
                    <div class="input-div">
                        <input id="username" type="text" value="" placeholder="  Email">
                    </div>
                    <div class="input-div"></div>
                    <div class="input-div">
                        <input id="password" type="password" value="" placeholder="  Password">
                    </div>
                    <%--
                    <div data-role="fieldcontain" >
                        <label for="confirmPassword1" >Confirm-Password</label>
                        <br />
                        <input id="confirmPassword" name="confirmPassword" class="required" type="password" value="">
                        <form:password path="confirmPassword" cssClass="required" />
                    </div>
                    --%>
                    <div class="input-div"></div>
                    <div class="input-div">
                        <a href="license" rel="external" >Terms of Service</a>
                    </div>
                    <div class="input-div"></div>
                    <div class="input-div">
                        <input type="button" id="registerBtn" data-role="button" data-theme="b" value="I Accept. Create my account." />
                    </div>

                </fieldset>
            </form:form>
        </div>
    </div>
    <div data-role="footer" data-position="fixed">
        <div data-role="navbar" data-position="fixed">
            <ul>
                <li><a href="javascript:void(0);" >@Zytoon.me</a></li>
            </ul>
        </div>
    </div><!-- /footer -->
</div>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>    
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/GeneralAjaxService.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/static/login/signup.js'></script>

