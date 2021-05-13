package it.test.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.test.model.error.Errore;
import it.test.model.request.PaymentRequest;
import it.test.model.response.PaymentResponse;
import it.test.services.IApiService;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	IApiService service;
	
    /**
     * @return lista pagamenti
     */
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PaymentResponse>> getPayments() {
        return ResponseEntity.ok(service.getPayments());
    }
    
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Input non valido")
        })
    public ResponseEntity<?> addPayment(@Valid @RequestBody PaymentRequest payment, Errors errors) {
    	if (errors.hasErrors()) {
    		List<Errore> errs = new ArrayList<>();
    		errors.getFieldErrors().stream().forEach(fe -> errs.add(new Errore(fe.getField(), fe.getDefaultMessage())));
    		return ResponseEntity.badRequest().body(errs);
    	}
    	
    	service.addPayment(payment);
    	return ResponseEntity.ok().build();
    }
}
