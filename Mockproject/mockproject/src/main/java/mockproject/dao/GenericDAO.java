package mockproject.dao;

import java.util.List;

import mockproject.mapper.RowMapper;



public interface GenericDAO<T> {
	<T> List<T> query(String sql, RowMapper<T> rowMapper, Object... params);
	
	
	// Them xoa sua
	void update(String sql, Object... params);
	Integer insert(String sql, Object... params);
	void delete(String sql, Object...params);
	
}
