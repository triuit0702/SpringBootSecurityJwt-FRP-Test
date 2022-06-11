package tri.chung.sbjwt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tri.chung.sbjwt.entity.Place;
import tri.chung.sbjwt.service.PlaceService;

@RestController
@RequestMapping("/places/")
public class PlaceController {

	@Autowired
	private PlaceService placeService;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUser() {
		List<Place> places = new ArrayList<>();
		try {
			places = placeService.getAll();
		} catch (Exception e) {
			System.out.println("error");
			return new ResponseEntity<List<Place>>(places, null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<Place>>(places, null, HttpStatus.OK);
	}
}
