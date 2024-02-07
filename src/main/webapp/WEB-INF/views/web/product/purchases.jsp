<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<c:url value="/api/web/order" var ="api"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Purchases</title>
</head>
<body>
<section class="vh-100 mt-3">
    <div class="container h-100">
        <div class="position-fixed  p-3 alert" id="errorSystem" role="alert"
             style="z-index:999;display:none;width: 33%;height: 50px;left: 60%">
            Thông báo ở đây
        </div>
        <div class="row d-flex justify-content-center align-items-center h-100" >
            <div class="col" style="background-color: #100557;border-radius: 0.5rem">
                <p class="text-white pt-2"> <span class="h2">Shopping Cart </span></p>

                <div class="card mb-4">
                    <div class="card-body p-4">

                        <c:forEach items="${carts}" var ="item">
                            <div class="row align-items-center">
                                <div class="col-md-2">
                                    <img src="<c:url value='${item.product.img}'/>"
                                         class="img-fluid" alt="Generic placeholder image">
                                </div>
                                <div class="col-md-4 d-flex justify-content-center">
                                    <div>
                                        <p class="small text-muted mb-4 pb-2">Name</p>
                                        <p class="lead fw-normal mb-0" style="display: flex;flex-wrap: wrap">${item.product.name}</p>
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
                                        <p class="lead fw-normal mb-0 qt-pd">${item.quantity}</p>
                                    </div>
                                </div>
                                <div class="col-md-2 d-flex justify-content-center">
                                    <div>
                                        <p class="small text-muted mb-4 pb-2">Price</p>
                                        <p class="lead fw-normal mb-0">$<span class="sp-pr">${item.product.price}</span></p>
                                    </div>
                                </div>
                                <hr>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="card mb-5">
                    <div class="card-body p-4">
                        <form id="form-submit">
                            <!-- 2 column grid layout with text inputs for the first and last names -->
                            <div class="row mb-4">
                                <div class="col-lg-8">
                                    <div data-mdb-input-init class="form-outline">
                                        <input type="text" id="form6Example1" name="recipientName" class="form-control required" placeholder="Full Name"/>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div data-mdb-input-init class="form-outline">
                                        <input type="number" id="form6Example6" name="phoneNumber" class="form-control required" placeholder="Phone"/>
                                    </div>
                                </div>
                            </div>

                            <div class="row mb-4">
                                <div class="col-lg-8">
                                    <div data-mdb-input-init class="form-outline">
                                        <input type="text" id="form6Example3" name="receAddress" class="form-control required" placeholder="Receiving address" />
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div data-mdb-input-init class="form-outline">
                                        <select class="form-control" name="transportCode">
                                            <c:forEach items="${transports}" var="item">
                                                <option value="${item.code}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <!-- Text input -->
                            <div data-mdb-input-init class="form-outline mb-4">

                            </div>

                            <!-- Message input -->
                            <div data-mdb-input-init class="form-outline mb-4">
                                <textarea class="form-control required" id="form6Example7" rows="4" placeholder="Note"></textarea>
                            </div>
                        </form>
                        <div class="float-end">
                            <p class="mb-0 me-5 d-flex align-items-center">
                                <span class="small text-muted me-2">Order total:</span> <span
                                    class="lead fw-normal" id="total-p">$</span>
                            </p>
                        </div>

                    </div>
                </div>

                <div class="d-flex justify-content-end" style="margin-bottom: 100px">
                    <a type="button" class="btn btn-light btn-lg me-2" href="<c:url value='/web/cart'/> ">Continue cart</a>
                    <button type="button" id="add-form" class="btn btn-lg" style="background-color: #19cb86">Add to cart</button>
                </div>

            </div>
        </div>
    </div>
</section>
<script type="text/javascript">
    const element = $('#errorSystem');
    const pr = document.getElementsByClassName('sp-pr');
    const qt = document.getElementsByClassName('qt-pd');
    var total=0;
    for (let i = 0;i<pr.length;i++){
        total += parseFloat(pr[i].textContent)*parseFloat(qt[i].textContent);
    }
    $('#total-p').text('$'+total.toFixed(3));

    $('#add-form').click(function (e){
        e.preventDefault();
        var ids = [];
        <c:forEach items="${carts}" var="cart">
             ids.push(${cart.id});
        </c:forEach>
        console.log(ids);
        var formData = $('#form-submit').serializeArray();
        var data = {};
        $.each(formData,function(i,v){
            data[""+v.name+""] = v.value;
        });
        data['ids'] = ids;
        console.log(data)
        $.ajax({
            url:'${api}',
            type:'POST',
            contentType:'application/json',
            data:JSON.stringify(data),
            success: function(result){
                element.removeClass().addClass("alert alert-success");
                element.text("Thêm đơn hàng thành công");
                element.show();
                setTimeout(function() {
                    element.hide(); // Ẩn thông báo sau 3 giây
                    element.removeClass("alert alert-success");
                    window.location.href ="<c:url value ='/home'/>";
                }, 2000);
            },
            error: function(xhr,status,error){
                var textbody = xhr.responseText; //lấy nội dung phản hồi từ body
                element.removeClass().addClass("alert alert-danger");
                if(!textbody.trim().startsWith("Error")){
                    element.text("Lỗi hệ thống");
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
    })


</script>
</body>
</html>
