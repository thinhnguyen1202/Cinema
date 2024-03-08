package mockproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mockproject.model.CategoryFilmModel;

public class CategoryFilmMapper  implements RowMapper<CategoryFilmModel>{

	@Override
	public CategoryFilmModel mapRow(ResultSet resultSet) {
		// TODO Auto-generated method stub
		try {
			CategoryFilmModel categoryFilmModel = new CategoryFilmModel();
			categoryFilmModel.setCategoryid(resultSet.getInt("id"));
			categoryFilmModel.setFilmid(resultSet.getInt("film_id"));
			categoryFilmModel.setCategoryid(resultSet.getInt("category_id"));
			
			return categoryFilmModel;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
