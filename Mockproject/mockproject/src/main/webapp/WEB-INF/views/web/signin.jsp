<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Metiz</title>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
          integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
      <link rel="stylesheet" href="/css/styles.css">
  </head>
  <body style="display:flex; align-items:center; justify-content:center;">
  <div class="login-page">
    <div class="form">
      <form class="register-form" action="signup"  method="POST" id="form-register" role="form" autocomplete="off">
        <h2> Register</h2>
        <div class="form-group">
        <input type="text" class="form-control"
			name="username" id="username" placeholder="Username" required> <span
			class="form-message"></span>
		</div>
       <div class="form-group">
		<input type="password"
		class="form-control" id="password" name="password"
		placeholder="Password"> <span class="form-message"></span>
		</div>
		<div class="form-group">
		<input type="password"
		class="form-control" name="repassword"
		id="password_confirmation" placeholder="Repeat password">
		<span class="form-message"></span>
		</div>
		<div class="form-group">
		<input type="text" class="form-control"
		id="fullname" name="full_name" placeholder="Full name">
		<span class="form-message"></span>
		</div>
		<div class="form-group">
		<input type="tel"
		class="form-control" id="phone" name="phone"
		placeholder="Number phone"> <span class="form-message"></span>
		</div>
		<div class="form-group">
		<input type="email" class="form-control"
		id="email" name="email" placeholder="Email"> <span
		class="form-message"></span>
		</div>
        <button type="submit">create</button>
        <p class="message">Already registered? <a href="#">Login</a></p>
        ${message }
      </form>
      <form class="login-form" action="${pageContext.request.contextPath}/j_spring_security_check"
							method="POST" id="form-login" autocomplete="off">
        <h2> Login</h2>
        <div class="form-group">
		<input type="text" class="form-control"
		name="username" placeholder="Username" id="username"
		> <span class="form-message"></span>
		</div>
        <div class="form-group">
		<input type="password"
		class="form-control" name="password" placeholder="Password"
		id="password"> <span class="form-message"></span>
		</div>
        <button type="submit" name="send2">login</button>
        <p class="message">Not registered? <a href="#">Create an account</a></p>
        <c:if test="${param.error == 'true'}">
			<div style="color: red; margin: 10px 0px;">

				Đăng nhập thất bại! <br /> Tài khoản hoặc mật khẩu không đúng!

			</div>
		</c:if>
      </form>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="/js/main.js"></script>
  </body>
  </html>
