package com.example.demo.controller;


import com.example.demo.exception.DateException;
import com.example.demo.exception.ExpenseBillException;
import com.example.demo.service.DateService;
import com.example.demo.service.ExpenseBillService;
import com.example.demo.vo.BillStates;
import com.example.demo.vo.ExpenseBill;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;


@Getter
@RequestMapping("/expensebill")
@RestController
public class ExpenseBillController {
    @Autowired
    ExpenseBillService expenseBillService;
    @Autowired
    DateService dateService;


    @GetMapping("/new")
    public ExpenseBill createNewExpenseBill(@RequestParam String name,
                                         @RequestParam String description,
                                         @RequestParam String date) throws ExpenseBillException, DateException {

        ExpenseBill e = this.expenseBillService.saveExpenseBill(ExpenseBill.builder()
                .amount(0)
                .listLineBill(new ArrayList<>())
                .name(name)
                .date(dateService.parseDate(date))
                .descritpion(description)
                .state(BillStates.DRAFT)
                .build());

        return e;
    }

    @GetMapping("/delete")
    public HttpStatus deleteExpenseBill(@RequestParam int id) throws ExpenseBillException {
        return this.expenseBillService.deleteExpenseBill(id);
    }

    @GetMapping("/list")
    public List<ExpenseBill> getExpenseBillList() {
        return this.expenseBillService.getExpenseBillList();
    }

    //TODO validation de toute la note de frais


    // TODO quand utilisateur sera codé faire des fonction de get en fonction de UserID passe en parametre
}