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
				   "PAYMENT P join UTENTE U " +
				   "on P.USER_ID = U.USER_ID " +
				   "WHERE U.USERNAME = :username", nativeQuery = true)
	public List<Payment> findAllByUsername(@Param("username") String username);
	
	@Query(value = "select P.TIPO as tipo, sum(P.IMPORTO) as importoTotale, count(P.*) as numeroPagamenti " +
			   "from PAYMENT P join UTENTE U on P.USER_ID = U.USER_ID WHERE U.USERNAME = :username " +
			   "group BY TIPO", nativeQuery = true)
	public List<IPaymentReport> getPaymentsReportByUsername(@Param("username") String username);
	
}
