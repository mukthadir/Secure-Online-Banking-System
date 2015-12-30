/**
 * 
 *//**
 * Build for Registration page.
 */

$(document).ready(function(){
	$("#name").hover(function(e){
		e.preventDefault();
		$("#name-rules").fadeIn();
		$('#name-rules').css('display','inline-block');
	},
	function(){
		$("#name-rules").fadeOut();
	},
	$("#lastname").hover(function(e){
		e.preventDefault();
		$("#lname-rules").fadeIn();
		$('#lname-rules').css('display','inline-block');
	},
	function(){
		$('#lname-rules').fadeOut();
	},
	$("#email").hover(function(e){
		e.preventDefault();
		$("#email-rule").fadeIn();
		$('#email-rule').css('display','inline-block');
	},
	function(){
		$('#email-rule').fadeOut();
	}
	,
	$("#user-address").hover(function(e){
		e.preventDefault();
		$("#address-rule").fadeIn();
		$('#address-rule').css('display','inline-block');
	},
	function(){
		$('#address-rule').fadeOut();
	}
	,
	$("#social").hover(function(e){
		e.preventDefault();
		$("#ssn-rule").fadeIn();
		$('#ssn-rule').css('display','inline-block');
	},
	function(){
		$('#ssn-rule').fadeOut();
	}
	,
	$("#username").hover(function(e){
		e.preventDefault();
		$("#user-rule").fadeIn();
		$('#user-rule').css('display','inline-block');
	},
	function(){
		$('#user-rule').fadeOut();
	}
	,
	$("#phone").hover(function(e){
		e.preventDefault();
		$("#phone-rule").fadeIn();
		$('#phone-rule').css('display','inline-block');
	},
	function(){
		$('#phone-rule').fadeOut();
	}
	,
	$("#passwordrule").hover(function(e){
		e.preventDefault();
		$("#pass-rule").fadeIn();
		$('#pass-rule').css('display','inline-block');
	},
	function(){
		$('#pass-rule').fadeOut();
	}
	,
	$("#security1").hover(function(e){
		e.preventDefault();
		$("#security-rule1").fadeIn();
		$('#security-rule1').css('display','inline-block');
	},
	function(){
		$('#security-rule1').fadeOut();
	}
	,
	$("#security2").hover(function(e){
		e.preventDefault();
		$("#security-rule2").fadeIn();
		$('#security-rule2').css('display','inline-block');
	},
	function(){
		$('#security-rule2').fadeOut();
	}
	,
	$("#usernameip").keypress(function(key)
			{
			if((key.charCode <97 || key.charCode > 122) && (key.charCode<65 || key.charCode > 90) && key.charCode !=45 && key.charCode !=95 && key.charCode!=8 && (key.charCode < 48 || key.charCode > 57))
				{
					$("#user-rule").show();
					return false;
				}
			else
				{
					$("#user-rule").hide();
					return true;
				}
			},
	$("#password").keypress(function(key)
			{
			if((key.charCode <97 || key.charCode > 122) && (key.charCode<63 || key.charCode > 90) && key.charCode !=45 && key.charCode !=95 && key.charCode !=33 && key.charCode!=8 && (key.charCode < 35 || key.charCode > 37) && (key.charCode < 47 || key.charCode > 57))
				{
					$("#pass-rule").show();
				
					return false;
				}
			else
				{
					$("#pass-rule").hide();
				
					return true;
				}
			}
	))))))))))))
});


