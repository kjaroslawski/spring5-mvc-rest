package pl.qamar.spring5mvcrest.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.qamar.spring5mvcrest.api.v1.model.VendorDTO;
import pl.qamar.spring5mvcrest.api.v1.model.VendorListDTO;
import pl.qamar.spring5mvcrest.controllers.v1.AbstractRestControllerTest;
import pl.qamar.spring5mvcrest.controllers.v1.VendorController;
import pl.qamar.spring5mvcrest.services.VendorService;

import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerTest extends AbstractRestControllerTest {

    private static final long ID = 1L;
    private static final String NAME = "Abc";
    private static final Integer ID_INT = 1;

    @MockBean
    VendorService vendorService;

    @Autowired
    private MockMvc mockMvc;

    private VendorDTO vendorDTO_1;
    private VendorDTO vendorDTO_2;

    @Before
    public void setUp() {
        vendorDTO_1 = new VendorDTO(ID, "Abc", VendorController.BASE_URL + "/1");
        vendorDTO_2 = new VendorDTO(2L, "Def", VendorController.BASE_URL + "/2");
    }

    @Test
    public void testListVendors() throws Exception {
        VendorListDTO vendors = new VendorListDTO(Arrays.asList(vendorDTO_1, vendorDTO_2));

        given(vendorService.getAllVendors()).willReturn(vendors);

        mockMvc.perform(get(VendorController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void testGetVendorById() throws Exception {
        given(vendorService.getVendorById(anyLong())).willReturn(vendorDTO_1);

        mockMvc.perform(get(VendorController.BASE_URL + "/" + ID).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", equalTo(ID_INT)));
    }

    @Test
    public void testCreateNewVendor() throws Exception {
        given(vendorService.createNewVendor(vendorDTO_1)).willReturn(vendorDTO_1);

        mockMvc.perform(post(VendorController.BASE_URL).contentType(MediaType.APPLICATION_JSON)
                                                       .content(asJsonString(vendorDTO_1)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())))
               .andExpect(jsonPath("$.vendorUrl", equalTo(vendorDTO_1.getVendorUrl())));
    }

    @Test
    public void testUpdateVendor() throws Exception {
        given(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO_1);

        mockMvc.perform(put(VendorController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON)
                                                             .content(asJsonString(vendorDTO_1)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())))
               .andExpect(jsonPath("$.vendorUrl", equalTo(vendorDTO_1.getVendorUrl())));
    }

    @Test
    public void testPatchVendor() throws Exception {
        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(vendorDTO_1);

        mockMvc.perform(patch(VendorController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON)
                                                               .content(asJsonString(vendorDTO_1)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())))
               .andExpect(jsonPath("$.vendorUrl", equalTo(vendorDTO_1.getVendorUrl())));
    }

    @Test
    public void testDeleteVendor() throws Exception {
        mockMvc.perform(delete(VendorController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
        verify(vendorService).deleteVendorById(anyLong());
        then(vendorService).should().deleteVendorById(anyLong());
    }
}
