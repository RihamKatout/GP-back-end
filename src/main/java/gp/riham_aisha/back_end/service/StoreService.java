package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.store.AddStoreDto;
import gp.riham_aisha.back_end.dto.store.GetStoresDto;
import gp.riham_aisha.back_end.model.Store;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoreService {
    List<Store> getAllStores();
    Store addNewStore(AddStoreDto addStoreDto, MultipartFile ... images);

    Store updateStore(Long id, AddStoreDto addStoreDto);

    void banStore(Long id);

    void unbanStore(Long id);

    void activateStore(Long id);

    void deactivateStore(Long id);

    void deleteStore(Long id);

    Store getStore(Long id);
    List<Store> getStoresByManagerId(Long managerId);
    List<GetStoresDto> getStoresByCategoryId(Long categoryId);
}
