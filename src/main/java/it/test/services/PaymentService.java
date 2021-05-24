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
import it.test.model.response.PaymentReport;
import it.test.model.response.PaymentResponse;
import it.test.repository.PaymentRepository;
import it.test.repository.UserRepository;

@Service
public class PaymentService implements IPaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Mapper mapperDozer;

	@Override
	public List<PaymentResponse> getPayments() {
		return StreamSupport.stream(this.paymentRepository.findAll().spliterator(), false)
				.map(from -> mapperDozer.map(from, PaymentResponse.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<PaymentResponse> getPaymentsByUsername(String username) {
		return StreamSupport.stream(this.paymentRepository.findAllByUsername(username).spliterator(), false)
				.map(from -> mapperDozer.map(from, PaymentResponse.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<PaymentReport> getPaymentsReportByUsername(String username) {
		return StreamSupport.stream(this.paymentRepository.getPaymentsReportByUsername(username).spliterator(), false)
				.map(from -> mapperDozer.map(from, PaymentReport.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public PaymentResponse getPayment(int id) {
		return mapperDozer.map(
				this.paymentRepository.findById(id).orElseThrow(ResourceNotFoundException::new), 
				PaymentResponse.class);
	}

	@Override
	public void addPayment(PaymentRequest payment) {
		Payment p = mapperDozer.map(payment, Payment.class);
		p.setUser(this.userRepository.findByUsername(payment.getUsername()));
		this.paymentRepository.save(p);
	}
	
	@Override
	public void deletePayment(int id) {
		if (this.paymentRepository.existsById(id)) {
			this.paymentRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException();
		}
	}

}
