<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<nav class="sidebar sidebar-offcanvas" id="sidebar">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/admin-home"/>">
                <i class="mdi mdi-grid-large menu-icon"></i>
                <span class="menu-title">Dashboard</span>
            </a>
        </li>
        <li class="nav-item nav-category">UI Elements</li>
        <li class="nav-item">
            <a class="nav-link" data-bs-toggle="collapse" href="#ui-basic" aria-expanded="false"
               aria-controls="ui-basic">
                <i class="menu-icon mdi mdi-floor-plan"></i>
                <span class="menu-title">
Management directory</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="ui-basic">
                <ul class="nav flex-column sub-menu">
                    <sec:authorize access="hasRole('ADMIN')">
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/admin/user?page=1'/>">
                            Account management</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/admin/role?page=1'/>">Decentralized
                            management</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('SHIPPER')">
                        <li class="nav-item"><a class="nav-link" href="#">
                            Delivery</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('CENSOR')">
                        <li class="nav-item"><a class="nav-link" href="#">
                            Order management</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('SALER')">
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/saler/product?page=1'/>">Product
                            Management</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Order management</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/saler/register/category'/>">register category and size</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('MANAGER')">
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/manager/product?page=1'/>">Product
                            Management</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/manager/shop'/>">
                            Shop management</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/manager/category?page=1'/>">Manage
                            product types</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/manager/gender?page=1'/>">
                            Gender management</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/manager/size?page=1'/>">
                            Size management</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/manager/transport'/>">
                            Transport management</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/manager/order'/>">
                            Order management</a></li>
                    </sec:authorize>


                </ul>
            </div>
        </li>
        <li class="nav-item nav-category">Forms and Datas</li>
        <li class="nav-item">
            <a class="nav-link" data-bs-toggle="collapse" href="#form-elements" aria-expanded="false"
               aria-controls="form-elements">
                <i class="menu-icon mdi mdi-card-text-outline"></i>
                <span class="menu-title">Form elements</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="form-elements">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"><a class="nav-link" href="pages/forms/basic_elements.html">Basic Elements</a>
                    </li>
                </ul>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-bs-toggle="collapse" href="#charts" aria-expanded="false" aria-controls="charts">
                <i class="menu-icon mdi mdi-chart-line"></i>
                <span class="menu-title">Charts</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="charts">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"><a class="nav-link" href="pages/charts/chartjs.html">ChartJs</a></li>
                </ul>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-bs-toggle="collapse" href="#tables" aria-expanded="false" aria-controls="tables">
                <i class="menu-icon mdi mdi-table"></i>
                <span class="menu-title">Tables</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="tables">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"><a class="nav-link" href="pages/tables/basic-table.html">Basic table</a></li>
                </ul>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-bs-toggle="collapse" href="#icons" aria-expanded="false" aria-controls="icons">
                <i class="menu-icon mdi mdi-layers-outline"></i>
                <span class="menu-title">Icons</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="icons">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"><a class="nav-link" href="pages/icons/mdi.html">Mdi icons</a></li>
                </ul>
            </div>
        </li>
        <li class="nav-item nav-category">pages</li>
        <li class="nav-item">
            <a class="nav-link" data-bs-toggle="collapse" href="#auth" aria-expanded="false" aria-controls="auth">
                <i class="menu-icon mdi mdi-account-circle-outline"></i>
                <span class="menu-title">User Pages</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="auth">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"><a class="nav-link" href="pages/samples/login.html"> Login </a></li>
                </ul>
            </div>
        </li>
        <li class="nav-item nav-category">help</li>
        <li class="nav-item">
            <a class="nav-link" href="http://bootstrapdash.com/demo/star-admin2-free/docs/documentation.html">
                <i class="menu-icon mdi mdi-file-document"></i>
                <span class="menu-title">Documentation</span>
            </a>
        </li>
    </ul>
</nav>