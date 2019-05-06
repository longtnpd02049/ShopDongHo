<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="danhmucsp/head.jsp" %>
<script src="js/jquery-1.11.3.min.js"></script>
<script>
	function load_data(page){
		some = new URLSearchParams(window.location.search);
		var id = some.get('id');
		var id2 = some.get('id2');
		var tukhoa = $("#search").val();
		if(id2!=null){
			$.ajax({  
		      	url:"danhmucajax",  
		        method:"POST",
		        data:{id:id,id2:id2,pageid:page},
		        success:function(data){  
		        	$('#show').html(data);  
		        }
	    	});
	    	exit;
		}else{
			$.ajax({  
		      	url:"danhmucajax",  
		        method:"POST",
		        data:{id:id,pageid:page},
		        success:function(data){  
		        	$('#show').html(data);  
		        }
	    	});
	    	exit;
		}
    	
	}
	$(document).ready(function(){
		$(document).on('click', '.page-link', function(){  
			var page = $(this).attr("id");  
			load_data(page);
		});
		setTimeout(load_data,100);
	});
</script>
<body>
	<div class="container">
        <%@include file="samecontent/header.jsp" %>
        <%@include file="samecontent/menu-ngang.jsp" %>
        <%@include file="samecontent/menu-doc.jsp" %>
        <div id="show"></div>
        <%@include file="danhmucsp/right-side.jsp" %>
    </div><br>
	<%@include file="samecontent/end-side.jsp" %>
    <footer>
    	<%@include file="samecontent/footer.jsp" %>
     </footer>  
</body>
</html>