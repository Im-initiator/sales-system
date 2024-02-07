<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/api/web/cart" var="url"/>
<c:url value="/web/redirect/purchase" var="rd"/>
<c:url value="/api/web/purchase" var="purchases"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet" href="<c:url value='/template/common/css/all.min.css'/>" type="text/css">
    <link rel="stylesheet" href="<c:url value='/template/web/css/elegant-icons.css'/>" type="text/css">
    <link rel="stylesheet" href="<c:url value='/template/web/css/jquery-ui.min.css'/>" type="text/css">
    <link rel="stylesheet" href="<c:url value='/template/web/bootstrap/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/web/Cart/Cart.css'/>">
    <script src="<c:url value='/template/web/js/jquery-3.3.1.min.js'/>"></script>
    <script src="<c:url value='/template/web/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/template/common/js/all.min.js'/>"></script>
</head>
<body>
<section class="h-100 h-custom" style="background-color: #d2c9ff;">
    <div class="container py-5 h-100">
        <div class="position-fixed  p-3 alert" id="errorSystem" role="alert"
             style="z-index:999;display:none;width: 33%;height: 50px;left: 60%">
            Thông báo ở đây
        </div>
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12">
                <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                    <div class="card-body p-0">
                        <div class="row g-0">
                            <div class="col-lg-8">
                                <div class="p-5">
                                    <div class="d-flex justify-content-between align-items-center mb-5">
                                        <h1 class="fw-bold mb-0 text-black">Shopping Cart</h1>

                                        <h6 class="mb-0 text-muted">${count} items</h6>
                                    </div>
                                    <div class="pt-1">
                                        <h6 class="mb-0"><a href="<c:url value='/home'/> " class="text-body"><i
                                                class="fas fa-long-arrow-alt-left me-2"></i>Back to home</a></h6>
                                    </div>
                                    <hr class="my-4">

                                    <form id="form-Purchase">
                                        <c:forEach items="${cart}" var="cart" varStatus="i">
                                            <div class="row mb-4 d-flex justify-content-between align-items-center" id="cl-${cart.id}">
                                                <div class="col-md-2 col-lg-2 col-xl-2">
                                                    <img
                                                            src="<c:url value='${cart.product.img}'/> "
                                                            class="img-fluid rounded-3" alt="Cotton T-shirt">
                                                </div>
                                                <div class="col-md-3 col-lg-3 col-xl-3">
                                                    <h6 class="text-muted">${cart.product.category.name}</h6>
                                                    <h6 class="text-black mb-0">${cart.product.name}</h6>
                                                </div>
                                                <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                                    <button class="btn btn-link px-2" onclick='update(event,"${cart.id}","${cart.product.id}",false)'>
                                                        <i class="fa-solid fa-minus">

                                                        </i>
                                                    </button>

                                                    <input id="ip-${cart.id}" min="0" name="quantity"
                                                           value="${cart.quantity}" type="number"
                                                           class="form-control form-control-sm qs-cls-quantity" readonly
                                                           style="max-width: 60px;min-width: 40px"/>

                                                    <button class="btn btn-link px-2" onclick='update(event,"${cart.id}","${cart.product.id}",true)'>
                                                        <i class="fa-solid fa-plus"></i>
                                                    </button>
                                                </div>
                                                <div class="col-md-2 col-lg-2 col-xl-2 offset-lg-1">
                                                    <h6 class="mb-0" id="pr-${cart.id}">$<span class="pr-cls-price">${cart.product.price}</span></h6>
                                                </div>
                                                <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                                    <input type="checkbox" value="${cart.id}" name="id" id="${cart.product.id}" class="p-3 fs-2 sb-sl"
                                                           style="width: 20px; height: 20px">
                                                </div>
                                                <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                                    <button class="p-3 btn btn-danger" type="button" id="btn-delete" onclick='deleteCart(event,"${cart.id}")'>
                                                        <i class="fa-solid fa-trash-can"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </form>
                                    <hr class="my-4">
                                </div>
                            </div>
                            <div class="col-lg-4 bg-grey">
                                <div class="p-5">
                                    <h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
                                    <hr class="my-4">

                                    <div class="d-flex justify-content-between mb-4">
                                        <h5 class="text-uppercase">items</h5>
                                        <h5 class="total"></h5>
                                    </div>

                                    <h5 class="text-uppercase mb-3">Shipping</h5>

                                    <hr class="my-4">

                                    <div class="d-flex justify-content-between mb-5">
                                        <h5 class="text-uppercase">Total price</h5>
                                        <h5 class="total-ps">$ 0.0</h5>
                                    </div>

                                    <button type="button" class="btn btn-dark btn-block btn-lg"
                                            data-mdb-ripple-color="dark" id="btn-purchases">Purchases
                                    </button>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <form method="get" action="${rd}" style="display: none" id="formsm">

    </form>
    <script type="text/javascript">

        var number = function (iscK){
            const pr = document.getElementsByClassName('pr-cls-price');
            const nb = document.getElementsByClassName('qs-cls-quantity');
            const ip = document.getElementsByClassName('sb-sl');
            var  quantity ;
            var price ;
            var total=0;
            for (let i=0;i<pr.length;i++){
                if(iscK===false){
                    quantity = parseInt(nb[i].value);
                    price = parseFloat(pr[i].textContent);
                    total+=quantity*price;
                }else {
                    if (ip[i].checked===true){
                        quantity = parseInt(nb[i].value);
                        console.log(quantity)
                        price = parseFloat(pr[i].textContent);
                        console.log(price)
                        total+=quantity*price;
                    }
                }
            }
            return total.toFixed(3);
        }
        $('.total').text("$ "+number(false));
        console.log(number());
        const myerr = $('#errorSystem');
        function update(event, id, productId,flag) {
            event.preventDefault();
            var text = '#ip-'+id;
            var quantity = $(text).val();
            if (flag===false){
                quantity --;
            }else {
                quantity++;
            }
            if(quantity<=0){
                myerr.removeClass().addClass("alert alert-danger");
                myerr.text("Số lượng không được nhỏ hơn 1");
                myerr.show();
                setTimeout(function () {
                    console.log(number(false));
                    myerr.hide(); // Ẩn thông báo sau 3 giây
                    myerr.removeClass("alert alert-danger");
                }, 3000);
                return;
            }
            var data = {};
            data["id"] = id;
            data["productId"] = productId;
            data["quantity"] = quantity;
            $.ajax({
                url: "${url}",
                type: "PUT",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (result) {
                    var id = result.id;
                    var ipId = '#ip-'+id;
                    $(ipId).val(result.quantity)
                    $('.total').text("$ "+number(false));
                    $('.total-ps').text("$ "+number(true));
                },
                error: function (xhr, status, error) {

                    var textbody = xhr.responseText; //lấy nội dung phản hồi từ body
                    myerr.removeClass().addClass("alert alert-danger");
                    if (textbody.trim() === "") {
                        myerr.text("Không thể thêm sản phẩm");
                    }
                    myerr.show();
                    setTimeout(function () {
                        myerr.hide(); // Ẩn thông báo sau 3 giây
                        myerr.removeClass("alert alert-danger");
                    }, 3000);

                }

            });
        }

        var ipcheckbox = document.getElementsByClassName('sb-sl');
        for (let  i = 0;i<ipcheckbox.length;i++){
            ipcheckbox[i].addEventListener("change",function (){
                $('.total-ps').text("$ "+number(true));
            })
        }

        function deleteCart(e,id){
            event.preventDefault();
            var data = [];
            data[0] = id;
            $.ajax({
                url: "${url}",
                type: "DELETE",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (result) {
                    location.reload();
                },
                error: function (xhr, status, error) {
                    var textbody = xhr.responseText; //lấy nội dung phản hồi từ body
                    myerr.removeClass().addClass("alert alert-danger");
                    if (textbody.trim() === "") {
                        myerr.text("Không thể xóa sản phẩm");
                    }
                    myerr.show();
                    setTimeout(function () {
                        myerr.hide(); // Ẩn thông báo sau 3 giây
                        myerr.removeClass("alert alert-danger");
                    }, 3000);

                }

            });
        }

        $('#btn-purchases').click(function (){
            const ip = document.getElementsByClassName('sb-sl');
            const formSm = document.getElementById('formsm');
            var length = ip.length;
            var flag = false;
            for (let i = 0;i<length;i++){
                if (ip[i].checked===true){
                    if(flag===false){
                        flag=true;
                    }
                    var clone = ip[i].cloneNode(true);
                    formSm.appendChild(clone);
                }
            }
            if(flag===true){
                formSm.submit();
            }
        })


    </script>
</section>
</body>
</html>