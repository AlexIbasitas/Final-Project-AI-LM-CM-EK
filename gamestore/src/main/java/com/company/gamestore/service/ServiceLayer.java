package com.company.gamestore.service;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.Tax;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {
    private InvoiceRepository invoiceRepository;
    private GameRepository gameRepository;
    private ConsoleRepository consoleRepository;
    private TshirtRepository tShirtRepository;
    private FeeRepository feeRepository;
    private TaxRepository taxRepository;

    @Autowired
    public ServiceLayer(InvoiceRepository invoiceRepository, GameRepository gameRepository,
                        ConsoleRepository consoleRepository, TshirtRepository tShirtRepository,
                        FeeRepository feeRepository, TaxRepository taxRepository) {
        this.invoiceRepository = invoiceRepository;
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
        this.tShirtRepository = tShirtRepository;
        this.feeRepository = feeRepository;
        this.taxRepository = taxRepository;
    }

    @Transactional
    public InvoiceViewModel saveInvoice (InvoiceViewModel ivm) {

        //Persist Invoice
        Invoice i = new Invoice();
        i.setName(ivm.getName());
        i.setStreet(ivm.getStreet());
        i.setCity(ivm.getCity());

        // validate state
        if (taxRepository.findByState(ivm.getState()) == null) {
            throw new IllegalArgumentException("Must enter a valid state.");
        }

        i.setState(ivm.getState());

        i.setZipcode(ivm.getZip());

        // validate item type, item id, and quantity
        // will set each if they are validated as well as set unit price
        validationHelper(ivm, i);


        i.setUnit_price(handleUnitPrice(ivm.getItem_id()));
        ivm.setUnit_price(i.getUnit_price());

        i.setProcessing_fee(handleProcessingFee(ivm.getItem_type()));
        ivm.setProcessing_fee(i.getProcessing_fee());

        i.setSubtotal(calculateSubtotal(ivm.getQuantity(), i.getUnit_price()));
        ivm.setSubtotal(i.getSubtotal());

        i.setTax(handleTax(ivm.getState(), i.getSubtotal()));
        ivm.setTax(i.getTax());

        i.setTotal(calculateTotal(ivm.getSubtotal(),
                ivm.getTax(),
                ivm.getProcessing_fee()));
        ivm.setTotal(i.getTotal());

        i = invoiceRepository.save(i);

        ivm.setId(i.getId());

        return ivm;
    }

    public void validationHelper (InvoiceViewModel ivm, Invoice i) {
        // ensuring that the item type is valid
        if(!ivm.getItem_type().equals("Game")
                && !ivm.getItem_type().equals("Console")
                && !ivm.getItem_type().equals("TShirt"))  {

            throw new NotFoundException("Must enter an item type of 'Game', 'Console', or 'TShirt'");

        }

        // setting the item type after successful validation
        i.setItem_type(ivm.getItem_type());

        String type = ivm.getItem_type();
        int id = ivm.getItem_id();

        // This switch-case block ensures the item id exists
        // and ensures that there is quantity enough to meet the invoice
        // It will also set the unit price
        switch (type) {

            case "Game":
                if(!gameRepository.findById(id).isPresent()) {
                    throw new NotFoundException("Item id does not exist.");
                }
                i.setItem_id(id);

                if (gameRepository.findById(id).get().getQuantity() >= ivm.getQuantity()) {
                    i.setQuantity(ivm.getQuantity());
                }
                else {
                    throw new IllegalArgumentException("Quantity cannot exceed " +
                            gameRepository.findById(id).get().getQuantity() + ".");
                }

                // setting the unit price
                i.setUnit_price(gameRepository.findById(id).get().getPrice());
                break;

            case "Console":
                if(!consoleRepository.findById(id).isPresent()) {
                    throw new NotFoundException("Item id does not exist.");
                }
                i.setItem_id(id);

                if (consoleRepository.findById(id).get().getQuantity() >= ivm.getQuantity()) {
                    i.setQuantity(ivm.getQuantity());
                }
                else {
                    throw new IllegalArgumentException("Quantity cannot exceed " +
                            consoleRepository.findById(id).get().getQuantity() + ".");
                }

                //setting the unit price
                i.setUnit_price(consoleRepository.findById(id).get().getPrice());
                break;

            case "TShirt":
                if(!tShirtRepository .findById(id).isPresent()) {
                    throw new NotFoundException("Item id does not exist.");
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

        if(count <= 10) {
            BigDecimal quantity = new BigDecimal(count);
            BigDecimal subTotal = quantity.multiply(unitPrice);

            return subTotal;
        }

        BigDecimal quantity = new BigDecimal(count);
        BigDecimal subTotal = quantity.multiply(unitPrice);
        subTotal.add(new BigDecimal(15.49)); // additional charge for quantity > 10

        return subTotal;
    }

    public BigDecimal handleTax(String initials, BigDecimal subTotal) {
        BigDecimal stateTax = taxRepository.findByState(initials).getRate();
        BigDecimal tax = stateTax.multiply(subTotal);
        return tax.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal handleUnitPrice(int id) {
        BigDecimal unitPrice = gameRepository.findById(id).get().getPrice();

        return unitPrice;
    }

    public BigDecimal handleProcessingFee(String type) {
        // Appending the 's' based of the READ.me example
        // containing 'Games'
        BigDecimal fee = feeRepository.findByProductType(type + "s").getFee();

        return fee.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTotal(BigDecimal subTotal, BigDecimal tax, BigDecimal fee) {
        BigDecimal total = tax.add(subTotal).add(fee);

        return total;
    }

    public InvoiceViewModel findInvoice (int id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        return invoice.isPresent() ? buildInvoiceViewModel(invoice.get()) : null;
    }

    public List<InvoiceViewModel> findAllInvoices () {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        List<InvoiceViewModel> ivmList = new ArrayList<>();

        // Building our list of InvoiceViewModel
        invoiceList.stream().forEach(i -> ivmList.add(buildInvoiceViewModel(i)));

        return ivmList;
    }

    public List<InvoiceViewModel> findInvoiceByName (String name) {
        List<Invoice> invoiceList = invoiceRepository.findByName(name);
        List<InvoiceViewModel> ivmList = new ArrayList<>();

        // Building our list of InvoiceViewModel
        invoiceList.stream().forEach(i -> ivmList.add(buildInvoiceViewModel(i)));

        return ivmList;
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        // Assemble the InvoiceViewModel
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setId(invoice.getId());
        ivm.setName(invoice.getName());
        ivm.setStreet(invoice.getStreet());
        ivm.setCity(invoice.getCity());
        ivm.setState(invoice.getState());
        ivm.setZip(invoice.getZipcode());
        ivm.setItem_type(invoice.getItem_type());
        ivm.setItem_id(invoice.getItem_id());
        ivm.setQuantity(invoice.getQuantity());
        ivm.setUnit_price(invoice.getUnit_price());
        ivm.setTax(invoice.getTax());
        ivm.setProcessing_fee(invoice.getProcessing_fee());
        ivm.setTotal(invoice.getTotal());

        return ivm;
    }
}
