<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var="urlList" value='/admin/user?page=1'/>
<c:url var="urlUpdate" value='/api/admin/user'/>
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
						    <form:label class="form-label" path="userName">User Name</form:label>
						    <form:input class="form-control" path="userName"/>							    
						  </div>
						  <div class="mb-3">
						    <form:label class="form-label" path="password">Password</form:label>
						    <form:input class="form-control" path="password"/>
						  </div>	  	  
						  <div class="mb-3" id="RoleUser">
						 		 <form:label  class="form-label" path="roles">Role: </form:label>
							 	 <form:checkboxes items="${mapRoles}"  path="roles"/>						 	 						 
							</div>		  
						  <div class="mb-3">
						    <form:label  class="form-label" path="fullName">Full Name</form:label>
						    <form:input  class="form-control" path="fullName"/>
						  </div>	
						  <div class="mb-3">
						      <form:label  class="form-label" path="status">Status: </form:label>
						      <form:radiobutton path = "status" value = "0" label = "0" />
                			  <form:radiobutton path = "status" value = "1" label = "1" />			
						  </div>	
						  <div class="mb-3">
						    <form:label  class="form-label" path="address">Address</form:label>
						    <form:input type="text" class="form-control" path="address"/>
						  </div>	
						  <div class="mb-3">
						    <form:label  class="form-label" path="phoneNumber">Phone Number</form:label>
						    <form:input class="form-control" path="phoneNumber"/>
						  </div>	
						   <form:hidden path="id" />		 			 			
				</form:form>
				<button type="button" class="btn btn-primary" id="submitForm">Update</button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">

		var selectedValues = [];
		// Lấy tất cả các checkbox được chọn bên trong thẻ div có id "RoleUser"
		$('#RoleUser input[type="checkbox"]:checked').each(function() {
		  selectedValues.push($(this).val());
		});
				
		$('#submitForm').click(function(e) {
			e.preventDefault();		
			var data={};
			var formData = $('#formSubmit').serializeArray();
			$.each(formData,function(i,v){
				data[""+v.name+""] = v.value;
			});
			data["roles"] = selectedValues;
			console.log(data);
			updateProduct(data);
		})
		
		function updateProduct(data) {
			$.ajax({
				url:'${urlUpdate}',
				type:'PUT',
				contentType:'application/json',
				data:JSON.stringify(data),
				success: function(result){
					$('#errorSystem').removeClass().addClass("alert alert-success");
					$('#errorSystem').text("Cập nhật thành công");
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