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
<c:url var="urlCategory" value="/api/saler/category"/>
<c:url var="urlSize" value="/api/saler/size"/>

<div class="container">
    <div class="row">
        <div class="row col-md-12">
            <div class="position-fixed  p-3 alert" id="errorSystem" role="alert" style="z-index:999;display:none" >
                Thông báo ở đây
            </div>
            <div class="col-md-6">
                <p class="bg-primary text-uppercase fs-4 fw-bold p-2 text-white text-center">Register category</p>
                <form action="<c:url value='${link}'/>" id="formSubmit" method="GET">
                    <div class="table-responsive">
                        <table id="mytable1" class="table table-bordred table-striped">
                            <thead>
                            <th><input type="checkbox" id="checkall1"/></th>
                            <th>Name</th>
                            <th>Code</th>
                            </thead>
                            <tbody>
                            <c:forEach items="${categories}" var ="category" varStatus="loop">
                                <tr>
                                    <td><input type="checkbox" value="${category.id}" id = "${category.id}"/></td>
                                    <input type="hidden" class="category${loop.index}" value="${category.id}"/>
                                    <td class="category${loop.index}">${category.name}</td>
                                    <td class="category${loop.index}">${category.code}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <button id="register">REGISTER</button>
                </form>
                <hr>

                <p class="bg-primary text-uppercase fs-4 fw-bold p-2 text-white text-center">DANH SÁCH ĐÃ ĐĂNG KÝ</p>
                <hr>

                <form action="<c:url value='${link}'/>" id="formSubmit1" method="GET">
                    <div class="table-responsive">
                        <table id="mytable2" class="table table-bordred table-striped">
                            <thead>
                            <th><input type="checkbox" id="checkall2"/><button type="button" class="btn text-danger" id="delete-product"> <i class="bi bi-trash fs-4"></i></button></th>
                            <th>Name</th>
                            <th>Code</th>
                            </thead>
                            <tbody>
                            <c:forEach items="${categoryShop}" var ="category" varStatus="loop">
                                <tr>
                                    <td><input type="checkbox" value="${category.id}" id = "${category.id}"/></td>
                                    <input type="hidden" class="category${loop.index}" value="${category.id}"/>
                                    <td class="category${loop.index}">${category.name}</td>
                                    <td class="category${loop.index}">${category.code}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>

            <div class="col-md-5 offset-md-1">
                <p class="bg-primary text-uppercase fs-4 fw-bold p-2 text-white text-center">Register Size</p>
                <form action="<c:url value='${link}'/>" id="formSubmit2" method="GET">
                    <div class="table-responsive">
                        <table id="mytable3" class="table table-bordred table-striped">
                            <thead>
                            <th><input type="checkbox" id="checkall3"/></th>
                            <th>Name</th>
                            </thead>
                            <tbody>
                            <c:forEach items="${sizes}" var ="size" varStatus="loop">
                                <tr>
                                    <td><input type="checkbox" value="${size.id}" id ="${size.id}"/></td>
                                    <input type="hidden" class="category${loop.index}" value="${size.id}"/>
                                    <td class="category${loop.index}">${size.name}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <button id="register2">REGISTER</button>
                </form>
                <hr>

                <p class="bg-primary text-uppercase fs-4 fw-bold p-2 text-white text-center">DANH SÁCH ĐÃ ĐĂNG KÝ</p>
                <hr>


                <form action="<c:url value='${link}'/>" id="formSubmit3" method="GET">
                    <div class="table-responsive">
                        <table id="mytable4" class="table table-bordred table-striped">
                            <thead>
                            <th><input type="checkbox" id="checkall4"/><button type="button" class="btn text-danger" id="c"> <i class="bi bi-trash fs-4"></i></button></th>
                            <th>Name</th>
                            </thead>
                            <tbody>
                            <c:forEach items="${sizes}" var ="size" varStatus="loop">
                                <tr>
                                    <td><input type="checkbox" value="${size.id}" id ="${size.id}"/></td>
                                    <input type="hidden" class="category${loop.index}" value="${size.id}"/>
                                    <td class="category${loop.index}">${size.name}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </form>

            </div>
        </div>

    </div>

</div>
<!-- Modal edit  -->
<div class="modal fade" id="ModelEdit" tabindex="-1" aria-labelledby="exampleModalLabel2" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel2">Add new Authorization</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="formUpdate">
                    <div class="mb-3">
                        <label for="name" class="form-label">Authorization name</label>
                        <input type="text" class="form-control" name="name" id="name" aria-describedby="emailHelp" value="">
                    </div>
                    <div class="mb-3">
                        <label for="code" class="form-label">Authorization code</label>
                        <input type="text" class="form-control" name="code" id="code" value="">
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
<script src="<c:url value='/template/web/js/handle.js'/> "></script>
<script type="text/javascript">

    const element = $('#errorSystem');
    const urlCategory = "${urlCategory}";
    const urlSize = "${urlSize}";

    $(document).ready(function(){
        $("[data-toggle=tooltip]").tooltip();
    });

    $('#register').click(function(e){
        e.preventDefault();
        var data = [];
        $('#mytable1 tbody input[type="checkbox"]:checked').each(function() {
            data.push($(this).val());
        });
        console.log(data);
        addOb(data,urlCategory);

    });

    $('#register1').click(function(e){
        e.preventDefault();
        var data = [];
        $('#mytable2 tbody input[type="checkbox"]:checked').each(function() {
            data.push($(this).val());
        });
        console.log(data);
        addOb(data,urlSize);
    });




    function addOb(data,url){
        $.ajax({
            url:url,
            type:'POST',
            contentType:'application/json',
            data:JSON.stringify(data),
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
            url:'${urlRole}',
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
                url:'${urlRole}',
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