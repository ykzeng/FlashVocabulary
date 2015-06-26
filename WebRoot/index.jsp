<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0059)file:///R:/Workspace/Front-End/FlashVocabulary/recital.html -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>FlashVocabulary-Boost your vocabulary online!</title>
<link rel="stylesheet" type="text/css" href="css/basics.css">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
    function init(){
        $.getJSON("RandomFlashCardAction", null, getJson);
        document.getElementById("progress_bar").style.width= 100*parseInt(document.getElementById("cLF").innerText)/parseInt(document.getElementById("cLC").innerText) + "%";
    }

    function getJson(data){
        var flashCards = data.RFlashCard;
        window.sessionStorage.flashCards = JSON.stringify(flashCards);
        //alert(flashCards[0].word);
        //var test = JSON.parse(sessionStorage.flashCards);
        //alert(test[0].word);
        window.sessionStorage.count = 0;
        var initCard = flashCards[0];
        document.getElementById("spell").innerText = initCard.word;
        document.getElementById("phonetics").innerText = initCard.phonetics;
        var trans = new Array();
        trans = initCard.trans.split("|");
        for (var i = 0; i < trans.length; i++) {
            if (trans[i] != "") {
            var newLi = document.createElement("li");
                    var text = document.createTextNode(trans[i]);
                    newLi.appendChild(text);
                    document.getElementById("transList").appendChild(newLi);
                /*document.getElementById("a"+i).innerText = trans[i];
                var newA = document.createElement("a");
                var text = document.createTextNode(trans[i]);
                newA.appendChild(text);
                document.getElementById("transList").appendChild(newA);*/
            }
        }
    }
    function lastCard(){
        if (sessionStorage.count > 0) {
            var currentCount = (--sessionStorage.count);
            var next_card = (JSON.parse(sessionStorage.flashCards))[currentCount];
            document.getElementById("spell").innerText = next_card.word;
            document.getElementById("phonetics").innerText = next_card.phonetics;
            document.getElementById("transList").innerHTML="";
            var trans = new Array();
            trans = next_card.trans.split("|");
            for (var i = 0; i < trans.length; i++) {
                if (trans[i] != "") {
                    var newLi = document.createElement("li");
                    var text = document.createTextNode(trans[i]);
                    newLi.appendChild(text);
                    document.getElementById("transList").appendChild(newLi);
                }
            }
        }
    }
    function nextCard(){
        if (sessionStorage.count < 9) {
            sessionStorage.count++;
            var currentCount = sessionStorage.count;
            var next_card = (JSON.parse(sessionStorage.flashCards))[currentCount];
            document.getElementById("spell").innerText = next_card.word;
            document.getElementById("phonetics").innerText = next_card.phonetics;
            document.getElementById("transList").innerHTML="";
            var trans = new Array();
            trans = next_card.trans.split("|");
            for (var i = 0; i < trans.length; i++) {
                if (trans[i] != "") {
                    var newLi = document.createElement("li");
                    var text = document.createTextNode(trans[i]);
                    newLi.appendChild(text);
                    document.getElementById("transList").appendChild(newLi);
                }
            }
        }
    }
</script>
</head>

<body class="uni_background" onload="init()">
<jsp:include  page="include/header.jsp"/>
<a id="to_recite" href="GoReciteAction"><img src="images/right-arrow.png"></a>
    <div class="left_card">
    <a class="right_side" style="right:-7px;top:65%;" onclick="nextCard()"><img src="images/rs_arrow.png" style="width:60%;"></a>
    <a class="left_side" onclick="lastCard()"><img src="images/ls_arrow.png" style="width:60%;"></a>
        <div class="lib_status_card">
            <h3 style="color:#707070;text-align:center">FLASHCARD</h3>
        </div>
        <div class="today_status_card" style="text-align:center;">
            <h1 style="margin-top:0px;color:black;margin-bottom:0px;" id="spell">abandon</h1>
            <h4 style="margin-top:0px;" id="phonetics">[im'pә:tinәnt]</h4>
            <ol class="left_card_list" id="transList">
                
            </ol>
        </div>
    </div>
    <div class="status_card">
        <div class="lib_status_card">
            <h3 style="color:#707070;">${currentLibname}</h3>
            <h3 style="font-weight:normal">WILL BE FINISHED</h3>
            <h3 style="font-weight:normal;text-align:right;margin-right:8px;">IN <span style="color:#2dbe60">${dayToFinish}</span> DAYS</h3>
            <p style="font-size:16px;">
                FINISHED <strong style="color:#2dbe60">${currentLibFinished}</strong> / ${currentLibCount}
            </p>
            <div class="progress_back">
                <div id="progress_bar" style="width:0%;"></div>
            </div>
        </div>
        <div class="today_status_card">
            <h3 style="color:#707070;">TODAY</h3>
            <h3 style="font-weight:normal;">TOTAL <span style="color:#2dbe60">${todayCount}</span></h3>
            <h3 style="font-weight:normal;text-align:right;margin-right:16px;"><span style="color:red;">${todayNoFinished}</span> TO GO</h3>
        </div>
    </div>
    <label id="cLC" style="display:none;">${currentLibCount}</label>
    <label id="cLF" style="display:none;">${currentLibFinished}</label>

    <script type="text/javascript">
        function test(){
            var temp = document.getElementById("try_strong").innerText;
            alert(temp);
        }
    </script>
</body></html>
