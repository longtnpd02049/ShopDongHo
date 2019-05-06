<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="search/head.jsp" %>
<script src="js/jquery-1.11.3.min.js"></script>
<script>
//http://localhost:8080/DuAn2/search?gia-min=0&gia-max=0&idhieusp=0&idsapxeptheo=0&key=%C4%91%E1%BB%93ng+h%E1%BB%93
	function load_data(page){
		some = new URLSearchParams(window.location.search);
		var giamin = some.get('gia-min');
		var giamax = some.get('gia-max');
		var idhieusp = some.get('idhieusp');
		var idsapxeptheo = some.get('idsapxeptheo');
		var key = some.get('key');
		$.ajax({  
	      	url:"searchajax",  
	        method:"POST",
	        data:{giamin:giamin,giamax:giamax,idhieusp:idhieusp,idsapxeptheo:idsapxeptheo,key:key,pageid:page},
	        success:function(data){  
	        	$('#show').html(data);  
	        }
    	});
    	exit;
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
        <%@include file="search/right-side.jsp" %>
    </div><br>
	<%@include file="samecontent/end-side.jsp" %>
    <footer>
    	<%@include file="samecontent/footer.jsp" %>
     </footer>  
</body>
</html>