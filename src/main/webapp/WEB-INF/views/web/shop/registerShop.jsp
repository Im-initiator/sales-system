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
<c:url var="urlRegister" value="/web/registershop"/>

<section class="vh-100 bg-image"
  style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
  <div class="mask d-flex align-items-center h-100 gradient-custom-3">
    <div class="container h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
          <div class="card" style="border-radius: 15px;">
            <div class="card-body p-5">
              <h2 class="text-uppercase text-center mb-5">Create a shop</h2>
						
					<div class="alert" id="errorSystem" role="alert">
				  		
					</div>
					
              <form id ="form-register" enctype="multipart/form-data">
                <div class="form-outline mb-4">
                  <label class="form-label fs-3" for="name">Shop Name</label>
                  <input type="text" id = "name"  class="form-control form-control-lg" name="name" placeholder="Shop name"/>

                </div>

                <div class="form-outline mb-4">
                  <label class="form-label fs-3" for="name">Phone Number</label>
                  <input type="number" id = "phoneNumber"  class="form-control form-control-lg" name="phoneNumber" placeholder="Phone Number"/>

                </div>

                <div class="form-outline mb-4">
                  <label class="form-label fs-3" for="name">Shop Email</label>
                  <input type="email" id = "email"  class="form-control form-control-lg" name="email" placeholder="Shop Email"/>

                </div>
                
                 <div class="form-outline mb-4">
                   <label class="form-label fs-3" for="thumbnail">Shop's Thumbnail</label>
                  <input type="file" id="thumbnail"  class="form-control form-control-lg" name="imgThumbnail" placeholder=" Your Thumbnail"/>

                </div>
                
                 <div class="form-outline mb-4">
                   <label class="form-label fs-3" for="address">Shop's Address</label>
                  <input type="text"  id="address" class="form-control form-control-lg" name="address" placeholder="Your Address"/>

                </div>

                <div class="form-outline mb-4">
                  <label class="form-label fs-3" for="shortDescription">Short Description</label>
                  <textarea id="shortDescription"  class="form-control form-control-lg" name="shortDescription" placeholder="Short description"></textarea>
                </div>

                <div class="d-flex justify-content-center">
                  <button type="button" id="register" class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Register</button>
                </div>

                <p class="text-center text-muted mt-5 mb-0"> back home <a href="<c:url value='/home'/>"
                    class="fw-bold text-body"><u>Home</u></a></p>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<script type="text/javascript">

    var element = $('#errorSystem');
   // var id = $('#p-id').val();
    var editor='';
    $( document ).ready(function() {
      editor = CKEDITOR.replace('shortDescription');

    });


	$('#register').click(function(e){
		e.preventDefault();
        var form = document.getElementById('form-register');
        var formData = new FormData(form);
        formData.delete('shortDescription');
        formData.append("shortDescription",editor.getData());
        console.log(formData)
		addShop(formData);

	});

		function addShop(data){
			$.ajax({
				url:'${urlRegister}',
				type:'POST',
				data:data,
                processData: false,
                contentType: false,
				success: function(result){
					$('#errorSystem').removeClass().addClass("alert alert-success");
					$('#errorSystem').text("Đăng ký thành công");
					$('#errorSystem').show();
					element.show();
					 setTimeout(function() {
					    	element.hide(); // Ẩn thông báo sau 3 giây
					    	element.removeClass("alert alert-success");
					 }, 3000);

					
				},
				error: function(xhr,status,error){

					var textbody = xhr.responseText; //lấy nội dung phản hồi từ body
					$('#errorSystem').removeClass().addClass("alert alert-danger");
					if(textbody.trim()===""){
						$('#errorSystem').text("Đăng ký không thành công");
					}else  if(xhr===400){
                      $('#errorSystem').text("Bạn chưa đăng nhập");
                    }else {
						$('#errorSystem').text(textbody);
					}

					$('#errorSystem').show();
					 setTimeout(function() {
					    	element.hide(); // Ẩn thông báo sau 3 giây
					    	element.removeClass("alert alert-danger");
					 }, 3000);
					
				}
			});
		}
</script>

</body>
</html>