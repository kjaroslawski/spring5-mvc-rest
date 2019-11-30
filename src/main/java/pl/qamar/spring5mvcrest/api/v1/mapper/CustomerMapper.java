package pl.qamar.spring5mvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.qamar.spring5mvcrest.api.v1.model.CustomerDTO;
import pl.qamar.spring5mvcrest.domain.Customer;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "customerURL", ignore = true)
    @Mapping(source = "id", target = "id")
    CustomerDTO customerToCustomerDTO(Customer category);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
