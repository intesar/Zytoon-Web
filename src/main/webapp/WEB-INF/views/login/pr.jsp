<%-- 
    Document   : register
    Created on : Jul 11, 2011, 1:14:54 AM
    Author     : atefahmed
--%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <body>
        <div data-role="page" data-theme="a" >

            <div data-role="header">
                <h1>Password Reset</h1>
            </div>
            <div data-role="content" >
                <div class="prepend-4 span-30 container" style="width: 300px;padding-left:10%;padding-right:10%">
                    <fieldset>
                        <div class="input-div">
                            <input id="username" type="text" data-inline="true" placeholder="  Email">
                        </div>

                        <div data-inline="true" >
                            <input type="button" id="emailAccessCodeBtn" class="full" data-role="button" data-theme="a" value="Email Access Code!" />
                        </div>

                        <br><br><br>

                        <div class="input-div">
                            <input id="ac1" class="required" type="text" value="" placeholder="  Access Code">
                        </div>

                       <div class="input-div">
                            <input id="password" name="password" class="required" type="password" value="" placeholder="  New Password">
                        </div>

                        <div class="input-div">
                            <input id="confirmPassword" name="confirmPassword" class="required" type="password" value="" placeholder="  Confirm Passowrd">
                        </div>

                        <div class="input-div">
                            <input type="button" id="resetPasswordBtn" class="full" data-role="button" data-theme="b" value="Reset Password" />
                        </div>

                    </fieldset>

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
        <script type='text/javascript' src='${pageContext.request.contextPath}/static/login/pr.js'></script>
    </body>
</html>
