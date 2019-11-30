package pl.qamar.spring5mvcrest.services;

import org.springframework.stereotype.Service;
import pl.qamar.spring5mvcrest.api.v1.mapper.CustomerMapper;
import pl.qamar.spring5mvcrest.api.v1.model.CustomerDTO;
import pl.qamar.spring5mvcrest.repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(customer -> {
            CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
            customerDTO.setCustomURL("/api/v1/customers/" + customer.getId());
            return customerDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id).map(customerMapper::customerToCustomerDTO).orElseThrow(
                RuntimeException::new);
    }
}
