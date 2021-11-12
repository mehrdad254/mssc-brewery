package guru.springframework.msscbrewery.web.service;

import java.util.UUID;

import guru.springframework.msscbrewery.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID beerId);

	BeerDto saveBeer(BeerDto beerDto);

	void updateBeer(UUID beerID, BeerDto beerDto);

	void deleteBeerById(UUID beerId);


}
