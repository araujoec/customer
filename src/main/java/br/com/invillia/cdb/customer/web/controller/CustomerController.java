package br.com.invillia.cdb.customer.web.controller;

import br.com.invillia.cdb.customer.application.CustomerService;
import br.com.invillia.cdb.customer.persistence.entities.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "create-consumer", method = RequestMethod.GET)
    public ResponseEntity<CustomerEntity> createCustomer(
            @RequestParam("name")
            String name,
            @RequestParam("document")
            String document,
            @RequestParam("email")
            String email) {
        return ResponseEntity.ok(customerService.createCustomer(name, document, email));
    }
}
