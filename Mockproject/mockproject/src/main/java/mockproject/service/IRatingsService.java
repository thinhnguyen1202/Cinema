package mockproject.service;

import java.util.List;

import mockproject.model.RatingsModel;


public interface IRatingsService {
	int getStarOfFilmByFilmId(int id);
	List<RatingsModel> listAllRating(int film_id);
	int addOneRow(int film_id,int rate);
}
