package com.company.gamestore.controller;

import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceController {
    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody @Valid InvoiceViewModel ivm) {
        return serviceLayer.saveInvoice(ivm);
    }

    @GetMapping("/invoices/{id}")
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
        return serviceLayer.findInvoice(id);
    }

    @GetMapping("/invoices")
    public List<InvoiceViewModel> getAllInvoice() {
        return serviceLayer.findAllInvoices();
    }

    @GetMapping(value = "/invoices/name/{name}")
    public List<InvoiceViewModel> getInvoiceByCustomerName(@PathVariable String name) {
        return serviceLayer.findInvoiceByName(name);
    }
}
