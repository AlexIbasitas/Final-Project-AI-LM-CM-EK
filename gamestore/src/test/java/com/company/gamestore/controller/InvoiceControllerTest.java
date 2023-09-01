package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();
    private List<Invoice> invoiceList = new ArrayList<>();

    InvoiceViewModel ivm = new InvoiceViewModel();
    Invoice i = new Invoice();


    @Test
    public void postInvoiceShouldReturnStatus201() throws Exception {
        // Arrange
        ivm.setName("John Doe");
        ivm.setStreet("123 Main St");
        ivm.setCity("Los Angeles");
        ivm.setState("CA");
        ivm.setZip("90001");
        ivm.setItem_type("Game");
        ivm.setItem_id(123);
        ivm.setQuantity(1);

        serviceLayer.saveInvoice(ivm);

        String inputJSON = mapper.writeValueAsString(ivm);


        // Act
        mockMvc.perform(
                        post("/invoices")
                                .content(inputJSON)
                                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void getByIdShouldReturnStatus200 () throws Exception {
        // Arrange
        ivm.setId(1);
        ivm.setName("John Doe");
        ivm.setStreet("123 Main St");
        ivm.setCity("Los Angeles");
        ivm.setState("CA");
        ivm.setZip("90001");
        ivm.setItem_type("Game");
        ivm.setItem_id(123);

        serviceLayer.saveInvoice(ivm);

        String inputJSON = mapper.writeValueAsString(ivm);

        // Act
        mockMvc.perform(
                get("/invoices/1")
                        .content(inputJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void getAllShouldReturnStatus200 () throws Exception {
        // Arrange
        ivm.setName("John Doe");
        ivm.setStreet("123 Main St");
        ivm.setCity("Los Angeles");
        ivm.setState("CA");
        ivm.setZip("90001");
        ivm.setItem_type("Game");
        ivm.setItem_id(123);

        serviceLayer.saveInvoice(ivm);

        String inputJSON = mapper.writeValueAsString(ivm);

        // Act
        mockMvc.perform(
                        get("/invoices")
                                .content(inputJSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getByNameShouldReturnStatus200 () throws Exception {
        // Arrange
        ivm.setName("John Doe");
        ivm.setStreet("123 Main St");
        ivm.setCity("Los Angeles");
        ivm.setState("CA");
        ivm.setZip("90001");
        ivm.setItem_type("Game");
        ivm.setItem_id(123);
        ivm.setQuantity(1);

        serviceLayer.saveInvoice(ivm);

        String inputJSON = mapper.writeValueAsString(ivm);

        // Act
        mockMvc.perform(
                        get("/invoices/name/John Doe")
                                .content(inputJSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void emptyStateShouldReturnStatus422() throws Exception {
        // Arrange
        ivm.setName("John Doe");
        ivm.setStreet("123 Main St");
        ivm.setCity("Los Angeles");
        ivm.setState("");
        ivm.setZip("90001");
        ivm.setItem_type("Game");
        ivm.setItem_id(123456);
        ivm.setQuantity(1);

        String inputJSON = mapper.writeValueAsString(ivm);

        // Act
        mockMvc.perform(
                        post("/invoices")
                                .content(inputJSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
