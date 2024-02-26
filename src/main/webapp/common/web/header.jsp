<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.leminhtien.utils.SecurityUtils" %>
<%@ include file="/common/taglib.jsp" %>


<link rel="stylesheet" href="<c:url value='/template/web/user/fonts/icomoon/style.css'/> ">

<link rel="stylesheet" href="<c:url value='/template/web/user/css/owl.carousel.min.css'/> ">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="<c:url value='/template/web/user/css/bootstrap.min.css'/> ">

<!-- Style -->
<link rel="stylesheet" href="<c:url value='/template/web/user/css/style.css'/> ">
<style>
    .nav::before,
    .nav::after {
        content: none;
    }
</style>

<sec:authorize access="isAuthenticated()">
    <aside class="sidebar" style="max-width: 300px">
        <div class="toggle" id="showToggle">
            <a href="#" id="icon-sidbar"  class="js-menu-toggle menu-toggle fs-2" style="color: #e61f1f">
                <i class="fa-solid fa-circle-chevron-left" id="icon-show"></i>
            </a>
        </div>
        <div id="sidebar">
        <div class="img bg-wrap text-center py-4" style="background-image: url('<c:url value='/template/web/user/images/bg_1.jpg'/>');">
            <div class="user-logo">
                <div class="img" style="background-image: url(<c:url value='${user.avatar}'/>);"></div>
                <h3>${user.fullName}</h3>
            </div>
        </div>
        <ul class="list-unstyled components mb-5">
            <li class="active">
                <a href="<c:url value='/home'/>"><span class="fa fa-home mr-3"></span> Home</a>
            </li>
            <li class="active">
                <a href="<c:url value='/web/profile'/>"><i class="fa-solid fa-user me-md-3"></i> Profile</a>
            </li>
            <sec:authorize access="hasRole('CENSOR') or hasRole('ADMIN') or hasRole('SALER') or hasRole('MANAGER') or hasRole('CENSOR') or hasRole('SHIPPER')">
            <li class="active">
                <a href="<c:url value='/admin/home'/>">
                    <i class="fa-solid fa-lock me-3"></i>  Administrator
                </a>
            </li>
            </sec:authorize>
            <sec:authorize access="hasRole('SALER')">
            <li class="active">
                <a href="<c:url value='/web/shop'/>">
                    <i class="fa-solid fa-store me-3"></i> Shop
                </a>
            </li>
            </sec:authorize>
            <sec:authorize access="!hasRole('SALER')">
            <li class="active">
                <a href="<c:url value='/web/shop/register'/>">
                    <i class="fa-solid fa-file-invoice-dollar me-md-3"></i>Register sell
                </a>
            </li>
            </sec:authorize>
            <li>
                <a href="#"><span class="fa fa-download mr-3 notif"><small class="d-flex align-items-center justify-content-center">5</small></span> Download</a>
            </li>
            <li>
                <a href="#"><span class="fa fa-trophy mr-3"></span> Top Review</a>
            </li>
            <li>
                <a href="#"><span class="fa fa-cog mr-3"></span> Settings</a>
            </li>
            <li>
                <a href="#"><span class="fa fa-support mr-3"></span> Support</a>
            </li>
            <li class="active">
                <a href="<c:url value='/logout'/>"><span class="fa fa-sign-out mr-3"></span> Sign Out</a>
            </li>
        </ul>
        </div>
    </aside>
</sec:authorize>
	  <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Offcanvas Menu Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="offcanvas-menu-wrapper">
        <div class="offcanvas__close">+</div>
        <ul class="offcanvas__widget">
            <li><span class="icon_search search-switch"></span></li>
            <li><a href="#"><span class="icon_heart_alt"></span>
                <div class="tip">2</div>
            </a></li>
            <li><a href="#"><span class="icon_bag_alt"></span>
                <div class="tip">2</div>
            </a></li>
        </ul>
        <div class="offcanvas__logo">
            <a href="./index.html"><img src="<c:url value='/template/web/img/logo.png'/>" alt=""></a>
        </div>
        <div id="mobile-menu-wrap"></div>
        <div class="offcanvas__auth">
            <a href="#">Login</a>
            <a href="#">Register</a>
        </div>
    </div>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <header class="header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-3 col-lg-2">
                    <div class="header__logo">
                        <a href="<c:url value='/home'/>"><img src="<c:url value='/template/web/img/logo.png'/>" alt=""></a>
                    </div>
                </div>
                <div class="col-xl-6 col-lg-7">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="<c:url value='/home'/>">Home</a></li>
                            <li><a href="#">Women’s</a></li>
                            <li><a href="#">Men’s</a></li>
                            <li><a href="<c:url value='/web/shop'/>">Shop</a></li>
                            <li><a href="#">Pages</a>
                                <ul class="dropdown">
                                    <li><a href="./product-details.html">Product Details</a></li>
                                    <li><a href="./shop-cart.html">Shop Cart</a></li>
                                    <li><a href="./checkout.html">Checkout</a></li>
                                    <li><a href="./blog-details.html">Blog Details</a></li>
                                </ul>
                            </li>
                            <li><a href="./blog.html">Blog</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__right">
                    <sec:authorize access="isAnonymous()">
			        	   <div class="header__right__auth">
	                            <a href="<c:url value='/login'/>">Login</a>
	                            <a href="<c:url value='/register'/>">Register</a>
                      	   </div>
				    </sec:authorize>
                        <ul class="header__right__widget">
                            <li><span class="icon_search search-switch"></span></li>
                            <li><a href="#"><span class="icon_heart_alt"></span>
                                <sec:authorize access="isAuthenticated()">
                                    <div class="tip">0</div>
                                </sec:authorize>
                            </a></li>
                            <li><a href="<c:url value='/web/cart'/>"><i class="fa-solid fa-cart-shopping"></i>
                                <sec:authorize access="isAuthenticated()">
                                    <div class="tip">${count}</div>
                                </sec:authorize>
                            </a></li>
                            <li><a href="<c:url value='/web/detailOrder'/>"><span class="icon_bag_alt"></span>
                                <sec:authorize access="isAuthenticated()">
                                    <div class="tip">${countOrder}</div>
                                </sec:authorize>
                            </a></li>
                            <%--<li><span class="icon_search search-switch"></span></li>--%>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="canvas__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
        <script type="text/javascript">
            $("#icon-sidbar").click(function (event) {
                event.preventDefault()
            });

        </script>
    </header>
<script type="text/javascript">
    $(document).ready(function() {
        $("body").on("click", "#showToggle", function(event) {
            event.stopPropagation();
            $("body").toggleClass("show-sidebar");
        });

        $("body").on("click", function() {
            $("body").removeClass("show-sidebar");
        });
    });
</script>

