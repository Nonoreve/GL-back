package com.example.demo.controller;

import com.example.demo.exception.LineBillException;
import com.example.demo.exception.MissionException;
import com.example.demo.service.DateService;
import com.example.demo.service.LineBillService;
import com.example.demo.service.MissionService;
import com.example.demo.vo.LineBill;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Getter
@RequestMapping("/linebill")
@RestController
public class LineBillController {
    @Autowired
    LineBillService lineBillService;
    @Autowired
    MissionService missionService;
    @Autowired
    DateService dateService;


    @GetMapping("/new")
    public LineBill createNewLineBill(@RequestParam Float amount,
                                      @RequestParam (required = false) Float tvaPercent,
                                      @RequestParam (required = false) Float tva,
                                      @RequestParam String date,
                                      @RequestParam int idMission,
                                      @RequestParam String country) throws LineBillException, MissionException {

        LineBill l = this.lineBillService.saveLineBill(LineBill.builder()
                .amount(amount)
                .isValidated(false)
                .tvaPercent(tvaPercent)
                .mission(missionService.findById(idMission))
                .tva(tva)
                .date(dateService.parseDate(date))
                .country(country)
                .build());
        return l;
    }

    @GetMapping("/delete")
    public HttpStatus deleteLineBill(int id) throws LineBillException {

        return this.lineBillService.deleteLineBill(id);
    }

    @GetMapping("/list")
    public List<LineBill> getLineBillList() {
        return this.lineBillService.getLineBillList();
    }

    //TODO
    @GetMapping("/update")
    public LineBill updateLineBill(@RequestParam int id,
                                   @RequestParam (required = false)Float amount,
                                   @RequestParam (required = false) Float tvaPercent,
                                   @RequestParam (required = false) Float tva,
                                   @RequestParam (required = false)String date,
                                   @RequestParam (required = false) String country) throws LineBillException {
        LineBill lineBill = lineBillService.getLine(id);


        return lineBillService.updateLine(id);
    }
}