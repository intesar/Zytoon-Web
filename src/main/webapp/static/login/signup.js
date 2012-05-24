$(document).ready(function() {
    //dwr.util.useLoadingMessage("Trying to Register and Sign In");
    $("#registerBtn").click("tap", function(){
        register();
        })
    $("#password").keypress(function(event) {
        if ( event.which == 13 ) {
            event.preventDefault();
            register();
        }
    })
    function errh(msg, exc) {
        alert(msg);
    }
    dwr.engine.setErrorHandler(errh);
})
function register() {
    var n = $("#fullname").val();
    if ( n.length == 0 )  {
        alert ( " Name is required! ")
        return
    }
    var u = $("#username").val();
    var p = $("#password").val(); 
    var cp = p;//$("#confirmPassword").val();
    GeneralAjaxService.signUp(n,u,p,cp,function(){
        window.location.href = "/home/index";
    })
}
$('.passwordLength').hide();
$('.passwordLength').each( function() {
    $(this).prev().focus( function() {
        $(this).nextAll('.passwordLength').show('fast');
    }).blur( function() {
        $(this).nextAll('.passwordLength').hide('fast');
    });
});
$('.emailFormat').hide();
$('.emailFormat').each( function() {
    $(this).prev().focus( function() {
        $(this).nextAll('.emailFormat').show('fast');
    }).blur( function() {
        $(this).nextAll('.emailFormat').hide('fast');
    });
});