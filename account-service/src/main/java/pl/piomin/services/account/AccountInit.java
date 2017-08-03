package pl.piomin.services.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.piomin.services.account.repository.AccountRepository;

import pl.piomin.services.common.Account;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class AccountInit implements CommandLineRunner {

    @Autowired
    private AccountRepository repository;

    @Override
    public void run(String... strings) throws Exception {
        List<Account> customers = Arrays.asList(new Account("59832ac77ffea64bf42b0c42", "598326607ffea632c4c7dc7b", 500),
                new Account("59832ac77ffea64bf42b0c42", "598326607ffea632c4c7dc7c", 5005));
        System.out.println("Add accc");
        for (Account customer : customers) {
            final Mono<Account> newAccount = repository.save(Mono.just(customer));
            newAccount.log().block();
        }
    }
}
