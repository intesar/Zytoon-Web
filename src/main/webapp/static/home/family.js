$(document).ready(function() {
    $.mobile.page.prototype.options.domCache = true
    $.mobile.ajaxEnabled=false
    $("#searchBtn").live("tap",searchByEmail)
    $("#joinBtn").live("tap", joinMyNet)
    $("#inviteBtn").live("tap", joinZytoon)
    loadFamily()
})
// Family & Friends
function loadFamily() {
    dwr.engine.beginBatch()
    ProgramAjaxService.myNetwork(showNetwork)
    ProgramAjaxService.networkingRequests(0, 10, requests)
    dwr.engine.endBatch()
}
var searchByEmail = function () {
    em = $("#emailSearch").val()
    hideNetworks()
    ProgramAjaxService.searchEmail(em, displayUserInfo)
}
var em
var displayUserInfo = function(user) {
    if ( user != null) {
        $(".search-result").show()
        $("#ss-n").html(user.name)
        $("#ss-nn").html(user.nickname)
        $("#ss-em").html(em)
        $("#ss-a").html(user.city + ', ' + user.country)
        $("#emailSearch").val('')
    } else {
        $(".search-result-no").show()
        $("#ssn-em").html($("#emailSearch").val())
    }
}
function hideNetworks() {
    $(".search-result").hide()
    $(".search-result-no").hide()
}
var joinMyNet = function () {
    ProgramAjaxService.requestJoinNetwork(em, 'family', function() {
        ProgramAjaxService.myNetwork(showNetwork)
    })
    hideNetworks()
}
var joinZytoon = function() {
    ProgramAjaxService.sendInvite(em)
    hideNetworks()
}
var showNetwork = function (users) {
    var html = ''
    $.each(users,function(){
        html += '<p class="grey"><span>'+ this.friend.name + (this.active == false ? ' -- pending' : '') +'</span></p>'
    })
    if ( html.length > 0 ) {
        $(".my-net-div").show()
        $(".my-network").html(html)
    }
}
var requests = function(msgs) {
    var html = ''
    $(".join-request").hide()
    $.each(msgs, function() {
        html += '<div>'
        +'<p><span> ' + this.sender.name +'</span> <span style="font-size:80%; padding-left: 10px" class="grey"> (name)</span> </p>'
        +'<p><span>' + this.sender.nickname+ '</span><span style="font-size:80%; padding-left: 10px" class="grey">(nickname)</span></p>'
        +'<p><span>' + this.sender.city + ', ' + this.sender.country+ ' </span></p>'
        +'<div>'
        +'  <a data-role="button" data-theme="b" id="jr-' + this.id +'" data-icon="home" class="acceptBtn" data-inline="true" style="width:150px"> Accept </a>'
        +'   <a data-role="button" data-theme="a" id="jr-' + this.id +'" data-icon="star" class="declineBtn" data-inline="true" style="width:150px"> Decline </a>'
        +' </div>'
        +'</div>'
    })
    if ( html.length > 0 ) {
        $(".join-request").show()
        $(".join-request-c").html(html).trigger('create')
    }
}
$(document).ready(function() {
    $(".acceptBtn").live("tap", function() {
        ProgramAjaxService.requestAction(this.id.substr(3), 1, 'family', function() {
            ProgramAjaxService.networkingRequests(0, 10, requests)
            ProgramAjaxService.myNetwork(showNetwork)
        })
    })
    $(".declineBtn").live("tap", function() {
        ProgramAjaxService.requestAction(this.id.substr(3), 2, 'family', function() {
            ProgramAjaxService.networkingRequests(0, 10, requests)
            ProgramAjaxService.myNetwork(showNetwork)
        })
    })
})