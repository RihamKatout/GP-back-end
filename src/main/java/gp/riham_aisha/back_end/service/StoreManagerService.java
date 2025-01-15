package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.store_manager.ManagerStoresDto;

import java.util.List;

public interface StoreManagerService {
    List<ManagerStoresDto> getAllStoresForManager();
}
