<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../samecontent/head.jsp" %>
<script src="../js/jquery-1.11.3.min.js"></script>
<script>

function addDays(dateObj, numDays) {
   dateObj.setDate(dateObj.getDate() + numDays);
   return dateObj;
}
function one(){
	$("#xacnhan").click(function(){
		load_data();
	});
}
function load_data(page){
	var xemdoanhthutheo = $("#xemdoanhthutheo").val();
	var ngay1 = $("#ngay1").val();
	var ngay2 = $("#ngay2").val();
	var date = new Date(ngay2);
    var newdate = new Date(date);

    newdate.setDate(newdate.getDate());
    
    var dd = newdate.getDate()+1;
    var mm = newdate.getMonth()+1;
    var y = newdate.getFullYear();

    var ngay2cong1 = y + '-' + mm + '-' + dd;
    if(xemdoanhthutheo=="Toàn bộ thời gian"){
    	ngay1='';
		$.ajax({  
	      	url:"quanlydoanhthuajax",  
	        method:"POST",
	        data:{pageid:page,date:ngay1,date2:ngay2cong1},
	        success:function(data){  
	        	$('#show').html(data);  
	        }
		});
		exit;
	}else{
		if(ngay1!="" && ngay2!=""){
			if(ngay1<=ngay2){
				$.ajax({  
			      	url:"quanlydoanhthuajax",  
			        method:"POST",
			        data:{pageid:page,date:ngay1,date2:ngay2cong1},
			        success:function(data){  
			        	$('#show').html(data);  
			        }
				});
				exit;
			}else{
				alert("Vui lòng chọn lại ngày");
				exit;
			}
		}else{
			alert("Vui lòng chọn ngày");
			exit;
		}
	}
}
$(document).ready(function(){
	$("#xemdoanhthutheo").change(function(){
		var get = $("#xemdoanhthutheo").val();
		if(get=="Toàn bộ thời gian"){
			$("#date").hide();
		}else{
			$("#date").show();
		}
	});
	$(document).on('click', '.page-link', function(){  
		var page = $(this).attr("id");  
		load_data(page);
	});
	setTimeout(load_data,100);
	setInterval(one,0);
});
</script>
<body>
    <div id="wrapper">
        <%@include file="../samecontent/menu.jsp" %>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Quản lý doanh thu</h1>
                </div>
                <div class="form-group">
            		<label>Xem doanh thu theo</label>
                    <select class="form-control" name="xemdoanhthutheo" id="xemdoanhthutheo">
                    	<option value="Toàn bộ thời gian">Toàn bộ thời gian</option>
                    	<option value="Từ ngày đến ngày">Từ ngày đến ngày</option>
                    </select>
                </div>
	                <div id="date" style="display:none;">
		                <div class="form-group">
		                	<label>Từ</label>
		                	<input class="form-control" name="ngay1" id="ngay1" type="date" required/>
		                </div>
		                <div class="form-group">
		                	<label>Đến</label>
		                	<input class="form-control" name="ngay2" id="ngay2" type="date" required/>
		                	
		                </div>
	                </div>
	                <div class="form-group" align="center">
		            	<input type="submit" class="btn btn-default" id="xacnhan" value="Xác nhận"/>
		            </div>
                <div id="show"></div>
            </div>
	  	</div>
	 </div>
            <!-- /.row -->          
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="../vendor/raphael/raphael.min.js"></script>
    <script src="../vendor/morrisjs/morris.min.js"></script>
    <script src="../data/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

</body>


</html>
