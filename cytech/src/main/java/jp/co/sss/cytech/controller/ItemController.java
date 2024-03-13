package jp.co.sss.cytech.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.transaction.Transactional;
import jp.co.sss.cytech.bean.ItemBean;
import jp.co.sss.cytech.entity.Item;
import jp.co.sss.cytech.form.ItemForm;
import jp.co.sss.cytech.repository.ItemRepository;

@Controller
public class ItemController {

	@Autowired
	ItemRepository repository;
	
	@RequestMapping("/items/findAll")
	public String showItemList(Model model) {
		model.addAttribute("items",repository.findAll());
		
		return "items/item_list";
	}
	
	@RequestMapping("/items/findAllByOrderPriceDesc")
	public String showItemListDesc(Model model) {
		model.addAttribute("items", repository. findAllByOrderByPriceDesc());
		
		return "items/item_list";
	}
	
	//主キー検索
	@Transactional
	@RequestMapping("/items/getOne/{id}")
	public String showItem(@PathVariable int id, Model model) {
		Item item =repository.getReferenceById(id);
		ItemBean itemBean = new ItemBean();
		BeanUtils.copyProperties(item,itemBean);
		model.addAttribute("item", itemBean);
		
		return "items/item";
	}
	
	//主キー以外の条件検索
	@RequestMapping("/items/findByPrice/{price}")
	public String showItemPrice(@PathVariable Integer price, Model model) {
		model.addAttribute("items",repository.findByPrice(price));
		
		return "items/item_list";
	}
	
	//複数の列の検索
	@RequestMapping("/items/findByNameAndPrice/{name}/{price}")
	public String showListNamePrice(@PathVariable String name, @PathVariable Integer price, Model model) {
		model.addAttribute("items",repository.findByNameAndPrice(name, price));
		
		return "items/item_list";
	}
	
	//曖昧検索
	@RequestMapping("/items/findByNameLike/{name}")
	public String showItemLike(@PathVariable String name, Model model) {
		model.addAttribute("items", repository.findByNameContaining(name));
		
		return "items/item_list";
	}
	
	//フォーム
	@RequestMapping("/items/findAllAndSetDropdown")
	public String itemDropdown(Model model) {
		model.addAttribute("items", repository.findAll());
		
		return "items/item_list_dropdown";
	}
	
	//フォーム
	@RequestMapping("/items/create/input")
	public String createInput() {
		return "/items/create_input";
	}
	
	@RequestMapping(path = "/items/create/complete", method = RequestMethod.POST)
	public String createComplete(ItemForm form,Model model) {
		Item item = new Item();
		BeanUtils.copyProperties(form, item, "id");
		item=repository.save(item);
		ItemBean itemBean = new ItemBean();
		BeanUtils.copyProperties(item,itemBean);
		model.addAttribute("item", itemBean);
		
		return "items/item";
	}
	
	//更新
	@Transactional
	@RequestMapping("/items/update/input/{id}")
	public String update(@PathVariable Integer id, Model model) {
		Item item =repository.getReferenceById(id);
		ItemBean itemBean = new ItemBean();
		BeanUtils.copyProperties(item,itemBean);
		model.addAttribute("item", itemBean);
		
		return "items/updata_input";
	}
	
	@RequestMapping(path = "/items/update/complete/{id}", method = RequestMethod.POST)
	public String updateItem(@PathVariable Integer id, ItemForm form, Model model) {
		Item item = repository.getReferenceById(id);
		BeanUtils.copyProperties(form, item, "id");
		item=repository.save(item);
		ItemBean itemBean = new ItemBean();
		BeanUtils.copyProperties(item,itemBean);
		model.addAttribute("item", itemBean);
		
		return "items/item";
	}
	
	//削除
	@RequestMapping("/items/delete/input")
	public String delete(Model model) {
		model.addAttribute("items", repository.findAll());
	
		return "items/delete_input";
	}
	
	@Transactional
	@RequestMapping(path = "/items/delete/complete", method = RequestMethod.POST)
	public String deleteItem(@RequestParam Integer id) {
	    repository.deleteById(id);
	    
	    return "redirect:/items/findAll";
	}
	
	//Thymeleafについて
	@RequestMapping("/items/findAllJs")
	public String showItemListJs(Model model) {
		model.addAttribute("items", repository.findAll());
		
		return "items/item_list_js";
	}
}



