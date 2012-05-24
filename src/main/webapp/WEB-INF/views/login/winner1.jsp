<%--
    Document   : contest
    Created on : Sep 22, 2011, 6:14:54 AM
    Author     : atefahmed
--%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style>
    #contentDiv{
        float: left;
    }
    #description{
        float:left;
        margin-top: 15px;
        margin-left: 15px;
    }
</style>

<div data-role="page" data-theme="a" >

    <div data-role="header" data-position="fixed">
        <h1>Zytoon - HP TouchPad Winner</h1>
        <a data-icon="home" href="/" rel="external" style="width:100px">Back</a>
    </div>
    <div class="content" data-role="content" style="background-color:black;height: 100%">

        <div class="ui-grid-a" >

            <div class="ui-block-b">
                <p>
                    <img src="${pageContext.request.contextPath}/static/images/touchpad.png" alt="HP Touchpad"
                         id="imgStyle"/>
                </p>
            </div>

            <div class="ui-grid-b" style="margin-top: 70px;">
                <div>
                    <h3>Congratulations to HP TouchPad Winner from Zytoon Team.</h3>

                    <div>
                        <p> Winner ID # 29 </p>
                        <p> Winner Name # Umar Haque </p>
                        <p> Congratulations!!!</p>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
    <div data-role="footer" >
        <div data-role="navbar" >
            <ul>
                <li><a href="http://www.bizintelapps.com" rel="external" target="_blank">@BizIntelApps</a></li>
            </ul>
        </div>
    </div><!-- /footer -->
</div>