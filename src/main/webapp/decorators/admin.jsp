<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title><dec:title default="Home" /></title>
  <link rel="stylesheet" href="<c:url value='/template/admin/css/style.css'/>">
  <link rel="stylesheet" href="<c:url value='/template/common/css/font-awesome.min.css'/>" type="text/css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="<c:url value='/template/admin/vendors/feather/feather.css'/>">
  <link rel="stylesheet" href="<c:url value='/template/admin/vendors/mdi/css/materialdesignicons.min.css'/>">
  <link rel="stylesheet" href="<c:url value='/template/admin/vendors/ti-icons/css/themify-icons.css'/>">
  <link rel="stylesheet" href="<c:url value='/template/admin/vendors/typicons/typicons.css'/>">
  <link rel="stylesheet" href="<c:url value='/template/admin/vendors/simple-line-icons/css/simple-line-icons.css'/>">
  <link rel="stylesheet" href="<c:url value='/template/admin/vendors/css/vendor.bundle.base.css'/>">
  <link href="<c:url value='/template/web/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
  <link rel="stylesheet" href="<c:url value='/template/common/css/all.min.css'/>" type="text/css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
  <script src="<c:url value='/template/web/bootstrap/js/bootstrap.min.js'/>"></script>
  <script src="<c:url value='/template/famework/jquery.js'/>"></script>
  <script src="<c:url value='/template/ckeditor/ckeditor.js'/>"></script>

  <!-- endinject -->
  <!-- Plugin css for this page -->
  <link rel="stylesheet" href="<c:url value='/template/admin/js/select.dataTables.min.js'/>">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="<c:url value='/template/admin/css/vertical-layout-light/style.css'/>">
  <!-- endinject -->
  <link rel="shortcut icon" href="<c:url value='/template/admin/images/favicon.png'/>" />
  <script src="<c:url value='/template/famework/jquery.js'/>"></script>
  
</head>
<body>
 <div class="container-scroller"> 
	<%@include file="/common/admin/header.jsp" %>
	<div class="container-fluid page-body-wrapper">
		<%@include file="/common/admin/scroll.jsp" %>
		<%@include file="/common/admin/menu.jsp" %>
		<dec:body />
	</div>
</div>




<!-- plugins:js -->
  <script src="<c:url value='/template/admin/vendors/js/vendor.bundle.base.js'/>"></script>
 <script src="<c:url value='/template/common/js/all.min.js'/>"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <script src="<c:url value='/template/admin/vendors/chart.js/Chart.min.js'/>"></script>
  <script src="<c:url value='/template/admin/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js'/>"></script>
  <script src="<c:url value='/template/admin/vendors/progressbar.js/progressbar.min.js'/>"></script>

  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="<c:url value='/template/admin/js/off-canvas.js'/>"></script>
  <script src="<c:url value='/template/admin/js/hoverable-collapse.js'/>"></script>
  <script src="<c:url value='/template/admin/js/template.js'/>"></script>
  <script src="<c:url value='/template/admin/js/settings.js'/>"></script>
  <script src="<c:url value='/template/admin/js/todolist.js'/>"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="<c:url value='/template/admin/js/dashboard.js'/>"></script>
  <script src="<c:url value='/template/admin/js/Chart.roundedBarCharts.js'/>"></script>
  	<script src="<c:url value='/template/paging/jquery.twbsPagination.js'/>"></script>
  	<script src="<c:url value='/template/paging/jquery.twbsPagination.min.js'/>"></script>
  <!-- End custom js for this page-->
</body>
</html>