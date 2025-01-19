package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.dto.store_manager.GetStoresDto;
import gp.riham_aisha.back_end.dto.store_manager.StoreAnalytics;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import gp.riham_aisha.back_end.model.Store;
import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.service.ProductService;
import gp.riham_aisha.back_end.service.StoreManagementService;
import gp.riham_aisha.back_end.service.StoreService;
import gp.riham_aisha.back_end.service.UserService;
import gp.riham_aisha.back_end.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreManagementServiceImpl implements StoreManagementService {
    private final StoreService storeService;
    private final UserService userService;
    private final ProductService productService;
    private User getCurrentUser() {
        return userService.getUserByUsername(AuthUtil.getCurrentUser());
    }


    @Override
    public List<GetStoresDto> getAllStoresForManager() {
        List<Store> stores = storeService.getStoresByManagerId(getCurrentUser().getId());
        return GetStoresDto.fromStores(stores);
    }

    @Override
    public StoreAnalytics getStoreAnalytics(Long id) {
        Store store = storeService.getStore(id);
        AuthUtil.validateStoreOwner(store);
        List<Product> lowStock = productService.lowStockProducts(id);
        return new StoreAnalytics(store.getName(), store.getStoreCategory().getId(), lowStock);
    }
}
