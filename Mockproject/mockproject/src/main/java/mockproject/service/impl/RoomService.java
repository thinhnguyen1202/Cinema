package mockproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mockproject.dao.IRoomDAO;
import mockproject.model.RoomModel;
import mockproject.service.IRoomService;

@Service
public class RoomService implements IRoomService{
	
	@Autowired
	IRoomDAO roomDao;

	@Override
	public RoomModel getRoomNameById(int room_id) {
		return roomDao.getRoomNameById(room_id);
	}
	@Override
	public void updateRoomWithId( RoomModel roomModel) {
		roomDao.updateRoomWithId(roomModel);
	}

	@Override
	public List<RoomModel> listAllRoom() {
		// TODO Auto-generated method stub
		return roomDao.listAllRoom();
	}

	@Override
	public int getRoomIdByName(String name) {
		return roomDao.getRoomIdByName(name);
	}

	@Override
	public RoomModel getRoomById(int room_id) {
		return roomDao.getRoomById(room_id);
	}

	@Override
	public int insertRoom(RoomModel roomModel) {
		// TODO Auto-generated method stub
		return roomDao.insertRoom(roomModel);
	}
	
	
	
	
	
	
	
	
	
}
