package com.cob.salesforce.controllers;

import com.cob.salesforce.services.dashboard.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/dashboard")
public class DashBoardController {
    @Autowired
    DashboardService dashboardService;

    @ResponseBody
    @GetMapping(path = "/data")
    public ResponseEntity getDashBoardData() {
        return new ResponseEntity(dashboardService.getData(), HttpStatus.OK);
    }
}
