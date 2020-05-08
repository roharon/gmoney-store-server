package com.aaronroh.gmoney.api.store;

import com.aaronroh.gmoney.domain.store.Store;
import com.aaronroh.gmoney.domain.store.StoreRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Api(tags = {"1. Store"})
@RestController
@AllArgsConstructor
@RequestMapping(value = "/store")
public class StoreController {

    private StoreRepository storeRepository;
    final int RADIUS = 3000; //TODO: move to config

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

    @ApiOperation(value="근처 가맹점 조회", notes ="사용자 기준 근처 가맹점 조회")
    @GetMapping("/near")
    public @ResponseBody StoreListResponse getNearStore(
            @ApiParam(value="페이지", defaultValue = "0") @RequestParam Integer page,
            @ApiParam(value="시군", required = true) @RequestParam String sigoon,
            @ApiParam(value="위도", required = true) @RequestParam float lat,
            @ApiParam(value="경도", required = true) @RequestParam float lon,
            final Pageable pageable) {
        List<String> errors = new ArrayList<>();
        Page<Store> storeList = null;

        try{
            PageRequest pageRequest = PageRequest.of(page, 30);
            storeList = storeRepository.findBySigoonAndEarthDistance(pageRequest, sigoon, lat, lon, RADIUS);
        } catch(Exception e){
            errors.add(e.getMessage());
        }

        return StoreAdapter.storeListResponse(storeList, errors);
    }

    @ApiOperation(value="근처 BigCategory별 가맹점 수 조회", notes="사용자 기준 근처 카테고리별 가맹점 수 조회")
    @GetMapping("/near/category")
    public @ResponseBody StoreCategoryCountResponse getStoreCategoryCount(
            @ApiParam(value="카테고리", required = true) @RequestParam Store.BigCategory category,
            @ApiParam(value="시군", required = true) @RequestParam String sigoon,
            @ApiParam(value="위도", required = true) @RequestParam float lat,
            @ApiParam(value="경도", required = true) @RequestParam float lng) {
        List<String> errors = new ArrayList<>();
        Map<String, Integer> countDict = new HashMap<>();
        int storeCount;

        try{
            storeCount = storeRepository.countByCategoryAndSigoonAndEarthDistance(category.toString(), sigoon, lat, lng, RADIUS);
            countDict.put(category.toString(), storeCount);
        } catch(Exception e){
            errors.add(e.getMessage());
        }

        return StoreAdapter.storeCategoryCountResponse(countDict, errors);
    }
}
