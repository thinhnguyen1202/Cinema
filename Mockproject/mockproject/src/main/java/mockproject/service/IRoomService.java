package mockproject.service;

import java.util.List;

import mockproject.model.RoomModel;

public interface IRoomService {
	RoomModel getRoomNameById(int room_id);
	List<RoomModel> listAllRoom();
	int getRoomIdByName(String name);
	RoomModel getRoomById(int room_id);	
	int insertRoom(RoomModel roomModel);
	void updateRoomWithId( RoomModel roomModel);
}
