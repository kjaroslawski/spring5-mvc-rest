package pl.qamar.spring5mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.qamar.spring5mvcrest.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
