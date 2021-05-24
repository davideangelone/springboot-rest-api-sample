package it.test.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import it.test.model.response.PaymentReport;
import it.test.model.response.PaymentResponse;
import it.test.services.IPaymentService;
import it.test.services.IUserService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	IPaymentService paymentService;
	
	@Autowired
	IUserService userService;
	
    /**
     * Rstituisce la lista di tutti i pagamenti
     */
    @GetMapping("/get")
    public ResponseEntity<List<PaymentResponse>> getPayments() {
        return ResponseEntity.ok(this.paymentService.getPayments());
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
        return ResponseEntity.ok(this.paymentService.getPayment(id));
    }
    
    /**
     * Restituisce tutti i pagamenti di un utente
     * 
     * @param username
     * @return lista dei pagamenti
     */
    @GetMapping("/user/all/{username}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
        })
    public ResponseEntity<List<PaymentResponse>> getUserPayments(@PathVariable("username") String username) {
        return ResponseEntity.ok(this.paymentService.getPaymentsByUsername(username));
    }
    
    /**
     * Restituisce un report dei pagamenti di un utente
     * 
     * @param username
     * @return report dei pagamenti
     */
    @GetMapping("/user/report/{username}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
        })
    public ResponseEntity<List<PaymentReport>> getPaymentsReportByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(this.paymentService.getPaymentsReportByUsername(username));
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
            @ApiResponse(responseCode = "201", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Input non valido", 
            	content = @Content(schema = @Schema(implementation = Errore.class)))
        })
    public ResponseEntity<?> addPayment(@Valid @RequestBody PaymentRequest payment, Errors errors) {
    	if (errors.hasErrors()) {
    		List<Errore> errs = new ArrayList<>();
    		errors.getFieldErrors().stream().forEach(fe -> errs.add(new Errore(fe.getField(), fe.getDefaultMessage())));
    		return ResponseEntity.badRequest().body(errs);
    	}
    	if (!this.userService.existsByUsername(payment.getUsername())) {
    		return ResponseEntity.badRequest().body(new Errore("username", "Username non trovato"));
		}
    	
    	this.paymentService.addPayment(payment);
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    /**
     * Cancella un pagamento
     * 
     * @param id del pagamento
     */
    @DeleteMapping("/delete/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Pagamento non trovato")
        })
    public ResponseEntity<?> deletePayment(@PathVariable("id") int id) {
    	this.paymentService.deletePayment(id);
    	return ResponseEntity.ok().build();
    }
}
