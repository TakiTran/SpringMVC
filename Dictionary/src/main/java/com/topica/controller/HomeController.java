package com.topica.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.topica.model.Word;
import com.topica.model.dto.WordDTO;
import com.topica.service.WordService;

@Controller
public class HomeController {

	@Autowired
	private WordService wordService;

	@GetMapping("/home")
	public String getHome() {
		return "home";
	}

	@GetMapping("/absoluteSearch")
	public ResponseEntity<List<WordDTO>> absoluteSearch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<WordDTO> wordDtos = new ArrayList<>();
		String textSearch = req.getParameter("textSearch");
		System.out.println(textSearch);
		if (!textSearch.isEmpty()) {
			int type = 0;
			if (req.getParameter("type") != null) {
				type = Integer.parseInt(req.getParameter("type")) ;
			}
			List<Word> words = wordService.absoluteSearch(textSearch,type);
			if (!words.isEmpty()) {
				for (Word word : words) {
					WordDTO wordDto = new WordDTO(word.getId(), word.getKey(), word.getMean(), word.getType());
					wordDtos.add(wordDto);
				}
			}
			System.out.println(wordDtos.size());
		}
		return ResponseEntity.ok(wordDtos);	
	}
	
	@GetMapping("/relativeSearch")
	public ResponseEntity<List<WordDTO>> relativeSearch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<WordDTO> wordDtos = new ArrayList<>();
		String textSearch = req.getParameter("textSearch");
		if (!textSearch.equals("")) {
			int type = 0;
			if (req.getParameter("type") != null) {
				type = Integer.parseInt(req.getParameter("type")) ;
			}
			List<Word> words = wordService.relativeSearch(textSearch,type);
			if (!words.isEmpty()) {
				for (Word word : words) {
					WordDTO wordDto = new WordDTO(word.getId(), word.getKey(), word.getMean(), word.getType());
					wordDtos.add(wordDto);
				}
			}
		}
		return ResponseEntity.ok(wordDtos);	
	}
}
