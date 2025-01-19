package gp.riham_aisha.back_end.dto.store;

import gp.riham_aisha.back_end.model.Store;

public record StoreBasicInfo(
        Long storeId,
        String storeName,
        String storeLogoURL
) {
    public static StoreBasicInfo fromStore(Store store) {
        return new StoreBasicInfo(store.getId(), store.getName(), store.getLogoURL());
    }
}
