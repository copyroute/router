package com.copyroute.finance.controllers;

import com.copyroute.cdm.global.Statics;
//import com.copyroute.services.finance.BofA;
//import com.copyroute.services.finance.CapitalOne;
//import com.copyroute.services.finance.Google;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

/**
 * Created by flatline on 6/23/15.
 */

@Controller
public class FinanceController {


//    @Autowired
//    Google google;
//
//    @Autowired
//    CapitalOne capitalOne;
//
//    @Autowired
//    BofA bofa;

    @PostConstruct
    public void init() {
        Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString());
        try {
            //google.searchGoogle("http:google.com", "selenium");
            getCapitalOne();
            getBofA();
        }
        catch(Exception ex){Statics.Log(ex.getMessage());}
    }

    @RequestMapping("capitalone")
    public void getCapitalOne(){
        try {
//            capitalOne.searchCapitalOne("https://www.capitalone.com/", "flatline0", "0spaCeship0");
//            capitalOne.destroy();
        }
        catch(Exception ex){Statics.Log(ex.getMessage());}
    }

    @RequestMapping("bofa")
    public void getBofA(){
        try {
//            bofa.searchBOFA("https://www.bankofamerica.com/", "flatline0", ")spaCeship0");
//            bofa.destroy();
        }
        catch(Exception ex){Statics.Log(ex.getMessage());}
    }

}
