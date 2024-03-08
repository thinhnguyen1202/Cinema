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
	Validator.isEmail = function(selector, message) {
		return {
			selector: selector,
			test: function(value) {
				var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
				return regex.test(value) ? undefined : message || 'Trường này phải là email';
			}
		};
	}
	Validator({
		form: '#form-createshowtime',
		formGroupSelector: '.form-floating',
		errorSelector: '.form-message',
		rules: [
			/*Validator.isRequired('#namefilm'),
			Validator.isRequired('#actorsid'),
			Validator.isRequired('#producerid'),
			Validator.isRequired('#durationid'),*/
			Validator.isRequired('#dateid'),
			//Validator.isRequired('#nameid'),
			Validator.isRequired('#start_timeid'),
		]
	});
	Validator({
		form: '#form-showtimeedit',
		formGroupSelector: '.form-floating',
		errorSelector: '.form-message',
		rules: [
			Validator.isRequired('#dateid'),
			Validator.isRequired('#start_timeid'),
		]
	});
	Validator({
		form: '#form-createcategory',
		formGroupSelector: '.form-floating',
		errorSelector: '.form-message',
		rules: [
			
			Validator.isRequired('#nameid'),
		]
	});
	Validator({
		form: '#form-categoryedit',
		formGroupSelector: '.form-floating',
		errorSelector: '.form-message',
		rules: [
			
			Validator.isRequired('#nameid'),
		]
	});
	Validator({
		form: '#form-createfilm',
		formGroupSelector: '.form-floating',
		errorSelector: '.form-message',
		rules: [
			Validator.isRequired('#namefilm'),
			Validator.isRequired('#actorsid'),
			Validator.isRequired('#producerid'),
			Validator.isRequired('#durationid'),
			Validator.isRequired('#dateid'),
		]
	});
	Validator({
		form: '#form-filmedit',
		formGroupSelector: '.form-floating',
		errorSelector: '.form-message',
		rules: [
			Validator.isRequired('#nameid'),
			Validator.isRequired('#actorsid'),
			Validator.isRequired('#producerid'),
			Validator.isRequired('#durationid'),
			Validator.isRequired('#dateid'),
		]
	});
	Validator({
		form: '#form-createuser',
		formGroupSelector: '.form-floating',
		errorSelector: '.form-message',
		rules: [
			Validator.isRequired('#usernameid'),
			Validator.minLength('#usernameid', 6),
			Validator.isRequired('#passwordid'),
			Validator.minLength('#passwordid', 6),
			Validator.isRequired('#password2id'),
			Validator.isConfirmed('#password2id', function() {
				return document.querySelector('#form-createuser #passwordid').value;
			}, 'Mật khẩu nhập lại không khớp'),
			
		]
	});
	
	
});
