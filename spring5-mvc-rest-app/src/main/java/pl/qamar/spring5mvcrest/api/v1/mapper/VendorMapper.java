package pl.qamar.spring5mvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.qamar.spring5mvcrest.api.v1.model.VendorDTO;
import pl.qamar.spring5mvcrest.domain.Vendor;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(target = "vendorUrl", ignore = true)
    VendorDTO vendorToVendorDTO(Vendor category);

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
