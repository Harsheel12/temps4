package com.fdmgroup.sprintfourtemp.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.sprintfourtemp.model.CheckingAccount;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {

}
