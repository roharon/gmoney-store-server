package com.aaronroh.gmoney.api.store;

import com.aaronroh.gmoney.domain.store.Store;
import com.aaronroh.gmoney.domain.store.StoreRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Api(tags = {"1. Store"})
@RestController
@AllArgsConstructor
@RequestMapping(value = "/store")
public class StoreController {

    private StoreRepository storeRepository;

    @ApiOperation(value="가맹점 정보", notes = "해당 가맹점의 정보 조회")
    @GetMapping("/{id}")
    public @ResponseBody StoreResponse getStore(
            @ApiParam(value="가맹점 아이디", required = true) @PathVariable Long id){
        List<String> errors = new ArrayList<>();
        Store store = null;

        try {
            store = storeRepository.getOne(id);
        } catch (EntityNotFoundException e){
            errors.add(e.getMessage());
        }

        return StoreAdapter.storeResponse(store, errors);
    }

    @ApiOperation(value="가맹점 추가", notes = "가맹점 새로 추가")
    @PostMapping("/")
    public @ResponseBody StoreResponse editStore(
            @ApiParam(value = "가맹점 정보", required = true) @RequestBody StoreSaveRequestDto dto){
        List<String> errors = new ArrayList<>();
        Store store = dto.toEntity();
        try {
            storeRepository.save(store);
        } catch(Exception e){
            errors.add(e.getMessage());
        }

        return StoreAdapter.storeResponse(store, errors);
    }

}
