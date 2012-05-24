var reports = new Array();
var reportCount = 0;
var wallStartIndex = 0;
var wallMaxResult = 10;
var sCount = 0;
$(document).ready(function() {
    $.mobile.page.prototype.options.domCache = true
    $.mobile.ajaxEnabled=false
    //$("#main-page").show();
    $('div').live('pagehide',function(event, ui){
        if ( ui.nextPage.attr("id") == "wall")
            //ProgramAjaxService.getBooks(wallStartIndex,wallMaxResult, showPosts)
            ;
        else if ( ui.nextPage.attr("id") == "due-reports") {
            dwr.engine.beginBatch()
            ProgramAjaxService.getReports(showReports)
            //showMonthly(0)
            showGraph(0, true)
            //ProgramAjaxService.getMyActivePrograms(showMyPrograms)
            dwr.engine.endBatch()
        }
        else if ( ui.nextPage.attr("id") == "new-programs") 
            ProgramAjaxService.upcomingPrograms(upcomingPrograms)
    })
    var hash = window.location.hash
    if ( hash == '#new-programs' || hash.length == 0 ) {
        dwr.engine.beginBatch()
        ProgramAjaxService.upcomingPrograms(upcomingPrograms)
        ProgramAjaxService.getUser(salam)
        dwr.engine.endBatch()
    }
    else if ( hash == '#due-reports') {
        dwr.engine.beginBatch()
        ProgramAjaxService.getReports(showReports)
        //showMonthly(0)
        showGraph(0, true)
        ProgramAjaxService.getUser(salam)
        dwr.engine.endBatch()
    }
    $(".enroll").live("tap",function(){
        var id = $(this).attr("id").substring(7);
        if(!confirm("Enroll me in this program!")){
            return false;
        }
        $("#program"+id).hide();
        ProgramAjaxService.enroll(id)
    })
    $(".save-report").live("tap",function(){
        saveReport(false);
    })
    $(".submit-report").live("tap", function(){
        saveReport(true);
    })
    $("#postBtn").live("tap",function(){
        saveBook()
        return false
    })
    $("#more").live("tap",function(){
        wallStartIndex += wallMaxResult
        ProgramAjaxService.getBooks(wallStartIndex,wallMaxResult, showPosts)
        return false
    })
    $("#back").live("tap",function(){
        wallStartIndex -= wallMaxResult
        ProgramAjaxService.getBooks(wallStartIndex,wallMaxResult, showPosts)
        return false
    })
    $(".toggleRules").live("tap", toggleRules)
//    $("#searchBtn").live("tap",searchByEmail)
//    $("#joinBtn").live("tap", joinMyNet)
//    $("#inviteBtn").live("tap", joinZytoon)
});
var showReports = function(reports_){
    reportCount = 0
    reports = reports_;
    handleReports();
}
function handleReports(){
    if ( reportCount < reports.length ) {
        displayReport(reports[reportCount])
    } else {
        $("#report").html('<p>No reports due at this time!</p>').trigger('create')
    }
}
function displayReport(report) {
    var html = ''
    html = '<h3>'+ report.name +'</h3>'
    +'<p> Date: '+ report.date + '</p><p> Day ' + report.day + ' of ' + report.days +'</p>'
    //+'<p style="font-size:75%"> Please select whose objectives where met </p>'
    + (report.category == 'food' ? getFoodChks(report) : (report.category == 'one' ? getOneChks() : getSalahChks(report)))
    //+'<a href="javascript:void()" data-role="button" data-icon="check" style="width: 200px" data-theme="b" data-inline="true" id="report-'+ report.reportId +'" class="save-report">Save</a>'
    +'<a href="javascript:void()" data-role="button" data-icon="check" style="width: 200px" data-theme="e" data-inline="true" id="report-'+ report.reportId +'" class="submit-report">Submit</a>'
    +'<p style="font-size:75%"> You cannot change after Submit </p>'
    +'<p> <a class="toggleRules"> Program rules </a> </p> '
    +'<div class="rules" style="display:block">' + report.description + '</div>'
    $("#report").html('<div data-role="collapsible" style="font-size:80%" data-collapsed="'+ isReportTime(report) +'" data-theme="e" data-content-theme="a" id="report-stuff' + report.id +'">'+html+'</div>').trigger('create')
}
function isReportTime(report) {
    if ( new Date().toString('EEE, MMM dd').substr(4, 6) != report.date.substr(5,7) )
        return false
    else if ( new Date().getHours() >= 19 )
        return false
    return true
}
var show = true
var toggleRules = function() {
    if ( !show ) 
        $(".rules").show()
    else 
        $(".rules").hide()
    show = show ? false : true
}
function getSalahChks(r) {
    var html = '<div data-role="fieldcontain" class="salahChks" style="width:450px">'
    +'<fieldset data-role="controlgroup" data-type="horizontal" data-role="fieldcontain">'
    +'    <input type="checkbox" name="checkbox-1" id="checkbox-1" data-theme="c" class="custom fajr" ' + (r.fajr == 1 ? "checked" : "") +'/>'
    +'    <label for="checkbox-1">Fajr</label>'
    +'    <input type="checkbox" name="checkbox-2" id="checkbox-2" data-theme="c" class="custom zuhr" ' + (r.zuhr == 1 ? "checked" : "") +'/>'
    +'    <label for="checkbox-2">Dhuhr</label>'
    +'    <input type="checkbox" name="checkbox-3" id="checkbox-3" data-theme="c" class="custom asr" ' + (r.asr == 1 ? "checked" : "") +'/>'
    +'    <label for="checkbox-3">Asr</label>'
    +'    <input type="checkbox" name="checkbox-4" id="checkbox-4" data-theme="c" class="custom magrib" ' + (r.magrib == 1 ? "checked" : "") +'/>'
    +'    <label for="checkbox-4">Magrib</label>'
    +'    <input type="checkbox" name="checkbox-5" id="checkbox-5" data-theme="c" class="custom isha" ' + (r.isha == 1 ? "checked" : "") +'/>'
    +'    <label for="checkbox-5">Isha</label>'
    +'</fieldset>'
    +'</div>';
    return html;
}
function getFoodChks(r) {
    var html = '<div data-role="fieldcontain" class="salahChks" style="width:450px">'
    +'<fieldset data-role="controlgroup" data-type="horizontal" data-role="fieldcontain">'
    +'    <input type="checkbox" name="checkbox-1" id="checkbox-1" data-theme="c" class="custom fajr" ' + (r.fajr == 1 ? "checked" : "") +'/>'
    +'    <label for="checkbox-1">Breakfast</label>'
    +'    <input type="checkbox" name="checkbox-2" id="checkbox-2" data-theme="c" class="custom zuhr" ' + (r.zuhr == 1 ? "checked" : "") +'/>'
    +'    <label for="checkbox-2">Lunch</label>'
    +'    <input type="checkbox" name="checkbox-3" id="checkbox-3" data-theme="c" class="custom asr" ' + (r.asr == 1 ? "checked" : "") +'/>'
    +'    <label for="checkbox-3">Dinner</label>'
    +'</fieldset>'
    +'</div>';
    return html;
}
function getOneChks(r) {
    var html = ''
    +'<fieldset data-role="controlgroup" data-type="vertical">'
    +'    <input type="radio" name="checkbox-1" id="checkbox-1" data-theme="c" class="custom fajr" />'
    +'    <label for="checkbox-1">Yes, followed all the rules</label>'
    +'    <input type="radio" name="checkbox-1" id="checkbox-2" data-theme="c" checked class="custom zuhr" />'
    +'    <label for="checkbox-2">No, missed few or all the rules</label>'
    +'</fieldset>'
    +'';
    return html;
}
function saveReport(iS) {
    var report = reports[reportCount]
    
    var flag = false
    if ( ++reportCount < reports.length ) {flag = true; handleReports() }
    
    var rId = report.reportId;
    var f = $("#checkbox-1").is(':checked') == true ? 1 : 0;
    var z = $("#checkbox-2").is(':checked') == true ? 1 : 0;
    var a = 0
    if ( report.category == 'food') {
        a = $("#checkbox-3").is(':checked') == true ? 1 : 0;
    }
    var m = 0
    var i = 0
    if ( report.category == 'salah') {
        m = $("#checkbox-4").is(':checked') == true ? 1 : 0;
        i = $("#checkbox-5").is(':checked') == true ? 1 : 0;
    }
    
    //var countSelected = f+z+a+m+i;

    //    if(iS /**&& countSelected < 5 **/){
    //        if(!confirm("Please submit!")){
    //            return ;
    //        }
    //    }
    //reportCount++;
    //TODO: Where is <param>r</param> coming from?
    //$("#report").html('<p>submitting..').trigger('create')
    
    if ( report.category == 'salah') {
        ProgramAjaxService.submitSalah(rId, f,z,a,m,i,iS,function(r){
            if (!flag) {
            if ( r == "p" || r == "f") {
                var r1 = r == "p" ? "Congratulations, you have successfully completed this Program, please check email for detail report" :
                "Please check email for detail report.";
                alert(r1);
                $("#report").html('<p>No reports due at this time!</p>').trigger('create')
                dwr.engine.beginBatch()
                ProgramAjaxService.getReports(showReports)
                //showMonthly(0)
                showGraph(0, true)
                dwr.engine.endBatch()
            } else if ( r == "d") { 
                dwr.engine.beginBatch()
                ProgramAjaxService.getReports(showReports)
                //showMonthly(0)
                showGraph(0, true)
                dwr.engine.endBatch()
            } else {
                //showMonthly(0)
                showGraph(0, false)
                $("#report").html('<p>No reports due at this time!</p>').trigger('create')
            }
            }
        })
    } else if ( report.category == 'food') {
        ProgramAjaxService.submitFood(rId, f,z,a,iS,function(r){
            if (!flag) {
            if ( r == "p" || r == "f") {
                var r1 = r == "p" ? "Congratulations, you have successfully completed this Program, please check email for detail report" :
                "Please check email for detail report.";
                alert(r1);
                $("#report").html('<p>No reports due at this time!</p>').trigger('create')
                dwr.engine.beginBatch()
                ProgramAjaxService.getReports(showReports)
                //showMonthly(0)
                showGraph(0, true)
                dwr.engine.endBatch()
            } else if ( r == "d") { 
                dwr.engine.beginBatch()
                ProgramAjaxService.getReports(showReports)
                //showMonthly(0)
                showGraph(0, true)
                dwr.engine.endBatch()
            } else {
                //showMonthly(0)
                showGraph(0, false)
                $("#report").html('<p>No reports due at this time!</p>').trigger('create')
            }
            }
        })
        
    }
    else if ( report.category == 'one') {
        ProgramAjaxService.submitOneRuleProgram(rId, f,function(r){
            if (!flag) {
            if ( r == "p" || r == "f") {
                var r1 = r == "p" ? "Congratulations, you have successfully completed this Program, please check email for detail report" :
                "Please check email for detail report.";
                alert(r1);
                $("#report").html('<p>No reports due at this time!</p>').trigger('create')
                dwr.engine.beginBatch()
                ProgramAjaxService.getReports(showReports)
                //showMonthly(0)
                showGraph(0, true)
                dwr.engine.endBatch()
            } else if ( r == "d") { 
                dwr.engine.beginBatch()
                ProgramAjaxService.getReports(showReports)
                //showMonthly(0)
                showGraph(0, true)
                dwr.engine.endBatch()
            } else {
                //showMonthly(0)
                showGraph(0, false)
                $("#report").html('<p>No reports due at this time!</p>').trigger('create')
            }
            }
        })
        
    }
}
var showMyPrograms = function(programs) {
//showProgramsInDiv("active-programs", programs) // TODO
}
var upcomingPrograms = function (programs) {
    showProgramsInDiv("upcoming-programs", programs)
}
var salam = function(name){
    var hours = new Date().getHours()
    var msg = ''
    if ( hours < 10 ) {
        msg = "Good Morning"
    }
    else if (hours > 11 && hours < 15) {
        msg = "Good Afternoon"
    }
    else if (hours > 16 && hours < 20 )
    {
        msg = "Good Evening"
    }
    $(".salam").html(" - Salaam, " + msg + " "+ name)
}
function showProgramsInDiv(div, programs) {
    var first = true
    var html = ''
    
    $.each(programs,function(){
        var theme = 'b'
        if ( this.programStructure.name.indexOf('Month') != -1 || this.programStructure.name.indexOf('Eat') != -1)
            theme = 'e'
        if (this.programStructure.name.indexOf('Year') != -1) 
            theme = 'c'
        var current = ''
        var his = 'Previous Enrollments: '
        var history = this.history
        if (  history.times > 0 ) {
            his += history.times 
            if ( history.current ) {
                his += ', Currently enrolled till ' + history.end
            //theme = 'c'
            //current = ' - Currently Enrolled'
            } else {
                his += ', Most recent '
                + history.start 
                + ' - '
                + history.end
            }
        } else {
            his += 'Never'
        }
        html += '<div data-role="collapsible" style="font-size:80%" data-collapsed="'+ first +'" data-theme="'+theme+'" data-content-theme="a" id="program'+this.id+'">'
        +'<h3>'+ this.programStructure.name +' - ' + this.formatedStartDate + current + '</h3>'
        +'<p>'+ this.programStructure.description +'</p>'
        +'<p>' + his + '</p>' 
        +'<p> Current Offering Dates: ' + this.formatedStartDate +' - ' + this.endDate + '</p>'
        +'<a href="javascript:void()" data-role="button" data-icon="plus" style="width: 250px;" data-theme="b" id="enroll-'+ this.id +'" class="enroll" >Join this program</a>'
        +'</div>'
        first = true
    })
    if ( html.length > 0 ) {
        html = '<div data-role="collapsible-set">' + html + '</div>'
        $("#" + div).html(html).trigger('create')
    } else {
        $("#" + div).html("<p>No programs found!</p>").trigger('create')
    }
}
// posts
var showPosts = function (posts){
    var html = ''
    $.each(posts,function(){
        //var url = this.url.startswith("http://") ? this.url : "http://"+this.url
        html += '<a href="'+this.url+'" rel="external" target="_blanck">'+ this.title +'</a>'
        + '<p class="stream-b"> By ' + this.userProfile.firstName +', ' + this.formatedPostDate + '</p>'
    })
    if ( 0 < wallStartIndex) {
        html += '<a data-role="button" href="javascript:void()" data-icon="arrow-l" data-inline="true" style="width:150px" id="back">Prev</a>'
    }
    if ( posts.length == wallMaxResult) {
        html += '<a data-role="button" href="javascript:void()" data-icon="arrow-r" data-inline="true" style="width:150px" id="more">Next</a>'
    }
    $("#streamDiv").html('<div id="stream-'+ (++sCount) +'"></div>')
    $("#stream-"+(sCount)).html(html).trigger('create')
}
function saveBook() {
    var t = $("#title").val()
    var u = $("#url").val()
    if ( t.length > 0 && u.length > 0 ) {
        var url = u.toString().valueOf().indexOf("http", 0);
        var appendUrl = "http://";
        if(url != 0){
            u = appendUrl+u;
        }
        ProgramAjaxService.saveBook(t,u,wallStartIndex,wallMaxResult,function(posts) {
            clearBook()
            showPosts(posts)
            alert("Thanks for sharing!")
        })
    } else {
        alert("Title and Url are required fields")
    }
}
function clearBook(){
    $("#title").val("")
    $("#url").val("")
}
// graphs
var m_p = 0
var salah = true
$(document).ready(function() {
    $("#plus").live("tap", function() {
        salah = true
        m_p = 0
        showGraph(0, false)
    })
    $("#minus").live("tap", function() {
        salah = false
        m_p = 0
        showFoodGraph(0, false)
    })
    $("#m_p_p").live("tap", function() {
        if ( salah ) 
            showGraph(--m_p, false)
        else
            showFoodGraph(--m_p, false)
    })
    $("#m_p_n").live("tap", function() {
        if ( salah )
            showGraph(++m_p, false)
        else
            showFoodGraph(++m_p, false)
    })
})
function showGraph(month, flag){
    $("#chartL").html('')
    $("#chartF").html('')
    if ( !flag) dwr.engine.beginBatch()
    showMonthly(month)
    showFamily(month)
    if ( !flag) dwr.engine.endBatch()
}
function showFoodGraph(month, flag){
    $("#chartL").html('')
    $("#chartF").html('')
    if ( !flag) dwr.engine.beginBatch()
    showFoodMonthly(month)
    showFoodFamily(month)
    if ( !flag) dwr.engine.endBatch()
}
function showLifetime() {
    $("#chartL").html('')
    if ($("#radio-choice-1").attr("checked") == "checked" || $("#radio-choice-3").attr("checked") == "checked") {
        $("#radio-choice-1").attr("checked",false).checkboxradio("refresh")
        $("#radio-choice-2").attr("checked",true).checkboxradio("refresh")
        $("#radio-choice-3").attr("checked",false).checkboxradio("refresh")
    }
    ProgramAjaxService.getHistory(function(data) {
        if ( data.length == 0 ) return
        var all = new Array()
        var count = 0
        var avg = 0
        $.each(data,function(){
            all[count++] = [ this[0] + "-"+ (this[1] + 1) + "-"+ this[2] , this[3] ]
            avg += this[3]
        })
        var plot1 = $.jqplot("chartL", [all], {
            seriesColors: ["rgba(78, 135, 194, 0.7)", "rgb(211, 235, 59)"],
            title: 'Ontime',
            highlighter: {
                show: true,
                sizeAdjust: 1,
                tooltipOffset: 9
            },
            grid: {
                background: 'rgba(57,57,57,0.0)',
                drawBorder: false,
                shadow: false,
                gridLineColor: '#666666',
                gridLineWidth: 2
            },
            legend: {
                show: true,
                placement: 'outside',
                location: 'se'
            },
            seriesDefaults: {
                rendererOptions: {
                    smooth: true,
                    animation: {
                        show: true
                    }
                },
                showMarker: false
            },
            series: [
            {
                fill: true,
                label: 'Avg ' + parseFloat(avg/count).toFixed(2)
            }
            ],
            axesDefaults: {
                rendererOptions: {
                    baselineWidth:1.5,
                    baselineColor: '#444444',
                    drawBaseline: false
                }
            },
            axes: {
                xaxis: {
                    renderer: $.jqplot.DateAxisRenderer,
                    tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                    tickOptions: {
                        formatString: "%b %e",
                        angle: -30,
                        textColor: '#dddddd'
                    },
                    //min: "2011-08-01",
                    //max: "2012-09-30",
                    tickInterval: "3 months",
                    drawMajorGridlines: true
                },
                yaxis: {
                    //renderer: $.jqplot.PyramidAxisRenderer,
                    pad: 0,
                    rendererOptions: {
                        minorTicks: 0
                    },
                    tickOptions: {
                        formatString: "%'d",
                        showMark: true
                    },
                    min:0,
                    max:5
                }
            },
            cursor:{
                show: true,
                zoom: true
            } 
        });
        $('.jqplot-highlighter-tooltip').addClass('ui-corner-all')
        $("#month_p").hide()
    })
}
function showMonthly(month) {
    m_p = month
    ProgramAjaxService.getSalahMonthly(month, function(hs) {
        var missed = ( hs.fajr + hs.dhuhr + hs.asr + hs.magrib + hs.ishaa )
        var total = (-missed + hs.prayed) / 5
        var line1 = [hs.fajr, hs.dhuhr, hs.asr, hs.magrib, hs.ishaa, missed];
        var line2 = [total + hs.fajr, total + hs.dhuhr, total + hs.asr, total + hs.magrib, total + hs.ishaa, hs.prayed];
        var ticks = ['Fajr', 'Dhuhr', 'Asr', 'Maghrib', 'Ishaa', 'Total']
        var title = hs.monthName + ' ' + hs.year + ' <span style="font-size:50%">My Salah</span>'
        drawPersonal(line2, line1, ticks, title)
    })
}
function showFoodMonthly(month) {
    //m_p = month
    ProgramAjaxService.getFoodMonthly(month, function(hs) {
        var missed = ( hs.breakfast + hs.lunch + hs.dinner )
        var total = (-missed + hs.points) / 3
        var line1 = [hs.breakfast, hs.lunch, hs.dinner, missed];
        var line2 = [total + hs.breakfast, total + hs.lunch, total + hs.dinner, hs.points];
        var ticks = ['Breakfast', 'Lunch', 'Dinner', 'Total']
        var title = hs.monthName + ' ' + hs.year + ' <span style="font-size:50%">My Eating Adaab</span>'
        drawPersonal(line2, line1, ticks, title)
    })
}
function drawPersonal(line2, line1, ticks, title){
    $.jqplot('chartL', [line2, line1], {
        seriesColors: ["rgba(78, 135, 194, 0.7)", "rgb(192, 136, 64)"],
        title: title,
        grid: {
            drawGridLines: false,
            background: 'rgba(57,57,57,0.0)',
            drawBorder: false,
            shadow: true,
            gridLineColor: '#666',
            gridLineWidth: 0
        },
        seriesDefaults: {
            renderer: $.jqplot.BarRenderer,
            rendererOptions: {
                fillToZero: true
            }
        },
        series:[
        {
            pointLabels:{
                show: true,
                formatString: '%s', 
                labels: line2
            },
            label:'Ontime'
        },
        {
            pointLabels:{
                show: true,
                labels: line1
            },
            label:'Missed'
        }],
        legend: {
            show:true,
            placement: 'outside',
            location: 'se'
        },
        axes: {
            xaxis:{
                renderer:$.jqplot.CategoryAxisRenderer,
                ticks: ticks
            },
            yaxis:{
                padMax:1.3
            }
        }
    });
    $('.jqplot-highlighter-tooltip').addClass('ui-corner-all')
}
function showFamily(month) {
    m_p = month
    //    $("#chartL").html('')
    //    if ($("#radio-choice-1").attr("checked") == "checked" || $("#radio-choice-2").attr("checked") == "checked") {
    //        $("#radio-choice-1").attr("checked",false).checkboxradio("refresh")
    //        $("#radio-choice-2").attr("checked",false).checkboxradio("refresh")
    //        $("#radio-choice-3").attr("checked",true).checkboxradio("refresh")
    //    }
    //    $("#month_p").show()
    ProgramAjaxService.getSalahFamily(month, function(dtos) {
        var line1 = new Array(dtos.length) //[['', 0], ['', 0], ['', 0], ['', 0], ['', 0], ['', 0], ['', 0]];
        var line2 = new Array(dtos.length) //['','','','','','']
        var count = 0
        var title
        if ( dtos.length < 2 ) {
            $("#chartF").html('')
            return
        }
        $.each(dtos, function() {
            var missed = ( this.fajr + this.dhuhr + this.asr + this.magrib + this.ishaa )
            var total1 = (this.prayed * 100) / (-missed + this.prayed)
            var total = parseFloat(total1).toFixed(0)
            line1[count] = [this.user.name.split(" ")[0], total]
            line2[count++] = total + '%'
        })
        title = ' <span style="font-size:50%">Family Salah</span>'
        drawFamily(line1, line2, title)
    })
}
function showFoodFamily(month) {
    m_p = month
    ProgramAjaxService.getFoodFamily(month, function(dtos) {
        var line1 = new Array(dtos.length) //[['', 0], ['', 0], ['', 0], ['', 0], ['', 0], ['', 0], ['', 0]];
        var line2 = new Array(dtos.length) //['','','','','','']
        var count = 0
        var title
        if ( dtos.length < 2 ) {
            $("#chartF").html('')
            return
        }
        $.each(dtos, function() {
            var missed = ( this.breakfast + this.lunch + this.dinner )
            var total1 = (this.points * 100) / (-missed + this.points)
            var total = parseFloat(total1).toFixed(0)
            line1[count] = [this.user.name.split(" ")[0], total]
            line2[count++] = total + '%'
        })
        title = ' <span style="font-size:50%">Family Eating Adaab</span>'
        drawFamily(line1, line2, title)
    })
}
function drawFamily(line1, line2, title) {
    $.jqplot('chartF', [line1], {
        seriesColors: ["rgba(78, 135, 194, 0.7)", "rgb(211, 235, 59)"],
        title: title,
        grid: {
            drawGridLines: false,
            background: 'rgba(57,57,57,0.0)',
            drawBorder: false,
            shadow: true,
            gridLineColor: '#666',
            gridLineWidth: 0
        },
        series:[
        {
            renderer:$.jqplot.BarRenderer,
            pointLabels:{
                show: true,
                labels: line2
            },
            rendererOptions: {
                barWidth: 50,
                fillToZero: true
            }
        }
        ],
        axesDefaults: {
            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
            tickOptions: {
                angle: -30
            }
        },
        axes: {
            xaxis: {
                renderer: $.jqplot.CategoryAxisRenderer
            },
            yaxis: {
                //autoscale:120,
                padMax:1.3,
                max:120,
                min:0
                    
            }
        }
    });
    $('.jqplot-highlighter-tooltip').addClass('ui-corner-all')
}