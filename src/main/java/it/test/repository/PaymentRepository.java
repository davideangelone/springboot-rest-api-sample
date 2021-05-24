package it.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.test.model.entity.Payment;
import it.test.model.response.IPaymentReport;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
	
	@Query(value = "select PAYMENT.* from " +
				   "PAYMENT join USER " +
				   "on PAYMENT.USER_ID = USER.USER_ID " +
				   "WHERE USER.USERNAME = :username", nativeQuery = true)
	public List<Payment> findAllByUsername(@Param("username") String username);
	
	@Query(value = "select PAYMENT.TIPO as tipo, sum(PAYMENT.IMPORTO) as importoTotale, count(PAYMENT.*) as numeroPagamenti " +
			   "from PAYMENT join USER on PAYMENT.USER_ID = USER.USER_ID WHERE USER.USERNAME = :username " +
			   "group BY TIPO", nativeQuery = true)
	public List<IPaymentReport> getPaymentsReportByUsername(@Param("username") String username);
	
}
