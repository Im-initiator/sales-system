<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.leminhtien.utils.SecurityUtils" %>
<%@ include file="/common/taglib.jsp" %>
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
                            <li><sec:authorize access="hasRole('CENSOR') or hasRole('ADMIN') or hasRole('SALER') or hasRole('MANAGER') or hasRole('CENSOR') or hasRole('SHIPPER')">
                                <a href="<c:url value='/admin/home'/>">Administrator</a>
                            </sec:authorize></li>
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
				    <sec:authorize access="isAuthenticated()">
				        	<div class="header__right__auth">
	                            <a href="#">Wellcome <%= SecurityUtils.getPrincipal().getFullName() %></a>
	                            <a href="<c:url value='/logout'/>">Thoát</a>
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
                        </ul>
                    </div>
                </div>
            </div>
            <div class="canvas__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
