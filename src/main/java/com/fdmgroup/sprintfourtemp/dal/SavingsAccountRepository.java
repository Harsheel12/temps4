package com.fdmgroup.sprintfourtemp.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.sprintfourtemp.model.SavingsAccount;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

}
