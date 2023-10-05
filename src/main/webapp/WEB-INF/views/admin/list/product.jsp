<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var="urldelete" value="/admin/product"/>
<c:url var="search" value="/admin/product?type=search"/>
<c:url var="urllist" value='/admin/product?sortName=name&sortBy=ASC&page=1&limit=10&type=list'/>
<!DOCTYPE html>
<html>
<head>
<script src="<c:url value='/template/famework/jquery.js'/>"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Sản phẩm</title>
</head>
<body>
	<div class="container">
		<div class="row">    
			<div class="col-md-12">
				<div class="position-fixed  p-3 alert" id="messa" role="alert" style="right:5%;z-index:999;display:none" >		
				  			 Thông báo ở đây
		    	</div>
				<form action="<c:url value='/admin/product'/>" id="form-submit" method="GET"> 
					<div class="table-responsive">    
							<a href="<c:url value="/admin/product?type=add" />" class="text-primary ms-3" id=themoi title="thêm mới sản phẩm">Thêm mới</a>         
							<table id="mytable" class="table table-bordred table-striped">         							
								<thead>    								             
							         <th><input type="checkbox" id="checkall"/><button type="button" class="btn text-danger" id="delete-product"> <i class="bi bi-trash fs-4"></i></button></th>
							         <th>Tên sản phẩm</th>
							         <th>Đánh giá</th>
							         <th>Mô tả ngắn</th>
							         <th>Số lượng có</th>
							         <th>Nội dung</th>
							         <th>Số lượng bán</th>   
							         <th>chỉnh sửa</th>
								</thead>
		    					<tbody>
									<c:forEach items="${PRODUCT}" var ="product">
									    <tr>
										    <td><input type="checkbox" class="checkthis" value="${product.getId()}" /></td>
										    <td>${product.getName()}</td>
										    <td>${product.getPrize()}</td>
										    <td>${product.getShortDescription()}</td>
										    <td>${product.getQuantity()}</td>
										    <td style="word-wrap: break-all">${product.getContent()}</td>
										    <td>${product.getSellNumber()}</td>
										    <td><a href="<c:url value='/admin/product?type=edit&id=${product.getId()}'/>" title="Chỉnh sửa"><i class="bi bi-pencil-square bs-bx p-2"></i></a></td>												    
									    </tr>
		    						</c:forEach>
		   						</tbody>     						   
					  		</table>        
	         	 	  </div>    
	         	 	
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
		
		 var totalPage = ${Page.getTotalPage()}
		 var currentPage = ${Page.getPage()}
		 var limit = ${Page.getLimit()}
		 if($('#idsearch').val()!==""){
			 $('#paging').hide();
			 $('#themoi').text('Trở lại');
			 $('#themoi').attr("href","${urllist}");
		 }
		 $(function () {
		        window.pagObj = $('#pagination').twbsPagination({
		            totalPages: totalPage,
		            visiblePages: 10,
		            startPage:currentPage,
		            onPageClick: function (event, page) {
		            	if(page != currentPage){
		            		console.log(page);
		            		$('#type').val("list");
		            		$('#sortName').val("name");
		            		$('#sortBy').val("ASC");
		            		$('#page').val(page);
		            		$('#limit').val(limit);
		            		if($('#idsearch').val()!==""){
		            			$('#idsearch').val($('#idsearch').val());
		            			console.log($('#idsearch').val())
		            		}
		            		$('#form-submit').submit();	    
		            	}
		                
		            }
		        }).on('page', function (event, page) {
		            console.info(page + ' (from event listening)');
		        });
		    });
		 
		 $(document).ready(function() {
			    $('#form-submit input[type="checkbox"]').click(function() {
			      if ($('#form-submit input[type="checkbox"]:checked').length > 0) {
			        $('#delete').show();
			      } else {
			        $('#delete').hide();
			      }
			    });
		  });
		 
		 $('#delete-product').click(function(e){
			e.preventDefault();
			var data = {};
			//duyệt qua tbody và kiểm tra checkbox nào được select và get giá trị cho vào mảng
			var ids = $('tbody input[type=checkbox]:checked').map(function(){
				return $(this).val();
			}).get();
			data['ids'] = ids;
			deleteproduct(ids);
	 
		 })
		 
		 var message = $('#messa');
		 
		 function deleteproduct(data){
			 $.ajax({
					url:'${urldelete}',
					type:'DELETE',
					data:JSON.stringify(data),
					contentType:'application/json',
					dataType:'json',
					success:function(result){
						message.addClass('alert-'+result.alert);
						message.text(result.message);
						message.show();
						setTimeout(() => {
							message.hide();
							message.removeClass('alert-'+result.alert);
							window.location.href = "${urllist}";
						},1000);
					},
					error: function(error) {
						message.addClass('alert-danger');
						message.text('Không gửi được yêu cầu');
						message.show();
						setTimeout(() => {
							message.hide();
							message.removeClass('alert-danger');
						},3000);
					}					
				})			 
		 }
//	var x = "${urldelete}";
//	$("#form-search").attr("action",x+subPath+"?type=list");
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
			url:'/Sales/admin/product?type=search',
			type:'GET',
			data:data,
			contentType:'application/json',
			dataType:'json',
			success: function (result) {
				divSearch.show();
				
				// Lặp qua mảng JSON chứa các tên sản phẩm
				$.each(result, function(index, productName) {
				    // Tạo thẻ a và thêm nội dung vào thẻ a
				    var productLink = $("<a>").html(productName);
				    
				    // Bổ sung class "x" vào thẻ a
				    productLink.addClass("text-dark text-decoration-none d-block m-3");
				    
				    //thêm thuộc tính href
				    productLink.attr("href", "${urllist}"+"&name="+value);
				    
				    // Thêm thẻ a vào thẻ div
				    divSearch.append(productLink);
				    count ++;
				    if (count >= 5) {
				        return false; // Dừng vòng lặp khi count đạt giá trị 5
				    }
				});	
				
				},
			error: function () {
				console.log('không gửi được');
				}			
			})
		}else{
			divSearch.hide();
			divSearch.empty();
		}
		console.log(data);

	})
	//$('#content-search')
		 
	console.log(subPath);
		 
		
	</script>
</body>
</html>
