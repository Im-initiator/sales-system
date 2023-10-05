<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<c:url var="urladd" value="/admin/product"/>
<c:url var="urllist" value='/admin/product?sortName=name&sortBy=ASC&page=1&limit=10&type=list'/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="container ">
			
		<a href="${urllist}" class="mt-5 mb-5"><i class="bi bi-arrow-left"></i> Quay lại</a>
		<div class="row mt-3">    
			<div class="col-md-12">
			 
			 <div class="position-fixed  p-3 alert" id="messa" role="alert" style="right:5%;z-index:999;display:none" >		
				  			 Thông báo ở đây
			 </div>

				<form class="row g-3" id="formSubmit">
					  <div class="col-12">
					    <label for="name" class="form-label">Tên sản phẩm</label>
					    <input type="text" class="form-control" id="name" name = "name" value="${PRODUCT.getName()}">
					  </div>
					  <div class="col-12">
					    <label for="shortDescription" class="form-label">Mô tả ngắn</label>
					    <input type="text" class="form-control" id="shortDescription" name = "shortDescription" value="${PRODUCT.getShortDescription() }">
					  </div>
					  <div class="col-12">
					    <label for="img" class="form-label">Ảnh</label>
					    <div class="input-group mb-3">
					 		 <span class="input-group-text" id="basic-addon3">https://example.com/users/</span>
					  		<input type="text" class="form-control" id="img" aria-describedby="basic-addon3" name="img" value="${PRODUCT.getImg() }">
						</div>
					  </div>
					  <div class="col-md-6">
					    <label for="typeId" class="form-label"></label>
					    
					     <select id="typeId" class="form-select" name="typeId">
					        <option value="null" selected>Chọn thể loại</option>
					        <c:forEach items="${TYPE}" var="type">					        
					        	<option value="${type.getId()}" <c:if test="${type.getId()==PRODUCT.getTypeId()}">selected="selected"</c:if>>${type.getName() }</option>
					        </c:forEach>
					        
					    </select>
					  </div>
					 <div class="d-flex flex-wrap justify-content-around col-md-3">		
					 	<c:forEach items="${SIZE}" var="size">
					   		<div class="form-check ms-4 mt-3" id="productsize">
								  <input class="form-check-input input${size.getName()}" type="checkbox" value="${size.getId()}" id="input${size.getId()}" 
									  	<c:forEach items="${PRODUCT.getProductSize()}" var="check">
									  		<c:if test="${check== size.getId()}">checked</c:if>
									  	</c:forEach>
								  >
								  <label class="fs-6" for="input${size.getId()}">
								    ${size.getName()}
								  </label>
							</div>
						</c:forEach>	  
								
					</div>
					  <div class="col-md-2">
					    <label for="quantity" class="form-label">Số lượng</label>
					    <input type="text" class="form-control" id="quantity" name="quantity" value="${PRODUCT.getQuantity() }">
					  </div>
					  
					  
					  
					  <div class="col-md-12">
						 <div class="form-floating">
							  <textarea class="form-control" placeholder="Leave a comment here" id="content" name="content">${PRODUCT.getContent() }</textarea>
						</div>
					</div>
					<button type="submit" class="btn btn-primary" id="submitForm">Thêm mới</button>
					<input type="hidden" class="" id="id" value="${PRODUCT.getId() }" name="id">
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var id = $('#id').val();
		var editor='';		
		$( document ).ready(function() {
		    editor = CKEDITOR.replace('content');		   
		    
		});
		if(id!==""){
	    	$('#submitForm').text('Cập nhật');	    	
	    }
	
		var element = $('#messa');				
		$('#submitForm').click(function(e) {
			e.preventDefault();
	
			//duyệt qua thẻ div có id là productsize và cho tất cả các giá trị thẻ div vào một mảng
			var selectedValues = [];
			$('#productsize input[type="checkbox"]').each(function() {
			    if ($(this).is(':checked')) {
			        selectedValues.push($(this).val());
			    }
			});			
			var data={};
			var formData = $('#formSubmit').serializeArray();
			$.each(formData,function(i,v){
				data[""+v.name+""] = v.value;
			});
			data["content"] = editor.getData();
			data["productSize"] = selectedValues;
			console.log(data);
			if(id==""){
				addProduct(data);
			}else{
				updateProduct(data);
			}
		})
		
		function addProduct(data) {
			$.ajax({
				url:'${urladd}',
				type:'POST',
				contentType:'application/json',
				data:JSON.stringify(data),
				dataType: 'json',
				success: function(result){
					element.addClass('alert-'+result.alert);
					element.text(result.message);
					element.show(); // Hiển thị thông báo
				    setTimeout(function() {
				    	element.hide(); // Ẩn thông báo sau 3 giây
				    	element.removeClass('alert-'+result.alert);
				    }, 3000);	    
				},
				error: function(error){				
					element.text('Có lỗi hệ thống xảy ra');
					element.show();
					setTimeout(function() {
				    	element.hide(); // Ẩn thông báo sau 3 giây
				    }, 3000);
				}	
			})
		}
		
		function updateProduct(data) {
			$.ajax({
				url:'${urladd}',
				type:'PUT',
				contentType:'application/json',
				data:JSON.stringify(data),
				dataType: 'json',
				success: function(result){
					element.addClass('alert-'+result.alert);
					element.text(result.message);
					element.show();
					 setTimeout(function() {
					    	element.hide(); // Ẩn thông báo sau 3 giây
					    	element.removeClass('alert-'+result.alert);
					    	window.location.href = "${urllist}";
					 }, 2000);	   
					
				},
				error: function(error) {
					element.show();
					element.text('Có lỗi hệ thống xảy ra');	
					 setTimeout(function() {
					    	element.hide(); // Ẩn thông báo sau 3 giây
					    	window.location.href = "${urllist}";
					}, 2000);
				}
	
			})
		}
		
	</script>

</body>
</html>