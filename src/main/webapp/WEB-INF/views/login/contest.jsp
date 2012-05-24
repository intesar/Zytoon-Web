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
        <h1>Zytoon - Sign Up Contest</h1>
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
                    <h3>Sign Up & Win HP TouchPad!</h3>

                    <div>
                        <p style="font-size:70%">
                            There is always a scope for each person to improve their Salaah.  A person praying five times in a Masjid can take their Salaah to next level and also pray Tahajjud and then retain it for life. 
                            Another person who only gets a chance to pray Jummah can start praying little more and improve from there. Zytoon.me is all about improving Salaah and many more skills for individuals, parents and children.
                        </p>
                        <p style="font-size:70%">
                            We are looking first users to Sign Up with Zytoon.me Beta and Enroll in one Salaah Program. 
                            A lucky winner will receive a new HP TouchPad 16GB.
                            Contest ends October 15th 2011. 
                            We ship only US & Canada.
                        </p>
                    </div>
                    
                    <%--
                    <a data-role="button" href="${pageContext.request.contextPath}/login/register" 
                       data-icon="plus" style="width:150px" data-theme="a"
                       >Sign up</a>
                    --%>

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