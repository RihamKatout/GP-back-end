package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.dto.CloudinaryResponse;
import gp.riham_aisha.back_end.dto.store.AddStoreDto;
import gp.riham_aisha.back_end.dto.store.GetStoresDto;
import gp.riham_aisha.back_end.enums.Role;
import gp.riham_aisha.back_end.enums.StoreStatus;
import gp.riham_aisha.back_end.model.Store;
import gp.riham_aisha.back_end.model.StoreCategory;
import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.repository.StoreRepository;
import gp.riham_aisha.back_end.service.CategoryService;
import gp.riham_aisha.back_end.service.CloudinaryService;
import gp.riham_aisha.back_end.service.StoreService;
import gp.riham_aisha.back_end.service.UserService;
import gp.riham_aisha.back_end.util.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final CategoryService storeCategoryService;
    private final UserService userService;
    private final CloudinaryService cloudinaryService;

    private static final String IS_NOT_FOUND = " is not found";
    private static final String NOT_FOUND = "Store with id: %d" + IS_NOT_FOUND;

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Transactional
    @Override
    public Store addNewStore(AddStoreDto addStoreDto, MultipartFile... images) {
        String managerUsername = AuthUtil.getCurrentUser();
        Long managerId = managerUsername.equals("System") ? 1L : userService.getUserByUsername(managerUsername).getId();
        User manager = userService.getUser(managerId);
        manager.addRole(Role.STORE_MANAGER);
        Long categoryId = addStoreDto.categoryId();
        StoreCategory category = storeCategoryService.getStoreCategory(categoryId);
        Store store = new Store(addStoreDto, category, manager);
        storeRepository.save(store);
        manager.addStore();
        if (images != null && images.length > 0) {
            CloudinaryResponse response = cloudinaryService.uploadFile(images[0], String.valueOf(store.getId()));
            if (response != null) {
                store.setLogoURL(response.getUrl());
            }
        }
        if (images != null && images.length > 1) {
            CloudinaryResponse response = cloudinaryService.uploadFile(images[1], String.valueOf(store.getId()));
            if (response != null) {
                store.setCoverURL(response.getUrl());
            }
        }
        log.info("Store with id: {} is added successfully by: {}", store.getId(), AuthUtil.getCurrentUser());
        return store;
    }

    @Override
    public Store updateStore(Long id, AddStoreDto addStoreDto) {
        Store store = getStore(id);
        AuthUtil.validateStoreOwner(store);
        store.setName(addStoreDto.name());
        store.setDescription(addStoreDto.description());
        store.setLogoURL(addStoreDto.logoURL());
        store.setCoverURL(addStoreDto.coverURL());
        store.setStoreCategory(storeCategoryService.getStoreCategory(addStoreDto.categoryId()));
        storeRepository.save(store);
        log.info("Store with id: {} is updated successfully by: {}", store.getId(), AuthUtil.getCurrentUser());
        return store;
    }

    @Override
    public void banStore(Long id) {
        Store store = getStore(id);
        store.setStatus(StoreStatus.BANNED);
        storeRepository.save(store);
        log.info("Store with id: {} is banned successfully by: {}", store.getId(), AuthUtil.getCurrentUser());
    }

    @Override
    public void unbanStore(Long id) {
        Store store = getStore(id);
        if (store.getStatus() != StoreStatus.BANNED) {
            throw new IllegalStateException("Store is not banned");
        }
        store.setStatus(StoreStatus.ACTIVE);
        storeRepository.save(store);
        log.info("Store with id: {} is unbanned successfully by: {}", store.getId(), AuthUtil.getCurrentUser());
    }

    @Override
    public void activateStore(Long id) {
        // 1- check kif the user is admin or support -> activate it
        // 2- check if the user is the store manager -> send a request to admin to activate it
        Store store = getStore(id);
        if (AuthUtil.isCurrentUserSupport()) {
            store.setStatus(StoreStatus.ACTIVE);
            storeRepository.save(store);
            log.info("Store with id: {} is activated successfully by: {}", store.getId(), AuthUtil.getCurrentUser());
        }
        //TODO: send a request to admin to activate the store
    }

    @Override
    public void deactivateStore(Long id) {
        Store store = getStore(id);
        if (store.getStatus() != StoreStatus.ACTIVE) {
            throw new IllegalStateException("Store is not active");
        }
        AuthUtil.validateStoreOwner(store);
        store.setStatus(StoreStatus.INACTIVE);
        storeRepository.save(store);
        log.info("Store with id: {} is deactivated successfully by: {}", store.getId(), AuthUtil.getCurrentUser());
    }

    @Override
    @Transactional
    public void deleteStore(Long id) {
        // check if the user is an admin and store still under review
        Store store = getStore(id);
        if (Boolean.TRUE.equals(AuthUtil.doesCurrentUserHasAuthority(Role.ADMIN))
                && (store.getStatus() == StoreStatus.UNDER_REVIEW)) {
            storeRepository.deleteById(id);
            log.info("Store with id: {} is deleted successfully by: {}", id, AuthUtil.getCurrentUser());
            return;
        }
        User manager = AuthUtil.validateStoreOwner(store);
        manager.removeStore();
        storeRepository.deleteById(id);
        log.info("Store with id: {} is deleted successfully by: {}", id, AuthUtil.getCurrentUser());
    }

    @Override
    public Store getStore(Long id) {
        return storeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND, id)));
    }

    @Override
    public List<Store> getStoresByManagerId(Long managerId) {
        return storeRepository.findByManagerId(managerId);
    }

    @Override
    public List<GetStoresDto> getStoresByCategoryId(Long categoryId) {
        List<Store> stores;
        stores = (categoryId == null) ? storeRepository.findAllByOrderByRatingDesc() : storeRepository.findByStoreCategory_Id(categoryId);
        return GetStoresDto.fromStores(stores);
    }

}
