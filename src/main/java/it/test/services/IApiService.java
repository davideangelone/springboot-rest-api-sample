package it.test.services;

import java.util.List;

import it.test.model.request.PaymentRequest;
import it.test.model.response.PaymentResponse;

public interface IApiService {

	public List<PaymentResponse> getPayments();
	
	public PaymentResponse getPayment(int id);
	
	public void addPayment(PaymentRequest payment);
	
	void deletePayment(int it);
	
}
