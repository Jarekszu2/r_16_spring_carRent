package com.javagda25.securitytemplate.component;

import com.javagda25.securitytemplate.model.Account;
import com.javagda25.securitytemplate.model.AccountRole;
import com.javagda25.securitytemplate.repository.AccountRepository;
import com.javagda25.securitytemplate.repository.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private AccountRepository accountRepository;
    private AccountRoleRepository accountRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(AccountRepository accountRepository, AccountRoleRepository accountRoleRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.accountRoleRepository = accountRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        addDefaultRole("USER");
        addDefaultRole("ADMIN");
        addDefaultRole("GRUNT");
        addDefaultRole("MANAGER");
        addDefaultRole("CLIENT");

//        addDefaultUser("user", "user", "USER");
        addDefaultUser("admin", "admin", "admin name", "admin surname", "11", "22", "ADMIN", "GRUNT", "MANAGER", "CLIENT");
        addDefaultUser("manager", "a", "manager name", "manager surname", "33", "44", "MANAGER");
        addDefaultUser("grunt", "a", "grunt name", "grunt surname", "55", "66", "GRUNT");
        addDefaultUser("client", "a", "client name", "client surname","email", "address", "CLIENT");

    }

    private void addDefaultUser(String username, String password, String name, String surname, String email, String address, String... roles) {
        if (!accountRepository.existsByUsername(username)) {
            Account account = new Account();
            account.setPassword(passwordEncoder.encode(password));
            account.setUsername(username);
            account.setName(name);
            account.setSurname(surname);
            account.setEmail(email);
            account.setAddress(address);
//            account.setPosition(position);

            Set<AccountRole> userRoles = findRoles(roles);
            account.setAccountRoles(userRoles);

            accountRepository.save(account);
        }
    }

    private Set<AccountRole> findRoles(String[] roles) {
        Set<AccountRole> accountRoles = new HashSet<>();
        for (String role : roles) {
            accountRoleRepository.findByName(role).ifPresent(accountRoles::add);
        }
        return accountRoles;
    }

    private void addDefaultRole(String role) {
        if (!accountRoleRepository.existsByName(role)) {
            AccountRole newRole = new AccountRole();
            newRole.setName(role);

            accountRoleRepository.save(newRole);
        }
    }
}
