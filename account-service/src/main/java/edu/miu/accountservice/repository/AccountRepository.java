package edu.miu.accountservice.repository;

import edu.miu.accountservice.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
