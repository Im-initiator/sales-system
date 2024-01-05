<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var="urlList" value='/saler/product?page=1'/>
<c:url var="urlProduct" value='/api/saler/product'/>
<c:url var="urlEdit" value='/saler/edit/product'/>
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

				<form:form class="row g-3" id="formSubmit" modelAttribute="model" enctype="multipart/form-data">
						  <div class="mb-3">
						    <form:label class="form-label" path="name">Name</form:label>
						    <form:input class="form-control" path="name"/>							    
						  </div>  	  
						 <div class="row">
							 <div class="mb-3 row">
								 <form:label style="border: 1px solid #7c6f79;margin: 0; border-radius: 2px;"  class="form-label col-4" path="fileImage">Chose file</form:label>
								 <div class="col-8" style="border: 2px solid #ccc;">
										<p id="preview"></p>
								 </div>
								 <div class="" style="opacity:0; display:none;">
									 <form:input type="file" accept="image/*,.pdf" id="fileImage" path="fileImage" onchange="displaySelectedFileName()"/>
								 </div>
							 </div>
							 <div class="row">
								 <div class="col-4" id="img-product">
									 <img style="width: 50px;" src="<c:url value='${model.img}'/>">
								 </div>
							 </div>
						 </div>
						  <div class="mb-3">
						      <form:select path="categoryCode">
						      		<option>----Select Category---</option>
						      		<form:options items="${categories}" />						      
						      </form:select>
						  </div>

							<div class="mb-3">
								<form:select path="genderCode">
									<option>----Select Gender---</option>
									<form:options items="${genders}" />
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


		

				
		$('#submitForm').click(function(e) {
			e.preventDefault();		
			// var data={};
			var selectedValues = [];
			var form = document.getElementById('formSubmit');
			var formData = new FormData(form);
			console.log(formData.get('name'))
			formData.delete('content');
			formData.delete('sizes')
			console.log(formData.get('fileImage'));
			// $.each(formData,function(i,v){
			// 	data[""+v.name+""] = v.value;
			// });
			// $.each(form, function (index, field) {
			// 	formData.append(field.name+"", field.value);
			// });
			$('#p-size input[type="checkbox"]:checked').each(function() {
				selectedValues.push($(this).val());
			});
			console.log(selectedValues);
			formData.append("content",editor.getData());
			formData.append("sizes",selectedValues);
			// Lấy tất cả các checkbox được chọn bên trong thẻ div có id "p-size"

			console.log(formData);
			saveProduct(formData);
		})
		
		function saveProduct(data) {
			$.ajax({
				url:'${urlEdit}',
				type:'POST',
				data:data,
				processData: false,
				contentType: false,
				success: function(result){
					$('#errorSystem').removeClass().addClass("alert alert-success");
					if(id===""){
						$('#errorSystem').text("Thêm sản phẩm thành công");
					}else{
						$('#errorSystem').text("Cập nhật sản phẩm thành công");
					}
					$('#errorSystem').show();
					 setTimeout(function() {
					    	element.hide(); // Ẩn thông báo sau 3 giây
					    	element.removeClass("alert alert-success");
					  //  	window.location.href ="${urlList}";
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
		

		var fileNameDisplay = document.getElementById('preview');
		function displaySelectedFileName() {
			var fileInput = document.getElementById('fileImage');
			$('#img-product').hide();
			if (fileInput.files.length > 0) {
				var fileName = fileInput.files[0].name;
				fileNameDisplay.textContent = fileName;
			} else {
				fileNameDisplay.textContent = "";
			}
		}

		var content = "${model.img}";
		console.log(content);
		var temp = content.indexOf("tm36");
		if(temp === -1){
			fileNameDisplay.textContent = content.substring(content.lastIndexOf("/")+1);
		}else{
			fileNameDisplay.textContent = content.substring(content.lastIndexOf("/")+1,temp)+content.substring(content.lastIndexOf("."));
		}

		console.log(content);
	</script>
</body>
</html>