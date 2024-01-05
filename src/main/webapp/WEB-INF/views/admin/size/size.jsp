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
<c:url var="urlSize" value="/api/manager/size"/>
<c:url var="urlList" value="/manager/size"/>

<div class="container">
		<div class="row">    
			<div class="col-md-12">
				<div class="position-fixed  p-3 alert" id="errorSystem" role="alert" style="z-index:999;display:none" >		
				  			 Thông báo ở đây
		    	</div>
				<sec:authorize access="hasRole('MANAGER')">
					<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
						Add new
					</button>
					<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Add new Category</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<form id="formAdd">
										<div class="mb-3">
											<label for="size_name" class="form-label">Name</label>
											<input type="text" class="form-control" name="name" id="size_name">
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
									<button id="submitCategory" type="button" class="btn btn-primary" data-bs-dismiss="modal">Save</button>
								</div>
							</div>
						</div>
					</div>
				</sec:authorize>
				<form action="<c:url value='${link}'/>" id="formSubmit" method="GET">
					<div class="table-responsive">    							       
							<table id="mytable" class="table table-bordred table-striped">         							
								<thead>    								             
							         <th><input type="checkbox" id="checkall"/><button type="button" class="btn text-danger" id="delete-product"> <i class="bi bi-trash fs-4"></i></button></th>
							         <th>Name</th>
							        <sec:authorize access="hasRole('MANAGER')">
										<th></th>
									</sec:authorize>
								</thead>
		    					<tbody>
									<c:forEach items="${model}" var ="size" varStatus="loop">
									    <tr>									    	
									    	<td><input type="checkbox" value="${size.id}" id ="${size.id}"/></td>
									    	<input type="hidden" class="category${loop.index}" value="${size.id}"/>
										    <td class="category${loop.index}">${size.name}</td>
										    <sec:authorize access="hasRole('MANAGER')">
												<td><i class="bi bi-pencil-square bs-bx p-2 edit-category" data-bs-toggle="modal" data-bs-target="#ModelEdit"  id="${loop.index}"></i></td>
											</sec:authorize>
									    </tr>
			    					</c:forEach> 
		   						</tbody>     						   
					  		</table>        
	         	 	  </div> 
	         	 	  <input id="page" type="hidden" value="" name="page"/>
					<input id="name_search" type="hidden" value="" name="name"/>
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
	<!-- Modal edit  -->
		<sec:authorize access="hasRole('MANAGER')">
			<div class="modal fade" id="ModelEdit" tabindex="-1" aria-labelledby="exampleModalLabel2" aria-hidden="true">
				  <div class="modal-dialog">
					<div class="modal-content">
					  <div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel2">Add new Category</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					  </div>
					  <div class="modal-body">
							<form id="formUpdate">
							  <div class="mb-3">
								<label for="x_name" class="form-label">Name</label>
								<input type="text" class="form-control" name="name" id="x_name" aria-describedby="emailHelp" value="">
							  </div>
							  <input type="hidden" id = "id" name = "id" value=""/>
							</form>
					  </div>
					  <div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						<button id="update" type="button" class="btn btn-primary" data-bs-dismiss="modal">Update</button>
					  </div>
					</div>
				  </div>
				</div>
		</sec:authorize>
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
            	if(page!==currentPage){
					if(paramValue!==null){
						$('#name_search').val(paramValue);
					}else{
						$('#name_search').prop('disabled', true);
					}
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
    

	$('#submitCategory').click(function(e){
		e.preventDefault();
		var data = {};
		var formData = $('#formAdd').serializeArray();
		
		$.each(formData,function(i,v){
			data[""+v.name+""] = v.value;
		});
		console.log(data);
		addCategory(data);

	});
 
		function addCategory(data){
			$.ajax({
				url:'${urlSize}',
				type:'POST',
				contentType:'application/json',
				data:JSON.stringify(data),
				success: function(result){
					$('#errorSystem').removeClass().addClass("alert alert-success");
					$('#errorSystem').text("Thêm kích thước thành công");
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
		}


		$('#update').click(function(e){
			e.preventDefault();
			var data = {};
			var formData = $('#formUpdate').serializeArray();
			
			$.each(formData,function(i,v){
				data[""+v.name+""] = v.value;
			});
			console.log(data);
			updateCategory(data);

		});
	 
			function updateCategory(data){
				$.ajax({
					url:'${urlSize}',
					type:'PUT',
					contentType:'application/json',
					data:JSON.stringify(data),
					success: function(result){
						$('#errorSystem').removeClass().addClass("alert alert-success");
						$('#errorSystem').text("Update kích thước thành công");
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
			}
		


		
		//delete category
		
		  $('#delete-product').click(function(e) {
			  e.preventDefault();
			    var data = [];
			    $('#mytable tbody input[type="checkbox"]:checked').each(function() {
			    	data.push($(this).val());
			    });

			    console.log(data);
			    console.log(JSON.stringify(data));
			    $.ajax({
				    url:'${urlSize}',
					type:'DELETE',
					contentType:'application/json',
					data:JSON.stringify(data),
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
     
       		    
	var categoryName = $('#categoryName');
 	//thực hiển mở modal khi click vào icon edit
     $(".edit-category").each(function(index) {
    	  $(this).on("click", function() {  		 
    		  var id = $(this).attr("id");
     		    console.log(id);
     		   var clas = "category"+id;
     		   var element = $('.'+clas);
     		  console.log(element);
       		 var categoryId = element[0].value;
     		   $('#id').val(categoryId);
     		   $('#name').val(element[1].textContent);
      		   $('#code').val(element[2].textContent); 		
     		    console.log(this);
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
				url:'${urlSize}',
				type:'GET',
				data:data,
				//			contentType:'application/json',
				dataType:'json',
				success: function (result) {
					divSearch.show();

					// Lặp qua mảng JSON chứa các tên sản phẩm
					$.each(result, function(index, size) {
						// Tạo thẻ a và thêm nội dung vào thẻ a
						var productLink = $("<a>").html(size.name);

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