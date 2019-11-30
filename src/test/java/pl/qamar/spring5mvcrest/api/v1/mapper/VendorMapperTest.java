package pl.qamar.spring5mvcrest.api.v1.mapper;

import org.junit.Test;
import pl.qamar.spring5mvcrest.api.v1.model.VendorDTO;
import pl.qamar.spring5mvcrest.domain.Vendor;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {

    private static final long ID = 1L;
    private static final String NAME = "Abc";
    private VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTO() {

        //Given
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        //When
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        //Then
        assertEquals(Long.valueOf(ID), vendorDTO.getId());
        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    public void vendorDTOToVendor() {

        //Given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        //When
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        //Then
        assertEquals(vendorDTO.getName(), vendor.getName());
    }
}
