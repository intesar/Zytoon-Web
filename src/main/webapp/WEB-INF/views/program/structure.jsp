<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : structure
    Created on : Jul 11, 2011, 1:15:10 AM
    Author     : atefahmed
--%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <div data-role="content" >
        <div class="prepend-4 span-30">
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=MacRoman">
                <title>Track programs</title>
            </head>
            <body>
            <form:form action="${loginUrl}" modelAttribute="loginForm" method="post" data-ajax="false">
                <fieldset>
                    <div data-role="fieldcontain" style="width: 40%;">
                        <label for="profile">Advanced Programs</label><br/>
                        <button>One month Salaah, Etiquette and ABC program</button>
                    </div>

                    <div data-inline="true" id="specialty" style="width: 41%;">
                        <label>Specialty</label><br/>

                        <table class="specialtyClass">
                            <tbody>
                                <tr>
                                    <td>
                                    <button>Salaah</button>
                                    </td>
                                    <td>
                                    <button>Etiquette</button>
                                    </td>
                                    <td>
                                    <button>Speaking truth</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div data-inline="true" id="foundation" style="width: 40%;">
                        <label>Foundation</label><br/>
                        <button>Salaah On Time</button>
                    </div>

                    <div data-inline="true" id="beginners" style="width: 40%;">
                        <label>Beginners</label><br/>
                        <button>Start Salaah</button>
                    </div>


                </fieldset>
            </form:form>
        </div>
    </div>
</body>
</html>
