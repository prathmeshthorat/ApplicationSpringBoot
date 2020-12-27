package com.cookpick.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cookpick.dto.EntityList;
import com.cookpick.dto.Product;
import com.cookpick.exception.NoSuchProductException;
import com.cookpick.service.CookPickService;

@RestController
@Validated //To trigger validation on URI parameters
public class CookPickController {
	
	@Autowired
	private CookPickService cpService;
	
	@GetMapping//Simple get Service
	public ResponseEntity<String> greet(){
		LocalDate date = LocalDate.now();
		DayOfWeek today= date.getDayOfWeek();
		String greeting = "Welcome to "+today.toString().toLowerCase()+" sale!!";
		return new ResponseEntity<String>(greeting, HttpStatus.OK);
	}
	
	@PostMapping(path = "/product", consumes = "application/json", produces = "application/xml")//simple post with @RequestBody
	public ResponseEntity<String> addProduct(@Valid @RequestBody Product product){
		String result = cpService.addProduct(product) + " Added Successfully!";
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/requestParam", produces = "application/xml")// Get with @RequestParam
	public ResponseEntity<Product> getByQueryParam(@RequestParam("productCode") String productCode) throws NoSuchProductException{
		return new ResponseEntity<Product>(cpService.getProduct(productCode), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{code}", produces = "text/html")
	public ResponseEntity<String> deleteCustomer(@PathVariable("code") String productCode) throws NoSuchProductException {
		String result = cpService.removeProduct(productCode);
		return new ResponseEntity<String>(result, HttpStatus.OK);		
	}
	
	@GetMapping(value = "/{query}/product", produces = "application/xml")
	public ResponseEntity<EntityList<Product>> getProductsMatrix(@MatrixVariable(pathVar = "query") List<String> productNames ) throws NoSuchProductException {
		List<Product> products= new ArrayList<>();
		for(String name: productNames) {
			Product product = cpService.getProduct(name);
			products.add(product);
			}
		
		return new ResponseEntity<EntityList<Product>>(new EntityList<>(products), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{productCode}", consumes = "application/json")
	public ResponseEntity<String> updateProduct(@PathVariable("productCode") 
	@Pattern(regexp = "[A-Z]{3}[0-9]+",message = "{product.productCode.incorrectFormat}") String productCode,
	@RequestBody Product product){
		String result = cpService.updateProduct(productCode, product);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
}
