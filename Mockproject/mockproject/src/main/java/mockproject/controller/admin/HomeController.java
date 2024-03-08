package mockproject.controller.admin;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import mockproject.model.ShowtimeModel;
import mockproject.model.UserModel;
import mockproject.service.ISeatService;
import mockproject.service.IShowtimeService;
import mockproject.service.IUserService;
import mockproject.utils.DateFormater;

@Controller(value = "HomeControllerThisIsForStatistic")
@SessionAttributes("user")
public class HomeController {

	@Autowired
	IShowtimeService showtimeService;
	@Autowired
	ISeatService seatService;
	
	@Autowired 
	IUserService userService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getStatics(Model model,Principal principal) {
		model.addAttribute("none");
		Date today = DateFormater.toSqlDateNow();
		Date yesterday = DateFormater.toSqlDateYesterday();
		Date past7days = DateFormater.toSqlDatePastSeveday();
		
		Integer incomeToday = incomeForSpecificdate(today);
		Integer yesterdayIncome = incomeForSpecificdate(yesterday);
		Integer weekIncome = incomeForWeek(past7days, today);
		
		
		
		model.addAttribute("today", today.toString());
		model.addAttribute("yesterday", yesterday.toString());
		model.addAttribute("past7days", past7days.toString());
		model.addAttribute("incometoday", incomeToday);
		model.addAttribute("incomyesterday", yesterdayIncome);
		model.addAttribute("incomeweek",weekIncome);
		UserModel user = userService.getUserByName(principal.getName());
		System.out.println(user.getUsername());
		model.addAttribute("user", user);
		return "admin/statistic/statistic";
	}
	
	@RequestMapping(value = "/admin/datetodate", method = RequestMethod.GET)
	public String forToday(@RequestParam("pastday") String pastDay, @RequestParam("futureday") String futureDay, Model model) {
		Date today = DateFormater.toSqlDateNow();
		Date yesterday = DateFormater.toSqlDateYesterday();
		Date past7days = DateFormater.toSqlDatePastSeveday();
		
		Integer incomeToday = incomeForSpecificdate(today);
		Integer yesterdayIncome = incomeForSpecificdate(yesterday);
		Integer weekIncome = incomeForWeek(past7days, today);
		
		
		
		model.addAttribute("today", today.toString());
		model.addAttribute("yesterday", yesterday.toString());
		model.addAttribute("past7days", past7days.toString());
		model.addAttribute("incometoday", incomeToday);
		model.addAttribute("incomyesterday", yesterdayIncome);
		model.addAttribute("incomeweek",weekIncome);
		
		
		Date pastDayForQuery = DateFormater.toSqlDate(pastDay);
		Date futureDayForQuery = DateFormater.toSqlDate(futureDay);
		
		if(!checkingValid(pastDayForQuery, futureDayForQuery)) {
			// that bai
			System.out.println("Thất Bại");
			String message = "Ngày trong quá khứ " + pastDayForQuery.toString() + " > " + futureDayForQuery.toString(); 
			model.addAttribute("message", message);
			model.addAttribute("status", -1);
			return "admin/statistic/statistic";
		}else {
			String message = "(" + pastDay + ") <-> " + "(" + futureDay + ")";
			model.addAttribute("message",message);
			model.addAttribute("status", 1);
		}
		
		//nếu thành công thì truy vấn
		// từ ngày quá khứ đến hiện tại
		Integer incomeFormDate2Date = incomeFromDateToDate(pastDayForQuery, futureDayForQuery);
		
		Integer seats = seatFromDatetoDate(pastDayForQuery, futureDayForQuery);
		
		model.addAttribute("incomeForDate2Date",incomeFormDate2Date );
		model.addAttribute("seats",seats);
		
	
		// co ngay qua khu and ngay tuong lai
		
		return "admin/statistic/statistic";
	}
	
	boolean checkingValid(Date date1, Date date2) {
		if(date1.after(date2)) {
			return false;
		}
		return true;
	}
	
	

	int incomeForSpecificdate(Date date) {
		// truy vấn từ bảng showtime cho ngày hôm nay -> lấy được id show time
		// sử dụng showtime id để truy vấn bảng seats -> lấy được số lượng ghế
		// với show time đã bán ra trong ngày hôm này
		// lấy số lượng ghế bán ra nhân với giá showtime
		List<ShowtimeModel> listShowTimeWithThatDate = showtimeService.listAllShowtimeWithDate(date);
		int income = 0;
		if (listShowTimeWithThatDate != null && listShowTimeWithThatDate.size() != 0) {
			for (int i = 0; i < listShowTimeWithThatDate.size(); i++) {
				int idShowTime = listShowTimeWithThatDate.get(i).getId();
				int price = listShowTimeWithThatDate.get(i).getShowtime_price();

				// truy vấn ghế số lượng ghế với show time đó
				int numberOfBookedSeats = seatService.countingBookedSeatWithThatShowTimeId(idShowTime);
				if (numberOfBookedSeats == 0) {
					continue;
				} else {

					income += numberOfBookedSeats * price;
				}

			}
		}
		return income;
	}
	
	int incomeForWeek(Date date1, Date date2) {
		// truy vấn từ bảng showtime, list ra được các showtime -> có showdate nằm trong khoảng đó
		
		List<ShowtimeModel> listShowTimeForWeek = showtimeService.listAllShowTimeForWeek(date1, date2);
		
		int income = 0;
		if(listShowTimeForWeek != null && listShowTimeForWeek.size() != 0) {
			for(int i = 0; i < listShowTimeForWeek.size(); i++) {
				int idShowtime = listShowTimeForWeek.get(i).getId();
				int price = listShowTimeForWeek.get(i).getShowtime_price();
				
				// truy vấn số lượng ghế với showtime đó
				int numberOfBookedSeats = seatService.countingBookedSeatWithThatShowTimeId(idShowtime);
				
				if(numberOfBookedSeats == 0) {
					continue;
				}else {
					income += numberOfBookedSeats * price;
				}
			}
		}
		return income;
	}
	
	int seatFromDatetoDate(Date date1, Date date2) {
		
		List<ShowtimeModel> listShowTimeForWeek = showtimeService.listAllShowTimeForWeek(date1, date2);
		
		
		int seats = 0;
		if(listShowTimeForWeek != null && listShowTimeForWeek.size() != 0) {
			for(int i = 0; i < listShowTimeForWeek.size(); i++) {
				int idShowtime = listShowTimeForWeek.get(i).getId();
				
				// truy vaan ghe
				int numberOfBookedSeats = seatService.countingBookedSeatWithThatShowTimeId(idShowtime);
				
				seats += numberOfBookedSeats;
			}
		}
		
		return seats;
	}
	
	

	int incomeFromDateToDate(Date date1, Date date2) {

		return incomeForWeek(date1, date2);
	}

}
