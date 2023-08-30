package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
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
    public InvoiceViewModel createInvoice(@Valid @RequestBody InvoiceViewModel ivm) {
        return serviceLayer.saveInvoice(ivm);
    }

//    @GetMapping
//    public List<InvoiceViewModel> getAllInvoice() {
//        return serviceLayer.findAllInvoices();
//    }
//
//    @GetMapping(value = "/{id}")
//    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
//        return serviceLayer.findInvoiceById(id);
//    }
}
