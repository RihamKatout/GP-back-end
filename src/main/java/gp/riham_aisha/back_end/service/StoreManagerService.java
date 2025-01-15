package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.store_manager.GetStoresDto;

import java.util.List;

public interface StoreManagerService {
    List<GetStoresDto> getAllStoresForManager();
}
