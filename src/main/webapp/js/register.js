//jquery的写法
/*function doCheckName(){
	//获取文本框中的数据
	var uname=$("form input[name=userName]").val();
	alert("uname-->"+uname);
	//发送ajax异步请求
	$.ajax({
		url:"CheckNameServlet",
		type:"post",
		data:{"uname":uname},
		dataType:"json",
		success:function(result){
			//alert(result.msg)
			alert(result.status+"  "+result.message)
		},
		error:function(){
			alert("请求失败!");
		}
	});
}*/
//原生js的写法
var xmlHttpRequest;
function createXMLHttpRequest(){
	if(window.XMLHttpRequest){
		//非ie浏览器
		xmlHttpRequest=new XMLHttpRequest();
	}else{
		//ie浏览器
		xmlHttpRequest=new ActiveXObject("Miscrosoft.XMLHTTP");
	}
}
//ajax异步请求访问数据库,检测用户名是否存在
function doCheckName(){
	createXMLHttpRequest();
	//获取文本框的值
	var uname=document.forms[0].userName.value;
	alert(uname);
	//调用open方法
	xmlHttpRequest.open("get","CheckNameServlet?uname="+uname,true);
	//如果是post提交必须添加下面的语句
	//xmlHttpRequest.setRequestHeader("content-type","applicatoin/x-www-form-urlencoded");
	//给xmlHTTPRequest对象注册onreadystatechange事件
	xmlHttpRequest.onreadystatechange=function(){
		//alert("aa-->"+xmlHttpRequest.readyState);
		handStateChange();
	};
	//发送请求CheckNameServlet?username=aa,此时才请求servlet
	xmlHttpRequest.send(null);
}
function handStateChange(){
	if(xmlHttpRequest.readyState==4){
		if(xmlHttpRequest.status==200){
			//获取服务端响应的文本信息
			var responseText=xmlHttpRequest.responseText;
			//把得到的文本做dom编程,修改html页面中的局部内容
			var span_ele=document.getElementById("uname");
			//清除span_ele元素的第一个节点内容
			if(span_ele.hasChildNodes()){
				span_ele.removeChild(span_ele.childNodes[0]);
			}
			//根据响应回来的文本创建文本节点对象
			var txt_node=document.createTextNode(responseText);
			var submit_ele=document.getElementById("submit");
			if(responseText=='{"message":"用户名被占用","status":0}'){
				submit_ele.setAttribute("disabled","disabled");
			}else{
				submit_ele.removeAttribute("disabled");
			}
			span_ele.appendChild(txt_node);
			
		}
	}
}