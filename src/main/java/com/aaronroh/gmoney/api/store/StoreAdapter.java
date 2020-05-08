package com.aaronroh.gmoney.api.store;

import com.aaronroh.gmoney.domain.store.Store;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public class StoreAdapter {

    public static StoreResponse storeResponse(final Store store, final List<String> errors){
        return StoreResponse.builder()
                .store(store)
                .errors(errors)
                .build();
    }

    public static StoreListResponse storeListResponse(final Page<Store> storeList, final List<String> errors){
        return StoreListResponse.builder()
                .storeList(storeList)
                .errors(errors)
                .build();
    }

    public static StoreCategoryCountResponse storeCategoryCountResponse(final Map<String, Integer> countDict,
                                                                        final List<String> errors){
        return StoreCategoryCountResponse.builder()
                .countDict(countDict)
                .errors(errors)
                .build();
    }
}
