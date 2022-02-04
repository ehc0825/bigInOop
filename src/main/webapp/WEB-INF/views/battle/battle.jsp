<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function() {
	var Mdelay= 10-$('#MatkSpeed').val();
	var Matk=$('#Matk').val();
	var playersheeld=$('#playersheeld').val();
	var playerdex = $('#playerdex').val()/100;
	if(Mdelay<0)
		{
		Mdelay=0;
		}
	 setInterval(function() {
		var dmg=Matk-playersheeld;
		var now_hp=$('#playernowHp').val()-dmg;
		let reHtml ="";
		let reHtml2 ="";
		if($('#playernowHp').val()>=0)
		{
			var playernowHp = $('#playernowHp').val();
			var monsternowHp = $('#monsternowHp').val();
			if(Math.random()<playerdex)
				{
			
				reHtml +="<input id='playernowHp' type='number' value='"+playernowHp+"' readonly='readonly'>";
				 reHtml2 +="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;플레이어가 공격을 회피했습니다.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				 document.getElementById("playerhpstate").innerHTML ="";
				 document.getElementById("playerhpstate").innerHTML =reHtml;
				 document.getElementById("resultmsg").innerHTML ="";
				 document.getElementById("resultmsg").innerHTML =reHtml2;
				}
			else
				{
			
				reHtml +="<input id='playernowHp' type='number' value='"+now_hp+"' readonly='readonly'>";
				 reHtml2 +="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;플레이어는 공격받았습니다.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				 document.getElementById("playerhpstate").innerHTML ="";
				 document.getElementById("playerhpstate").innerHTML =reHtml;
				 document.getElementById("resultmsg").innerHTML ="";
				 document.getElementById("resultmsg").innerHTML =reHtml2;
				}
			 
			 test(playernowHp,monsternowHp);
			
		}
		else
			{
			 reHtml2 +="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;전투종료&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			 document.getElementById("resultmsg").innerHTML ="";
			 document.getElementById("resultmsg").innerHTML =reHtml2;
			 var playernowHp = $('#playernowHp').val();
				var monsternowHp = $('#monsternowHp').val();
				test(playernowHp,monsternowHp);
			}
	 }, Mdelay*1000);
});

//일반공격
$(document).on("click",".attack",function() {
	var delay= 10-$('#atkSpeed').val();
	if(delay<0)
		{
		delay=0;
		}
	 	const target = document.getElementById('attack');
	 	 target.disabled = true;
	setTimeout(function() { 
		const target = document.getElementById('attack');
		 target.disabled = false;
	}, delay*1000);
	let playernowHp = $('#playernowHp').val();
	let playernowMp = $('#playernowMp').val();
	let monsternowHp = $('#monsternowHp').val();
	let monsternowMp = $('#monsternowMp').val();
	let monsterNo = $('#monsterNo').val();
	let playerNo = $('#playerNo').val();
	let battleNo = $('#battleNo').val();
	$.ajax({
		url : 'attack.do',
		type : 'post',
		data : {
			playernowHp :playernowHp,
			playernowMp : playernowMp,
			monsternowHp :monsternowHp,
			monsternowMp : monsternowMp,
			monsterNo : monsterNo,
			playerNo : playerNo,
			battleNo : battleNo
	},
	success : function(result) {
		attach(result);
		var playernowHp = $('#playernowHp').val();
		var monsternowHp = $('#monsternowHp').val();
		test(playernowHp,monsternowHp);
	},
	error : function() {
		alert('서버 에러');
	}
});
});
//힐
$(document).on("click",".heal",function() {
	let playernowHp = $('#playernowHp').val();
	let playernowMp = $('#playernowMp').val();
	let monsternowHp = $('#monsternowHp').val();
	let monsternowMp = $('#monsternowMp').val();
	let monsterNo = $('#monsterNo').val();
	let playerNo = $('#playerNo').val();
	let battleNo = $('#battleNo').val();
	$.ajax({
		url : 'heal.do',
		type : 'post',
		data : {
			playernowHp :playernowHp,
			playernowMp : playernowMp,
			monsternowHp :monsternowHp,
			monsternowMp : monsternowMp,
			monsterNo : monsterNo,
			playerNo : playerNo,
			battleNo : battleNo
	},
	success : function(result) {
		attach(result);
		var playernowHp = $('#playernowHp').val();
		var monsternowHp = $('#monsternowHp').val();
		test(playernowHp,monsternowHp);
	},
	error : function() {
		alert('서버 에러');
	}
});
});
//광폭화
$(document).on("click",".steam",function() {
	let playernowHp = $('#playernowHp').val();
	let playernowMp = $('#playernowMp').val();
	let monsternowHp = $('#monsternowHp').val();
	let monsternowMp = $('#monsternowMp').val();
	let monsterNo = $('#monsterNo').val();
	let playerNo = $('#playerNo').val();
	let battleNo = $('#battleNo').val();

	const target = document.getElementById('steam');
		target.disabled = true;
		setTimeout(function() {
			const target = document.getElementById('steam');
			target.disabled = false;
		}, 5000);
		$.ajax({
			url : 'steam.do',
			type : 'post',
			data : {
				playernowHp : playernowHp,
				playernowMp : playernowMp,
				monsternowHp : monsternowHp,
				monsternowMp : monsternowMp,
				monsterNo : monsterNo,
				playerNo : playerNo,
				battleNo : battleNo
			},
			success : function(result) {
				attach(result);
				var playernowHp = $('#playernowHp').val();
				var monsternowHp = $('#monsternowHp').val();
				test(playernowHp,monsternowHp);
			},
			error : function() {
				alert('서버 에러');
			}
		});
	});
	//붙이기 공통모듈
	function attach(result) {
		let reHtml = "";
		if (result == null) {
			reHtml += "<tr><td><span class='text-muted'>오류입니다</span></td></tr>";
		} else {
			reHtml += "<tr>";
			reHtml += "<td>";
			reHtml += "<input type='hidden' value='"+result.player.playerNo+"' id='playerNo'>";
			reHtml += "<input type='hidden'value='"+result.battle.battleNo+"' id='battleNo'>";
			reHtml += "<input type='hidden' value='"+result.monster.monsterNo+"' id='monsterNo'>";
			reHtml += "<pre id='playerhpstate'><input id='playernowHp' type='number' value='"+result.player.nowHp+"' readonly='readonly'></pre>/"
					+ result.player.hp + "<br>";
			reHtml += "<pre><input id='playernowMp' type='number' value='"+result.player.nowMp+"' readonly='readonly'></pre>/"
					+ result.player.mp + "<br>";
			reHtml += "</td>";
			reHtml += "<td id='resultmsg'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ result.msg
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
			reHtml += "<td>";
			reHtml += "<pre><input id='monsternowHp' type='number' value='"+result.monster.nowHp+"' readonly='readonly'></pre>/"
					+ result.monster.hp + "<br>";
			reHtml += "<pre><input id='monsternowMp' type='number' value='"+result.monster.nowMp+"' readonly='readonly'></pre>/"
					+ result.monster.mp + "<br>";
			reHtml += "</td>";
			reHtml += "</tr>";
		}
		$("#statusT").html(reHtml);
	}
	function test(playernowHp,monsternowHp) {
		if(playernowHp<=0)
			{
			alert("패배하였습니다");
			location.href="lose.do";
			}
		if(monsternowHp<=0)
		{
			alert("승리하였습니다");
			location.href="win.do";
		}
		
	}
	//guard
$(document).on("click",".guard",function() {
	const target = document.getElementById('guard');
	target.disabled = true;
	setTimeout(function() {
		const target = document.getElementById('guard');
		target.disabled = false;
	}, 5000);
	let playernowHp = $('#playernowHp').val();
	let playernowMp = $('#playernowMp').val();
	let monsternowHp = $('#monsternowHp').val();
	let monsternowMp = $('#monsternowMp').val();
	let monsterNo = $('#monsterNo').val();
	let playerNo = $('#playerNo').val();
	let battleNo = $('#battleNo').val();
	$.ajax({
		url : 'guard.do',
		type : 'post',
		data : {
			playernowHp :playernowHp,
			playernowMp : playernowMp,
			monsternowHp :monsternowHp,
			monsternowMp : monsternowMp,
			monsterNo : monsterNo,
			playerNo : playerNo,
			battleNo : battleNo
	},
	success : function(result) {
		attach(result);
		var playernowHp = $('#playernowHp').val();
		var monsternowHp = $('#monsternowHp').val();
		test(playernowHp,monsternowHp);
	},
	error : function() {
		alert('서버 에러');
	}
});
});
//invincible
$(document).on("click",".invincible",function() {
	const target = document.getElementById('invincible');
	target.disabled = true;
	setTimeout(function() {
		const target = document.getElementById('invincible');
		target.disabled = false;
	}, 10000);
	let playernowHp = $('#playernowHp').val();
	let playernowMp = $('#playernowMp').val();
	let monsternowHp = $('#monsternowHp').val();
	let monsternowMp = $('#monsternowMp').val();
	let monsterNo = $('#monsterNo').val();
	let playerNo = $('#playerNo').val();
	let battleNo = $('#battleNo').val();
	$.ajax({
		url : 'invincible.do',
		type : 'post',
		data : {
			playernowHp :playernowHp,
			playernowMp : playernowMp,
			monsternowHp :monsternowHp,
			monsternowMp : monsternowMp,
			monsterNo : monsterNo,
			playerNo : playerNo,
			battleNo : battleNo
	},
	success : function(result) {
		attach(result);
		var playernowHp = $('#playernowHp').val();
		var monsternowHp = $('#monsternowHp').val();
		test(playernowHp,monsternowHp);
	},
	error : function() {
		alert('서버 에러');
	}
});
});

//anger
$(document).on("click",".anger",function() {
	const target = document.getElementById('anger');
	target.disabled = true;
	setTimeout(function() {
		const target = document.getElementById('anger');
		target.disabled = false;
	}, 5000);
	let playernowHp = $('#playernowHp').val();
	let playernowMp = $('#playernowMp').val();
	let monsternowHp = $('#monsternowHp').val();
	let monsternowMp = $('#monsternowMp').val();
	let monsterNo = $('#monsterNo').val();
	let playerNo = $('#playerNo').val();
	let battleNo = $('#battleNo').val();
	$.ajax({
		url : 'anger.do',
		type : 'post',
		data : {
			playernowHp :playernowHp,
			playernowMp : playernowMp,
			monsternowHp :monsternowHp,
			monsternowMp : monsternowMp,
			monsterNo : monsterNo,
			playerNo : playerNo,
			battleNo : battleNo
	},
	success : function(result) {
		attach(result);
		var playernowHp = $('#playernowHp').val();
		var monsternowHp = $('#monsternowHp').val();
		test(playernowHp,monsternowHp);
	},
	error : function() {
		alert('서버 에러');
	}
});
});

//frenzy
$(document).on("click",".frenzy",function() {
	const target = document.getElementById('frenzy');
	target.disabled = true;
	setTimeout(function() {
		const target = document.getElementById('frenzy');
		target.disabled = false;
	}, 60000);
	let playernowHp = $('#playernowHp').val();
	let playernowMp = $('#playernowMp').val();
	let monsternowHp = $('#monsternowHp').val();
	let monsternowMp = $('#monsternowMp').val();
	let monsterNo = $('#monsterNo').val();
	let playerNo = $('#playerNo').val();
	let battleNo = $('#battleNo').val();
	$.ajax({
		url : 'frenzy.do',
		type : 'post',
		data : {
			playernowHp :playernowHp,
			playernowMp : playernowMp,
			monsternowHp :monsternowHp,
			monsternowMp : monsternowMp,
			monsterNo : monsterNo,
			playerNo : playerNo,
			battleNo : battleNo
	},
	success : function(result) {
		attach(result);
		var playernowHp = $('#playernowHp').val();
		var monsternowHp = $('#monsternowHp').val();
		test(playernowHp,monsternowHp);
	},
	error : function() {
		alert('서버 에러');
	}
});
});
//elusion
$(document).on("click",".elusion",function() {
	const target = document.getElementById('elusion');
	target.disabled = true;
	setTimeout(function() {
		const target = document.getElementById('elusion');
		target.disabled = false;
	}, 5000);
	let playernowHp = $('#playernowHp').val();
	let playernowMp = $('#playernowMp').val();
	let monsternowHp = $('#monsternowHp').val();
	let monsternowMp = $('#monsternowMp').val();
	let monsterNo = $('#monsterNo').val();
	let playerNo = $('#playerNo').val();
	let battleNo = $('#battleNo').val();
	$.ajax({
		url : 'elusion.do',
		type : 'post',
		data : {
			playernowHp :playernowHp,
			playernowMp : playernowMp,
			monsternowHp :monsternowHp,
			monsternowMp : monsternowMp,
			monsterNo : monsterNo,
			playerNo : playerNo,
			battleNo : battleNo
	},
	success : function(result) {
		attach(result);
		var playernowHp = $('#playernowHp').val();
		var monsternowHp = $('#monsternowHp').val();
		test(playernowHp,monsternowHp);
	},
	error : function() {
		alert('서버 에러');
	}
});
});
//rapid
$(document).on("click",".rapid",function() {
	const target = document.getElementById('rapid');
	target.disabled = true;
	setTimeout(function() {
		const target = document.getElementById('rapid');
		target.disabled = false;
	}, 60000);
	let playernowHp = $('#playernowHp').val();
	let playernowMp = $('#playernowMp').val();
	let monsternowHp = $('#monsternowHp').val();
	let monsternowMp = $('#monsternowMp').val();
	let monsterNo = $('#monsterNo').val();
	let playerNo = $('#playerNo').val();
	let battleNo = $('#battleNo').val();
	$.ajax({
		url : 'rapid.do',
		type : 'post',
		data : {
			playernowHp :playernowHp,
			playernowMp : playernowMp,
			monsternowHp :monsternowHp,
			monsternowMp : monsternowMp,
			monsterNo : monsterNo,
			playerNo : playerNo,
			battleNo : battleNo
	},
	success : function(result) {
		attach(result);
		var playernowHp = $('#playernowHp').val();
		var monsternowHp = $('#monsternowHp').val();
		test(playernowHp,monsternowHp);
	},
	error : function() {
		alert('서버 에러');
	}
});
});
</script>
</head>
<body>
<table>
<tr>
<td><img alt="" src="resources/MirrorBTPic/${player.tribe}/${player.tribe}N.png"></td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><img alt="" src="resources/MirrorBTPic/${monster.tribe}/${monster.tribe}N.jpeg"></td>
</tr>
<tr>
<td>
플레이어 레벨:${player.lv}&nbsp;&nbsp; 플레이어 닉네임:${player.nick}&nbsp;&nbsp; 플레이어 종족:${player.tribe}
</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>
몬스터 레벨:${monster.lv}&nbsp;&nbsp; 몬스터 이름:${monster.monsterNAME}&nbsp;&nbsp; 몬스터 종족:${monster.tribe}
</td>
</tr>
</table>
<input type="hidden" value="${player.dex}" id="playerdex">
<input type="hidden" value="${player.sheeld}" id="playersheeld">
<input type="hidden" value="${player.atkSpeed}" id="atkSpeed">
<input type="hidden" value="${monster.atkSpeed}" id="MatkSpeed">
<input type="hidden" value="${monster.atk}" id="Matk">
<input type="hidden" value="${player.hp}" id="playerhp">
<input type="hidden" value="${player.mp}" id="playermp">
<input type="hidden" value="${monster.hp}" id="monsterhp">
<input type="hidden" value="${monster.mp}" id="monstermp">
<table id="statusT">
<tr>
<td>
<input type="hidden" value="${player.playerNo}" id="playerNo">
<input type="hidden" value="${battle.battleNo}" id="battleNo">
<input type="hidden" value="${monster.monsterNo}" id="monsterNo">
<pre id="playerhpstate"><input id="playernowHp" type="number" value="${player.nowHp}" readonly="readonly"></pre>/${player.hp}<br>
<pre><input id="playernowMp" type="number" value="${player.nowMp}" readonly="readonly"></pre>/${player.mp}<br>
</td>
<td id="resultmsg">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>
<pre><input id="monsternowHp" type="number" value="${monster.nowHp}" readonly="readonly"></pre>/${monster.hp}<br>
<pre><input id="monsternowMp" type="number" value="${monster.nowMp}" readonly="readonly"></pre>/${monster.mp}<br>
</td>
</tr>
</table>
<table>
<tr>
<td><input id="attack" type='button' class="attack btn btn-primary btn-sm mt-1" value="공격">
&nbsp;<input id="heal" type='button' class="heal btn btn-primary btn-sm mt-1" value="힐사용">
&nbsp;<input id="steam" type='button' class="steam btn btn-primary btn-sm mt-1" value="광폭화">
&nbsp;
<c:if test="${player.tribe eq 'human'}">
<input id="guard" type='button' class="guard btn btn-primary btn-sm mt-1" value="가드">
&nbsp;
<c:if test="${player.lv ge 100 }">
<input id="invincible" type='button' class="invincible btn btn-primary btn-sm mt-1" value="무적">
&nbsp;
</c:if>
</c:if>
<c:if test="${player.tribe eq 'orc'}">
<input id="anger" type='button' class="anger btn btn-primary btn-sm mt-1" value="분노">
&nbsp;
<c:if test="${player.lv ge 100 }">
<input id="frenzy" type='button' class="frenzy btn btn-primary btn-sm mt-1" value="격분">
&nbsp;
</c:if>
</c:if>
<c:if test="${player.tribe eq 'elf'}">
<input id="elusion" type='button' class="elusion btn btn-primary btn-sm mt-1" value="일루전">
&nbsp;
<c:if test="${player.lv ge 100 }">
<input id="rapid" type='button' class="rapid btn btn-primary btn-sm mt-1" value="레피드">
&nbsp;
</c:if>
</c:if>
</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>&nbsp;&nbsp;</td>
</tr>
</table>
</body>
</html>