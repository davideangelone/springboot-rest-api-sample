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
import it.test.model.request.UserRequest;
import it.test.model.response.UserResponse;
import it.test.services.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
    /**
     * Rstituisce la lista di tutti gli utenti
     */
    @GetMapping("/get")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }
    
    /**
     * Restituisce un utente
     * 
     * @param id utente
     * @return utente
     */
    @GetMapping("/get/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
        })
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.userService.getUser(id));
    }
    
    /**
     * Inserisce un utente
     * 
     * @param utente
     * @param errors
     * @return
     */
    @PostMapping("/add")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK"),
            @ApiResponse(responseCode = "409", description = "Utente esistente"),
            @ApiResponse(responseCode = "400", description = "Input non valido", 
            	content = @Content(schema = @Schema(implementation = Errore.class)))
        })
    public ResponseEntity<?> addUser(@Valid @RequestBody UserRequest user, Errors errors) {
    	if (errors.hasErrors()) {
    		List<Errore> errs = new ArrayList<>();
    		errors.getFieldErrors().stream().forEach(fe -> errs.add(new Errore(fe.getField(), fe.getDefaultMessage())));
    		return ResponseEntity.badRequest().body(errs);
    	}
    	if (this.userService.existsByUsername(user.getUsername())) {
    		return ResponseEntity.status(HttpStatus.CONFLICT).build();
    	}
    	
    	this.userService.addUser(user);
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    /**
     * Cancella un utente
     * 
     * @param id utente
     */
    @DeleteMapping("/delete/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
        })
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
    	this.userService.deleteUser(id);
    	return ResponseEntity.ok().build();
    }
}
