package mockproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mockproject.model.CategoryModel;

public class CategoriesMapper implements RowMapper<CategoryModel>{

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		
		try {
			CategoryModel categoryModel = new CategoryModel();
			categoryModel.setId(resultSet.getInt("id"));
			categoryModel.setName(resultSet.getString("name"));
			
			return categoryModel;
			
		}catch(SQLException e) {
			return null;
		}
	}

	
	
}
