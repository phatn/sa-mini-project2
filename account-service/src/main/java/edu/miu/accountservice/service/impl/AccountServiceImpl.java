package edu.miu.accountservice.service.impl;

import edu.miu.accountservice.dto.AccountDto;
import edu.miu.accountservice.entity.Account;
import edu.miu.accountservice.mapper.AccountMapper;
import edu.miu.accountservice.repository.AccountRepository;
import edu.miu.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public AccountDto findById(int id) {
        AccountDto account = accountRepository.findById(id)
                .map(accountMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Cannot find account with id: " + id));
        return account;
    }

    @Override
    public AccountDto findByEmail(String email) {
        Account account = accountRepository.findByEmail(email);
        AccountDto accountDto = accountMapper.toDto(account);
        return accountDto;
    }
}
