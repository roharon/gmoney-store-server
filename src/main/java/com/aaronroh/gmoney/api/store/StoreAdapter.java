package com.aaronroh.gmoney.api.store;

import com.aaronroh.gmoney.domain.store.Store;

import java.util.List;

public class StoreAdapter {

    public static StoreResponse storeResponse(final Store store, final List<String> errors){
        return StoreResponse.builder()
                .store(store)
                .errors(errors)
                .build();
    }

    public static StoreListResponse storeListResponse(final List<Store> storeList, final List<String> errors){
        return StoreListResponse.builder()
                .storeList(storeList)
                .errors(errors)
                .build();
    }
}
