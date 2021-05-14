package it.test.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.test.exception.ResourceNotFoundException;
import it.test.model.entity.Payment;
import it.test.model.request.PaymentRequest;
import it.test.model.response.PaymentResponse;
import it.test.repository.PaymentRepository;

@Service
public class ApiService implements IApiService {
	
	@Autowired
	PaymentRepository repository;
	
	@Autowired
	private Mapper mapperDozer;

	@Override
	public List<PaymentResponse> getPayments() {
		return StreamSupport.stream(this.repository.findAll().spliterator(), false)
				.map(from -> mapperDozer.map(from, PaymentResponse.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public PaymentResponse getPayment(int id) {
		return mapperDozer.map(
				this.repository.findById(id).orElseThrow(ResourceNotFoundException::new), 
				PaymentResponse.class);
	}

	@Override
	public void addPayment(PaymentRequest payment) {
		this.repository.save(mapperDozer.map(payment, Payment.class));
	}
	
	@Override
	public void deletePayment(int id) {
		if (this.repository.existsById(id)) {
			this.repository.deleteById(id);
		} else {
			throw new ResourceNotFoundException();
		}
	}

}
