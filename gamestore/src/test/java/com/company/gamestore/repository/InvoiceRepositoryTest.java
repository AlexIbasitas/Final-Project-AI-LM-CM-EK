package com.company.gamestore.repository;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InvoiceRepositoryTest {
    @Autowired
    InvoiceRepository invoiceRepository;
    GameRepository gameRepository;

    @Autowired
    ServiceLayer serviceLayer;

    @BeforeEach
    public void setUp() throws Exception {
        invoiceRepository.deleteAll();
    }

    @Test
    public void shouldCreateInvoice() {

        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setName("John Doe");
        ivm.setStreet("123 Main St");
        ivm.setCity("Los Angeles");
        ivm.setState("CA");
        ivm.setZip("90001");
        ivm.setItem_type("Game");
        ivm.setItem_id(123);
        ivm.setQuantity(1);

        ivm = serviceLayer.saveInvoice(ivm);

        Optional<Invoice> invoice1 = invoiceRepository.findById(ivm.getId());
        assertEquals(invoice1.get(), ivm);
    }
}
