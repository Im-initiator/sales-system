<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
  <link rel="stylesheet" href="<c:url value='/template/common/css/all.min.css'/>" type="text/css">
  <link rel="stylesheet" href="<c:url value='/template/web/css/elegant-icons.css'/>" type="text/css">
  <link rel="stylesheet" href="<c:url value='/template/web/css/jquery-ui.min.css'/>" type="text/css">
 <link rel="stylesheet" href="<c:url value='/template/web/bootstrap/css/bootstrap.min.css'/>">
  <link rel="stylesheet" href="<c:url value='/template/web/bootstrap/bootstrap.min.js'/>">
   <link rel="stylesheet" href="<c:url value='/template/web/bootstrap/bootstrap.bundle.min.js'/>">
   <link rel="stylesheet" href="<c:url value='/template/web/Cart/Cart.css'/>">
</head>
<body>
	<section class="h-100 h-custom" style="background-color: #d2c9ff;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12">
        <div class="card card-registration card-registration-2" style="border-radius: 15px;">
          <div class="card-body p-0">
            <div class="row g-0">
              <div class="col-lg-8">
                <div class="p-5">
                  <div class="d-flex justify-content-between align-items-center mb-5">
                    <h1 class="fw-bold mb-0 text-black">Shopping Cart</h1>
                    <h6 class="mb-0 text-muted">3 items</h6>
                  </div>
                  <hr class="my-4">

                <form id="form-Purchase">
                  <c:forEach items="${products}" var="cart">
                    <div class="row mb-4 d-flex justify-content-between align-items-center">
                      <div class="col-md-2 col-lg-2 col-xl-2">
                        <img
                                src="<c:url value='${cart.product.img}'/> "
                                class="img-fluid rounded-3" alt="Cotton T-shirt">
                      </div>
                      <div class="col-md-3 col-lg-3 col-xl-3">
                        <h6 class="text-muted">cart.product.category.name</h6>
                        <h6 class="text-black mb-0">cart.product.name</h6>
                      </div>
                      <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                        <button class="btn btn-link px-2"
                                onclick="">
                          <i class="fa-solid fa-minus"></i>
                        </button>

                        <input id="form1" min="0" name="quantity" value="1" type="number"
                               class="form-control form-control-sm" style="min-width: 50px" />

                        <button class="btn btn-link px-2"
                                onclick="">
                          <i class="fa-solid fa-plus"></i>
                        </button>
                      </div>
                      <div class="col-md-2 col-lg-2 col-xl-2 offset-lg-1">
                        <h6 class="mb-0">€${cart.product.price}</h6>
                      </div>
                      <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                        <input type="checkbox" name="name" class="p-3 fs-2" style="width: 20px; height: 20px">
                      </div>
                      <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                        <button class="p-3 btn btn-danger" type="button">
                          <i class="fa-solid fa-trash-can"></i>
                        </button>
                      </div>
                    </div>
                  </c:forEach>
                </form>
                  <hr class="my-4">
                  <div class="pt-5">
                    <h6 class="mb-0"><a href="<c:url value='/home'/> " class="text-body"><i
                          class="fas fa-long-arrow-alt-left me-2"></i>Back to home</a></h6>
                  </div>
                </div>
              </div>
              <div class="col-lg-4 bg-grey">
                <div class="p-5">
                  <h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
                  <hr class="my-4">

                  <div class="d-flex justify-content-between mb-4">
                    <h5 class="text-uppercase">items 3</h5>
                    <h5>€ 132.00</h5>
                  </div>

                  <h5 class="text-uppercase mb-3">Shipping</h5>

                  <div class="mb-4 pb-2">
                    <select class="select">
                      <option value="1">Standard-Delivery- €5.00</option>
                      <option value="2">Two</option>
                      <option value="3">Three</option>
                      <option value="4">Four</option>
                    </select>
                  </div>

                  <h5 class="text-uppercase mb-3">Give code</h5>

                  <div class="mb-5">
                    <div class="form-outline">
                      <input type="text" id="form3Examplea2" class="form-control form-control-lg" />
                      <label class="form-label" for="form3Examplea2">Enter your code</label>
                    </div>
                  </div>

                  <hr class="my-4">

                  <div class="d-flex justify-content-between mb-5">
                    <h5 class="text-uppercase">Total price</h5>
                    <h5>€ 137.00</h5>
                  </div>

                  <button type="button" class="btn btn-dark btn-block btn-lg"
                    data-mdb-ripple-color="dark">Register</button>

                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
    <script src="<c:url value='/template/web/js/jquery-3.3.1.min.js'/>"></script>
    <script src="<c:url value='/template/web/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/template/common/js/all.min.js'/>"></script>
</body>
</html>