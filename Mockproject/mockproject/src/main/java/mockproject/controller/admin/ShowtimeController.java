package mockproject.controller.admin;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mockproject.model.FilmModel;
import mockproject.model.RoomModel;
import mockproject.model.ShowtimeModel;
import mockproject.service.IFilmService;
import mockproject.service.IRoomService;
import mockproject.service.ISeatService;
import mockproject.service.IShowtimeService;
import mockproject.utils.DateFormater;
import mockproject.utils.DateTimeFormat;

@Controller(value = "ShowtimeControllerForAdmin")
public class ShowtimeController {

	@Autowired
	IShowtimeService showtimeService;

	@Autowired
	IFilmService filmService;

	@Autowired
	IRoomService roomService;

	@Autowired
	ISeatService seatService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));

	}

	@RequestMapping(value = "/admin/showtime", method = RequestMethod.GET)
	public String listShowtime(Model model) {
		List<ShowtimeModel> listShowtimes = showtimeService.listAllShowTime();

		// tuong ung voi moi showtime thi la mot film, co the trung nhau
		// tuong voi moi show showtime thi la mot phong
		for (int i = 0; i < listShowtimes.size(); i++) {
			// lay duoc cai ten cua tung film bang id
			FilmModel filmById = filmService.getFilmById(listShowtimes.get(i).getFilm_id());
			RoomModel roomNamebyId = roomService.getRoomNameById(listShowtimes.get(i).getRoom_id());

			listShowtimes.get(i).setFilm_name(filmById.getName());
			listShowtimes.get(i).setRoom_name(roomNamebyId.getName());
			listShowtimes.get(i).setFilm_imagepath(filmById.getImagepath());
		}

		model.addAttribute("showtimeList", listShowtimes);

		return "/admin/showtime/showtimelist";
	}

	@RequestMapping(value = "/admin/showtime/deleteshowtime/{showtime_id}", method = RequestMethod.GET)
	public String deleteShowtime(@PathVariable int showtime_id) {

		// delete trong bang seat
		seatService.deleteByShowtimeid(showtime_id);

		// delete trong bang show time
		showtimeService.deleteOneRow(showtime_id);

		return "redirect:/admin/showtime";
	}

	// Them moi
	@RequestMapping(value = "/admin/showtime/createfirstsence", method = RequestMethod.GET)
	public String createShowtimeFirstSence(Model model) {

		/// list tat ca cac show time ra
		List<ShowtimeModel> listShowTime = showtimeService.listAllShowTime();
		// set lai ten film cho show time
		// set lai room name cho show time

		for (int i = 0; i < listShowTime.size(); i++) {
			int filmId = listShowTime.get(i).getFilm_id();
			String filmName = filmService.getFilmById(filmId).getName();
			int roomId = listShowTime.get(i).getRoom_id();
			String roomName = roomService.getRoomById(roomId).getName();

			listShowTime.get(i).setFilm_name(filmName);
			listShowTime.get(i).setRoom_name(roomName);
		}

		model.addAttribute("allTheShowtime", listShowTime);

		// list tat ca cac ten phim ra
		List<String> listFilmName = filmService.listAllFilmName();

		ShowtimeModel showtimeModel = new ShowtimeModel();
		List<RoomModel> listRoomModels = roomService.listAllRoom();
		List<String> listRoomsName = new ArrayList<>();
		for (int i = 0; i < listRoomModels.size(); i++) {
			listRoomsName.add(listRoomModels.get(i).getName());
		}

		model.addAttribute("listRoomName", listRoomsName);
		model.addAttribute("listFilmName", listFilmName);
		model.addAttribute("showtimefirstSence", showtimeModel);
		return "/admin/showtime/showtimecreatefirstsence";
	}

	@RequestMapping(value = "/admin/showtime/createfirstsence", method = RequestMethod.POST)
	public String createShowtimeFirstSencePOST(@ModelAttribute("showtimefirstSence") ShowtimeModel showtimeInForm,
			Model model) {
		List<ShowtimeModel> showtimeList = showtimeService.listAllShowTime();
		ShowtimeModel showTime = showtimeInForm;

		String roomNameTaken = showTime.getRoom_name();
		String filmName = showTime.getFilm_name();
		// lay id phim chieu

		String showtimeTakenDate = showTime.getShowDateString();
		String showtimeHours = showTime.getStart_time_string()+ ":00";
		int idRoom = roomService.getRoomIdByName(roomNameTaken);
		int idShowTimeFilm = filmService.getIdFilmByName(filmName).getId();

		int statusValid = statusValidShowtime(showtimeTakenDate, showtimeHours, idShowTimeFilm, idRoom);
		String message = null;
		if (statusValid == 1) {
			// oke
			message = "Thêm Suất Chiếu Thành Công";
			model.addAttribute("status", 1);
			model.addAttribute("message", message);
			Timestamp startTime = DateTimeFormat.parseTimeWithDateAndHours(showtimeTakenDate, showtimeHours);

			FilmModel getFilmModel = filmService.getFilmById(idShowTimeFilm);
			int filmDuration = getFilmModel.getDuration();
			Timestamp end_time = DateTimeFormat.plusTheTimeStampValueByHours(startTime, filmDuration);
			// Thêm 15 đảm bảo nghiệp vụ 15 phút dọn phòng
			end_time = DateTimeFormat.plusTheTimeStampValueByMinutes(end_time, 15);

			Date showdate = DateFormater.toSqlDate(showtimeTakenDate);
			Timestamp created_at = DateTimeFormat.getNow();
			Timestamp updated_at = DateTimeFormat.getNow();

			showTime.setFilm_id(idShowTimeFilm);
			showTime.setRoom_id(idRoom);
			showTime.setStart_time(startTime);
			showTime.setEnd_time(end_time);
			showTime.setShowdate(showdate);
			showTime.setCreated_date(created_at);
			showTime.setUpdated_date(updated_at);

			// checking the for the price
			// if show time start < 10:00 45k
			// if showtime start 10:00 -> 17:00 50k
			// if showtime start 17:00 -> 23:59 55k
			Timestamp tenAm = DateTimeFormat.parseTimeWithDateAndHours(showtimeTakenDate, "10:00:00");
			Timestamp fivePm = DateTimeFormat.parseTimeWithDateAndHours(showtimeTakenDate, "17:00:00");
			Timestamp elenvenFiftyNinePm = DateTimeFormat.parseTimeWithDateAndHours(showtimeTakenDate, "23:59:00");
			int showTimePrice = 40000;
			if (startTime.before(tenAm)) {
				showTimePrice = 45000;
			} else if (startTime.after(tenAm) && startTime.before(fivePm)) {
				showTimePrice = 50000;
			} else if (startTime.after(fivePm) && startTime.before(elenvenFiftyNinePm)) {
				showTimePrice = 55000;
			}
			showTime.setShowtime_price(showTimePrice);

			Integer showTimeid = showtimeService.insertoneRow(showTime);
			showtimeList = showtimeService.listAllShowTime();
			//set lai list
			setForList(showtimeList);
			model.addAttribute("showtimeList", showtimeList);

			// them row * col vao bang seats

			// room_id
			RoomModel roomModel = roomService.getRoomById(idRoom);
			int row = roomModel.getRow();
			int col = roomModel.getCol();

			seatService.insertSeatsWithRoomAndShowTime(showTimeid, idRoom, row, col);

			return "/admin/showtime/showtimelist";
			// thực hiện thêm vào một lịch chiếu

		} else if (statusValid == -1) {
			// trùng lịch
			message = "Suất Chiếu Bị Trùng";
			setForList(showtimeList);
			model.addAttribute("showtimeList", showtimeList);
			model.addAttribute("status", -1);
			model.addAttribute("message", message);
			return "/admin/showtime/showtimelist";

		} /*
			 * else if (statusValid == -2) { // Không cách nhau 2 ngày từ thời điểm
			 * hiện tại message = "Suất Chiếu Phải Cách 2 Ngày Với Hiện Tại";
			 * setForList(showtimeList); model.addAttribute("status", -2);
			 * model.addAttribute("message", message); model.addAttribute("showtimeList",
			 * showtimeList); return "/admin/showtime/showtimelist"; }
			 */
		else if (statusValid == -3) {
			// trong quá khứ
			message = "Không Thể Đặt Suất Chiếu Trong Quá Khứ";
			setForList(showtimeList);
			model.addAttribute("status", -3);
			model.addAttribute("message", message);
			model.addAttribute("showtimeList", showtimeList);
			return "/admin/showtime/showtimelist";

		}
		return "redirect:/admin/showtime";
	}

	@RequestMapping(value = "/admin/showtime/editshowtime/{id}", method = RequestMethod.GET)
	public String editShowtime(@PathVariable String id, Model model) {
		/// list tat ca cac show time ra
		List<ShowtimeModel> listShowTime = showtimeService.listAllShowTime();
		// set lai ten film cho show time
		// set lai room name cho show time

		for (int i = 0; i < listShowTime.size(); i++) {
			int filmId = listShowTime.get(i).getFilm_id();
			String filmName = filmService.getFilmById(filmId).getName();
			int roomId = listShowTime.get(i).getRoom_id();
			String roomName = roomService.getRoomById(roomId).getName();

			listShowTime.get(i).setFilm_name(filmName);
			listShowTime.get(i).setRoom_name(roomName);
		}

		model.addAttribute("allTheShowtime", listShowTime);

		// lay ra showtime with that id
		ShowtimeModel showtimeModel = showtimeService.getShowTimeWithId(Integer.parseInt(id));
		
		showtimeModel.setStart_time_time_type(DateTimeFormat.timeStampToTime(showtimeModel.getStart_time()));

		// lấy thông tin phòng chiếu
		List<RoomModel> listRoomModels = roomService.listAllRoom();
		List<String> listRoomsName = new ArrayList<>();
		for (int i = 0; i < listRoomModels.size(); i++) {
			listRoomsName.add(listRoomModels.get(i).getName());
		}

		List<String> listFilmName = filmService.listAllFilmName();

		// gán vào model

		model.addAttribute("listRoomName", listRoomsName);
		model.addAttribute("listFilmName", listFilmName);
		model.addAttribute("showtimeedit", showtimeModel);

		return "/admin/showtime/showtimeedit";
	}

	@RequestMapping(value = "/admin/showtime/editshowtime/{id}", method = RequestMethod.POST)
	public String editShowtimePost(@ModelAttribute("showtimeedit") ShowtimeModel showtimeInForm, Model model) {
		List<ShowtimeModel> showtimeList = showtimeService.listAllShowTime();
		ShowtimeModel showtimeToCheck = showtimeInForm;
		
		int showTimeid = showtimeToCheck.getId();
		System.out.println(showTimeid);
		String roomNameTaken = showtimeToCheck.getRoom_name();
		String filmName = showtimeToCheck.getFilm_name();

		// Get the date of showdate
		String showtimeTakenDate = showtimeToCheck.getShowDateString();
		// get the hourse of showdate
		String showtimeHours = showtimeToCheck.getStart_time_string();
		if(showtimeHours.length() == 5) {
			showtimeHours = showtimeHours + ":00";
		}
		// find id room
		int idRoom = roomService.getRoomIdByName(roomNameTaken);
		// find id of film
		int idShowTimeFilm = filmService.getIdFilmByName(filmName).getId();

		int statusValid = statusValidShowtimeForUpdate(showtimeTakenDate, showtimeHours, idShowTimeFilm, idRoom,showtimeToCheck.getId());

		String message = null;
		if (statusValid == 1) {
			message = "Cập nhật thành công cho suất chiếu với id = " + showtimeToCheck.getId();
			model.addAttribute("status", 1);
			model.addAttribute("message", message);

			Timestamp startTime = DateTimeFormat.parseTimeWithDateAndHours(showtimeTakenDate, showtimeHours);

			FilmModel getFilmModel = filmService.getFilmById(idShowTimeFilm);
			int filmDuration = getFilmModel.getDuration();
			Timestamp end_time = DateTimeFormat.plusTheTimeStampValueByHours(startTime, filmDuration);
			// Thêm 15 đảm bảo nghiệp vụ 15 phút dọn phòng
			end_time = DateTimeFormat.plusTheTimeStampValueByMinutes(end_time, 15);
			Date showdate = DateFormater.toSqlDate(showtimeTakenDate);
			Timestamp updated_at = DateTimeFormat.getNow();

			showtimeToCheck.setFilm_id(idShowTimeFilm);
			showtimeToCheck.setRoom_id(idRoom);
			showtimeToCheck.setStart_time(startTime);
			showtimeToCheck.setEnd_time(end_time);
			showtimeToCheck.setShowdate(showdate);
			showtimeToCheck.setUpdated_date(updated_at);

			// checking the for the price
			// if show time start < 10:00 45k
			// if showtime start 10:00 < 17:00 50k
			// if showtime start 17:00 < 23:59 55k
			Timestamp tenAm = DateTimeFormat.parseTimeWithDateAndHours(showtimeTakenDate, "10:00:00");
			Timestamp fivePm = DateTimeFormat.parseTimeWithDateAndHours(showtimeTakenDate, "17:00:00");
			Timestamp elenvenFiftyNinePm = DateTimeFormat.parseTimeWithDateAndHours(showtimeTakenDate, "23:59:00");
			int showTimePrice = 0;
			if (startTime.before(tenAm)) {
				showTimePrice = 45000;
			} else if (startTime.after(tenAm) && startTime.before(fivePm)) {
				showTimePrice = 50000;
			} else if (startTime.after(fivePm) && startTime.before(elenvenFiftyNinePm)) {
				showTimePrice = 550000;
			}
			showtimeToCheck.setShowtime_price(showTimePrice);

			showtimeService.updateWithId(showtimeToCheck);

			// list lai tat cac cac show time sau khi sua
			showtimeList = showtimeService.listAllShowTime();
			setForList(showtimeList);
			model.addAttribute("showtimeList", showtimeList);

			return "/admin/showtime/showtimelist";

		} else if (statusValid == -1) {
			// trùng lịch
			message = "Cập nhật thất bại "+": Suất Chiếu "+ showtimeToCheck.getId() +" Bị Trùng";
			setForList(showtimeList);
			model.addAttribute("showtimeList", showtimeList);
			model.addAttribute("status", -1);
			model.addAttribute("message", message);
			return "/admin/showtime/showtimelist";

		} /*
			 * else if (statusValid == -2) { // Không cách nhau 2 ngày từ thời điểm
			 * hiện tại message = "Cập nhật thất baị : Suất Chiếu "+
			 * showtimeToCheck.getId()+ " Phải Cách 2 Ngày Với Hiện Tại";
			 * setForList(showtimeList); model.addAttribute("status", -2);
			 * model.addAttribute("message", message); model.addAttribute("showtimeList",
			 * showtimeList); return "/admin/showtime/showtimelist"; }
			 */
		else if (statusValid == -3) {
			// trong quá khứ
			message = "Cập nhật thất bại: Không Thể Đặt Suất Chiếu" + showtimeToCheck.getId() +" Trong Quá Khứ";
			setForList(showtimeList);
			model.addAttribute("status", -3);
			model.addAttribute("message", message);
			model.addAttribute("showtimeList", showtimeList);
			return "/admin/showtime/showtimelist";

		}

		return null;
	}

	public int statusValidShowtime(String date, String hours, int idFilm, int roomId) {

		int status = -1;

		Timestamp startTime = DateTimeFormat.parseTimeWithDateAndHours(date, hours);

		// get duration to set the end time
		FilmModel getFilmModel = filmService.getFilmById(idFilm);
		int filmDuration = getFilmModel.getDuration();

		Timestamp endTime = DateTimeFormat.plusTheTimeStampValueByHours(startTime, filmDuration);

		Date showDate = DateFormater.toSqlDate(date);
		LocalDate dateNow = LocalDate.now();

		if (showDate.before(Date.valueOf(dateNow))) {
			// Can not add show Date trong quá khứ
			return -3;
		}

		/*
		 * if (!showDate.equals(Date.valueOf(dateNow))) { LocalDateTime localDateTimeNow
		 * = LocalDateTime.now(); Timestamp timeNow =
		 * Timestamp.valueOf(localDateTimeNow); // add mot lich chieu phai it nhat cach
		 * 2 ngay
		 * 
		 * long secondDifferentFromNow = timeNow.getTime() - startTime.getTime(); if
		 * (secondDifferentFromNow < 0) {
		 * 
		 * // lich chieu hien gio dung sau thoi gian bay gio secondDifferentFromNow =
		 * (long) Math.abs(secondDifferentFromNow); // phai cach it nhat 2 ngay moi cho
		 * set lich chieu 172800000ms if (secondDifferentFromNow >= 172800000) { // sau
		 * 2 ngày status = 1; } else { // Không sau 2 ngày status = -2; return status;
		 * }
		 * 
		 * }
		 * 
		 * }
		 */
		// List show of that day of that rooms
		List<ShowtimeModel> listShowtimeInDay = showtimeService.listAllShowTimeWithDateAndRoomId(showDate, roomId);

		if (listShowtimeInDay.size() == 0) {
			status = 1; // ok get vao
		} else {
			for (int i = 0; i < listShowtimeInDay.size(); i++) {
				Timestamp startTimeDB = listShowtimeInDay.get(i).getStart_time();

				Timestamp endTimeDB = listShowtimeInDay.get(i).getEnd_time();

				if (startTimeDB == null) {
					continue;
				}
				if (endTimeDB == null) {
					continue;
				}

				if (startTime.after(startTimeDB) && startTime.before(endTimeDB)) {
					// Trùng với một lịch chiếu
					status = -1;
					break;
				} else if (startTime.after(endTimeDB)) {
					// Ok
					status = 1;
				} else if (startTime.before(startTimeDB)) {
					if (endTime.before(startTimeDB)) {
						status = 1;
					} else {
						// trùng lịch chiếu
						status = -1;
						return status;
					}
				}
			}
		}

		return status;

	}

	public int statusValidShowtimeForUpdate(String date, String hours, int idFilm, int roomId, int showtime_id) {
		int status = -1;
		Timestamp startTime = DateTimeFormat.parseTimeWithDateAndHours(date, hours);

		// get duration to set the end time
		FilmModel getFilmModel = filmService.getFilmById(idFilm);
		int filmDuration = getFilmModel.getDuration();

		Timestamp endTime = DateTimeFormat.plusTheTimeStampValueByHours(startTime, filmDuration);

		Date showDate = DateFormater.toSqlDate(date);
		LocalDate dateNow = LocalDate.now();

		if (showDate.before(Date.valueOf(dateNow))) {
			// Can not add show Date trong quá khứ
			return -3;
		}

		/*
		 * if (!showDate.equals(Date.valueOf(dateNow))) { LocalDateTime localDateTimeNow
		 * = LocalDateTime.now(); Timestamp timeNow =
		 * Timestamp.valueOf(localDateTimeNow); // add mot lich chieu phai it nhat cach
		 * 2 ngay
		 * 
		 * long secondDifferentFromNow = timeNow.getTime() - startTime.getTime(); if
		 * (secondDifferentFromNow < 0) {
		 * 
		 * // lich chieu hien gio dung sau thoi gian bay gio secondDifferentFromNow =
		 * (long) Math.abs(secondDifferentFromNow); // phai cach it nhat 2 ngay moi cho
		 * set lich chieu 172800000ms if (secondDifferentFromNow >= 172800000) { // sau
		 * 2 ngày status = 1; } else { // Không sau 2 ngày status = -2; return status;
		 * }
		 * 
		 * }
		 * 
		 * }
		 */

		// kiểm tra với tất cả show time khác mà có id không bằng cái đó cái
		// id n
		// List show of that day of that rooms
		List<ShowtimeModel> listShowtimeInDay = showtimeService.listAllShowTimeWithDateAndRoomIdButNotEquasToid(showDate, roomId,showtime_id);
		
		if (listShowtimeInDay.size() == 0) {
			status = 1; // ok get vao
		}else {
			for (int i = 0; i < listShowtimeInDay.size(); i++) {
				Timestamp startTimeDB = listShowtimeInDay.get(i).getStart_time();

				Timestamp endTimeDB = listShowtimeInDay.get(i).getEnd_time();

				if (startTimeDB == null) {
					continue;
				}
				if (endTimeDB == null) {
					continue;
				}

				if (startTime.after(startTimeDB) && startTime.before(endTimeDB)) {
					// Trùng với một lịch chiếu
					status = -1;
					break;
				} else if (startTime.after(endTimeDB)) {
					// Ok
					status = 1;
				} else if (startTime.before(startTimeDB)) {
					if (endTime.before(startTimeDB)) {
						status = 1;
					} else {
						// trùng lịch chiếu
						status = -1;
						return status;
					}
				}
			}
		}
		return status;
	}
	
	public void setForList(List<ShowtimeModel> showtimeList) {
		for(int i = 0; i < showtimeList.size(); i++) {
			int id_film = showtimeList.get(i).getFilm_id();
			String imagePath = filmService.getFilmById(id_film).getImagepath();
			String film_name = filmService.getFilmById(id_film).getName();
			showtimeList.get(i).setFilm_imagepath(imagePath);
			showtimeList.get(i).setFilm_name(film_name);
			
			int id_room = showtimeList.get(i).getRoom_id();
			String roomName = roomService.getRoomById(id_room).getName();
			showtimeList.get(i).setRoom_name(roomName);
		}
	}
}
