package mockproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mockproject.model.RoomModel;

public class RoomMapper implements RowMapper<RoomModel> {

	@Override
	public RoomModel mapRow(ResultSet resultSet) {
		try {
			RoomModel model = new RoomModel();
			model.setId(resultSet.getInt("id"));
			model.setName(resultSet.getString("name"));
			model.setStatus(resultSet.getString("status"));
			model.setRow(resultSet.getInt("row"));
			model.setCol(resultSet.getInt("col"));
			model.setTotal(resultSet.getInt("row") * resultSet.getInt("col"));
			return model;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
