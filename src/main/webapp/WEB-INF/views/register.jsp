<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
</head>
<body>
<c:url var="urlRegister" value="/dang-ky?action=register"/>

<section class="vh-100 bg-image"
  style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
  <div class="mask d-flex align-items-center h-100 gradient-custom-3">
    <div class="container h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
          <div class="card" style="border-radius: 15px;">
            <div class="card-body p-5">
              <h2 class="text-uppercase text-center mb-5">Create an account</h2>
						
					<div class="alert" id="errorSystem" role="alert">
				  		
					</div>
					
              <form id ="form-register">

                <div class="form-outline mb-4">
                  <input type="text"  class="form-control form-control-lg" name="name" placeholder="User name"/>
                  <label class="form-label" for="form3Example1cg"></label>
                </div>
                
                 <div class="form-outline mb-4">
                  <input type="text"  class="form-control form-control-lg" name="fullname" placeholder=" Your Full Name"/>
                  <label class="form-label" for=""></label>
                </div>
                
                 <div class="form-outline mb-4">
                  <input type="text"  class="form-control form-control-lg" name="address" placeholder="Your Address"/>
                  <label class="form-label" for=""></label>
                </div>

                <div class="form-outline mb-4">
                  <input type="email"  class="form-control form-control-lg" name="email" placeholder="Your Email"/>
                  <label class="form-label" for="form3Example3cg"></label>
                </div>

	 			<div class="form-outline mb-4">
                  <input type="text"  class="form-control form-control-lg" name="phonenumber" placeholder="Your phone number"/>
                  <label class="form-label" for=""></label>
                </div>

                <div class="form-outline mb-4">
                  <input type="password"  class="form-control form-control-lg" id="password" name="password"  placeholder="Password" />
                  <label class="form-label" for="form3Example4cg"></label>
                </div>

                <div class="form-outline mb-4">
                  <input type="password"  class="form-control form-control-lg" id="repeatPassword" placeholder="Repeat your password"/>
                  <label class="form-label" for="form3Example4cdg"></label>
                </div>

                <div class="form-check d-flex justify-content-center mb-5">
                  <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3cg" />
                  <label class="form-check-label" for="form2Example3g">
                    I agree all statements in <a href="#!" class="text-body"><u>Terms of service</u></a>
                  </label>
                </div>

                <div class="d-flex justify-content-center">
                  <button type="button" id="register"
                    class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Register</button>
                </div>

                <p class="text-center text-muted mt-5 mb-0">Have already an account? <a href="<c:url value='/dang-nhap?action=login'/>"
                    class="fw-bold text-body"><u>Login here</u></a></p>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<script type="text/javascript">
	$('#register').click(function(e){
		e.preventDefault();
		var data = {};
		var formData = $('#form-register').serializeArray();
		$.each(formData,function(i,v){
			data[""+v.name+""] = v.value;
		});
		console.log(data);
		addUser(data);

	});

		function addUser(data){
			$.ajax({
				url:'${urlRegister}',
				type:'POST',
				contentType:'application/json',
				data:JSON.stringify(data),
				dataType: 'json',
				success: function(result){
					$('#errorSystem').removeClass().addClass("alert alert-"+result.alert+"");
					$('#errorSystem').text(result.message);
					$('#errorSystem').show();
				},
				error: function(error){
					$('#errorSystem').removeClass().addClass("alert alert-danger");
					$('#errorSystem').text("Có lỗi hệ thống xảy ra")
					$('#errorSystem').show();
				}
			});
		}

		

</script>

</body>
</html>