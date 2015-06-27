<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0059)file:///R:/Workspace/Front-End/FlashVocabulary/recital.html -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="css/basics.css">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	/*global@wordSet json data
	  global@count the number of the current word in the word set
	  global@true_id the current true number of answers
	*/
	function init(){
        $.getJSON("GetWordSetAction", null, getWordSet);
            //document.write(temp[0].sim_trans);
            /*var answers = new Array();
            answers = wordSet[count].trans.split("|");
            for (var i = 0; i <= 3; i++) {
        		document.getElementById("a" + i).innerText = answers[i];
        	}
    		true_id = "a"+wordSet[count].position;
    		window.sessionStorage.count = 0;
    		window.sessionStorage.wordSet = wordSet;*/
    }
    function getWordSet(data){
        var wordSet = data.wordSet;
        var totalCount = wordSet.length - 1;

        setContent(wordSet[0]);

        for (var i = 0; i <= totalCount ; i++) {
            var tempWord = wordSet[i];
            var aId = "word_" + (i+1);
            var divId = "div_w" + (i+1);
            var transId = "trans_w" + (i+1);
            document.getElementById(divId).innerHTML = "<h3>" + tempWord.spell + "</h3><div>"
            +tempWord.usages+"</div><a onclick=\"show_trans(this.id)\" id=\"" + aId 
            + "\"><img src=\"images/close_icon.png\"></a><div style=\"display:none;\" id=\"" + transId
            + "\">" + tempWord.sen_trans + "</div>";
            document.getElementById(divId).style.display = "block";
        }
        if (totalCount < 6) {
            for (var i = totalCount + 1; i < 7; i++) {
                document.getElementById("div_w" + i).style.display = "none";
            }
        }

        initResult(wordSet, totalCount);
        window.sessionStorage.count = 0;
        window.sessionStorage.totalCount = totalCount;
        window.sessionStorage.wordSet = JSON.stringify(wordSet);
    }
    function initResult(wordSet, totalCount){
        var resultData = {setResult:
                        [{"tid":"70", "wid":"1", "isCheck":"0"}, 
                        {"tid":"71", "wid":"1", "isCheck":"0"},
                        {"tid":"70", "wid":"1", "isCheck":"0"},
                        {"tid":"70", "wid":"1", "isCheck":"0"},
                        {"tid":"70", "wid":"1", "isCheck":"0"},
                        {"tid":"70", "wid":"1", "isCheck":"0"},
                        {"tid":"70", "wid":"1", "isCheck":"0"}],
                        setCount: (totalCount + 1)};
        for (var i = 0; i <= totalCount ; i++) {
            resultData.setResult[i].wid = wordSet[i].id;
            resultData.setResult[i].tid = wordSet[i].idOfTodayWordLib;
        }
        window.sessionStorage.resultData = JSON.stringify(resultData);
        var temp = JSON.parse(window.sessionStorage.resultData);
    }
    function setContent(wordToShow){
        document.getElementById("spell").innerText = wordToShow.spell;
        document.getElementById("phonetics").innerText = wordToShow.phonetics;

        document.getElementById("detail_spell").innerText = wordToShow.spell;
        document.getElementById("detail_phonetics").innerText = wordToShow.phonetics;
        
        var options = new Array();
        options = wordToShow.trans.split("|");
        for (var i = 0; i < 4; i++) {
            document.getElementById("a" + i).innerText = options[i];
        }
        
        var c_trans = new Array();
        c_trans = wordToShow.c_trans.split("|");
        document.getElementById("detail_trans").innerHTML = "";
        for (var i = 0; i < c_trans.length - 1; i++) {
            var newLi = document.createElement("li");
            var text = document.createTextNode(c_trans[i]);
            newLi.appendChild(text);
            document.getElementById("detail_trans").appendChild(newLi);
        }
        
        true_id = "a" + wordToShow.position;

        var syn = new Array();
        syn = wordToShow.synonyms.split("|");
        document.getElementById("detail_syn").innerHTML = "";
        for (var i = 0; i < syn.length - 1; i++) {
            var newLi = document.createElement("li");
            var text = document.createTextNode(syn[i]);
            newLi.appendChild(text);
            document.getElementById("detail_syn").appendChild(newLi);
        }

        var anto = new Array();
        anto = wordToShow.antonyms.split("|");
        document.getElementById("detail_anto").innerHTML = "";
        for (var i = 0; i < anto.length - 1; i++) {
            var newLi = document.createElement("li");
            var text = document.createTextNode(anto[i]);
            newLi.appendChild(text);
            document.getElementById("detail_anto").appendChild(newLi);
        }

        var sampleSen = wordToShow.sentences;
        document.getElementById("detail_usage").innerHTML = sampleSen;
    }
    function nextWord(){
        var count = (++window.sessionStorage.count);
        var wordSet = JSON.parse(window.sessionStorage.wordSet);
        var next_word = wordSet[count];
        setContent(next_word);
    }
    function postResult(){
        alert("I'm in function!");

        var setResult = {data:JSON.stringify(initData)};
        $.post("SetCompleteAction", setResult);
    }
</script>
</head>

<body class="uni_background" onload="init()">
<jsp:include  page="include/header.jsp"/>
<div id="answer_div">
	<a class="right_side" onclick="toDetails()" style="display:none;" id="a_next"><img src="images/right-arrow.png"></a>
	<div class="flashcard">
    	<div class="word_label">
			<h1 id="spell">impertinent</h1>
			<h3 id="phonetics">[im'pә:tinәnt]</h3>
		</div>
		<div class="answers">
			<ul style="width:100%;list-style-type:none;text-align:center;padding:0px;font-size:1.2em;">
				<li>
					<a id="a0" onclick="answer_click(this.id)">n. 狂热；放任 vt. 遗弃；放弃</a>
				</li>
				<li>
					<a id="a1" onclick="answer_click(this.id)">n. 狂热；放任 vt. 遗弃；放弃</a>
				</li>
				<li>
					<a id="a2" onclick="answer_click(this.id)">n. 狂热；放任 vt. 遗弃；放弃</a>
				</li>
				<li>
					<a id="a3" onclick="answer_click(this.id)">n. 狂热；放任 vt. 遗弃；放弃</a>
				</li>
			</ul>
		</div>
    </div>
</div>
<div id="detail_div" style="display:none">
	<a id="detail_next" class="right_side" onclick="toNext()"><img src="images/right-arrow.png"></a>
	<div class="flashcard">
    	<div class="word_label">
			<h1 id="detail_spell">abandon</h1>
			<h3 id="detail_phonetics">[ə’bændən]</h3>
		</div>
		<div class="sentence_card" style="margin-top:4%;">
				<h3>Translations</h3>
				<ol id="detail_trans">
					<li>
						<span>n. 狂热；放任 vt. 遗弃；放弃</span>
					</li>
					<li>
						<span>n. 狂热；放任 vt. 遗弃；放弃</span>
					</li>
					<li>
						<span>n. 狂热；放任 vt. 遗弃；放弃</span>
					</li>
				</ol>
			</div>
		<div class="trans_container">
			<div class="trans_card">
				<h3>Synonyms</h3>
				<ol id="detail_syn">
					<li>
						unconstraint, uninhibitedness, unrestraint
					</li>
					<li>
						indulge, surrender, give up
					</li>
					<li>
						abort, drop, repeal, rescind, revoke, call off
					</li>
				</ol>
			</div>
			<div class="trans_card" style="float:right">
				<h3>Antonyms</h3>
				<ol id="detail_anto">
					<li>
						stymie, frustrate, obstruct, thwart, impede, forestall , hinder
					</li>
				</ol>
			</div>
		</div>

		<div class="sentence_card">
			<h2>Usages</h2>
				<ol class="sentence_list" id="detail_usage">
					<li>
					<span>added spices to the stew with complete <strong>abandon</strong></span><br>
					<p>肆无忌惮地向炖菜里面加调料</p>
					</li>
					<li>
					<span>added spices to the stew with complete <strong>abandon</strong></span><br>
					<p>肆无忌惮地向炖菜里面加调料</p>
					</li>
					<li>
					<span>added spices to the stew with complete <strong>abandon</strong></span><br>
					<p>肆无忌惮地向炖菜里面加调料</p>
					</li>
				</ol>
		</div>
    </div>
</div>
<div style="display:none" id="sum_div">
<a class="right_side" onclick="to_next_set()"><img src="images/right-arrow.png"></a>
	<div class="flashcard">
        <div class="trans_container" style="overflow:visible;">
            <div class="review_div_left">
                <div class="review_card" id="div_w1">
                    <h3>abandon</h3>
                    <div>added spices to the stew with complete <strong>abandon</strong></div>
                    <a onclick="show_trans(this.id)" id="word_1"><img src="images/close_icon.png"></a>
                    <div id="trans_w1" style="display:none;">肆无忌惮地向炖菜里面加调料</div>
                </div>
                <div class="review_card" id="div_w3">
                    <h3>abandon</h3>
                    <div>added spices to the stew with complete <strong>abandon</strong></div>
                    <a onclick="show_trans(this.id)" id="word_3"><img src="images/close_icon.png"></a>
                    <div id="trans_w3" style="display:none;">肆无忌惮地向炖菜里面加调料</div>
                </div>
                <div class="review_card" id="div_w5">
                    <h3>abandon</h3>
                    <div>added spices to the stew with complete <strong>abandon</strong></div>
                    <a onclick="show_trans(this.id)" id="word_5"><img src="images/close_icon.png"></a>
                    <div id="trans_w5" style="display:none;">肆无忌惮地向炖菜里面加调料</div>
                </div>
                <div class="review_card" id="div_w7">
                    <h3>abandon</h3>
                    <div>added spices to the stew with complete <strong>abandon</strong></div>
                    <a onclick="show_trans(this.id)" id="word_7"><img src="images/close_icon.png"></a>
                    <div id="trans_w7" style="display:none;">肆无忌惮地向炖菜里面加调料</div>
                </div>
            </div>
            <div class="review_div_left" style="float:right">
                <div class="review_card" id="div_w2">
                    <h3>abandon</h3>
                    <div>added spices to the stew with complete <strong>abandon</strong></div>
                    <a onclick="show_trans(this.id)" id="word_2"><img src="images/close_icon.png"></a>
                    <div id="trans_w2" style="display:none;">肆无忌惮地向炖菜里面加调料</div>
                </div>
                <div class="review_card" id="div_w4">
                    <h3>abandon</h3>
                    <div>added spices to the stew with complete <strong>abandon</strong></div>
                    <a onclick="show_trans(this.id)" id="word_4"><img src="images/close_icon.png"></a>
                    <div id="trans_w4" style="display:none;">肆无忌惮地向炖菜里面加调料</div>
                </div>
                <div class="review_card" id="div_w6">
                    <h3>abandon</h3>
                    <div>added spices to the stew with complete <strong>abandon</strong></div>
                    <a onclick="show_trans(this.id)" id="word_6"><img src="images/close_icon.png"></a>
                    <div id="trans_w6" style="display:none;">肆无忌惮地向炖菜里面加调料</div>
                </div>
            </div>
        </div>
    </div>
</div>
	
    <label style="display:none;" id="a_label">a2</label>
    <script type="text/javascript">
        function to_next_set(){
            var resultData = JSON.parse(window.sessionStorage.resultData);
            var setResult = {data:JSON.stringify(resultData)};
            $.post("SetCompleteAction", setResult);
            window.location.href = "GoReciteAction";
        }

    	function answer_click (choice){
    		choice_id = choice;
    		//correct one->choice becomes green
    		document.getElementById(true_id).style.background = "limegreen";
    		document.getElementById(true_id).style.color = "white";
    		//wrong choice->choice becomes red
    		if (choice != true_id) {
    			document.getElementById(choice).style.background = "red";
    			document.getElementById(choice).style.color = "white";
    		}
            else{

            }
    		//forbid onclick after choosed
    		//made all the wrong choice to show red when hover,
    		//except the choosed one, which is set to show red always
    		for (var i = 0; i <= 3; i++) {
    			var temp = "a"+i;
    			document.getElementById(temp).onclick= function(){ return false; };
				if (temp==true_id||temp==choice) {
					document.getElementById(temp).onmouseover=function(){
					  	return;
					} ;
					document.getElementById(temp).onmouseout=function(){
						return;
					} ;
				}
				else{
					document.getElementById(temp).onmouseover=function(){
					  this.style.backgroundColor="red";
					  this.style.color="white";
					} ;
					document.getElementById(temp).onmouseout=function(){
						this.style.backgroundColor="white";
						this.style.color="black";
					} ;
				}
    		};
    		//show next button
    		document.getElementById("a_next").style.display = "inline";
    	}

    	function toDetails(){
    		//switch answer and detail div
    		document.getElementById("answer_div").style.display = "none";
    		document.getElementById("detail_div").style.display = "block";
            document.getElementById("detail_next").style.display = "block";
    	}

    	function toNext(){
            //set the check status in the result to return
            var resultData = JSON.parse(window.sessionStorage.resultData);
            if (true_id == choice_id) {
                resultData.setResult[window.sessionStorage.count].isCheck = 1;
                window.sessionStorage.resultData = JSON.stringify(resultData);
            }
    		//restore the init visual style of choice cards
            var count = window.sessionStorage.count;
            var totalCount = window.sessionStorage.totalCount;
    		document.getElementById(true_id).style.background = "white";
    		document.getElementById(true_id).style.color = "black";
    		document.getElementById(choice_id).style.background = "white";
    		document.getElementById(choice_id).style.color = "black";
    		//is all words in the word set recited
    		if (count >= totalCount)//Goto the result div
    		{
    			document.getElementById("answer_div").style.display = "none";
    			document.getElementById("detail_div").style.display = "none";
    			document.getElementById("sum_div").style.display = "block";
    			document.getElementById("detail_next").style.display="none";
    			document.getElementById("a_next").style.display="none";
                return;
    		}
    		//switch answer and detail div
    		document.getElementById("answer_div").style.display = "block";
    		document.getElementById("detail_div").style.display = "none";
    		//get new translation choices and write to the choice cards
            nextWord();
    		//restore all visual styles of choice cards
    		uncertain_card();
    		document.getElementById("a_next").style.display="none";

    	}

    	function uncertain_card(){
    		for (var i = 0; i <= 3; i++) {
    			var temp = "a"+i;
    			document.getElementById(temp).onclick= function(){
    				answer_click(this.id);
    			};
    			document.getElementById(temp).onmouseover=function(){
					  this.style.backgroundColor="limegreen";
					  this.style.color="white";
					} ;
				document.getElementById(temp).onmouseout=function(){
					this.style.backgroundColor="white";
					this.style.color="black";
				} ;	
    		}
    	}

    	function show_trans(id){
	        var number = id.split("_")[1];
	        var target = "trans_w" + number;
	        document.getElementById(target).style.display = "block";
    	}
    </script>
</body></html>