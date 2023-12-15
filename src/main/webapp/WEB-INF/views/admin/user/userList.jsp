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
<c:url var="urlRegister" value="/api/user"/>
<c:url var="urlUser" value="/api/admin/user"/>
<c:url var="urlList" value="/admin/user"/>

<div class="container">
		<div class="row">    
			<div class="col-md-12">
				<div class="position-fixed  p-3 alert" id="errorSystem" role="alert" style="z-index:999;display:none" >		
				  			 Thông báo ở đây
		    	</div>
		    	
		    	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
				  Add new
				</button>
				<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">Add new Uer</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        	<form id="formAdd">
							  <div class="mb-3">
							    <label for="userName" class="form-label">User Name</label>
							    <input type="text" class="form-control" name="userName" id="userName" aria-describedby="emailHelp">							    
							  </div>
							  <div class="mb-3">
							    <label for="password" class="form-label">Password</label>
							    <input type="password" class="form-control" name="password" id="password">
							  </div>	  	  
							  <div class="mb-3" id="RoleUser">
								  <c:forEach items="${Role}" var="item">
									  <input class="form-check-input" type="checkbox" value="${item.code}" id="${item.code}">
									  <label class="form-check-label" for="${item.code}">
									    ${item.name}
									  </label>							  
								 </c:forEach>								 
								</div>		  
							  <div class="mb-3">
							    <label for="fullName" class="form-label">Full Name</label>
							    <input type="text" class="form-control" id="fullName" name="fullName">
							  </div>	
							  <div class="mb-3">
							      <label for="status1" class="form-label">Status </label>
							      <input class="form-check-input" type="radio" name="status" id="status1" value="1" checked>
								  <label class="form-check-label" for="status1">
								    1
								  </label>
								  <input class="form-check-input" type="radio" name="status" value="0" id="status0">
								  <label class="form-check-label" for="status0">
								    0
								  </label>
							  </div>	
							  <div class="mb-3">
							    <label for="address" class="form-label">Address</label>
							    <input type="text" class="form-control" id="address" name="address">
							  </div>	
							  <div class="mb-3">
							    <label for="phoneNumber" class="form-label">Phone Number</label>
							    <input type="text" class="form-control" id="phoneNumber" name="phoneNumber">
							  </div>							 
							</form>				        				        				        				        				        				      
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				        <button id="submitAddUser" type="button" class="btn btn-primary">Save</button>
				      </div>
				    </div>
				  </div>
				</div>
			
				<form action="<c:url value='/admin/user'/>" id="formSubmit" method="GET"> 
					<div class="table-responsive">    							       
							<table id="mytable" class="table table-bordred table-striped">         							
								<thead>    								             
							         <th><input type="checkbox" id="checkall"/><button type="button" class="btn text-danger" id="delete-user"> <i class="bi bi-trash fs-4"></i></button></th>
							         <th>User name</th>
							         <th>Full Name</th>
							          <th>email</th>						
							         <th>Status</th>							  
							         <th>Address</th>   
							         <th>Phone Number</th>
							         <th></th>
								</thead>
		    					<tbody>
									<c:forEach items="${model.list}" var ="user"> 
									    <tr>
									    	<td><input type="checkbox" value="${user.userName}"/></td>
										    <td>${user.userName}</td>   
										    <td>${user.fullName}</td>
										    <td>${user.email}</td>										   
										    <td>${user.status}</td>   
										    <td>${user.address}</td>
										    <td>${user.phoneNumber}</td>
										    <td><a href="<c:url value='/admin/edit/user?userName=${user.userName}'/>" title="Chỉnh sửa"><i class="bi bi-pencil-square bs-bx p-2"></i></a></td>									    										   							
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
	
	<script type="text/javascript">

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

	function getCheckboxValues() {
		  const checkboxes = document.querySelectorAll("#RoleUser input[type='checkbox']");
		  const values = [];

		  checkboxes.forEach((checkbox) => {
		    if (checkbox.checked) {
		      values.push(checkbox.value);
		    }
		  });

		  console.log(values);
		  return values;
	}
    

	$('#submitAddUser').click(function(e){
		e.preventDefault();
		var data = {};
		var formData = $('#formAdd').serializeArray();
		
		$.each(formData,function(i,v){
			data[""+v.name+""] = v.value;
		});
		data["roles"] = getCheckboxValues();
		console.log(data);
		addUser(data);

	});

	const element = $('#errorSystem');
		function addUser(data){
			$.ajax({
				url:'${urlRegister}',
				type:'POST',
				contentType:'application/json',
				data:JSON.stringify(data),
				success: function(result){
					$('#errorSystem').removeClass().addClass("alert alert-success");
					$('#errorSystem').text("Thêm tài khoản thành công");
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

		//delete user
		
		  $('#delete-user').click(function(e) {
			  e.preventDefault();
			    var data = [];
			    $('#mytable tbody input[type="checkbox"]:checked').each(function() {
			    	data.push($(this).val());
			    });

			    console.log(data);
			    console.log(JSON.stringify(data));
			    $.ajax({
				    url:'${urlUser}',
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
				url:'${urlUser}',
				type:'GET',
				data:data,
				//			contentType:'application/json',
				dataType:'json',
				success: function (result) {
					divSearch.show();

					// Lặp qua mảng JSON chứa các tên sản phẩm
					$.each(result, function(index, user) {
						// Tạo thẻ a và thêm nội dung vào thẻ a
						var userLink = $("<a>").html(user.userName);

						// Bổ sung class "x" vào thẻ a
						userLink.addClass("text-dark text-decoration-none d-block m-3");

						//thêm thuộc tính href
						userLink.attr("href", "${urlList}"+"?name="+value+"&page=1");

						// Thêm thẻ a vào thẻ div
						divSearch.append(userLink);
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