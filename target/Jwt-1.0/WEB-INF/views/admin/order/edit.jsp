<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url value="${api}" var="api"/>
<c:url value="${link}" var="link"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

</head>
<body>
<section class="vh-100 mt-3" style="width: 100%">
    <div class="container h-100">
        <div class="position-fixed  p-3 alert" id="errorSystem" role="alert"
             style="z-index:999;display:none;width: 33%;height: 50px;left: 60%">
            Thông báo ở đây
        </div>
        <div class="row d-flex justify-content-center align-items-center h-100" >
            <div class="col" style="background-color: #100557;border-radius: 0.5rem">
                <p class="text-white pt-2"> <span class="h2"></span></p>

                <div class="card mb-4">
                    <div class="card-body p-4">
                            <div class="row align-items-center">
                                <div class="col-md-2">
                                    <img src="<c:url value='${order.product.img}'/>"
                                         class="img-fluid" alt="Generic placeholder image">
                                </div>
                                <div class="col-md-4 d-flex justify-content-center">
                                    <div>
                                        <p class="small text-muted mb-4 pb-2">Name</p>
                                        <p class="lead fw-normal mb-0" style="display: flex;flex-wrap: wrap">${order.product.name}</p>
                                    </div>
                                </div>
                                <div class="col-md-2 d-flex justify-content-center">
                                    <div>
                                        <p class="small text-muted mb-4 pb-2">Color</p>
                                        <p class="lead fw-normal mb-0"><i class="fas fa-circle me-2" style="color: #fdd8d2;"></i>
                                            pink rose</p>
                                    </div>
                                </div>
                                <div class="col-md-2 d-flex justify-content-center">
                                    <div>
                                        <p class="small text-muted mb-4 pb-2">Quantity</p>
                                        <p class="lead fw-normal mb-0 qt-pd">${order.quantity}</p>
                                    </div>
                                </div>
                                <div class="col-md-2 d-flex justify-content-center">
                                    <div>
                                        <p class="small text-muted mb-4 pb-2">Price</p>
                                        <p class="lead fw-normal mb-0">$<span class="sp-pr">${order.product.price}</span></p>
                                    </div>
                                </div>
                                <hr>
                            </div>
                    </div>
                </div>

                <div class="card mb-5">
                    <div class="card-body p-4">
                        <form id="form-submit" >
                            <!-- 2 column grid layout with text inputs for the first and last names -->
                            <div class="row mb-4">
                                <div class="col-lg-6 row">
                                        <div class="col-lg-3">
                                            <div data-mdb-input-init class="form-outline">
                                                <label for="form6Example1">Name</label>
                                            </div>
                                        </div>
                                        <div class="col-lg-7">
                                            <div data-mdb-input-init class="form-outline">
                                                <input type="text" id="form6Example1" value="${order.userName}" class="form-control" disabled/>
                                            </div>
                                        </div>
                                </div>
                                <div class="col-lg-6 row">
                                    <div class="col-lg-3">
                                        <div data-mdb-input-init class="form-outline">
                                            <label for="quantity_order">Quantity</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-3">
                                        <div data-mdb-input-init class="form-outline">
                                            <input type="text" id="quantity_order" value="${order.product.quantity}" class="form-control" disabled/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row mb-4">
                                <div class="col-lg-6 row">
                                    <div class="col-lg-3">
                                        <div data-mdb-input-init class="form-outline">
                                            <label for="sendAddress">SendAddress</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-7">
                                        <div data-mdb-input-init class="form-outline">
                                            <input type="text" id="sendAddress" value="${order.sendAddress}" class="form-control" disabled/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 row">
                                        <div class="col-lg-3">
                                            <div data-mdb-input-init class="form-outline">
                                                <label for="receAddress">ReceAddress</label>
                                            </div>
                                        </div>
                                        <div class="col-lg-7">
                                            <div data-mdb-input-init class="form-outline">
                                                <input type="text" id="receAddress" value="${order.receAddress}" class="form-control" disabled/>
                                            </div>
                                        </div>
                                </div>
                            </div>

                            <div class="row mb-4">
                                <div class="col-lg-6 row">
                                    <div class="col-lg-3">
                                        <div data-mdb-input-init class="form-outline">
                                            <label for="recipientName">Recipient Name</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-7">
                                        <div data-mdb-input-init class="form-outline">
                                            <input type="text" id="recipientName" value="${order.receAddress}" class="form-control" disabled/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 row">
                                        <div class="col-lg-3">
                                            <div data-mdb-input-init class="form-outline">
                                                <label for="phoneNumber">Phone Number</label>
                                            </div>
                                        </div>
                                        <div class="col-lg-7">
                                            <div data-mdb-input-init class="form-outline">
                                                <input type="text" id="phoneNumber" value="${order.phoneNumber}" class="form-control" disabled/>
                                            </div>
                                        </div>
                                </div>
                            </div>

                            <div class="row mb-4">
                                <div class="col-lg-6 row">
                                    <div class="col-lg-3">
                                        <div data-mdb-input-init class="form-outline">
                                            <label for="status">Status</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-5">
                                        <div data-mdb-input-init class="">
                                            <select name="status" class="form-select" id="status">
                                                <option value="-1">Đơn hàng hủy</option>
                                                <option value="0">Giao hàng thất bại</option>
                                                <option value="1">Chờ xác nhận</option>
                                                <option value="2">Dang chuẩn bị</option>
                                                <option value="3">Đã giao hàng</option>
                                                <option value="4">Shipper giữ hàng</option>
                                                <option value="5">Shipper đang giao</option>
                                                <option value="6">Giao hàng thành công</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 row">
                                    <div class="col-lg-3">
                                        <div data-mdb-input-init class="form-outline">
                                            <label for="transport">Transport</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-7">
                                        <div data-mdb-input-init class="form-outline">
                                            <input type="text" id="transport" value="${order.transport.name}" class="form-control" disabled/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div data-mdb-input-init class="form-outline mb-4">
                                <textarea class="form-control" rows="4" style="min-height: 100px" placeholder="Note">${order.note}</textarea>
                            </div>

                            <input type="hidden" name="id" value="${order.id}">
                        </form>

                    </div>
                </div>

                <div class="d-flex justify-content-end" style="margin-bottom: 100px">
                    <a type="button" class="btn btn-light btn-lg me-2" href="<c:url value='/manager/order'/> ">Come back</a>
                    <button type="button" id="update-form" class="btn btn-lg" style="background-color: #19cb86">Update</button>
                </div>

            </div>
        </div>
    </div>
</section>

<script type="text/javascript">
    const element = $('#errorSystem');
    $("#status option").each(function() {
        if ($(this).val() === "${order.status}") {
            $(this).prop("selected", true);
        }
    });
    $('#update-form').click(function (e){
        e.preventDefault();
        var formData = $('#form-submit').serializeArray();
        var data = {};
        $.each(formData,function(i,v){
            data[""+v.name+""] = v.value;
        });
        console.log(formData);
        $.ajax({
            url:'${api}',
            type:'PUT',
            contentType:'application/json',
            data:JSON.stringify(data),
            success: function(result){
                element.removeClass().addClass("alert alert-success");
                element.text("Cập nhật trạng thái thành công");
                element.show();
                setTimeout(function() {
                    element.hide(); // Ẩn thông báo sau 3 giây
                    element.removeClass("alert alert-success");
                    window.location.href ="${link}";
                }, 2000);
            },
            error: function(xhr,status,error){
                var textbody = xhr.responseText; //lấy nội dung phản hồi từ body
                element.removeClass().addClass("alert alert-danger");
                if(!textbody.trim().startsWith("Error")){
                    $('#errorSystem').text("Lỗi hệ thống");
                }else{
                    $('#errorSystem').text(textbody);
                }
                element.show();
                setTimeout(function() {
                    element.hide(); // Ẩn thông báo sau 3 giây
                    element.removeClass("alert alert-danger");
                }, 2000);
            }

        })
    })
</script>

</body>
</html>