package edu.miu.accountservice.service;

import edu.miu.accountservice.dto.AccountDto;

public interface AccountService {
    AccountDto findById(int id);
}

