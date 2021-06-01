package com.taras.springdemo.controller;

import com.taras.springdemo.dao.CustomerDAO;
import com.taras.springdemo.entity.Customer;
import com.taras.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //inject customer service
    @Autowired
   private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model){

        //get customers from service
        List<Customer> customers = customerService.getCustomers();

        //add customers to Model
        model.addAttribute("customers", customers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        //create model to get form data
        Customer newCustomer = new Customer();
        model.addAttribute("customer", newCustomer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {

        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {

        //get customer from service
        Customer customer = customerService.getCustomer(id);

        //set customer as a model to pre-populate the form
        model.addAttribute("customer", customer);

        //send data to our form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id){

        //delete customer
        customerService.deleteCustomer(id);
        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomer(@RequestParam("searchName") String searchName, Model model){

        List<Customer> customers = customerService.searchCustomers(searchName);

        model.addAttribute("customers", customers);

        return "list-customers";
    }
}
