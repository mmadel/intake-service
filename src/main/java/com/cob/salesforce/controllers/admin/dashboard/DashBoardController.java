package com.cob.salesforce.controllers.admin.dashboard;

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
    @GetMapping(path = "/data/clinicId/{clinicId}/userId/{userId}/startDate/{startDate}/endDate/{endDate}")
    public ResponseEntity getDashBoardData(@PathVariable("clinicId") Long clinicId,
                                           @PathVariable("userId") Long userId,
                                           @PathVariable("startDate") Long startDate,
                                           @PathVariable("endDate") Long endDate) {
        return new ResponseEntity(dashboardService.getData(clinicId, userId, startDate, endDate), HttpStatus.OK);
    }
}
