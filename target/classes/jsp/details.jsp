<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="details/head.jsp" %>
<style>
.saokmau {
    background: url(images/nav_logo242.png) no-repeat -65px -230px;
    display: inline-block;
    height: 13px;
    overflow: hidden;
    position: relative;
    top: 1px;
    width: 15px;
}
.saovang {
    background: url(images/nav_logo242.png) no-repeat -110px -230px;
    display: inline-block;
    height: 13px;
    overflow: hidden;
    position: relative;
    top: 1px;
    width: 15px;
}
</style>
<script>
	var i = 1;
	myFunction(i);
	
	function plusDivs(n) {
		myFunction(i += n);
	}
	function currentDiv(n) {
		myFunction(i = n);
	}
	function myFunction(n) {
	    if(n==1){
	    	document.getElementById("sao1").className = "saovang";
	    	document.getElementById("sao2").className = "saokmau";
	    	document.getElementById("sao3").className = "saokmau";
	    	document.getElementById("sao4").className = "saokmau";
	    	document.getElementById("sao5").className = "saokmau";
	    	document.getElementById("danhGia").value = n;
	    	document.getElementById("statusSao").innerHTML = "Không thích";
	    }else if(n==2){
	    	document.getElementById("sao1").className = "saovang";
	    	document.getElementById("sao2").className = "saovang";
	    	document.getElementById("sao3").className = "saokmau";
	    	document.getElementById("sao4").className = "saokmau";
	    	document.getElementById("sao5").className = "saokmau";
	    	document.getElementById("danhGia").value = n;
	    	document.getElementById("statusSao").innerHTML = "Tạm được";
	    }else if(n==3){
	    	document.getElementById("sao1").className = "saovang";
	    	document.getElementById("sao2").className = "saovang";
	    	document.getElementById("sao3").className = "saovang";
	    	document.getElementById("sao4").className = "saokmau";
	    	document.getElementById("sao5").className = "saokmau";
	    	document.getElementById("danhGia").value = n;
	    	document.getElementById("statusSao").innerHTML = "Bình thường";
	    }else if(n==4){
	    	document.getElementById("sao1").className = "saovang";
	    	document.getElementById("sao2").className = "saovang";
	    	document.getElementById("sao3").className = "saovang";
	    	document.getElementById("sao4").className = "saovang";
	    	document.getElementById("sao5").className = "saokmau";
	    	document.getElementById("danhGia").value = n;
	    	document.getElementById("statusSao").innerHTML = "Rất tốt";
	    }else if(n==5){
	    	document.getElementById("sao1").className = "saovang";
	    	document.getElementById("sao2").className = "saovang";
	    	document.getElementById("sao3").className = "saovang";
	    	document.getElementById("sao4").className = "saovang";
	    	document.getElementById("sao5").className = "saovang";
	    	document.getElementById("danhGia").value = n;
	    	document.getElementById("statusSao").innerHTML = "Tuyệt vời quá";
	    }
	}
	
</script>
<body>
    <div class="thongtinnguoidungbinhluan">
        <div class="titlebar">
            <span>Thông tin người dùng</span>
            <a class="back">
                <i class="form-close"></i>
            </a>
        </div>
        <f:form method="post" action="postcomment" modelAttribute="comment">
        	<p style="text-align:center;font-size:23px;padding-top:20px;">
        		<label>Nam</label><input style="margin:10px;" class="text" type="radio" name="gioiTinh" value="0"/>
        		<label>Nữ</label><input style="margin:10px;" class="text" type="radio" name="gioiTinh" value="1"/>
        	</p><br>
        	<p>
                <input class="text" type="hidden" placeholder="ID Chi tiết sp" name="chitietsp.idChiTietSp" id="chitietsp.idChiTietSp" value="${idsp}"/>
            </p>
            <p>
                <input class="text" type="text" placeholder="Họ tên (bắt buộc)" name="hoTen" id="hoTen" />
            </p><br>
            <p>
                <input class="text" type="hidden" placeholder="Đánh giá" name="danhGia" id="danhGia" />
            </p><br>
           
            <p align="center">
             	<label>Đánh giá:</label>
	            <span class="saokmau" id="sao1" onclick="myFunction(1)" title="Không thích"></span>
				<span class="saokmau" id="sao2" onclick="myFunction(2)" title="Tạm được"></span>
				<span class="saokmau" id="sao3" onclick="myFunction(3)" title="Bình thường"></span>
				<span class="saokmau" id="sao4" onclick="myFunction(4)" title="Rất tốt"></span>
				<span class="saokmau" id="sao5" onclick="myFunction(5)" title="Tuyệt vời quá"></span>
				<span id="statusSao" style="margin:30px;"></span>
			</p>
            <p>
                <input class="text" type="text" placeholder="Email (để nhận phản hồi qua Email)" name="email" id="email" />
            </p><br/>
            <p>
                <input class="text" type="text" placeholder="Số điện thoại (để nhận phản hồi qua Zalo)" name="phone" id="phone" />
            </p><br/>
            <p>
                <input class="text" type="hidden" placeholder="Status" name="status" id="status" value="wait"/>
            </p><br/>
            <p>
                <textarea style="display:none;" name="noiDungBinhLuan" id="noiDungBinhLuan"></textarea>
            </p>
            <p>
                <input type="submit" name="action" id="submit" value="GỬI BÌNH LUẬN" />
            </p><br/>
        </f:form>
    </div>
	<div class="container">
       <%@include file="samecontent/header.jsp" %>
       <%@include file="samecontent/menu-ngang.jsp" %>
       <%@include file="samecontent/menu-doc.jsp" %>
       <%@include file="details/center-side.jsp" %>
       <%@include file="details/right-side.jsp" %>
    </div><br>
    <%@include file="samecontent/end-side.jsp" %>
    <footer>
    	<%@include file="samecontent/footer.jsp" %>
    </footer>
</body>
</html>
