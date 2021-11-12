package guru.springframework.msscbrewery.web.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.service.BeerService;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {
	
	private final BeerService beerService;
	
	
	public BeerController(BeerService beerService) {
		super();
		this.beerService = beerService;
	}


	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId){
		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<BeerDto> handleBeer(@RequestBody BeerDto beerDto) {
		
		BeerDto saveBeer = beerService.saveBeer(beerDto);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location" + "/api/v1/beer/", saveBeer.getId().toString());
		
		return new ResponseEntity<BeerDto>(headers , HttpStatus.CREATED);
	}
	
	@PutMapping("/{bearId}")
	public ResponseEntity<BeerDto> handleUpdate(@PathVariable("beerId") UUID beerID,@RequestBody BeerDto beerDto) {
		beerService.updateBeer(beerID,beerDto);
		return new ResponseEntity<BeerDto>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{beerId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		beerService.deleteBeerById(beerId);
	}

}
