package mockproject.dao;

import java.util.List;

import mockproject.model.RoomModel;

public interface IRoomDAO extends GenericDAO<RoomModel> {
	

	RoomModel getRoomNameById(int room_id);
	int getRoomIdByName(String name);
	List<RoomModel> listAllRoom();
	
	RoomModel getRoomById(int room_id);
	int insertRoom(RoomModel roomModel);
	void updateRoomWithId( RoomModel roomModel);
}
