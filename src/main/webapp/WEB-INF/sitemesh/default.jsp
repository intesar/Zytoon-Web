<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta name="Muslim social network" content="Zytoon" />
        <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0"> 
        <title>Zytoon</title>
        <%--<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0rc2/jquery.mobile-1.0rc2.min.css" /> --%>
        <link rel="stylesheet" href="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.css" />
        <style type="text/css">
            input.ui-input-text, textarea.ui-input-text, .full, .ui-btn-inline, .ui-select {
                width:100%
            }
            .ui-input-text, .grey{
                color: grey;
            }
            .input-div{
                padding-bottom: 5px;
            }
        </style>
        <%--
        <script src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
        <script src="http://code.jquery.com/mobile/1.0rc2/jquery.mobile-1.0rc2.min.js"></script>
        --%>
        <script src="http://code.jquery.com/jquery-1.6.4.min.js" type="text/javascript"></script>
        <script src="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.js" type="text/javascript"></script>
        
    </head>
    <body class="parent-div" style="display: none">

        <decorator:body />
        
        <script type="text/javascript">
            $(document).ready(function() {
                jQuery.fn.center = function () {
                    this.css("position","absolute");
                    var top = (($(window).height() - this.outerHeight()) / 2) 
                    top = top < 0 ? 30 : top
                    var left = (($(window).width() - this.outerWidth()) / 2)
                    left = left < 0 ? 0 : left
                    this.css("top",  top + $(window).scrollTop() + "px");
                    this.css("left",  left + $(window).scrollLeft() + "px");
                    return this;
                }
                $(".parent-div").show();
                $(".container").center();
                if ( $.browser.msie ) {
                    $(".parent-div").hide();
                    alert("Please use Firefox, Chrome or Safari. IE is not supported.")                    
                }
            })
        </script>
        <%--
        <script type="text/javascript">
            // example with asynchronous google analytics
            var _gaq = _gaq || [];
            _gaq.push([ '_setAccount', 'UA-24830640-1' ]); // change with your google id
            _gaq.push([ '_setCustomVar', 1, 'rootPackage', 'com.bizintelapps.zytoon', 1 ]); // example of user defined variable
            _gaq.push([ '_trackPageview' ]);

            (function() {
                var ga = document.createElement('script');
                ga.type = 'text/javascript';
                ga.async = true;
                ga.src = ('https:' == document.location.protocol ? 'https://ssl'
                : 'http://www')
                    + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(ga, s);
            })();
        </script>
        --%>
    </body>
</html>
