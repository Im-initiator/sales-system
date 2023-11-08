<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:url var="urlProduct" value="/api/admin/product"/>

<div class="container">
		<div class="row">    
			<div class="col-md-12">
				<div class="position-fixed  p-3 alert" id="errorSystem" role="alert" style="z-index:999;display:none" >		
				  			 Thông báo ở đây
		    	</div>
		    	
				<a href="<c:url value='/admin/edit/product'/>" class="btn btn-primary">Add new</a>
			
				<form action="<c:url value='/admin/product'/>" id="formSubmit" method="GET"> 
					<div class="table-responsive">    							       
							<table id="mytable" class="table table-bordred table-striped">         							
								<thead>    								             
							         <th><input type="checkbox" id="checkall"/><button type="button" class="btn text-danger" id="delete-product"> <i class="bi bi-trash fs-4"></i></button></th>
							         <th>Name</th>
							         <th>Prize</th>
							         <th>SDescription</th>						
							         <th>Quantity</th>							  
							         <th>Image</th>   
							         <th>Content</th>
							         <th></th>
								</thead>
		    					<tbody>
									<c:forEach items="${model}" var ="product"> 
									    <tr>
									    	<td><input type="checkbox" value="${product.id}"/></td>
										    <td>${product.name}</td>   
										    <td>${product.prize}</td>
										    <td>${product.shortDescription}</td>										   
										    <td>${product.quantity}</td>   
										    <td>${product.img}</td>
										    <td>${product.content}</td>
										    <td><a href="<c:url value='/admin/edit/product?id=${product.id}'/>" title="Chỉnh sửa"><i class="bi bi-pencil-square bs-bx p-2"></i></a></td>									    										   							
									    </tr>
			    					</c:forEach> 
		   						</tbody>     						   
					  		</table>        
	         	 	  </div> 
	         	 	  <input id="page" type="hidden" value="" name="page"/>    
	         	 	
	         	</form>  
				         <div class="row mt-3" id="paging">
							<div class="col-sm-12 ">
								<nav aria-label="Page navigation">
				      				<ul class="pagination" id="pagination"></ul>
				   				</nav>  
							</div>
						</div>   	 	  		 
      	 	</div>
      	 	
		</div>
		
	</div>
	
	<script type="text/javascript">

	const element = $('#errorSystem');
	  
	$(document).ready(function(){
		$("#mytable #checkall").click(function () {
		        if ($("#mytable #checkall").is(':checked')) {
		            $("#mytable input[type=checkbox]").each(function () {
		                $(this).prop("checked", true);
		            });

		        } else {
		            $("#mytable input[type=checkbox]").each(function () {
		                $(this).prop("checked", false);
		            });
		        }
		    });
		    
		    $("[data-toggle=tooltip]").tooltip();
	});
	
	var currentPage = ${page};
	var totalItem = ${totalItem};
	var limit = ${limit}
	var totalpage = Math.ceil(totalItem / limit);
	$(function () {
        window.pagObj = $('#pagination').twbsPagination({
        	startPage:currentPage,
            totalPages: totalpage,
            visiblePages: 10,
            onPageClick: function (event, page) {
            	if(page!=currentPage){
            		$('#page').val(page);
                	$('#formSubmit').submit();
            	}
            }
        })
    }); 

	/* function getCheckboxValues() {
		  const checkboxes = document.querySelectorAll("#RoleUser input[type='checkbox']");
		  const values = [];

		  checkboxes.forEach((checkbox) => {
		    if (checkbox.checked) {
		      values.push(checkbox.value);
		    }
		  });

		  console.log(values);
		  return values;
	} */
    

		//delete product
		
		  $('#delete-product').click(function(e) {
			  e.preventDefault();
			    var ids = [];
			    $('#mytable tbody input[type="checkbox"]:checked').each(function() {
			    	ids.push($(this).val());
			    });

			    console.log(ids);
			    console.log(JSON.stringify(ids));
			    $.ajax({
				    url:'${urlProduct}',
					type:'DELETE',
					contentType:'application/json',
					data:JSON.stringify(ids),
					success: function(result){
						$('#errorSystem').removeClass().addClass("alert alert-success");
						$('#errorSystem').text("Xóa thành công");
						$('#errorSystem').show();
						 setTimeout(function() {
						    	element.hide(); // Ẩn thông báo sau 3 giây
						    	element.removeClass("alert alert-success");
						    	location.reload(); 
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
			 	 });
		  });
    
	
		  

	</script>
	
	
	
</body>
</html>