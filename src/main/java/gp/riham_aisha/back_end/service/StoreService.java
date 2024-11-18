package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.StoreDto;
import gp.riham_aisha.back_end.model.Store;

public interface StoreService {
    Store addNewStore(StoreDto storeDto);

    Store updateStore(Long id, StoreDto storeDto);

    void banStore(Long id);

    void unbanStore(Long id);

    void activateStore(Long id);

    void deactivateStore(Long id);

    void deleteStore(Long id);

    Store getStore(Long id);
}
