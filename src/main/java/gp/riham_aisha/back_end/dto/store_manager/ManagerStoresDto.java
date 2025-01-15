package gp.riham_aisha.back_end.dto.store_manager;

import gp.riham_aisha.back_end.enums.StoreStatus;
import gp.riham_aisha.back_end.model.Store;

import java.util.List;

public record ManagerStoresDto(
        Long id,
        String name,
        String logoURL,
        StoreStatus status
) {
    public static List<ManagerStoresDto> fromStores(List<Store> stores) {
        return stores.stream()
                .map(store -> new ManagerStoresDto(
                        store.getId(),
                        store.getName(),
                        store.getLogoURL(),
                        store.getStatus()
                ))
                .toList();
    }
}
