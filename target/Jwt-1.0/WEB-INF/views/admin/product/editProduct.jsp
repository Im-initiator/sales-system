<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var="urlList" value='/admin/product?page=1'/>
<c:url var="urlProduct" value='/api/admin/product'/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container ">			
		<a href="${urlList}" class="mt-5 mb-5"><i class="bi bi-arrow-left"></i> Quay lại</a>
		<div class="row mt-3">    
			<div class="col-md-12">
			 
			<div class="position-fixed  p-3 alert" id="errorSystem" role="alert" style="z-index:999;display:none" >		
				  			 Thông báo ở đây
		    	</div>

				<form:form class="row g-3" id="formSubmit" modelAttribute="model">
						  <div class="mb-3">
						    <form:label class="form-label" path="name">Name</form:label>
						    <form:input class="form-control" path="name"/>							    
						  </div>  	  
						  <div class="mb-3">
						    <form:label  class="form-label" path="img">Image</form:label>
						    <form:input  class="form-control" path="fileImage"/>
						  </div>	
						  <div class="mb-3">
						      <form:select path="categoryCode">
						      		<option>----Select Category---</option>
						      		<form:options items="${categories}" />						      
						      </form:select>
						  </div>
						  
						  <div class="mb-3" id="p-size">
						    <form:label class="form-label" path="sizes">Size</form:label>
						    <form:checkboxes items="${sizes}" path="sizes"/>
						  </div>
						  	
						  <div class="mb-3">
						    <form:label class="form-label" path="quantity">Quantity</form:label>
						    <form:input class="form-control" path="quantity"/>
						  </div>
						  <div class="mb-3">
						    <form:label  class="form-label" path="shortDescription">Description</form:label>
						    <form:textarea type="text" class="form-control" path="shortDescription"/>
						  </div>	
						  <div class="mb-3">
						    <form:label  class="form-label" path="content">Content</form:label>
						    <form:textarea class="form-control" id="content" path="content"/>
						  </div>	
						   <form:hidden path="id" id="p-id"/>		 			 			
				</form:form>
				<button type="button" class="btn btn-primary" id="submitForm">Update</button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">

		var element = $('#errorSystem');
		var id = $('#p-id').val();
		var editor='';		
		$( document ).ready(function() {
		    editor = CKEDITOR.replace('content');		   
		    
		});

		if(id === ""){
			$('#submitForm').text("Save");
		}else{
			$('#submitForm').text("Update");
		}
		
		var selectedValues = [];
				
		$('#submitForm').click(function(e) {
			e.preventDefault();		
			var data={};
			var formData = $('#formSubmit').serializeArray();
			$.each(formData,function(i,v){
				data[""+v.name+""] = v.value;
			});
			// Lấy tất cả các checkbox được chọn bên trong thẻ div có id "p-size"
			$('#p-size input[type="checkbox"]:checked').each(function() {
				selectedValues.push($(this).val());
			});
				
			data["content"] = editor.getData();
			data["sizes"] = selectedValues;
			console.log(selectedValues);
			console.log(data);

			if(id === ""){
				saveProduct(data);
			}else{
				updateProduct(data);
			}
		})
		
		
		
		function saveProduct(data) {
			$.ajax({
				url:'${urlProduct}',
				type:'POST',
				contentType:'application/json',
				data:JSON.stringify(data),
				success: function(result){
					$('#errorSystem').removeClass().addClass("alert alert-success");
					$('#errorSystem').text("Thêm sản phẩm thành công");
					$('#errorSystem').show();
					 setTimeout(function() {
					    	element.hide(); // Ẩn thông báo sau 3 giây
					    	element.removeClass("alert alert-success");
					    	window.location.href ="${urlList}";
					 }, 2000);	   
				},
				error: function(xhr,status,error){
					var textbody = xhr.responseText; //lấy nội dung phản hồi từ body
					$('#errorSystem').removeClass().addClass("alert alert-danger");
					if(!textbody.trim().startsWith("Error")){
						$('#errorSystem').text("Lỗi hệ thống");
					}else{
						$('#errorSystem').text(textbody);
					}
					$('#errorSystem').show();
					 setTimeout(function() {
					    	element.hide(); // Ẩn thông báo sau 3 giây
					    	element.removeClass("alert alert-danger");
					 }, 2000);	 
				}
	
			})
		} 
		
		function updateProduct(data) {
			$.ajax({
				url:'${urlProduct}',
				type:'POST',
				contentType:'application/json',
				data:JSON.stringify(data),
				success: function(result){
					$('#errorSystem').removeClass().addClass("alert alert-success");
					$('#errorSystem').text("Cập nhật sản phẩm thành công");
					$('#errorSystem').show();
					 setTimeout(function() {
					    	element.hide(); // Ẩn thông báo sau 3 giây
					    	element.removeClass("alert alert-success");
					    	window.location.href ="${urlList}";
					 }, 2000);	   
				},
				error: function(xhr,status,error){
					var textbody = xhr.responseText; //lấy nội dung phản hồi từ body
					$('#errorSystem').removeClass().addClass("alert alert-danger");
					if(!textbody.trim().startsWith("Error")){
						$('#errorSystem').text("Lỗi hệ thống");
					}else{
						$('#errorSystem').text(textbody);
					}
					$('#errorSystem').show();
					 setTimeout(function() {
					    	element.hide(); // Ẩn thông báo sau 3 giây
					    	element.removeClass("alert alert-danger");
					 }, 2000);	 
				}
	
			})
		} 
		
	</script>
</body>
</html>