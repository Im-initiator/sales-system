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
<c:url value="${urlEdit}" var="urlEdit"/>
<c:url value="/order/search" var="search"/>
<c:url value="/api/censor/order" var="orderAPI"/>
<c:url value="/api/shipper/order" var="shipperAPI"/>
<c:url value="/shipper/order/delivery" var="shipperdelivery"/>

<div class="container">
		<div class="row">    
			<div class="col-md-12">
				<div class="position-fixed  p-3 alert" id="errorSystem" role="alert" style="z-index:999;display:none" >		
				  			 Thông báo ở đây
		    	</div>
				<form id="formSubmit" method="GET">
					<div class="table-responsive">    							       
							<table id="mytable" class="table table-bordred table-striped">         							
								<thead>

										<th><input type="checkbox" id="checkall"/>
											<sec:authorize access="hasAnyRole('SALER','MANAGER')">
												<button type="button" class="btn text-danger" id="delete-product"> <i class="bi bi-trash fs-4"></i></button>
											</sec:authorize>
										</th>

							         <th>Product name</th>
							         <th>Code</th>
							          <th>Status</th>
							         <th>Time Order</th>
							         <th>PhoneNumber</th>
							         <th>Transport</th>
							         <th></th>
								</thead>
		    					<tbody>
									<c:forEach items="${model}" var ="order">
									    <tr aria-disabled="false">
											<td><input type="checkbox" value="${order.id}" id = "${order.id}"/></td>
										    <td>${order.product.name}</td>
										    <td>${order.code}</td>
										    <td>${order.status}</td>
										    <td>${order.createDate}</td>
										    <td>${order.phoneNumber}</td>
										    <td>${order.transport.name}</td>
										    <sec:authorize access="hasAnyRole('MANAGER')">
												<td><a href="${urlEdit}?id=${order.id}" title="Edit" ><i class="fa-regular fa-pen-to-square"></i></a> </td>
											</sec:authorize>
											<sec:authorize access="hasAnyRole('SALER')">
												<td><a href="<c:url value='${urlUpdate}?id=${order.id}'/> " title="Detail" class="get-view" ></a> </td>
											</sec:authorize>
											<sec:authorize access="hasAnyRole('SHIPPER')">
												<td><a href="${shipperdelivery}?id=${order.id}" title="Detail" ><i class="fa-regular fa-pen-to-square"></i></a> </td>
											</sec:authorize>
									    </tr>
			    					</c:forEach>
		   						</tbody>     						   
					  		</table>        
	         	 	  </div> 
	         	 	  <input id="page" type="hidden" value="" name="page"/>
					  <c:if test="${not empty c}">
						  <input id="name_search" type="hidden" value="${c}" name="c"/>
					  </c:if>
					  <input id="userName" type="hidden" name="n" value="${user.userName}">
					 <sec:authorize access="hasAnyRole('CENSOR')">
						 <div class="d-flex ms-5 mt-3 flex-row-reverse me-5">
							 <button type="submit" class="btn btn-primary mt-2" name="" id="sub-btn"></button>
						 </div>
					 </sec:authorize>
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
		var uri = window.location.pathname;
		if (uri.endsWith('censor/order')){
			$('#sub-btn').text('Add order');

		}else if (uri.endsWith('/censor/shipper/order')){
			$('#sub-btn').text('Remove Order')
		}
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
	var totalpage = ${totalPage}
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

	var element = $('#errorSystem');
	var timerId;
	var divSearch = $('#content-search');
	$('#search-product').keyup(function(){
		clearTimeout(timerId); //
		timerId = setTimeout(function() {
			var count = 0;
			divSearch.hide();
			divSearch.empty();
//		var data ={"name":$('#search-product').val()}
			var value = $('#search-product').val();
			var data = "c="+value;
			let url = window.location.href;
			//url = url.substring(7);
			console.log(url)
			if(value !== ''){
				$.ajax({
					url:"${search}",
					type:'GET',
					data:data,
					//			contentType:'application/json',
					dataType:'json',
					success: function (result) {
						divSearch.show();

						// Lặp qua mảng JSON chứa các tên sản phẩm
						$.each(result, function(index, order) {
							// Tạo thẻ a và thêm nội dung vào thẻ a
							var userLink = $("<a>").html(order.code);

							// Bổ sung class "x" vào thẻ a
							userLink.addClass("text-dark text-decoration-none d-block m-3");

							//thêm thuộc tính href
							userLink.attr("href", url+"?c="+value);

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
						//console.log(xhr.responseText);
					}
				})
			}else{
				divSearch.hide();
				divSearch.empty();
			}
			console.log(data);

		}, 2000);
	})

	function update(url,flag){
		var ob = {};
		var data = [];
		$('#mytable tbody input[type="checkbox"]:checked').each(function() {
			data.push($(this).val());
		});
		ob["orderId"] = data;
		ob["userName"] = "${user.userName}"
		ob["flag"] = flag;
		console.log(ob);
		$.ajax({
			url:url,
			type:'PUT',
			contentType:'application/json',
			data:JSON.stringify(ob),
			success: function(result){
				element.removeClass().addClass("alert alert-success");
				element.text(result);
				element.show();
				setTimeout(function() {
					element.hide(); // Ẩn thông báo sau 3 giây
					element.removeClass("alert alert-success");
					location.reload();
				}, 2000);
			},
			error: function(xhr,status,error){
				var textbody = xhr.responseText; //lấy nội dung phản hồi từ body
				element.removeClass().addClass("alert alert-danger");
				if(!textbody.trim().startsWith("Error")){
					element.text(error);
				}else{
					element.text(textbody);
				}
				element.show();
				setTimeout(function() {
					element.hide(); // Ẩn thông báo sau 3 giây
					element.removeClass("alert alert-danger");
				}, 2000);
			}
		})
	}

	var uri = window.location.pathname;
	if (uri.endsWith('censor/order')){
		$('#sub-btn').click(function (e){
			e.preventDefault();
			var url = "${orderAPI}";
			update(url,true)
		})

	}else if (uri.endsWith('/censor/shipper/order')){
		$('#sub-btn').click(function (e){
			e.preventDefault();
			var url = "${orderAPI}";
			update(url,false)

		})
	}

	var currentURL = window.location.href;
	var url = new URL(currentURL);
	var searchParams = new URLSearchParams(url.search);
	var param = searchParams.get('t');
	const re = $('#get-view');
	if (param==="get-all"){
		$('.get-view').each(function() {
			$(this).attr('title', 'Detail');
			$(this).append('<i class="fa-solid fa-circle-info"></i>');
		});
	}else if (param==="wait-confirm"||param==="delivery-confirm"){
		$('.get-view').each(function() {
			re.attr('title', 'Update');
			re.append('<i class="fa-regular fa-pen-to-square"></i>');
		});
	}
	</script>
	

	
</body>
</html>