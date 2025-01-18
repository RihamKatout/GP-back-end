package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.store_manager.GetStoresDto;
import gp.riham_aisha.back_end.dto.store_manager.StoreAnalytics;

import java.util.List;

public interface StoreManagementService {
    List<GetStoresDto> getAllStoresForManager();
    StoreAnalytics getStoreAnalytics(Long id);
}
