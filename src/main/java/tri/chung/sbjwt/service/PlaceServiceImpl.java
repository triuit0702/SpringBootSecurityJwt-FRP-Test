package tri.chung.sbjwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tri.chung.sbjwt.entity.Place;
import tri.chung.sbjwt.repository.PlaceRepository;

@Service
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	PlaceRepository placeRepository;
	
	public List<Place> getAll() {
		List<Place> places = placeRepository.findAll();
		return places;
	}
}
