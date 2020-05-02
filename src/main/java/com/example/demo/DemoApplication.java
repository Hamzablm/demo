package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
class Customer {
    @Id
    private long id;
    private String name;
    private String address;
}

@Repository
interface CustomerRepo extends JpaRepository<Customer, Long> { }

@RestController
class CustomerResource {
	@Autowired
	CustomerRepo customerRepo;

	@GetMapping("/api/customers")
	public ResponseEntity<List<Customer>> showAllCustomer() {
		return ResponseEntity.ok(customerRepo.findAll());
	}

	@GetMapping("/api/customers/{id}")
    public Customer customerById(@PathVariable long id) {
        return customerRepo.findById(id).get();
    }

    @DeleteMapping("/api/customers/{id}")
    public void deleteCustomer(@PathVariable long id) {
	    customerRepo.deleteById(id);
    }
}













