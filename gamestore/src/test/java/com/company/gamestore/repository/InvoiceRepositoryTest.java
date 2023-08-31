package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InvoiceRepositoryTest {
    @Autowired
    InvoiceRepository invoiceRepository;

    @BeforeEach
    public void setUp() throws Exception {
        invoiceRepository.deleteAll();
    }

    // Test create
    @Test
    public void shouldCreateInvoice() {
        // Arrange
        Invoice i = new Invoice();
        i.setName("John Doe");
        i.setStreet("123 Main St");
        i.setCity("Los Angeles");
        i.setState("CA");
        i.setZipcode("90001");
        i.setItem_type("Game");
        i.setItem_id(123);
        i.setUnit_price(new BigDecimal("49.99"));
        i.setQuantity(2);
        i.setSubtotal(new BigDecimal("99.98"));
        i.setTax(new BigDecimal("5.99"));
        i.setProcessing_fee(new BigDecimal("1.49"));
        i.setTotal(new BigDecimal("107.46"));

        i = invoiceRepository.save(i);

        // Assert
        Optional<Invoice> foundInvoice = invoiceRepository.findById(i.getId());
        assertEquals(foundInvoice.get(), i);
    }

    // Test get by id
    @Test
    public void shouldGetInvoiceById() {
        // Arrange
        Invoice i = new Invoice();
        i.setName("John Doe");
        i.setStreet("123 Main St");
        i.setCity("Los Angeles");
        i.setState("CA");
        i.setZipcode("90001");
        i.setItem_type("Game");
        i.setItem_id(123);
        i.setUnit_price(new BigDecimal("49.99"));
        i.setQuantity(2);
        i.setSubtotal(new BigDecimal("99.98"));
        i.setTax(new BigDecimal("5.99"));
        i.setProcessing_fee(new BigDecimal("1.49"));
        i.setTotal(new BigDecimal("107.46"));

        i = invoiceRepository.save(i);

        Optional<Invoice> invoiceFound = invoiceRepository.findById(i.getId());
        assertEquals(invoiceFound.get(), i);
    }

    // Test get all
    @Test
    public void shouldGetAllInvoices() {
        // Arrange
        Invoice i = new Invoice();
        i.setName("John Doe");
        i.setStreet("123 Main St");
        i.setCity("Los Angeles");
        i.setState("CA");
        i.setZipcode("90001");
        i.setItem_type("Game");
        i.setItem_id(123);
        i.setUnit_price(new BigDecimal("49.99"));
        i.setQuantity(2);
        i.setSubtotal(new BigDecimal("99.98"));
        i.setTax(new BigDecimal("5.99"));
        i.setProcessing_fee(new BigDecimal("1.49"));
        i.setTotal(new BigDecimal("107.46"));

        i = invoiceRepository.save(i);

        // Assert
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertEquals(invoiceList.size(), 1);
    }

    // Test get by name
    @Test
    public void getInvoiceByCustomerName () {
        // Arrange
        Invoice i = new Invoice();
        i.setName("John Doe");
        i.setStreet("123 Main St");
        i.setCity("Los Angeles");
        i.setState("CA");
        i.setZipcode("90001");
        i.setItem_type("Game");
        i.setItem_id(123);
        i.setUnit_price(new BigDecimal("49.99"));
        i.setQuantity(2);
        i.setSubtotal(new BigDecimal("99.98"));
        i.setTax(new BigDecimal("5.99"));
        i.setProcessing_fee(new BigDecimal("1.49"));
        i.setTotal(new BigDecimal("107.46"));

        i = invoiceRepository.save(i);

        // Assert
        List<Invoice> invoiceList = invoiceRepository.findByName("John Doe");
        assertEquals(invoiceList.size(), 1);
    }

}
