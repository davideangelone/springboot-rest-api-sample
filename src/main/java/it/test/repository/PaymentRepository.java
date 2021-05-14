package it.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.test.model.entity.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
}
