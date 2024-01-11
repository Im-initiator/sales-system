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
<c:url var="urlProduct" value="${api}"/><%--/api/saler/product--%>
<c:url var="urlList" value="${link}"/><%--/manager/product--%>
<div class="container">
		<div class="row">    
			<div class="col-md-12">
				<div class="position-fixed  p-3 alert" id="errorSystem" role="alert" style="z-index:999;display:none" >		
				  			 Thông báo ở đây
		    	</div>
		    	
				<sec:authorize access="hasRole('SALER')">
					<a href="<c:url value='/saler/edit/product'/>" class="btn btn-primary">Add new</a>
				</sec:authorize>
			
				<form action="${urlList}" id="formSubmit" method="GET">
					<div class="table-responsive">    							       
							<table id="mytable" class="table table-bordred table-striped">
								<thead>    								             
							         <th><input type="checkbox" id="checkall"/><button type="button" class="btn text-danger" id="delete-product"> <i class="bi bi-trash fs-4"></i></button></th>
							         <th>Name</th>
									 <th>Price</th>
							         <th>Prize</th>
							         <th>SDescription</th>						
							         <th>Quantity</th>							  
							         <th>Image</th>   
							         <th>Content</th>
							         <sec:authorize access="hasRole('SALER')">
										 <th></th>
									 </sec:authorize>
								</thead>
		    					<tbody>
									<c:forEach items="${model}" var ="product"> 
									    <tr>
									    	<td><input type="checkbox" value="${product.id}"/></td>
											<td>
												<div class="td-container">
													<div class="td-content">
															${product.name}
													</div>
												</div>
											</td>
											<td>${product.price}</td>
											<td>${product.prize}</td>
										   	 <td>
												 <div class="td-container">
													 <div class="td-content">
														 ${product.shortDescription}
												 	</div>
												 </div>
											 </td>
										    <td>${product.quantity}</td>
											<td>
												<div class="td-container">
													<div class="td-content">
															${product.img}
													</div>
												</div>
											</td>

										    <td>
												<div class="td-container">
													<div class="td-content">
														${product.content}
													</div>
												</div>
											</td>

										   <sec:authorize access="hasRole('SALER')">
											   <td><a href="<c:url value='/saler/edit/product?id=${product.id}'/>" title="Chỉnh sửa"><i class="bi bi-pencil-square bs-bx p-2"></i></a></td>
										   </sec:authorize>
									    </tr>
			    					</c:forEach> 
		   						</tbody>     						   
					  		</table>        
	         	 	  </div> 
	         	 	  <input id="page" type="hidden" value="" name="page"/>
					<input id="name" type="hidden" value="" name="name"/>

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
	var urlParams = new URLSearchParams(window.location.search);
	var paramValue = urlParams.get('name');
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
					if(paramValue!==null){
						$('#name').val(paramValue);
					}else{
						$('#name').prop('disabled', true);
					}
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

	var divSearch = $('#content-search');
	$('#search-product').keyup(function(){
		var count = 0;
		divSearch.hide();
		divSearch.empty();
//		var data ={"name":$('#search-product').val()}
		var value = $('#search-product').val();
		var data = "name="+value;
		if(value !== ''){
			$.ajax({
				url:'${urlProduct}',
				type:'GET',
				data:data,
	//			contentType:'application/json',
				dataType:'json',
				success: function (result) {
					divSearch.show();

					// Lặp qua mảng JSON chứa các tên sản phẩm
					$.each(result, function(index, productName) {
						// Tạo thẻ a và thêm nội dung vào thẻ a
						var productLink = $("<a>").html(productName.name);

						// Bổ sung class "x" vào thẻ a
						productLink.addClass("text-dark text-decoration-none d-block m-3");

						//thêm thuộc tính href
						productLink.attr("href", "${urlList}"+"?name="+value+"&page=1");

						// Thêm thẻ a vào thẻ div
						divSearch.append(productLink);
						count ++;
						if (count >= 5) {
							return false; // Dừng vòng lặp khi count đạt giá trị 5
						}
					});

				},
				error: function (xhr,status,error) {
					console.log('không gửi được');
					console.log(xhr.responseText);
				}
			})
		}else{
			divSearch.hide();
			divSearch.empty();
		}
		console.log(data);

	})
	
		  

	</script>
	
	
	
</body>
</html>