package pl.piomin.services.customer;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piomin.services.customer.model.Customer;
import pl.piomin.services.customer.repository.CustomerRepository;
import reactor.core.publisher.Mono;

@Component
public class CustomerInit implements CommandLineRunner {

    @Autowired
    private CustomerRepository repository;

    @Override
    public void run(String... strings) throws Exception {
        List<Customer> customers = Arrays.asList(new Customer("Ivan", "Petrov", "34241235123"));
        System.out.println("Add customers");
        for (Customer customer : customers) {
            final Mono<Customer> newCustomer = repository.save(Mono.just(customer));
            newCustomer.log().block();
        }
    }
}
