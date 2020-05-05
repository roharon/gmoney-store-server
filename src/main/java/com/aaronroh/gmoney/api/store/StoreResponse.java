package com.aaronroh.gmoney.api.store;

import com.aaronroh.gmoney.domain.store.Store;
import com.aaronroh.gmoney.api.ApiResponse;
import lombok.Builder;

import java.util.List;

public class StoreResponse extends ApiResponse<Object> {

    @Builder
    public StoreResponse(final Store store, final List<String> errors) {
        super(store);
        this.setErrors(errors);
    }
}

class StoreListResponse extends ApiResponse<Object> {

    @Builder
    public StoreListResponse(final List<Store> storeList, final List<String> errors) {
        super(storeList);
        this.setErrors(errors);
        this.setSize(storeList.size());
    }
}
