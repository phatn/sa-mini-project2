package edu.miu.accountservice.mapper;

import edu.miu.accountservice.dto.AccountDto;
import edu.miu.accountservice.entity.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    @Autowired
    private final ModelMapper mapper;

    public AccountDto toDto(Account account) {
        return mapper.map(account, AccountDto.class);
    }
}
