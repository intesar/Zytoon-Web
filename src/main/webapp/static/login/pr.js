$(document).ready(function() {
    isHashPresent();
    $("#emailAccessCodeBtn").live("tap", function(){
        sendEmail();
    })
    $("#username").keypress(function(event) {
        if ( event.which == 13 ) {
            event.preventDefault();
            sendEmail();
        }
    })
    $("#resetPasswordBtn").live("tap", function(){
        reset();
    })
    $("#confirmPassword").keypress(function(event) {
        if ( event.which == 13 ) {
            event.preventDefault();
            reset();
        }
    })
    function errh(msg, exc) {
        alert(msg);
    }
    dwr.engine.setErrorHandler(errh);
})
function isHashPresent() {
    var vals = window.location.href.split("="); 
    if ( vals.length == 3) {
        var u = vals[1].split("&")[0]; 
        var ac = vals[2];
        $("#username").val(u);
        $("#ac1").val(ac);
        $("#emailAccessCodeBtn").attr("data-theme", "c");
    }
}   
function reset() {
    var u = $("#username").val();
    var ac = $("#ac1").val();
    var p = $("#password").val(); 
    var cp = $("#confirmPassword").val();
    GeneralAjaxService.resetPassword(u,ac,p,cp,function(){
        window.location.href = "/home/index";
    })
}
function sendEmail(){
    var u = $("#username").val();
    GeneralAjaxService.emailAccessCode(u,function(){
        alert("Please check your Email for Access Code!")
    })
}
