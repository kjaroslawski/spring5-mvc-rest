package pl.qamar.spring5mvcrest.api.v1.mapper;

import org.junit.Test;
import pl.qamar.spring5mvcrest.api.v1.model.CustomerDTO;
import pl.qamar.spring5mvcrest.domain.Customer;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    private static final long ID = 1L;
    private static final String FIRST_NAME = "Paul";
    private static final String LAST_NAME = "Newman";
    private CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {

        //Given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        //When
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //Then
        assertEquals(Long.valueOf(ID), customerDTO.getId());
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }
}
