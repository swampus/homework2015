package io.fourfinanceit.resource.repository;

import io.fourfinanceit.model.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, Long> {

}
