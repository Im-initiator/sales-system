<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<c:url value="/api/web/order" var="api"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Purchases</title>
</head>
<body>
<section class="h-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-10 col-xl-8">
                <div class="card" style="border-radius: 10px;">
                    <div class="card-header px-4 py-5">
                        <h5 class="text-muted mb-0">Thanks for your Order!</h5>
                    </div>
                    <div class="card-body p-4">
                        <div class="d-flex justify-content-between align-items-center mb-4">
                            <p class="lead fw-normal mb-0" style="color: #a8729a;">Receipt</p>
                            <p class="small text-muted mb-0">Receipt Voucher : 1KAU9-84UIL</p>
                        </div>

                        <c:forEach items="${orders}" var="item">
                            <div class="card shadow-0 border mb-4">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <img src="<c:url value='${item.product.img}'/>"
                                                 class="img-fluid" alt="Phone">
                                        </div>
                                        <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                            <p class="text-muted mb-0">${item.product.name}</p>
                                        </div>
                                        <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                            <p class="text-muted mb-0 small">${item.code}</p>
                                        </div>
                                        <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                            <p class="text-muted mb-0 small">${item.size}</p>
                                        </div>
                                        <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                            <p class="text-muted mb-0 small">${item.product.quantity}</p>
                                        </div>
                                        <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                            <p class="text-muted mb-0 small">$${item.product.price}</p>
                                        </div>
                                    </div>
                                    <hr class="mb-4" style="background-color: #e0e0e0; opacity: 1;">
                                    <div class="row d-flex align-items-center">
                                        <div class="col-md-2">
                                            <p class="text-muted mb-0 small">Track Order</p>
                                        </div>
                                        <div class="col-md-10">
                                            <div class="progress" style="height: 6px; border-radius: 16px;">
                                                <div class="progress-bar" role="progressbar"
                                                     style="width: ${item.status*20}%; border-radius: 16px; background-color: #a8729a;"
                                                     aria-valuenow="65"
                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                            <div class="d-flex justify-content-around mb-1">
                                                <p class="text-muted mt-1 mb-0 small ms-xl-2">Wait confirm</p>
                                                <p class="text-muted mt-1 mb-0 small ms-xl-2">preparing goods</p>
                                                <p class="text-muted mt-1 mb-0 small ms-xl-2">Sent</p>
                                                <p class="text-muted mt-1 mb-0 small ms-xl-2">delivering</p>
                                                <p class="text-muted mt-1 mb-0 small ms-xl-2">Success</p>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${item.status==4}">
                                        <div class="row d-flex justify-content-end">
                                            <div class="col-lg-2">
                                                <button class="btn btn-primary">received</button>
                                            </div>
                                            <div class="col-lg-2 ms-3">
                                                <button class="btn btn-danger">Cancel</button>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>


                        <div class="card-footer border-0 px-4 py-5"
                             style="background-color: #a8729a; border-bottom-left-radius: 10px; border-bottom-right-radius: 10px;">
                            <h5 class="d-flex align-items-center justify-content-end text-white text-uppercase mb-0"></h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript">
    const element = $('#errorSystem');
    const pr = document.getElementsByClassName('sp-pr');
    const qt = document.getElementsByClassName('qt-pd');
    var total = 0;
    for (let i = 0; i < pr.length; i++) {
        total += parseFloat(pr[i].textContent) * parseFloat(qt[i].textContent);
    }
    $('#total-p').text('$' + total.toFixed(3));

    $('#add-form').click(function (e) {
        e.preventDefault();
        var ids = [];
        <c:forEach items="${carts}" var="cart">
        ids.push(${cart.id});
        </c:forEach>
        console.log(ids);
        var formData = $('#form-submit').serializeArray();
        var data = {};
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });
        data['ids'] = ids;
        console.log(data)
        $.ajax({
            url: '${api}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                element.removeClass().addClass("alert alert-success");
                element.text("Thêm đơn hàng thành công");
                element.show();
                setTimeout(function () {
                    element.hide(); // Ẩn thông báo sau 3 giây
                    element.removeClass("alert alert-success");
                    window.location.href = "<c:url value ='/home'/>";
                }, 2000);
            },
            error: function (xhr, status, error) {
                var textbody = xhr.responseText; //lấy nội dung phản hồi từ body
                element.removeClass().addClass("alert alert-danger");
                if (!textbody.trim().startsWith("Error")) {
                    element.text("Lỗi hệ thống");
                } else {
                    element.text(textbody);
                }
                element.show();
                setTimeout(function () {
                    element.hide(); // Ẩn thông báo sau 3 giây
                    element.removeClass("alert alert-danger");
                }, 2000);
            }

        })
    })


</script>
</body>
</html>
