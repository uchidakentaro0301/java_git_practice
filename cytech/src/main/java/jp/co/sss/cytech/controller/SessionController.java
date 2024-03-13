package jp.co.sss.cytech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.cytech.form.LoginForm;
import jp.co.sss.cytech.form.LoginFormWithAnnotation;
import jp.co.sss.cytech.form.LoginFormWithValidation;

@Controller
public class SessionController {
	//GET
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		
		return "session/login";
	}
	
	@RequestMapping(path = "/doLogin", method = RequestMethod.GET)
	public String doLoginGet(Integer userId) {
		System.out.println("ユーザ ID:" + userId);
	
		return "session/login";
	}
	
	//POST
	@RequestMapping(path = "/doLogin", method = RequestMethod.POST)
	public String doLoginPost(Integer userId) {
		System.out.println("ユーザ ID:" + userId);
		
		return "session/login";
	}
	
	@RequestMapping(path = "/loginUsingForm",method = RequestMethod.GET)
	public String loginUsingForm() {
		return "session/loginUsingForm";
	}
	
	//フォームクラス
	@RequestMapping(path = "/doLoginUsingForm",method = RequestMethod.POST)
	public String doLoginUsingForm(LoginForm form) {
		System.out.println("ユーザーID：" + form.getUserId());
		System.out.println("ユーザーID：" + form.getPassword());
		
		return "session/loginUsingForm";
	}
	
	//リクエストスコープ
	@RequestMapping(path = "/loginOnRequest",method = RequestMethod.GET)
	public String doLoginOnRequest() {
		return "session/loginOnRequest";
	}
	
	@RequestMapping(path = "/doLoginOnRequest",method = RequestMethod.POST)
	public String doLoginOnRequest(LoginForm form, Model model) {
		model.addAttribute("userId",form.getUserId());
		
		return"session/loginOnRequest";
	}
	
	//セッションスコープ
	@RequestMapping(path = "/loginOnSession", method = RequestMethod.GET)
	public String logOnSession() {
		return "session/loginOnSession";
	}
	
	@RequestMapping(path = "/doLoginOnSession", method = RequestMethod.POST)
	public String doLoginOnSession(LoginForm form, HttpSession session) {
	    if (form.getUserId() == 123) {
	        //入力したユーザ ID をスコープ変数「userId」に代入し、その変数をセッションに登録
	        session.setAttribute("userId", form.getUserId());
	        return "redirect:/";
	    } else {
	        return "session/loginOnSession";
	    }
	}
	
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	//入力チェック
	@RequestMapping(path = "/loginValidation", method = RequestMethod.GET)
	public String loginValidation(@ModelAttribute LoginFormWithValidation form) {
		
		return "session/loginValidation";
	}

	@RequestMapping(path = "/loginValidation", method = RequestMethod.POST)
	public String doLoginValidation(@Valid @ModelAttribute LoginFormWithValidation form,BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "session/loginValidation";
		}
		if (form.getUserId() == 123) {
			//入力したユーザ ID をスコープ変数「userId」に代入して、その変数をセッションに登録
			session.setAttribute("userId", form.getUserId());
			return "redirect:/";
		} else {
			return "session/loginValidation";
		}
	}
	
	@RequestMapping(path = "/loginWithAnnotation", method = RequestMethod.GET)
	public String loginWithAnnotation(@ModelAttribute LoginFormWithAnnotation form) {
		return "session/loginWithAnnotation";
	}

	@RequestMapping(path = "/loginWithAnnotation", method = RequestMethod.POST)
	public String doLoginWithAnnotation(@Valid @ModelAttribute LoginFormWithAnnotation form,
			BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "session/loginWithAnnotation";
		}
		if (form.getUserId() == 123) {
			//ユーザ ID をスコープ変数「userId」に代入し、セッションに登録
			session.setAttribute("userId", form.getUserId());
			return "redirect:/";
		} else {
			return "session/loginWithAnnotation";
		}
	}
}




