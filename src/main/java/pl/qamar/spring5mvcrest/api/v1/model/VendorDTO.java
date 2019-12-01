package pl.qamar.spring5mvcrest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

    private Long id;

    @ApiModelProperty(value = "Name of the Vendor", required = true)
    private String name;
    private String vendorUrl;
}
