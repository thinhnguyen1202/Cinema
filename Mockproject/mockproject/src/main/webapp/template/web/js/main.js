$(document).ready(function() {

	function Validator(options) {
		function getParent(element, selector) {
			while (element.parentElement) {
				if (element.parentElement.matches(selector)) {
					return element.parentElement;
				}
				element = element.parentElement;
			}
		}

		var selectorRules = {};

		// Hàm thực hiện validate
		function validate(inputElement, rule) {
			var errorElement = getParent(inputElement, options.formGroupSelector).querySelector(options.errorSelector);
			var errorMessage;

			// Lấy ra các rules của selector
			var rules = selectorRules[rule.selector];

			// Lặp qua từng rule & kiểm tra
			// Nếu có lỗi thì dừng việc kiểm
			for (var i = 0; i < rules.length; ++i) {
				switch (inputElement.type) {
					case 'radio':
					case 'checkbox':
						errorMessage = rules[i](
							formElement.querySelector(rule.selector + ':checked')
						);
						break;
					default:
						errorMessage = rules[i](inputElement.value);
				}
				if (errorMessage) break;
			}

			if (errorMessage) {
				errorElement.innerText = errorMessage;
				getParent(inputElement, options.formGroupSelector).classList.add('invalid');
			} else {
				errorElement.innerText = '';
				getParent(inputElement, options.formGroupSelector).classList.remove('invalid');
			}

			return !errorMessage;
		}

		// Lấy element của form cần validate
		var formElement = document.querySelector(options.form);
		if (formElement) {
			// Khi submit form
			formElement.onsubmit = function(e) {
				e.preventDefault();

				var isFormValid = true;

				// Lặp qua từng rules và validate
				options.rules.forEach(function(rule) {
					var inputElement = formElement.querySelector(rule.selector);
					var isValid = validate(inputElement, rule);
					if (!isValid) {
						isFormValid = false;
					}
				});

				if (isFormValid) {
					formElement.submit();
				}
			}

			// Lặp qua mỗi rule và xử lý (lắng nghe sự kiện blur, input, ...)
			options.rules.forEach(function(rule) {

				// Lưu lại các rules cho mỗi input
				if (Array.isArray(selectorRules[rule.selector])) {
					selectorRules[rule.selector].push(rule.test);
				} else {
					selectorRules[rule.selector] = [rule.test];
				}

				var inputElements = formElement.querySelectorAll(rule.selector);

				Array.from(inputElements).forEach(function(inputElement) {
					inputElement.onblur = function() {
						validate(inputElement, rule);
					}

					inputElement.oninput = function() {
						var errorElement = getParent(inputElement, options.formGroupSelector).querySelector(options.errorSelector);
						errorElement.innerText = '';
						getParent(inputElement, options.formGroupSelector).classList.remove('invalid');
					}
				});
			});
		}

	}

	Validator.isRequired = function(selector, message) {
		return {
			selector: selector,
			test: function(value) {
				return value ? undefined : message || 'Vui lòng nhập trường này'
			}
		};
	}
	Validator.isEmail = function(selector, message) {
		return {
			selector: selector,
			test: function(value) {
				var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
				return regex.test(value) ? undefined : message || 'Trường này phải là email';
			}
		};
	}

	Validator.minLength = function(selector, min, message) {
		return {
			selector: selector,
			test: function(value) {
				return value.length >= min ? undefined : message || `Vui lòng nhập tối thiểu ${min} kí tự`;
			}
		};
	}

	Validator.isConfirmed = function(selector, getConfirmValue, message) {
		return {
			selector: selector,
			test: function(value) {
				return value === getConfirmValue() ? undefined : message || 'Giá trị nhập vào không chính xác';
			}
		}
	}
	Validator.isPhone = function(selector, message) {
		return {
			selector: selector,
			test: function(value) {
				var regex = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im;
				return regex.test(value) ? undefined : message || 'Trường này phải là số điện thoại';
			}
		};
	}
	Validator.isSeat = function(selector, message) {
		return {
			selector: selector,
			test: function(value) {
				return value ? undefined : message || 'Vui lòng chọn ghế';
			}
		};
	}
	


	//login
	Validator({
		form: '#form-login',
		formGroupSelector: '.form-group',
		errorSelector: '.form-message',
		rules: [
			Validator.isRequired('#username'),
			Validator.minLength('#username', 6),
			Validator.isRequired('#password'),
			Validator.minLength('#password', 6),
		]
	});

	//register
	Validator({
		form: '#form-register',
		formGroupSelector: '.form-group',
		errorSelector: '.form-message',
		rules: [
			Validator.isRequired('#username'),
			Validator.minLength('#username', 6),
			Validator.isRequired('#password'),
			Validator.minLength('#password', 6),
			Validator.isRequired('#password_confirmation'),
			Validator.isConfirmed('#password_confirmation', function() {
				return document.querySelector('#form-register #password').value;
			}, 'Mật khẩu nhập lại không khớp'),
			Validator.isRequired('#email'),
			Validator.isEmail('#email'),
			Validator.isRequired('#phone'),
			Validator.isPhone('#phone'),
		]
	});
	Validator({
		form: '#form-update',
		formGroupSelector: '.form-group',
		errorSelector: '.form-message',
		rules: [
			Validator.isRequired('#username'),
			Validator.minLength('#username', 6),
			Validator.isRequired('#password'),
			Validator.minLength('#password', 6),
			Validator.isRequired('#password_confirmation'),
			Validator.isConfirmed('#password_confirmation', function() {
				return document.querySelector('#form-register #password').value;
			}, 'Mật khẩu nhập lại không khớp'),
			Validator.isRequired('#email'),
			Validator.isEmail('#email'),
			Validator.isRequired('#phone'),
			Validator.isPhone('#phone'),
		]
	});
	
	// slider
	$('.main-slider').owlCarousel({
		items: 1,
		loop: true,
		nav: true,
		navText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>'],
		dots: true,
		autoplay: true,
		autoplayTimeout: 7000,
		
	});
	// tab
	$(".tab-content").find(".tab:first-child").show();
	$(".tab-control li:first-child a").addClass("active");
	$(".tab-control li a").click(function(e) {
		e.preventDefault();
		$(".tab-control li a").removeClass("active");
		var id = $(this).attr('href');
		$(this).addClass("active");
		$(".tab-content").find(".tab").hide();
		$(id).show();
	});
	
	//login
	$('.message a').click(function(){
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
 });
	

	$("#playing #loadmore").click(function(e) {
		e.preventDefault();
		var box = $(this).parent();
		var itemNum = box.find(".col-md-3").length;
		$.ajax({
			url: "/mockproject/loadmore",
			type: "POST", //send it through get method
			data: {
				number: itemNum
			},
			
			success: function(response) {
				var item = '';
				$.each(response, function(k, v) {
					var date = new Date(v.created_at);
					var date_format = (date.getDate().toString().length==2?date.getDate().toString():"0"+date.getDate().toString())+"/"+((date.getMonth()+1).toString().length==2?(date.getMonth()+1).toString():"0"+(date.getMonth()+1).toString())+"/"+date.getFullYear().toString()

					item += "<div class='col-md-3'>";
					item += "<a href='/mockproject/film/film-detail/" + v.id + "' class='item'>";

					item += "<div class='thumb'>";

					item += "<img src='"+v.imagepath+"' alt='photo'>";

					item += "</div>";

					item += "<div class='desc'>";;

					item += "<span class='title' title='" + v.name + "'>" + v.name + "</span>";

					item += "<div class='info'>";

					item += "<span class='time'>";
					item += ((v.duration * 60) + "Phút");
					item += "</span>";

					item += "<span   class='date' >";

					item += date_format; 
					item += "</span>";

					item += "</div>";

					item += "</div>";

					item += "</a>";

					item += "</div>";
					
				})

				box.find(".row").append(item);
			},
			error: function(xhr) {

			}
		});
	})


	$("#coming #loadmore-coming").click(function(e) {
		e.preventDefault();
		var box = $(this).parent();
		var itemNum = box.find(".col-md-3").length;
		$.ajax({
			url: "/mockproject/loadmore-film-coming",
			type: "POST", //send it through get method
			data: {
				number: itemNum
			},
			success: function(response) {
				var item = '';
				$.each(response, function(k, v) {
					var date = new Date(v.created_at);
					var date_format = (date.getDate().toString().length==2?date.getDate().toString():"0"+date.getDate().toString())+"/"+((date.getMonth()+1).toString().length==2?(date.getMonth()+1).toString():"0"+(date.getMonth()+1).toString())+"/"+date.getFullYear().toString()

					item += "<div class='col-md-3'>";
					item += "<a href='/mockproject/film/film-detail/" + v.id + "' class='item'>";

					item += "<div class='thumb'>";

					item += "<img src='"+v.imagepath+"' alt='photo'>";

					item += "</div>";

					item += "<div class='desc'>";;

					item += "<span class='title' title='" + v.name + "'>" + v.name + "</span>";

					item += "<div class='info'>";

					item += "<span class='time'>";
					item += ((v.duration * 60) + "Phút");
					item += "</span>";

					item += "<span   class='date' >";

					item += date_format; 
					item += "</span>";

					item += "</div>";

					item += "</div>";

					item += "</a>";

					item += "</div>";
					
				})

				box.find(".row").append(item);
			},
			error: function(xhr) {

			}

		});
	})

	$(".user-drop-down .login-icon i").click(function() {
		$(".user-drop-down .menu").slideToggle();
	})

});