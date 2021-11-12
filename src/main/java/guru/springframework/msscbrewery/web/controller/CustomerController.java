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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import guru.springframework.msscbrewery.web.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
	
	private CustomerService customerService;
	
	
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}



	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") UUID customerId){
		return new ResponseEntity<CustomerDto>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> handlePost(@RequestBody CustomerDto customerDto) {
		CustomerDto saveDto= customerService.saveNewCustomer(customerDto);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location","/api/v1/customer/" + saveDto.getId().toString() );
		
		return new ResponseEntity<CustomerDto>(headers,HttpStatus.CREATED);
	}
	
	@PutMapping("/{customerId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void handleUpdate(@PathVariable("customerId") UUID customerId ,@RequestBody CustomerDto customerDto) {
		customerService.updateCustomer(customerId,customerDto);
	}
	
	@DeleteMapping("/{custumerId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("customerId") UUID custumerId) {
		customerService.deleteById(custumerId);
	}
	
	

}
