package com.company.gamestore.service;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.Tax;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {
    private InvoiceRepository invoiceRepository;
    private GameRepository gameRepository;
    private ConsoleRepository consoleRepository;
    private TShirtRepository tShirtRepository;
    private FeeRepository feeRepository;
    private TaxRepository taxRepository;

    @Autowired
    public ServiceLayer(InvoiceRepository invoiceRepository, GameRepository gameRepository,
                        ConsoleRepository consoleRepository, TShirtRepository tShirtRepository,
                        FeeRepository feeRepository, TaxRepository taxRepository) {
        this.invoiceRepository = invoiceRepository;
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
        this.tShirtRepository = tShirtRepository;
        this.feeRepository = feeRepository;
        this.taxRepository = taxRepository;
    }


    @Transactional
    public Invoice saveInvoice (InvoiceViewModel viewModel) {
        //Persist Invoice
        Invoice i = new Invoice();
        i.setName(viewModel.getName());
        i.setStreet(viewModel.getStreet());
        i.setCity(viewModel.getCity());

        // validate state
        if (taxRepository.findByState(viewModel.getState()) != null) {
            i.setState(viewModel.getState());
        }
        else
        {
            throw new IllegalArgumentException("Must enter a valid state.");
        }

        i.setZipcode(viewModel.getZip());

        // validate Item_type
        if(viewModel.getItem_type() != "Games"
                || viewModel.getItem_type() != "Consoles"
                || viewModel.getItem_type() != "T-Shirts") {
            i.setItem_type(viewModel.getItem_type());
        }
        else {
            throw new IllegalArgumentException("Must enter an item type of 'Games', 'Consoles', or 'T-Shirts'");
        }

        // validate Item_id
        
        i.setItem_id(viewModel.getItem_id());
        i.setQuantity(viewModel.getQuantity());
        //i.setUnit_price();
        i = invoiceRepository.save(i);
        viewModel.setId(i.getId());

        return  i;
    }

    public float getSalesTax() {
        return 0;
    }

    public float getProcessingFee() {
        return 0;
    }

//    public InvoiceViewModel findInvoiceById (int id) {
//        Optional<Invoice> invoice = invoiceRepository.findById(id);
//        return invoice.isPresent() ? buildInvoiceViewModel(invoice.get()) : null;
//    }
//
//    public List<InvoiceViewModel> findAllInvoices () {
//        List<Invoice> invoiceList = invoiceRepository.findAll();
//        List<InvoiceViewModel> ivmList = new ArrayList<>();
//
//        // Building our list of InvoiceViewModel
//        invoiceList.stream().forEach(i -> ivmList.add(buildInvoiceViewModel(i)));
//
//        return ivmList;
//    }
//
//    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
//        // Assemble the InvoiceViewModel
//        InvoiceViewModel ivm = new InvoiceViewModel();
//        ivm.setId(invoice.getId());
//        ivm.setName(invoice.getName());
//        ivm.setStreet(invoice.getStreet());
//        ivm.setCity(invoice.getCity());
//        ivm.setState(invoice.getState());
//        ivm.setZip(invoice.getZipcode());
//        ivm.setItem_type(invoice.getItem_type());
//        ivm.setItem_id(invoice.getItem_id());
//        ivm.setQuantity(invoice.getQuantity());
//
//        return ivm;
//    }
}
