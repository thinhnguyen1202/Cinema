package mockproject.controller.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mockproject.model.BookingDetailModel;
import mockproject.model.TicketModel;
import mockproject.service.IBookingDetailService;
import mockproject.service.ISeatService;
import mockproject.service.ITicketService;

@Controller
public class TicketController {
	
	@Autowired
	ITicketService ticketService;
	
	@Autowired
	IBookingDetailService bookingDetailService;
	
	@Autowired 
	ISeatService seatService;
	
	@RequestMapping(value = "/admin/ticket", method = RequestMethod.GET)
	public String ticketHome(Model model) {
		int soluong = ticketService.getCountOfBookings();
		List<TicketModel> listTicketAlreadyBooked = ticketService.listALlTicket();
		model.addAttribute("listTicketAlreadyBooked", listTicketAlreadyBooked);
		model.addAttribute("soluong",Integer.valueOf(soluong));
		return "/admin/ticket/ticketlist";
	}
	
	@RequestMapping(value = "/admin/ticket/deleteticket/{id}", method = RequestMethod.GET)
	public String deleteFilm(@PathVariable String id , HttpSession session, Model model) {
		
		BookingDetailModel bookingDetailModel = bookingDetailService.getBookingDetailByTicketsID(Integer.valueOf(id));
		String message;
		if(bookingDetailModel == null) {
			/// có lỗi xảy ra ở đây
			message = "Có lỗi xảy ra, quý khách thông báo chờ chút!!";
			model.addAttribute("messageForDelete", message);
			model.addAttribute("statusForDelete",-1);
			
			
		}else {
			deleteTickets(bookingDetailModel);
			message = "Hủy vé thành công!";
			model.addAttribute("messageForDelete", message);
			model.addAttribute("statusForDelete",1);
			
		
		}

		return "redirect:/admin/ticket";
	}
    void deleteTickets(BookingDetailModel bookDetailModel) {
		
		int seats_id = bookDetailModel.getSeats_id();
		int book_id = bookDetailModel.getBooking_id();
		int tick_id = bookDetailModel.getTickets_id();
		
		
		// xoa the hien o tickets
		
		ticketService.deleteOneRow(tick_id);
		// xoa the hien o bookings
		bookingDetailService.deleteOneRowOnBooking(book_id);
		// xoa the hien o bookingdetail
		
		bookingDetailService.deleteOneRowOnBookingDetails(bookDetailModel.getId());
		
		//update status o ghe ve 0
		
		
		seatService.updateSeatStatus(seats_id);
	
	}
}
