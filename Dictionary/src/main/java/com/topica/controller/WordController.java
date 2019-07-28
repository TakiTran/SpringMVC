package com.topica.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.topica.model.Word;
import com.topica.service.WordService;

@Controller
public class WordController {

	@Autowired
	private WordService wordService;
	
	@GetMapping("admin/word-list")
	public String getWordList(Model model, 
			@ModelAttribute("typeMessage") String typeMess, 
			@ModelAttribute("contentMessage") String contentMess) {
		model.addAttribute("words", wordService.getAll());
		model.addAttribute("typeMessage", typeMess);
		model.addAttribute("contentMessage", contentMess);
		return "word-list";
	}
	
	@PostMapping("admin/word-list")
	public String getWordByType(Model model, @RequestParam("type") int type, @RequestParam("key") String word) {
		model.addAttribute("words", wordService.relativeSearch(word, type));
		return "word-list";
	}
	
	@GetMapping("admin/word-save")
	public String getFormWord(Model model, @RequestParam(value = "id", required=false) @PathVariable Integer id) {
		if (id != null) {
			Word word = wordService.findById(id);
			model.addAttribute("word", word);
		} else {
			model.addAttribute("word", new Word());
		}
		return "word";
	}
	
	
	@PostMapping("admin/word-save")
	public String saveWord(@ModelAttribute("word") Word word,RedirectAttributes model) {
		int id = word.getId();
		String message = "";
		wordService.saveWord(word);
		if (id != 0) {
			message = "Cập nhật thành công.";
		} else {
			message = "Tạo mới thành công.";
		}
		model.addAttribute("typeMessage", "success");
		model.addAttribute("contentMessage", message);
		return "redirect:word-list";
	}
	
	@GetMapping("admin/word-delete")
	public String deleteWord(@ModelAttribute("id") int id) {
		wordService.deleteWord(wordService.findById(id));
		return "redirect:word-list";
	}
	
	@GetMapping("admin/checkKey")
	public @ResponseBody String checkKey(HttpServletRequest req, HttpServletResponse resp) {
		String str = "";
		String key = req.getParameter("key");
		System.out.println(key);
		if (wordService.checkKeyExists(key)) {
			str = "exist";
		} else {
			str = "not exist";
		}
		System.out.println(str);
		return str;
	}
}
