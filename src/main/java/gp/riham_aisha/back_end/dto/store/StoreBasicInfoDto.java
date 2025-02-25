package gp.riham_aisha.back_end.dto.store;

import gp.riham_aisha.back_end.model.Store;

public record StoreBasicInfoDto(
        Long storeId,
        String storeName,
        String storeLogoURL,
        String description
) {
    public static StoreBasicInfoDto fromStore(Store store) {
        return new StoreBasicInfoDto(store.getId(), store.getName(), store.getLogoURL(), store.getDescription());
    }
}
