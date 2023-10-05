<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglib.jsp" %>
    <c:url var="urlLogin" value="/dang-nhap?action=loging"/>
     <c:url var="home" value="/trang-chu"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
	
<body>
<section class="vh-100" style="background-color: #9A616D;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col col-xl-10">
        <div class="card" style="border-radius: 1rem;">
          <div class="row g-0">
            <div class="col-md-6 col-lg-5 d-none d-md-block">
              <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/img1.webp"
                alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
            </div>
            <div class="col-md-6 col-lg-7 d-flex align-items-center">
              <div class="card-body p-4 p-lg-5 text-black">
	             

                <form id ="formLogin" action="j_spring_security_check" method="POST">

                  <div class="d-flex align-items-center mb-3 pb-1">
                    <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                    <span class="h1 fw-bold mb-0">Logo</span>
                  </div>

                  <h5 class="fw-normal mb-3 pb-3" id="alertlogin" style="letter-spacing: 1px;">Sign into your account</h5>
                   <div class="alert" id="errorSystem" role="alert">
					  		
				  </div>

                  <div class="form-outline mb-4">
                    <input type="text"  class="form-control form-control-lg" name = "j_username" placeholder="User name" />
                    <label class="form-label" for="form2Example17"></label>
                  </div>

                  <div class="form-outline mb-4">
                    <input type="password" id="form2Example27" class="form-control form-control-lg" name="j_password" placeholder="Password"/>
                    <label class="form-label" for="form2Example27"></label>
                  </div>
                  
                  <div class="form-outline mb-4 bg-danger text-center p-3 rounded">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8080/Sales/login-google&response_type=code
   						 &client_id=129913137786-ptsig5boh2erm7i23l6jl1pp68jb8i5v.apps.googleusercontent.com&approval_prompt=force" 
    						class="text-decoration-none text-white d-block">Login with Google</a>
                  </div>
                  
                   <div class="form-outline mb-4 bg-primary text-center p-3 rounded">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8080/Sales/login-google&response_type=code
   						 &client_id=129913137786-ptsig5boh2erm7i23l6jl1pp68jb8i5v.apps.googleusercontent.com&approval_prompt=force" 
    						class="text-decoration-none text-white d-block"> Login with FaceBook</a>
                  </div>
                 

                  <div class="pt-1 mb-4">
                    <button class="btn btn-dark btn-lg btn-block" type="submit" id="buttonLogin">Login</button>
                  </div>

                  <a class="small text-muted" href="#!">Forgot password?</a>
                  <p class="mb-5 pb-lg-2" style="color: #393f81;">Don't have an account? <a href="<c:url value='/register'/>"
                      style="color: #393f81;">Register here</a></p>
                  <a href="#!" class="small text-muted">Terms of use.</a>
                  <a href="#!" class="small text-muted">Privacy policy</a>
                </form>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<script type="text/javascript">
	<% if (request.getParameter("incorrectAccount") != null) { %>
		 var alert = $('#alertlogin');
		 alert.text("Account does not exist");
		 alert.css("color", "red");
		
	<% } %>
</script>

</body>
</html>