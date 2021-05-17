package it.test.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.test.model.error.Errore;
import it.test.model.request.PaymentRequest;
import it.test.model.response.PaymentResponse;
import it.test.services.IApiService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	IApiService service;
	
    /**
     * Rstituisce la lista di tutti i pagamenti
     */
    @GetMapping("/get")
    public ResponseEntity<List<PaymentResponse>> getPayments() {
        return ResponseEntity.ok(this.service.getPayments());
    }
    
    /**
     * Restituisce un pagamento
     * 
     * @param id del pagamento
     * @return pagamento
     */
    @GetMapping("/get/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Pagamento non trovato")
        })
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.service.getPayment(id));
    }
    
    /**
     * Inserisce un pagamento
     * 
     * @param payment
     * @param errors
     * @return
     */
    @PostMapping("/add")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Input non valido", 
            	content = @Content(schema = @Schema(implementation = Errore.class)))
        })
    public ResponseEntity<?> addPayment(@Valid @RequestBody PaymentRequest payment, Errors errors) {
    	if (errors.hasErrors()) {
    		List<Errore> errs = new ArrayList<>();
    		errors.getFieldErrors().stream().forEach(fe -> errs.add(new Errore(fe.getField(), fe.getDefaultMessage())));
    		return ResponseEntity.badRequest().body(errs);
    	}
    	
    	this.service.addPayment(payment);
    	return ResponseEntity.ok().build();
    }
    
    /**
     * Cancella un pagamento
     * 
     * @param id del pagamento
     * @return pagamento
     */
    @DeleteMapping("/delete/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Pagamento non trovato")
        })
    public ResponseEntity<?> deletePayment(@PathVariable("id") int id) {
    	this.service.deletePayment(id);
    	return ResponseEntity.ok().build();
    }
}
