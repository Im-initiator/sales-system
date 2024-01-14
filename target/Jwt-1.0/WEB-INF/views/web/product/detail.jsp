<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product</title>
</head>
<body>

<c:url value="/web/add/cart" var="url"/>

<header>
    <!-- content -->
    <section class="py-5">
        <div class="container">
            <div class="position-fixed  p-3 alert" id="errorSystem" role="alert" style="z-index:999;display:none;width: 33%;height: 50px;left: 60%" >
                Thông báo ở đây
            </div>
            <div class="row gx-5">
                <aside class="col-lg-6">
                    <div class="d-flex justify-content-center mb-3" style="width: 400px; height: 400px">
                        <%--Product imgs--%>
                        <img src="<c:url value='${product.img}'/>" alt="">
                    </div>

                </aside>
                <main class="col-lg-6">
                    <div class="ps-lg-3">
                        <h4 class="title text-dark">
                            ${product.name}
                        </h4>
                        <div class="d-flex flex-row my-3">
                            <div class="text-warning mb-1 me-2">
                                <i class="bi bi-star"></i>
                                <i class="bi bi-star"></i>
                                <i class="bi bi-star"></i>
                                <i class="bi bi-star"></i>
                                <i class="bi bi-star"></i>
                                <span class="ms-1">
                                    ${product.prize}
                                </span>
                            </div>
                            <span class="text-muted"><i
                                    class="fas fa-shopping-basket fa-sm mx-1"></i>${product.sellNumber} orders </span>

                        </div>

                        <div class="mb-3">
                            <span class="h5">$${product.price}</span>
                            <span class="text-muted">/per box</span>
                        </div>

                        <p>
                            ${product.shortDescription}
                        </p>

                        <div class="row">
                            <dt class="col-3">Type:</dt>
                            <dd class="col-9">${category.name}</dd>

                        </div>

                        <hr/>

                        <div class="row mb-4">
                            <div class="col-md-4 col-6">
                                <label class="mb-2">Size</label>
                                <select id="size-value" class="form-select border border-secondary" style="height: 35px;">
                                    <c:forEach items="${sizes}" var="item">
                                        <option>${item.name}</option>
                                    </c:forEach>
                                    <%--<option>Small</option>
                                    <option>Medium</option>
                                    <option>Large</option>--%>
                                </select>
                            </div>
                            <!-- col.// -->
                            <div class="col-md-4 col-6 mb-3">
                                <label class="mb-2 d-block">Quantity: ${product.quantity}</label>
                            </div>
                        </div>
                        <a href="#" class="btn btn-warning shadow-0"> Buy now </a>
                        <button id="add-cart" class="btn btn-primary shadow-0"> <i class="me-1 fa fa-shopping-basket"></i> Add to
                            cart </button>
                        <a href="#" class="btn btn-light border border-secondary py-2 icon-hover px-3"> <i
                                class="me-1 fa fa-heart fa-lg"></i> Save </a>
                    </div>
                </main>
            </div>
        </div>
    </section>
    <!-- content -->

    <section class="bg-light border-top py-4">
        <div class="container">
            <div class="row gx-4">
                <div class="col-lg-12 mb-4">
                    <div class=" rounded-2 px-3 py-2 bg-white">
                        <!-- Pills navs -->

                        <!-- Pills navs -->

                        <!-- Pills content -->
                        <div class="tab-content" id="ex1-content">
                            <div class="tab-pane fade show active" id="ex1-pills-1" role="tabpanel"
                                 aria-labelledby="ex1-tab-1">
                                <p>
                                    ${product.content}
                                </p>
                            </div>
                        </div>
                        <!-- Pills content -->
                    </div>
                </div>

            </div>
            <div class="row gx-4">

            </div>
        </div>
    </section>
    <!-- Footer -->

    <script type="text/javascript">

    </script>
</header>

<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-6 col-sm-7">
                <div class="footer__about d-flex">
                    <div class="footer__logo" style="   width: 50px;
                                                        height: 50px;
                                                        border-radius: 50%;
                                                        flex: 2;
                                                        margin-right: 10px;
                                                        margin-top: 10px;">
                        <a href="<c:url value='/web/shop?id=${shop.id}'/> "><img src="<c:url value='${shop.thumbnail}'/>" alt="" style="border-radius: 60%"></a>
                    </div>
                    <p style="flex: 10">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                        cilisis.</p>
                    <div class="footer__payment">
                        <a href="#"><img src="img/payment/payment-1.png" alt=""></a>
                        <a href="#"><img src="img/payment/payment-2.png" alt=""></a>
                        <a href="#"><img src="img/payment/payment-3.png" alt=""></a>
                        <a href="#"><img src="img/payment/payment-4.png" alt=""></a>
                        <a href="#"><img src="img/payment/payment-5.png" alt=""></a>
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-3 col-sm-5">
                <div class="footer__widget">
                    <h6>Quick links</h6>
                    <ul>
                        <li><a href="#">About</a></li>
                        <li><a href="#">Blogs</a></li>
                        <li><a href="#">Contact</a></li>
                        <li><a href="#">FAQ</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-2 col-md-3 col-sm-4">
                <div class="footer__widget">
                    <h6>Account</h6>
                    <ul>
                        <li><a href="#">My Account</a></li>
                        <li><a href="#">Orders Tracking</a></li>
                        <li><a href="#">Checkout</a></li>
                        <li><a href="#">Wishlist</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-4 col-md-8 col-sm-8">
                <div class="footer__newslatter">
                    <h6>NEWSLETTER</h6>
                    <form action="#">
                        <input type="text" placeholder="Email">
                        <button type="submit" class="site-btn">Subscribe</button>
                    </form>
                    <div class="footer__social">
                        <a href="#"><i class="fa fa-facebook"></i></a>
                        <a href="#"><i class="fa fa-twitter"></i></a>
                        <a href="#"><i class="fa fa-youtube-play"></i></a>
                        <a href="#"><i class="fa fa-instagram"></i></a>
                        <a href="#"><i class="fa fa-pinterest"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                <div class="footer__copyright__text">
                    <p>Copyright &copy; <script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a></p>
                </div>
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            </div>
        </div>
    </div>
</footer>
<!-- Footer Section End -->

<!-- Search Begin -->
<div class="search-model">
    <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch">+</div>
        <form class="search-model-form">
            <input type="text" id="search-input" placeholder="Search here.....">
        </form>
    </div>
</div>
<!-- Search End -->
<script type="text/javascript">
    var element = $('#errorSystem');
    $('#add-cart').click(function (e){

        e.preventDefault();
        var object = {};
        object["sizeName"] = $('#size-value').val();
        object["productId"] = "${product.id}";
        <sec:authorize access="isAnonymous()">
        window.location.href = "<c:url value='/login'/>";
        </sec:authorize>
        console.log(object);
        $.ajax({
            url:"${url}",
            type:"POST",
            contentType:"application/json",
            data:JSON.stringify(object),
            success: function(data,status, xhr){
                $('#errorSystem').removeClass().addClass("alert alert-success");
                var redirectUrl = xhr.getResponseHeader("Location");
                    if (xhr.status === 302) {
                        var redirectUrl = xhr.getResponseHeader("Location");
                        // Thực hiện chuyển hướng tới redirectUrl
                        window.location.href = redirectUrl;
                    }
                    if(xhr.status ===200||xhr.status===201){
                        $('#errorSystem').text("Thêm thành công");
                    }
                // }else{
                //     $('#errorSystem').text("Thêm không thành công");
                // }
                $('#errorSystem').show();
                setTimeout(function() {
                    element.hide(); // Ẩn thông báo sau 3 giây
                    element.removeClass("alert alert-success");
                }, 3000);
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
                }, 3000);
            }
        })
    });


</script>

</body>
</html>