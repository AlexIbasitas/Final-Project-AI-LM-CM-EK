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
    public InvoiceViewModel saveInvoice (InvoiceViewModel viewModel) {

        //Persist Invoice
        Invoice i = new Invoice();
        i.setName(viewModel.getName());
        i.setStreet(viewModel.getStreet());
        i.setCity(viewModel.getCity());

        // validate state
        if (taxRepository.findByState(viewModel.getState()) == null) {
            throw new IllegalArgumentException("Must enter a valid state.");
        }

        i.setState(viewModel.getState());

        i.setZipcode(viewModel.getZip());

        // validate item type
        // validate item id
        // validate quantity
        // will set each if they are validated
        validationHelper(viewModel, i);


        i.setUnit_price(handleUnitPrice(viewModel.getItem_id()));
        viewModel.setUnit_price(i.getUnit_price());

        i.setProcessing_fee(handleProcessingFee(viewModel.getItem_type()));
        viewModel.setProcessing_fee(i.getProcessing_fee());

        i.setSubtotal(calculateSubtotal(viewModel.getQuantity(), i.getUnit_price()));
        viewModel.setSubtotal(i.getSubtotal());

        i.setTax(handleTax(viewModel.getState(), i.getSubtotal()));
        viewModel.setTax(i.getTax());

        i.setTotal(calculateTotal(viewModel.getSubtotal(),
                viewModel.getTax(),
                viewModel.getProcessing_fee()));
        viewModel.setTotal(i.getTotal());

        i = invoiceRepository.save(i);

        viewModel.setId(i.getId());

        return viewModel;
    }

    public void validationHelper (InvoiceViewModel ivm, Invoice i) {
        if(ivm.getItem_type() != "Game"
                && ivm.getItem_type() != "Console"
                && ivm.getItem_type() != "TShirt") {

            throw new IllegalArgumentException("Must enter an item type of 'Game', 'Console', or 'TShirt'");
        }

        // setting the item type after successful validation
        i.setItem_type(ivm.getItem_type());

        String type = ivm.getItem_type();
        int id = ivm.getItem_id();

        switch (type) {
            case "Game":
                if(!gameRepository.findById(id).isPresent()) {

                    throw new IllegalArgumentException("Item id does not exist.");
                }
                i.setItem_id(id);
                if (gameRepository.findById(id).get().getQuantity() >= ivm.getQuantity())
                    i.setQuantity(ivm.getQuantity());
                else {
                    throw new IllegalArgumentException("Quantity cannot exceed " +
                            gameRepository.findById(id).get().getQuantity() + ".");
                }

                // setting the unit price
                i.setUnit_price(gameRepository.findById(id).get().getPrice());
                break;

            case "Console":
                if(!consoleRepository.findById(id).isPresent()) {
                    throw new IllegalArgumentException("Item id does not exist.");
                }
                i.setItem_id(id);
                if (consoleRepository.findById(id).get().getQuantity() >= ivm.getQuantity())
                    i.setQuantity(ivm.getQuantity());
                else {
                    throw new IllegalArgumentException("Quantity cannot exceed " +
                            consoleRepository.findById(id).get().getQuantity() + ".");
                }

                //setting the unit price
                i.setUnit_price(consoleRepository.findById(id).get().getPrice());
                break;

            case "TShirt":
                if(!tShirtRepository .findById(id).isPresent()) {
                    throw new IllegalArgumentException("Item id does not exist.");
                }
                i.setItem_id(id);
                if (tShirtRepository.findById(id).get().getQuantity() >= ivm.getQuantity())
                    i.setQuantity(ivm.getQuantity());
                else {
                    throw new IllegalArgumentException("Quantity cannot exceed " +
                            tShirtRepository.findAll().size() + ".");
                }

                // setting the unit price
                i.setUnit_price(tShirtRepository.findById(id).get().getPrice());
                break;
        }
    }

    public BigDecimal calculateSubtotal (int count, BigDecimal unitPrice) {
        BigDecimal quantity = new BigDecimal(count);
        BigDecimal subTotal = quantity.multiply(unitPrice);

        return subTotal;
    }

    public BigDecimal handleTax(String initials, BigDecimal subTotal) {
        BigDecimal stateTax = taxRepository.findByState(initials).getRate();
        BigDecimal tax = stateTax.multiply(subTotal);
        return tax;
    }

    public BigDecimal handleUnitPrice(int id) {
        BigDecimal unitPrice = gameRepository.findById(id).get().getPrice();

        return unitPrice;
    }

    public BigDecimal handleProcessingFee(String type) {
        BigDecimal fee = feeRepository.findByProductType(type + "s").getFee();

        return fee;
    }

    public BigDecimal calculateTotal(BigDecimal subTotal, BigDecimal tax, BigDecimal fee) {
        BigDecimal total = tax.add(subTotal).add(fee);

        return total;
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
