package com.cob.salesforce.services.dashboard;

import com.cob.salesforce.models.dashboard.DashboardDataContainer;

public interface DashboardService  {

     DashboardDataContainer getData(Long clinicId , Long userId,Long startDate , Long endDate);
}
