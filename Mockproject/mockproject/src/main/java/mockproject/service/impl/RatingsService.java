package mockproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mockproject.dao.IRatingDAO;
import mockproject.model.RatingsModel;
import mockproject.service.IRatingsService;
@Service
public class RatingsService implements IRatingsService{
	
	@Autowired
	IRatingDAO ratingDAO;
	
	@Override
	public int getStarOfFilmByFilmId(int id) {
		return ratingDAO.getStarOfFilmByFilmId(id);
	}

	@Override
	public List<RatingsModel> listAllRating(int film_id) {
		
		return ratingDAO.getAllRating(film_id);
	}

	@Override
	public int addOneRow(int film_id, int rate) {
		
		return ratingDAO.addOneRow(film_id, rate);
	}



}
