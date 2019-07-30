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
import com.topica.pagination.PaginationResult;
import com.topica.service.WordService;

@Controller
public class WordController {

	@Autowired
	private WordService wordService;
	
	@GetMapping("admin/word-list")
	public String getWordList(Model model, @RequestParam(value = "page", required = false) Integer indexPage, 
			@ModelAttribute("typeMessage") String typeMess, 
			@ModelAttribute("contentMessage") String contentMess) {
		PaginationResult<Word> page;
		int curPage;
		if(indexPage == null) {
			curPage = 1;
		} else {
			curPage = indexPage;
		}
		page = wordService.getAll(curPage);
		int totalPages = page.getTotalPages();
		int totalRecords = page.getTotalRecords();
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalrecords", totalRecords);
		model.addAttribute("curPage", curPage);
		model.addAttribute("words", page.getList());
		model.addAttribute("typeMessage", typeMess);
		model.addAttribute("contentMessage", contentMess);
		return "word-list";
	}
	
	@PostMapping("admin/word-list")
	public String getWordByType(Model model, @RequestParam("type") int type, 
			@RequestParam(value = "page", required = false) Integer indexPage,
			@RequestParam("key") String word) {
		PaginationResult<Word> page;
		int curPage;
		if(indexPage == null) {
			curPage = 1;
		} else {
			curPage = indexPage;
		}
		page = wordService.relativeSearchPage(word, type, curPage);
		int totalPages = page.getTotalPages();
		int totalRecords = page.getTotalRecords();
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalrecords", totalRecords);
		model.addAttribute("curPage", curPage);
		model.addAttribute("words", page.getList());
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
	public String saveWord(@ModelAttribute("word") Word word, RedirectAttributes model) {
		int id = word.getId();
		String message = "";
		String typeMessage = "";
		if (id != 0) {
			if (!wordService.updateWord(word)) {
				typeMessage = "warning";
				message = "Bạn đang cố tình chỉnh 1 số thuộc tính nhà phát hành không cho phép. Chúng tôi chỉ cập nhật những trường được cho phép.";
			} else {
				typeMessage = "success";
				message = "Cập nhật thành công.";
			}
		} else {
			wordService.saveWord(word);
			typeMessage = "success";
			message = "Tạo mới thành công.";
		}
		model.addAttribute("typeMessage", typeMessage);
		model.addAttribute("contentMessage", message);
		return "redirect:word-list";
	}
	
	@GetMapping("admin/word-delete")
	public String deleteWord(@ModelAttribute("id") int id, RedirectAttributes model) {
		wordService.deleteWord(wordService.findById(id));
		String message = "Xóa thành công";
		model.addAttribute("typeMessage", "success");
		model.addAttribute("contentMessage", message);
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
