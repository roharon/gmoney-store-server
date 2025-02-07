package com.aaronroh.gmoney.api.store;

import com.aaronroh.gmoney.domain.store.Store;
import com.aaronroh.gmoney.domain.store.StoreRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Size;
import java.util.*;

@Api(tags = {"1. Store"})
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/store")
public class StoreController {

    private static final Logger LOGGER = LogManager.getLogger(StoreController.class);
    private StoreRepository storeRepository;
    private final int RADIUS = 1000;
    private final int SIZE_PER_PAGE = 20;

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
            LOGGER.error(e.getMessage());
        }

        return StoreAdapter.storeResponse(store, errors);
    }

    @ApiOperation(value="가맹점 검색", notes = "가맹점 이름 연관검색")
    @Validated
    @GetMapping("/search")
    public @ResponseBody StoreListResponse searchStore(
            @ApiParam(value="페이지", defaultValue = "0") @RequestParam Integer page,
            @ApiParam(value="이름", required=true) @RequestParam @Size(min=2, message="minimum 2") String title,
            @ApiParam(value="시군") @RequestParam(required = false) String sigoon) {
        List<String> errors = new ArrayList<>();
        Page<Store> storeList = null;

        try{
            PageRequest pageRequest = PageRequest.of(page, SIZE_PER_PAGE);
            if (sigoon == null)
                storeList = storeRepository.findByTitleContaining(pageRequest, title);
            else
                storeList = storeRepository.findByTitleContainingAndSigoon(pageRequest, title, sigoon);
        } catch(Exception e){
            errors.add(e.getMessage());
            LOGGER.error(e.getMessage());
        }

        return StoreAdapter.storeListResponse(storeList, errors);
    }

    @ApiOperation(value="시군 단위 가맹점 전체 조회", notes="사용자의 시군 내 모든 가맹점 조회")
    @GetMapping("/all")
    public @ResponseBody StoreListResponse searchStore(
            @ApiParam(value="페이지", defaultValue = "0") @RequestParam Integer page,
            @ApiParam(value="시군", required=true) @RequestParam String sigoon,
            @ApiParam(value="카테고리") @RequestParam(required = false) Store.BigCategory category) {
        List<String> errors = new ArrayList<>();
        Page<Store> storeList = null;

        try{
            PageRequest pageRequest = PageRequest.of(page, SIZE_PER_PAGE);
            if (category == null)
                storeList = storeRepository.findBySigoon(pageRequest, sigoon);
            else
                storeList = storeRepository.findBySigoonAndBigCategory(pageRequest, sigoon, category.toString());
            LOGGER.error(storeList.getSize());
        } catch(Exception e){
            errors.add(e.getMessage());
            LOGGER.error(e.getMessage());
        }

        return StoreAdapter.storeListResponse(storeList, errors);
    }

    @ApiOperation(value="근처 가맹점 조회", notes ="사용자 기준 근처 가맹점 조회")
    @GetMapping("/near")
    public @ResponseBody StoreListResponse getNearStore(
            @ApiParam(value="페이지", defaultValue = "0") @RequestParam Integer page,
            @ApiParam(value="시군", required = true) @RequestParam String sigoon,
            @ApiParam(value="위도", required = true) @RequestParam float lat,
            @ApiParam(value="경도", required = true) @RequestParam float lng) {
        List<String> errors = new ArrayList<>();
        Page<Store> storeList = null;

        try{
            PageRequest pageRequest = PageRequest.of(page, SIZE_PER_PAGE);
            storeList = storeRepository.findBySigoonAndEarthDistance(pageRequest, sigoon, lat, lng, RADIUS);
        } catch(Exception e){
            errors.add(e.getMessage());
            LOGGER.error(e.getMessage());
        }

        return StoreAdapter.storeListResponse(storeList, errors);
    }

    @ApiOperation(value="근처 BigCategory별 가맹점 수 조회", notes="사용자 기준 근처 카테고리별 가맹점 수 조회")
    @GetMapping("/near/category/count")
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
            LOGGER.error(e.getMessage());
        }

        return StoreAdapter.storeCategoryCountResponse(countDict, errors);
    }

    @ApiOperation(value="근처 BigCategory별 가맹점 조회", notes ="사용자 기준 근처 카테고리별 가맹점 조회")
    @GetMapping("/near/category")
    public @ResponseBody StoreListResponse getNearStoreByCategory(
            @ApiParam(value="페이지", defaultValue = "0") @RequestParam Integer page,
            @ApiParam(value="카테고리", required = true) @RequestParam Store.BigCategory category,
            @ApiParam(value="시군", required = true) @RequestParam String sigoon,
            @ApiParam(value="위도", required = true) @RequestParam float lat,
            @ApiParam(value="경도", required = true) @RequestParam float lng) {
        List<String> errors = new ArrayList<>();
        Page<Store> storeList = null;

        try{
            PageRequest pageRequest = PageRequest.of(page, SIZE_PER_PAGE);
            storeList = storeRepository.findByCategoryAndSigoonAndEarthDistance(pageRequest, category.toString(), sigoon, lat, lng, RADIUS);
        } catch(Exception e){
            errors.add(e.getMessage());
            LOGGER.error(e.getMessage());
        }

        return StoreAdapter.storeListResponse(storeList, errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody Map handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> errorResult = new HashMap<>();
        errorResult.put("errors", e.getMessage());

        return errorResult;
    }
}
