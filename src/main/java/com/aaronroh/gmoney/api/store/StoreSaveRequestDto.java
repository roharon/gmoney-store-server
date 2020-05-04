package com.aaronroh.gmoney.api.store;

import com.aaronroh.gmoney.domain.store.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreSaveRequestDto {
    private String title;
    private String category;

    public Store toEntity(){
        return Store.builder()
                .title(title)
                .category(category)
                .build();
    }
}
