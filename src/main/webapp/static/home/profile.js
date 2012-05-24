$(document).ready(function() {
    ProgramAjaxService.getUserProfile(function(profile){
        fillProfile(profile)
        fillProfileV(profile)
    // TODO: once form submission is fixed.. will implement these.
    })
    $("#updateBtn").click(function(){
        var name = $("#firstname").val();
        var gender = $("#gender").val();
        var city = $("#city").val();
        var country = $("#country").val();
        var zipcode = $("#zipcode").val();
        var notify = $("#notifyReportDue").val()
        ProgramAjaxService.saveUserProfile('',name,'','',gender,city,zipcode,country,notify,function(profile){
            fillProfile(profile)
            fillProfileV(profile)
            $.mobile.changePage("#profileView")
        });
    })
})
function fillProfile(profile) {
    $("#firstname").val(profile.name);
    $("#gender").val(profile.gender).trigger('change');
    $("#city").val(profile.city);
    $("#zipcode").val(profile.zipcode);
    $("#country").val(profile.country).trigger('change');
    $("#notifyReportDue").val((profile.notifyReportDue) ? "true" : "false").trigger('change');
}
function fillProfileV(profile) {
    $("#pointsV").html(profile.points);
    $("#firstnameV").html(profile.name);
    $("#genderV").html(profile.gender);
    $("#cityV").html(profile.city);
    $("#zipcodeV").html(profile.zipcode);
    $("#countryV").html(profile.country);
    var x = (profile.notifyReportDue) ? "ON" : "OFF"
    $("#notifyReportDueV").html(x)
}