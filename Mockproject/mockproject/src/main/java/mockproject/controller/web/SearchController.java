package mockproject.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mockproject.model.FilmModel;
import mockproject.service.impl.FilmService;

@Controller
public class SearchController {
	
	@Autowired
	FilmService filmService;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@RequestParam("search-keyword") String input, Model model) {
		List<FilmModel> results  = filmService.searchFilm(input);
		model.addAttribute("Listfilms", results);
		model.addAttribute("input", input);
		return "web/search";
	}
}
