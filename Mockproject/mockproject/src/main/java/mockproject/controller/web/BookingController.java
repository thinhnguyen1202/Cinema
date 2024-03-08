package mockproject.controller.web;

import java.security.Principal;		
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import mockproject.service.impl.CategoryService;

@Controller
@SessionAttributes(value = { "user", "showtime", "seat" , "messageForBookingTickets", "statusForBookingTickets"})
public class BookingController {
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

	@RequestMapping(value = "/booking/{id}", method = RequestMethod.GET)
	public String bookingTime(@PathVariable int id, Model model) {

		List<ShowtimeModel> listShowtime = showtimeService.listShowtimesEnableBookingByFimId(id);
		FilmModel film = filmService.getFilmById(id);
		for (int i = 0; i < listShowtime.size(); i++) {
			// lay duoc cai ten cua tung film bang id
			FilmModel filmById = filmService.getFilmById(listShowtime.get(i).getFilm_id());
			RoomModel roomNamebyId = roomService.getRoomNameById(listShowtime.get(i).getRoom_id());
			
			listShowtime.get(i).setFilm_name(filmById.getName());
			listShowtime.get(i).setRoom_name(roomNamebyId.getName());
			listShowtime.get(i).setFilm_imagepath(filmById.getImagepath());
		}

		List<CategoryModel> listCatgory = categoryService.listAllCategoryByFilmid(id);
		film.setListCategory(listCatgory);

		Set<Date> listDateofShowtime = new TreeSet<>();
		for (int i = 0; i < listShowtime.size(); i++) {
			listDateofShowtime.add(listShowtime.get(i).getShowdate());
		}
		model.addAttribute("listShowtime", listShowtime);
		model.addAttribute("film", film);
		model.addAttribute("listDateofShowtime", listDateofShowtime);
		return "web/booking-time";
	}

	@RequestMapping(value = "/booking/chon-ghe", method = RequestMethod.POST)
	public String bookingSeat(Model model, @RequestParam("book-time") int showtime_id) {
		ShowtimeModel showtimeModel = showtimeService.getShowTimeWithId(showtime_id);
		List<SeatModel> seats = seatService.listAllSeatByShowtimeid(showtime_id);

		RoomModel roomModel = roomService.getRoomById(showtimeModel.getRoom_id());
		FilmModel film = filmService.getFilmById(showtimeModel.getFilm_id());

		model.addAttribute("showtime", showtimeModel);
		model.addAttribute("room", roomModel);
		model.addAttribute("seats", seats);
		model.addAttribute("film", film);

		return "web/booking-seat";
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/booking/thanh-toan/{showtime_id}", method = RequestMethod.POST)
	public String payTicket(Model model, @PathVariable int showtime_id, @RequestParam("seat") int[] seatsId,
			Principal principal) {
		ShowtimeModel showtimeModel = showtimeService.getShowTimeWithId(showtime_id);
		List<SeatModel> seats = seatService.listAllSeatByShowtimeid(showtime_id);
		RoomModel roomModel = roomService.getRoomById(showtimeModel.getRoom_id());
		FilmModel film = filmService.getFilmById(showtimeModel.getFilm_id());

		model.addAttribute("showtime", showtimeModel);
		model.addAttribute("room", roomModel);
		model.addAttribute("seats", seats);
		model.addAttribute("film", film);

		List<SeatModel> seatsActive = new ArrayList<>();

		for (int s : seatsId) {
			seatsActive.add(seatService.getSeatById(s));
		}
		
			model.addAttribute("seatsActive", seatsActive);

			model.addAttribute("totalSeatsBooking", seatsActive.size());

			String userBooking = principal.getName();

			UserModel userModel = userService.getUserByName(userBooking);

			model.addAttribute("user", userModel);

			// add to for seat
			model.addAttribute("seat", seatsActive);
			// add to for show time

			return "web/pay-ticket";
							
	}
	
	

	@RequestMapping(value = "/booking/thanh-toan/thanh-toan-thanh-cong", method = RequestMethod.POST)
	public String successfulPaymentPOST(Principal principal, HttpSession session, Model model) {

		System.out.println("Vao day");

		UserModel user = (UserModel) session.getAttribute("user");
		ShowtimeModel showTime = (ShowtimeModel) session.getAttribute("showtime");

		@SuppressWarnings("unchecked")
		List<SeatModel> listSeat = (List<SeatModel>) session.getAttribute("seat");
		// we have showtime id
		// we have list of selected seats id
		// we have id user
		// so we need insert the booking
		StringBuilder message = new StringBuilder();
		int status = 1;
		List<Integer> listNewIdTickets = new ArrayList<>();
		if (doInsert(listSeat, user.getId(), showTime.getId(), listNewIdTickets)) {
			message.append("Quý khách đã đặt " + listNewIdTickets.size() + " vé thành công!\n Id vé: ");
			for (int i = 0; i < listNewIdTickets.size(); i++) {
				message.append(listNewIdTickets.get(i) + ",");
				
			}
			message.append("\n Quý khách vui lòng đến lấy vé và thanh toán trong vòng 30 phút không hệ thống sẽ hủy vé!");

			model.addAttribute("messageForBookingTickets", message);

			model.addAttribute("statusForBookingTickets", status);
		} else {

			message.append("Có lỗi xảy ra trong quá trình đặt vé, quý khách xin vui lòng đặt lại!");
			status = -1;
			model.addAttribute("messageForBookingTickets", message);

			model.addAttribute("statusForBookingTickets", status);
		}

		return "redirect:/lich-su-mua-ve";
	}

	boolean doInsert(List<SeatModel> listSeat, int userId, int showTime_id, List<Integer> listIdOfNewTickets) {

		try {
			for (int i = 0; i < listSeat.size(); i++) {
				int seat_id = listSeat.get(i).getId();

				// add the hien vao booking
				int new_booking_id = insertBookings(userId);
				int new_ticket_id = insertTicket(showTime_id);
				int new_booking_details_id = insertBookingDetails(new_booking_id, new_ticket_id, seat_id);
				listIdOfNewTickets.add(new_ticket_id);
				// update the seat status = 1;

				seatService.updateForBookedSeat(seat_id);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	int insertBookings(int user_id) {
		return ticketService.insertBookings(user_id);
	}

	int insertTicket(int show_time_id) {
		return ticketService.insertTickets(show_time_id);
	}

	int insertBookingDetails(int booking_id, int ticket_id, int seat_id) {
		return ticketService.insertBookingsDetail(booking_id, ticket_id, seat_id);
	}
	
	
}


