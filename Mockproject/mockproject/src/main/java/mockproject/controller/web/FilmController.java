package mockproject.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mockproject.model.CategoryModel;
import mockproject.model.FilmModel;
import mockproject.service.impl.CategoryService;
import mockproject.service.impl.FilmService;
import mockproject.service.impl.RatingsService;

@Controller(value = "FilmControllerWeb")
public class FilmController {

	@Autowired
	FilmService filmService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	RatingsService ratingsService;
	
	@RequestMapping(value = "/film/film-detail/{id}", method = RequestMethod.GET)
	public String showDetail(@PathVariable int id, Model model) {
		FilmModel film = filmService.getFilmById(id);
		List<CategoryModel> listCatgory = categoryService.listAllCategoryByFilmid(id);
		film.setListCategory(listCatgory);
		int star = ratingsService.getStarOfFilmByFilmId(id);
		model.addAttribute("starOfFilm", star);
		model.addAttribute("film", film);
		return "/web/filmDetail";
		
	}
	
	@RequestMapping(value = "/film/film-detail/{id}/rating", method = RequestMethod.POST)
	public String rating(@PathVariable int id, Model model, HttpServletRequest rq) {
		int star = Integer.parseInt(rq.getParameter("rating"));
		ratingsService.addOneRow(id, star);
		return "redirect:/film/film-detail/{id}";
		
	}
	

}

