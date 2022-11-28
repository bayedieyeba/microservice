package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repository.Customerrepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(Customerrepository customerrepository,
											   RepositoryRestConfiguration restConfiguration
											){
		return  args -> {
			restConfiguration.exposeIdsFor(Customer.class);
			customerrepository.saveAll(
					List.of(
							Customer.builder().name("Modou").email("modou@gmail.com").build(),
							Customer.builder().name("Demba").email("demba@gmail.com").build(),
							Customer.builder().name("Ndeye").email("ndeye@gmail.com").build()
					)
			);
			customerrepository.findAll().forEach(customer -> {
				System.out.println(customer);
			});
		};
	}
}
