package mockproject.controller.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import mockproject.model.BookingDetailModel;
import mockproject.model.TicketModel;
import mockproject.model.UserModel;
import mockproject.service.IBookingDetailService;
import mockproject.service.ISeatService;
import mockproject.service.ITicketService;

@Controller
@SessionAttributes(value = {"user","usertickets","messageForDelete","statusForDelete"})
public class UserfTicketController {
	
	@Autowired
	ITicketService ticketService;
	
	@Autowired
	IBookingDetailService bookingDetailService;
	
	
	@Autowired 
	ISeatService seatService;
	
	
	@RequestMapping(value = "/lich-su-mua-ve", method = RequestMethod.GET)
	public String userTicketGet(Model model, HttpSession session) {
		// lấy được cái thông tin của vé dựa vào id người dùng
		// generate thông tin ra mã QR cho người dùng
		
		if(null != session.getAttribute("user")) {
			
			
			UserModel userModel = (UserModel) session.getAttribute("user");
			System.out.println(userModel.getUsername() + "in lich su mua ve");
			int userId = userModel.getId();
			List<TicketModel> listTicketOfUser = ticketService.listAllTickByUserId(userId);
			String message = "Lịch sử vé bạn đã đặt ";
			int status = 1;
			if(listTicketOfUser.isEmpty()) {
				message = "Bạn chưa xem phim nào cả, đặt đi chờ chi nữa!";
				status = -1;
			}
			model.addAttribute("message",message);
			model.addAttribute("status",status);
			model.addAttribute("usertickets",listTicketOfUser);
			
			
		}else {
			return "redirect:/home";
		}
		
		
		return "/web/userTicket/userticket";
	}
	
	@RequestMapping(value ="/lich-su-mua-ve/deleteticket/{id}", method = RequestMethod.GET)
	public String userTicketDelete(@PathVariable String id , HttpSession session, Model model) {
		
		UserModel userModle = (UserModel) session.getAttribute("user");
		
		// get duoc id cua tickets
		// xoa the hien o tickets
		
		
		// lay duoc the hien o bookings
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
		return "redirect:/lich-su-mua-ve";
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
