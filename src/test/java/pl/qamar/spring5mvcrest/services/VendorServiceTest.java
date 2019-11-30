package pl.qamar.spring5mvcrest.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.qamar.spring5mvcrest.api.v1.mapper.VendorMapper;
import pl.qamar.spring5mvcrest.api.v1.model.VendorDTO;
import pl.qamar.spring5mvcrest.api.v1.model.VendorListDTO;
import pl.qamar.spring5mvcrest.controllers.v1.VendorController;
import pl.qamar.spring5mvcrest.domain.Vendor;
import pl.qamar.spring5mvcrest.repositories.VendorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VendorServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "Abc";
    private VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void getAllVendors() {

        //Given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        //When
        VendorListDTO vendorDTOS = vendorService.getAllVendors();

        //Then
        assertEquals(3, vendorDTOS.getVendors().size());
        then(vendorRepository).should(times(1)).findAll();
        assertThat(vendorDTOS.getVendors().size(), is(equalTo(3)));
    }

    @Test
    public void getVendorById() {

        //Given
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        //Mockito BDD syntax
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

        //When
        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        //Then
        assertEquals(ID, vendorDTO.getId());
        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    public void createNewVendor() {

        //Given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //When
        VendorDTO savedDTO = vendorService.createNewVendor(vendorDTO);

        //Then
        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedDTO.getVendorUrl(), containsString("1"));

        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(VendorController.BASE_URL + "/1", savedDTO.getVendorUrl());
    }

    @Test
    public void updateVendor() {

        //Given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(ID);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //When
        VendorDTO savedDTO = vendorService.saveVendorByDTO(ID, vendorDTO);

        //Then
        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(VendorController.BASE_URL + "/" + ID, savedDTO.getVendorUrl());
    }

    @Test
    public void deleteVendorById() {

        Long id = 1L;
        vendorRepository.deleteById(id);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}
