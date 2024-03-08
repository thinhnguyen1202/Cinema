package mockproject.dao;

import java.util.List;

import mockproject.model.RatingsModel;

public interface IRatingDAO {
	
	int getStarOfFilmByFilmId(int id);
	List<RatingsModel> getAllRating(int film_id);
	int addOneRow(int film_id,int rate);
}
