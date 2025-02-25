package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.store.GetStoresDto;
import gp.riham_aisha.back_end.dto.store.StoreAnalytics;

import java.util.List;

public interface ManagerService {
    List<GetStoresDto> getAllStoresForManager();
    StoreAnalytics getStoreAnalytics(Long id);
}
