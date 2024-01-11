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
<c:url var="urlShop" value="/api/manager/shop"/><%--/api/saler/product--%>
<c:url var="urlList" value="/manager/shop"/><%--/manager/product--%>
<div class="container">
		<div class="row">    
			<div class="col-md-12">
				<div class="position-fixed  p-3 alert" id="errorSystem" role="alert" style="z-index:999;display:none" >		
				  			 Thông báo ở đây
		    	</div>
		    	
			<%--	<sec:authorize access="hasRole('SALER')">
					<a href="<c:url value='/saler/edit/shop'/>" class="btn btn-primary">Add new</a>
				</sec:authorize>--%>
			
				<form action="${urlList}" id="formSubmit" method="GET">
					<div class="table-responsive">    							       
							<table id="mytable" class="table table-bordred table-striped">
								<thead>    								             
							         <th><input type="checkbox" id="checkall"/><button type="button" class="btn text-danger" id="delete-product"> <i class="bi bi-trash fs-4"></i></button></th>
							         <th>Name</th>
							         <th>Reviews</th>
							         <th>Description</th>
							         <th>Thumbnail</th>
							         <th>address</th>
									 <th></th>
								</thead>
		    					<tbody>
									<c:forEach items="${model}" var ="shop">
									    <tr>
									    	<td><input type="checkbox" value="${shop.id}"/></td>
											<td>
												<div class="td-container">
													<div class="td-content">
															${shop.name}
													</div>
												</div>
											</td>
											<td>${shop.reviews}</td>
										   	 <td>
												 <div class="td-container">
													 <div class="td-content">
														 ${shop.shortDescription}
												 	</div>
												 </div>
											 </td>
											<td>
												<div class="td-container">
													<div class="td-content">
															${shop.thumbnail}
													</div>
												</div>
											</td>

										    <td>
												<div class="td-container">
													<div class="td-content">
														${shop.address}
													</div>
												</div>
											</td>
											   <td><a href="<c:url value='/manager/shop/detail?id=${shop.id}'/>" title="Chi tiết"><i class="bi bi-exclamation-circle-fill"></i></a></td>
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
	var currentPage = ${page}+1;
	var totalItem = ${totalItem};
	var limit = ${limit}
	var totalpage = ${totalPage}
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
		
		  $('#delete-product').click(function(e) {
			  e.preventDefault();
			    var ids = [];
			    $('#mytable tbody input[type="checkbox"]:checked').each(function() {
			    	ids.push($(this).val());
			    });

			    console.log(ids);
			    console.log(JSON.stringify(ids));
			    $.ajax({
				    url:'${urlShop}',
					type:'DELETE',
					contentType:'application/json',
					data:JSON.stringify(ids),
					success: function(result){
						$('#errorSystem').removeClass().addClass("alert alert-success");
						$('#errorSystem').text(result);
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
				url:'${urlShop}',
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