package pl.qamar.spring5mvcrest.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.qamar.spring5mvcrest.api.v1.mapper.CustomerMapper;
import pl.qamar.spring5mvcrest.api.v1.model.CustomerDTO;
import pl.qamar.spring5mvcrest.domain.Customer;
import pl.qamar.spring5mvcrest.repositories.CustomerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    private static final Long ID = 2L;
    private static final String FIRST_NAME = "Paul";
    private static final String LAST_NAME = "Newman";
    private CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomers() {

        //Given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        //When
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //Then
        assertEquals(3, customerDTOS.size());
    }

    @Test
    public void getCustomerById() {

        //Given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        //When
        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        //Then
        assertEquals(ID, customerDTO.getId());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }

    @Test
    public void createNewCustomer() {

        //Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //When
        CustomerDTO savedDTO = customerService.createNewCustomer(customerDTO);

        //Then
        assertEquals(customerDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals("api/v1/customers/1", savedDTO.getCustomerURL());
    }

    @Test
    public void updateCustomer() {

        //Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(ID);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //When
        CustomerDTO savedDTO = customerService.saveCustomerByDTO(ID, customerDTO);

        //Then
        assertEquals(customerDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals("api/v1/customers/" + ID, savedDTO.getCustomerURL());
    }
}
