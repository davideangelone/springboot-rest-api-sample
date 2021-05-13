package it.test.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import it.test.model.entity.Payment;
import it.test.model.request.PaymentRequest;
import it.test.model.response.PaymentResponse;

@Service
public class ApiService implements IApiService {
	
	private List<Payment> payments = new ArrayList<>();

	@Override
	public List<PaymentResponse> getPayments() {
		return payments.stream().map(PaymentResponse::new).collect(Collectors.toList());
	}

	@Override
	public void addPayment(PaymentRequest payment) {
		Payment p = new Payment();
		p.setId(1 + payments.size());
		p.setTimestamp(System.currentTimeMillis());
		p.setTipo(payment.getTipo());
		p.setImporto(payment.getImporto());
		p.generateHash();
		payments.add(p);
	}

}
