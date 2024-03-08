package mockproject.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import mockproject.model.FilmModel;
import mockproject.service.IFilmService;

@Controller(value = "controllerForWeb")
@SessionAttributes("user")
public class HomeController {
	@Autowired
	IFilmService filmService;

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		List<FilmModel> filmsPlaying = filmService.getAllFilmPlaying();
		List<FilmModel> filmsComing = filmService.getAllFilmComing();
		model.addAttribute("filmsPlaying", filmsPlaying);
		model.addAttribute("filmsComing", filmsComing);
		return "/web/home";

	}
	
	@RequestMapping(value = "/loadmore", method = RequestMethod.POST)
	public @ResponseBody List<FilmModel> loadMoreFilmPlaying(@RequestParam("number") int number, HttpServletResponse response, HttpServletRequest request) throws IOException {
		List<FilmModel> listFilm = filmService.getNextFilmPlaying(number);
		for(FilmModel s : listFilm) {
			s.setImagepath("/mockproject/upload/" + s.getImagepath());
		}
		
		return listFilm;
	}

	@RequestMapping(value = "/loadmore-film-coming", method = RequestMethod.POST)
	public @ResponseBody List<FilmModel> loadMoreFilmComing(@RequestParam("number") int number, HttpServletResponse response) {
		List<FilmModel> listFilm = filmService.getNextFilmComing(number);
		for(FilmModel s : listFilm) {
			s.setImagepath("/mockproject/upload/" + s.getImagepath());
		}
		return listFilm;
	}
}
