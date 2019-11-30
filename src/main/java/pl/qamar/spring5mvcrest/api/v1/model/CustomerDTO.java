package pl.qamar.spring5mvcrest.api.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String customURL;
}
