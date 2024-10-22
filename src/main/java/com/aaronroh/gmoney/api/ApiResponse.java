package com.aaronroh.gmoney.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class ApiResponse<T> {
    @NonNull private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer size;

    private List<String> errors;
}
