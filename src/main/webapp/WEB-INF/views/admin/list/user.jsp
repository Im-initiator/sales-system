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
	<div class="container">
		<div class="row">    
			<div class="col-md-12">
				<table id="mytable" class="table table-bordred table-striped">         							
					<thead>    								             
				         <th><input type="checkbox" id="checkall"/><button type="button" class="btn text-danger" id="delete-product"> <i class="bi bi-trash fs-4"></i></button></th>
				         <th>User Name</th>
				         <th>Full Name</th>
				         <th>Password</th>
				         <th>Status</th>
				      
					</thead>
   					<tbody>
						<c:forEach items="${model.list}" var ="user"> 
						    <tr>
						    	<td><input type="checkbox"/></td>
							    <td>${user.userName}</td>   
							    <td>${user.fullName}</td>
							    <td>${user.password}</td>
							    <td>${user.status}</td>
						    </tr>
    					</c:forEach> 
  						</tbody>     						   
		  		</table>        
      	 	</div>
      	 	
		</div>
		
	</div>
	
</body>
</html>