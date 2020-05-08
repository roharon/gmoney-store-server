package com.aaronroh.gmoney.api.store;

import com.aaronroh.gmoney.domain.store.Store;
import com.aaronroh.gmoney.api.ApiResponse;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public class StoreResponse extends ApiResponse<Object> {

    @Builder
    public StoreResponse(final Store store, final List<String> errors) {
        super(store);
        this.setErrors(errors);
    }
}

class StoreListResponse extends ApiResponse<Object> {

    @Builder
    public StoreListResponse(final Page<Store> storeList, final List<String> errors) {
        super(storeList);
        this.setErrors(errors);
        this.setSize(storeList.getSize());
    }
}

class StoreCategoryCountResponse extends ApiResponse<Object>{

    @Builder
    public StoreCategoryCountResponse(final Map<String, Integer> countDict, final List<String> errors) {
        super(countDict);
        this.setErrors(errors);
    }
}