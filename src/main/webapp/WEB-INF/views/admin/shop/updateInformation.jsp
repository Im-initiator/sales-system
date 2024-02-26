<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<body>
<link rel="stylesheet" href="<c:url value='/template/web/user/profile.css'/>">
<div class="container">
    <form:form modelAttribute="model" action="" method="put" id="formSubmit" enctype="multipart/form-data">
    <div class="row gutters">
        <div class="position-fixed  p-3 alert" id="errorSystem" role="alert" style="z-index:999;display:none;position: fixed;width: 50%; right: 3rem;" >
            Thông báo ở đây
        </div>
        <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12 ">

            <div class="card h-100">
                <div class="card-body">
                    <div class="account-settings">
                        <div class="user-profile">
                            <div class="user-avatar">
                                <img src="<c:url value='${model.thumbnail}'/>" alt="Image">
                            </div>
                            <h5 class="user-name">${model.name}</h5>
                            <h6 class="user-email">${model.email}</h6>
                        </div>
                        <div class="about">
                            <h5>About</h5>
                            <p>${model.shortDescription}</p>
                            <p>Link: <c:url value='/web/shop?id=${model.id}'/> </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
            <div class="card h-100">
                <div class="card-body">
                    <div class="row gutters">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mt-2">
                            <h6 class="mb-2 text-primary">Personal Details</h6>
                        </div>
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                            <div class="form-group">
                                <form:label path="name">Shop Name</form:label>
                                <form:input type="text" class="form-control" path="name" placeholder="Enter full name"/>
                            </div>
                        </div>
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                            <div class="form-group">
                                <form:label path="email">Email</form:label>
                                <form:input type="email" class="form-control" path="email" placeholder="Enter email ID"/>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12 mt-2">
                            <div class="form-group">
                                <form:label path="phoneNumber">Phone</form:label>
                                <form:input type="text" class="form-control" path="phoneNumber" placeholder="Enter phone number"/>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12 mt-2">
                            <div class="form-group">
                                <form:label path="imgThumbnail">Thumbnail</form:label>
                                <form:input type="file" accept="image/*" class="form-control" path="imgThumbnail" placeholder="Website url" onchange="displaySelectedFileName()"/>
                            </div>
                            <div id="fileDisplay" class="text-right"></div>
                        </div>
                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12 mt-2">
                            <div class="form-group">
                                <form:label path="address">Address</form:label>
                                <form:input type="text" class="form-control" path="address" placeholder="Enter phone number"/>
                            </div>
                        </div>
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mt-2">
                            <div class="form-group">
                                <form:label path="shortDescription">Description</form:label>
                                <form:textarea column="20" type="text" class="form-control" path="shortDescription"/>
                            </div>
                        </div>
                    </div>
                    <div class="row gutters">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mt-2">
                            <div class="text-end    ">
                                <button type="button" id="submit-cancel" name="submit-cancel" class="btn btn-secondary">
                                    Cancel
                                </button>
                                <button type="button" id="submitForm" name="submit" class="btn btn-primary">Update</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <form:hidden class="form-control" path="id"/>
        </form:form>
    </div>
</div>
<script type="text/javascript">
    var fileNameDisplay = document.getElementById('fileDisplay');
    var editor='';
    $( document ).ready(function() {
        editor = CKEDITOR.replace('shortDescription');
    });
    function displaySelectedFileName() {
        var fileInput = document.getElementById('imgThumbnail');
        $('#img-product').hide();
        if (fileInput.files.length > 0) {
            var fileName = fileInput.files[0].name;
            fileNameDisplay.textContent = fileName;
        } else {
            fileNameDisplay.textContent = "";
        }
    }

    $('#submitForm').click(function(e) {
        e.preventDefault();
        var form = document.getElementById('formSubmit');
        var formData = new FormData(form);
        formData.delete('shortDescription')
        formData.append("shortDescription",editor.getData());
        console.log(formData.get('imgThumbnail'));
        console.log(formData);
        updateShop(formData);
    })

    $('#submit-cancel').click(function (e){
        e.preventDefault();
        window.location.href = "<c:url value='/admin/home'/>";
    })
    const element = $('#errorSystem');
    function updateShop(data) {
        $.ajax({
            url:"<c:url value='/api/saler/shop'/>",
            type:'POST',
            data:data,
            processData: false,
            contentType: false,
            success: function(result){
                element.removeClass().addClass("alert alert-success");
                element.text("Success");
                element.show();
                setTimeout(function() {
                    element.hide(); // Ẩn thông báo sau 3 giây
                    element.removeClass("alert alert-success");
                    location.reload();
                }, 2000);
            },
            error: function(xhr,status,error){
                $('#errorSystem').removeClass().addClass("alert alert-danger");
                element.text(xhr.responseText);
                $('#errorSystem').show();
                setTimeout(function() {
                    element.hide(); // Ẩn thông báo sau 3 giây
                    element.removeClass("alert alert-danger");
                    location.reload();
                }, 2000);
            }

        })
    }
    var content = "${model.thumbnail}";
    console.log(content);
    var temp = content.indexOf("tm36");
    console.log(temp);
    if(temp === -1){
        fileNameDisplay.textContent = content.substring(content.lastIndexOf("/")+1);
    }else{
        fileNameDisplay.textContent = content.substring(content.lastIndexOf("/")+1,temp)+content.substring(content.lastIndexOf("."));
        console.log(content.substring(content.lastIndexOf("/")+1,temp)+content.substring(content.lastIndexOf(".")))
    }


    <%--var content = "${model.img}";--%>
    <%--console.log(content);--%>
    <%--var temp = content.indexOf("tm36");--%>
    <%--if(temp === -1){--%>
    <%--    fileNameDisplay.textContent = content.substring(content.lastIndexOf("/")+1);--%>
    <%--}else{--%>
    <%--    fileNameDisplay.textContent = content.substring(content.lastIndexOf("/")+1,temp)+content.substring(content.lastIndexOf("."));--%>
    <%--}--%>

</script>
</body>
</html>