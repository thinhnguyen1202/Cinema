package mockproject.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mockproject.model.CategoryModel;
import mockproject.model.FilmModel;
import mockproject.model.RoomModel;
import mockproject.model.UserModel;
import mockproject.service.IRoomService;
import mockproject.utils.DateTimeFormat;

@Controller(value = "RoomControllerForAdmin")
public class RoomController {

	@Autowired
	IRoomService roomService;

	@RequestMapping(value = "/admin/room", method = RequestMethod.GET)
	public String roomHome(Model model) {
		List<RoomModel> listRoom = roomService.listAllRoom();

		model.addAttribute("listRoom", listRoom);
		return "/admin/room/roomlist";
	}
	
	

	@RequestMapping(value = "/admin/room/create", method = RequestMethod.GET)
	public String roomCreate(Model model) {
		RoomModel roomModel = new RoomModel();
		model.addAttribute("roomCreate", roomModel);

		return "/admin/room/roomcreate";
	}

	@RequestMapping(value = "/admin/room/create", method = RequestMethod.POST)
	public String roomCreatePost(@ModelAttribute("roomCreate") RoomModel roomInForm, Model model) {
		RoomModel roomModel = roomInForm;
		List<RoomModel> listRoom = roomService.listAllRoom();
		int status = validInputRoom(roomModel);

		String message = null;
		switch (status) {
		case 1:
			int id = roomService.insertRoom(roomModel);
			message = "Thêm mới phòng thành công(ID = " + id + ")";
			listRoom = roomService.listAllRoom();
			model.addAttribute("message",message);
			model.addAttribute("status",status);
			model.addAttribute("listRoom",listRoom);
			
			break;
		case -1:
			message = "Vui lòng nhập vào trường tên phòng";
			model.addAttribute("message",message);
			model.addAttribute("status",status);
			model.addAttribute("listRoom",listRoom);
			break;
		case -2:
			message = "Phòng chiếu đã tồn tại";
			model.addAttribute("message",message);
			model.addAttribute("status",status);
			model.addAttribute("listRoom",listRoom);
			break;
		default:
			break;
		}
		
		if(status == 1) {
			return "/admin/room/roomlist";
		}
		model.addAttribute("roomCreate",roomModel);
		return "/admin/room/roomcreate";
	}
	

	int validInputRoom(RoomModel room) {
		/**
		 * 1 for oke
		 * 
		 * -1 for null -2 for duplicate name
		 */
		int status = 1;
		String roomInputName = room.getName();
		if (roomInputName == null || roomInputName.length() == 0) {
			status = -1;
			return status;
		}
		List<RoomModel> roomList = roomService.listAllRoom();
		if (roomList != null && roomList.size() != 0) {
			for (int i = 0; i < roomList.size(); i++) {
				if (roomInputName.equalsIgnoreCase(roomList.get(i).getName())) {
					status = -2;
					return status;
				}
			}
		}

		return status;
	}
	
	

}
