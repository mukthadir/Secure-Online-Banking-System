/**
 * 
 */

$(document).ready(function(){
	$("#username").keypress(function(key)
			{
			if((key.charCode <97 || key.charCode > 122) && (key.charCode<65 || key.charCode > 90) && key.charCode !=45 && key.charCode !=95 && key.charCode!=8 && (key.charCode < 48 || key.charCode > 57))
				{
					$("#name-invalid").show();
					$("#login").prop("disabled",true);
					return false;
				}
			else
				{
					$("#name-invalid").hide();
					$("#login").prop("disabled",false);
					return true;
				}
			})
			
	$("#password").keypress(function(key)
			{
			if((key.charCode <97 || key.charCode > 122) && (key.charCode<63 || key.charCode > 90) && key.charCode !=45 && key.charCode !=95 && key.charCode !=33 && key.charCode!=8 && (key.charCode < 35 || key.charCode > 37) && (key.charCode < 47 || key.charCode > 57))
				{
					$("#pwd-invalid").show();
					$("#login").prop("disabled",true);
					return false;
				}
			else
				{
					$("#pwd-invalid").hide();
					$("#login").prop("disabled",false);
					return true;
				}
			})
	
});

function validate()
{
	var login=document.getElementById("username").value;
	var password=document.getElementById("password").value;
	if(login.length==0 && password.length==0)
		{
			var error=document.getElementById("error-mssg");
			error.style.display="block";
			var error=document.getElementById("error-mssg-pwd");
			error.style.display="block";
			return false;
		}
	
	else if(login.length==0 && password.length==0)
	{
		var error=document.getElementById("error-mssg");
		error.style.display="block";	
		return false;
	}
	
	else if(password.length==0)
		{
			var error=document.getElementById("error-mssg-pwd");
			error.style.display="block";
			return false;
		}
};