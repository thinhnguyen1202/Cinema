package mockproject.controller.web;

import java.io.IOException;	
import java.security.Principal;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import mockproject.model.CategoryModel;
import mockproject.model.FilmModel;
import mockproject.model.RoomModel;
import mockproject.model.SeatModel;
import mockproject.model.ShowtimeModel;
import mockproject.model.UserModel;
import mockproject.service.ICategoryService;
import mockproject.service.IFilmService;
import mockproject.service.IRoomService;
import mockproject.service.ISeatService;
import mockproject.service.IShowtimeService;
import mockproject.service.ITicketService;
import mockproject.service.IUserService;


@Controller
@SessionAttributes(value = { "user", "showtime", "seat" , "messageForBookingTickets", "statusForBookingTickets"})
public class ShowtimeController {
	@Autowired
	IFilmService filmService;
	
	@Autowired
	ICategoryService categoryService;

	@Autowired
	IShowtimeService showtimeService;

	@Autowired
	IRoomService roomService;

	@Autowired
	ISeatService seatService;

	@Autowired
	IUserService userService;

	@Autowired
	ITicketService ticketService;
	@RequestMapping(value = "/showtime", method = RequestMethod.GET)
	public String bookingTime( Model model) {

		List<ShowtimeModel> listShowtime = showtimeService.listAllShowTimeNow();	
		
		for (int i = 0; i < listShowtime.size(); i++) {
			// lay duoc cai ten cua tung film bang id
			FilmModel filmById = filmService.getFilmById(listShowtime.get(i).getFilm_id());
			RoomModel roomNamebyId = roomService.getRoomNameById(listShowtime.get(i).getRoom_id());
			
			listShowtime.get(i).setFilm_name(filmById.getName());
			listShowtime.get(i).setRoom_name(roomNamebyId.getName());
			listShowtime.get(i).setFilm_imagepath(filmById.getImagepath());
		}
		Set<Integer> listFilm = new TreeSet<>();
		for (int i = 0; i < listShowtime.size(); i++) {			
			listFilm.add(listShowtime.get(i).getFilm_id());
		}
		Set<Date> listDateofShowtime1 = new TreeSet<>();
		for (int i = 0; i < listShowtime.size(); i++) {			
			listDateofShowtime1.add(listShowtime.get(i).getShowdate());
		}

		Set<Date> listDateofShowtime = new TreeSet<>();
		LocalDate d = LocalDate.now();
		for (int i = 0; i < 7; i++) {
			
			LocalDate d1=d.plusDays(i); 
			
			listDateofShowtime.add(Date.valueOf(d1));
			
		}
		
		
		model.addAttribute("film",listFilm);
		model.addAttribute("listDateofShowtime", listDateofShowtime);
		model.addAttribute("listDateofShowtime1", listDateofShowtime1);
		model.addAttribute("listShowtime", listShowtime);
		return "web/showtimenow";
	}
	
	@RequestMapping(value = "/showtime/{date}", method = RequestMethod.GET)
	public String getShowtimeWithDate( @PathVariable Date date, Model model) {
        
		List<ShowtimeModel> listShowtimewithdate = showtimeService.listAllShowtimeWithDate(date);	
		List<FilmModel> listfilm = filmService.getAllFilmPlaying();
		
		for (int i = 0; i < listShowtimewithdate.size(); i++) {
			// lay duoc cai ten cua tung film bang id
			FilmModel filmById = filmService.getFilmById(listShowtimewithdate.get(i).getFilm_id());
			RoomModel roomNamebyId = roomService.getRoomNameById(listShowtimewithdate.get(i).getRoom_id());
			
			listShowtimewithdate.get(i).setFilm_name(filmById.getName());
			listShowtimewithdate.get(i).setRoom_name(roomNamebyId.getName());
			listShowtimewithdate.get(i).setFilm_imagepath(filmById.getImagepath());
		
		}
		Set<Integer> listFilm = new TreeSet<>();
		for (int i = 0; i < listShowtimewithdate.size(); i++) {			
			listFilm.add(listShowtimewithdate.get(i).getFilm_id());
		}
		
		Set<Date> listDateofShowtime1 = new TreeSet<>();
		for (int i = 0; i < listShowtimewithdate.size(); i++) {			
			listDateofShowtime1.add(listShowtimewithdate.get(i).getShowdate());
		}
		Set<Integer> listid = new TreeSet<>();
		for (int i = 0; i < listShowtimewithdate.size(); i++) {			
			listid.add(listShowtimewithdate.get(i).getId());
		}
		
		Set<Date> listDateofShowtime = new TreeSet<>();
		LocalDate d = LocalDate.now();
		for (int i = 0; i < 7; i++) {
			
			LocalDate d1=d.plusDays(i); 
			
			listDateofShowtime.add(Date.valueOf(d1));
		
		}
		model.addAttribute("listid",listid);		
		model.addAttribute("film",listFilm);
		model.addAttribute("listfilm",listfilm);
		model.addAttribute("listDateofShowtime1", listDateofShowtime1);
		model.addAttribute("listDateofShowtime", listDateofShowtime);
		model.addAttribute("listShowtimewithdate", listShowtimewithdate);
		return "web/showtime";
	}
	
}
