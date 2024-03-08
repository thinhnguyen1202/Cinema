package mockproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import mockproject.dao.IRatingDAO;
import mockproject.mapper.RatingsMapper;
import mockproject.model.RatingsModel;
@Repository
public class RatingsDAO extends AbstractDAO<RatingsModel> implements IRatingDAO{

	@Override
	public int getStarOfFilmByFilmId(int id) {
		// get duoc so luong dong cua phim
		List<RatingsModel> allRate = getAllRating(id);
		int soluongDong = allRate.size();
		
		System.out.println("all rate: " + allRate);
		int star = 0;
		int sum = 0;
		for(int i = 0; i < allRate.size(); i++) {
			sum += allRate.get(i).getRate();
		}
		try {
			star = sum / soluongDong;
		}catch(ArithmeticException e) {
			return 0;
		}
		
		return star;
	}

	@Override
	public List<RatingsModel> getAllRating(int film_id) {
		String sql = "select * from ratings where film_id = ?";
		List<RatingsModel> list = query(sql, new RatingsMapper(), film_id);
		return query(sql, new RatingsMapper(),film_id);
	} 
	
	public int getCountByFilmId(int film_id) {
		String sql = "select count(*) as soluong from ratings where id = ?";
		int count = 0;
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			setParameters(statement, film_id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				count = rs.getInt("soluong");
				return count;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return 0;
	}

	@Override
	public int addOneRow(int film_id,int rate) {
		String sql = "insert into ratings(film_id, rate) values (?,?)";
		return insert(sql,film_id,rate );
	}

}
