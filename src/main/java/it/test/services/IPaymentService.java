package it.test.services;

import java.util.List;

import it.test.model.request.PaymentRequest;
import it.test.model.response.PaymentReport;
import it.test.model.response.PaymentResponse;

public interface IPaymentService {

	public List<PaymentResponse> getPayments();
	
	public List<PaymentResponse> getPaymentsByUsername(String username);

	public List<PaymentReport> getPaymentsReportByUsername(String username);
	
	public PaymentResponse getPayment(int id);
	
	public void addPayment(PaymentRequest payment);
	
	void deletePayment(int it);

}
