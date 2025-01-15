package gp.riham_aisha.back_end.dto.store_manager;

import gp.riham_aisha.back_end.enums.StoreStatus;
import gp.riham_aisha.back_end.model.Store;

import java.util.List;

public record GetStoresDto(
        Long id,
        String name,
        String logoURL,
        String coverURL,
        StoreStatus status,
        String description,
        int numberOfReviews,
        double rating
) {
    public static List<GetStoresDto> fromStores(List<Store> stores) {
        return stores.stream()
                .map(store -> new GetStoresDto(
                        store.getId(),
                        store.getName(),
                        store.getLogoURL(),
                        store.getCoverURL(),
                        store.getStatus(),
                        store.getDescription(),
                        store.getNumberOfReviews(),
                        store.getRating()
                ))
                .toList();
    }
}
