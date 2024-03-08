package mockproject.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mockproject.dao.IRoomDAO;
import mockproject.mapper.RoomMapper;
import mockproject.model.RoomModel;
import mockproject.model.UserModel;

@Repository
public class RoomDAO extends AbstractDAO<RoomModel> implements IRoomDAO {

	@Override
	public RoomModel getRoomNameById(int room_id) {
		String sql = "select * from rooms where id =  ?";
		
		List<RoomModel> roomList = query(sql, new RoomMapper(),room_id);
		
		if(roomList.size() != 0) {
			return roomList.get(0);
		}
		return null;
	}

	@Override
	public List<RoomModel> listAllRoom() {
		String sql = "select * from rooms";
		
		return query(sql, new RoomMapper());
	}

	@Override
	public int getRoomIdByName(String name) {
		String sql = "select * from rooms where name = ? limit 1";
		List<RoomModel> listRooms = query(sql, new RoomMapper(), name);
		return listRooms.get(0).getId();
	}

	@Override
	public RoomModel getRoomById(int room_id) {
		String sql = "select * from rooms where id = ? limit 1";
		List<RoomModel> roomList = query(sql, new RoomMapper(), room_id);
		if(roomList != null && roomList.size() > 0) {
			return roomList.get(0);
		}
		return null;
	}

	@Override
	public int insertRoom(RoomModel roomModel) {
		// TODO Auto-generated method stub
		String sql = "insert into rooms (name,status,rooms.row, rooms.col) values (?,?,?,?)";
		return insert(sql, roomModel.getName(),roomModel.getStatus(), roomModel.getRow(), roomModel.getCol());
	}
	
	@Override
	public void updateRoomWithId( RoomModel roomModel) {
		String sql = "update rooms set "
				+ "name = ?,"
				+ "status = ?,"
				+ "where id = ?";
		update(sql, roomModel.getName(), roomModel.getStatus(), roomModel.getId());
	}
	
	
	
	
	
	
	
	
	
	
}
