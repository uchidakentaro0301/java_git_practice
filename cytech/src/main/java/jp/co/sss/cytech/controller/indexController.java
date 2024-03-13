package jp.co.sss.cytech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
	@RequestMapping(path = "/")
	public String index() {
		
		return "index";
	}
	
	//画面遷移
	@RequestMapping(path = "/before")
	public String before() {
		
		return "before";
	}
	
	@RequestMapping(path = "/after")
	public String after() {
		
		return "after";
	}
	
	//フォワード
	@RequestMapping(path = "/transition")
	public String trasition() {
		
		return "sample_transition";
	}
	
	@RequestMapping(path = "/index_f")
	public String index_forwad() {
		
		return "index";
	}
	
	//リダイレクト
	@RequestMapping(path = "/index_r")
	public String index_redirect(){
		
		return "redirect:/";
	}
	
	//共通部品表示
	@RequestMapping("/layout_view")
	public String layout_view() {
		
		return "layout_view";
	}
}


