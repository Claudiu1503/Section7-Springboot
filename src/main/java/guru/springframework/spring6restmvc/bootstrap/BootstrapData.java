package guru.springframework.spring6restmvc.bootstrap;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.entities.Customer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor

public class BootstrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;


    @Override
    public void run(String... args) throws Exception {


        Beer beer1 = new Beer();
        beer1.setBeerName("Ursus Premium");
        beer1.setBeerStyle(BeerStyle.IPA);
        beer1.setUpc("123456789012");
        beer1.setQuantityOnHand(100);
        beer1.setPrice(new BigDecimal("7.99"));
        beer1.setCreatedDate(LocalDateTime.now());
        beer1.setUpdateDate(LocalDateTime.now());

        Beer beer2 = new Beer();
        beer2.setBeerName("Ciucas");
        beer2.setBeerStyle(BeerStyle.PALE_ALE);
        beer2.setUpc("987654321098");
        beer2.setQuantityOnHand(200);
        beer2.setPrice(new BigDecimal("5.99"));
        beer2.setCreatedDate(LocalDateTime.now());
        beer2.setUpdateDate(LocalDateTime.now());

        Beer beer3 = new Beer();
        beer3.setBeerName("Silva Dark");
        beer3.setBeerStyle(BeerStyle.PILSNER);
        beer3.setUpc("192837465012");
        beer3.setQuantityOnHand(50);
        beer3.setPrice(new BigDecimal("8.99"));
        beer3.setCreatedDate(LocalDateTime.now());
        beer3.setUpdateDate(LocalDateTime.now());

        beerRepository.save(beer1);
        beerRepository.save(beer2);
        beerRepository.save(beer3);

        // Populare cu date pentru entitatea Customer
        Customer customer1 = new Customer();
        customer1.setName("Ion Popescu");
        customer1.setCreatedDate(LocalDateTime.now());
        customer1.setUpdateDate(LocalDateTime.now());

        Customer customer2 = new Customer();
        customer2.setName("Maria Ionescu");
        customer2.setCreatedDate(LocalDateTime.now());
        customer2.setUpdateDate(LocalDateTime.now());

        Customer customer3 = new Customer();
        customer3.setName("George Enescu");
        customer3.setCreatedDate(LocalDateTime.now());
        customer3.setUpdateDate(LocalDateTime.now());

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);

        System.out.println("In Bootstrap");
        System.out.println("Beer Count: " + beerRepository.count());
        System.out.println("Customer Count: " + customerRepository.count());
    }
}
