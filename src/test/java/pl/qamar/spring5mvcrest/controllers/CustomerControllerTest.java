package pl.qamar.spring5mvcrest.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.qamar.spring5mvcrest.api.v1.model.CustomerDTO;
import pl.qamar.spring5mvcrest.controllers.v1.CustomerController;
import pl.qamar.spring5mvcrest.services.CustomerService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    private static final long ID = 1L;
    private static final int ID_INT = 1;
    private static final String FIRST_NAME = "Paul";
    private static final String LAST_NAME = "Newman";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCustomers() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(ID);
        customer1.setFirstName(FIRST_NAME);
        customer1.setLastName(LAST_NAME);

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setId(2L);
        customer2.setFirstName("Jack");
        customer2.setLastName("Sparrow");

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers").contentType(MediaType.APPLICATION_JSON)).andExpect(
                status().isOk()).andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        mockMvc.perform(get("/api/v1/customers/" + ID).contentType(MediaType.APPLICATION_JSON)).andExpect(
                status().isOk()).andExpect(jsonPath("$.id", equalTo(ID_INT)));
    }
}
