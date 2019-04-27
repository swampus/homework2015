package io.fourfinanceit.resource.repository;

import io.fourfinanceit.model.LoanApplication;
import org.springframework.data.repository.CrudRepository;

public interface LoanApplicationRepository extends CrudRepository<LoanApplication, Long> {

}
