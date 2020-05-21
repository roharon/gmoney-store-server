package com.aaronroh.gmoney.domain.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(
            value = "SELECT * FROM store s WHERE s.sigoon = :sigoon and earth_distance(" +
                    "ll_to_earth(CAST(s.latitude as float), CAST(s.longitude as float))," +
                    "ll_to_earth(:latitude, :longitude)) < :radius",
            countQuery = "SELECT COUNT(*) FROM store",
            nativeQuery = true)
    Page<Store> findBySigoonAndEarthDistance(Pageable pageable, @Param("sigoon") String sigoon, @Param("latitude") Float latitude,
                                             @Param("longitude") Float longitude, @Param("radius") Integer radius);

    @Query(
            value = "SELECT * FROM store s WHERE s.big_category = :bigCategory and s.sigoon = :sigoon and earth_distance(" +
                    "ll_to_earth(CAST(s.latitude as float), CAST(s.longitude as float))," +
                    "ll_to_earth(:latitude, :longitude)) < :radius",
            countQuery = "SELECT COUNT(*) FROM store",
            nativeQuery = true)
    Page<Store> findByCategoryAndSigoonAndEarthDistance(Pageable pageable,
                                                    @Param("bigCategory") String bigCategory,
                                                    @Param("sigoon") String sigoon, @Param("latitude") Float latitude,
                                                    @Param("longitude") Float longitude, @Param("radius") Integer radius);

    @Query(
            value = "SELECT count(*) FROM store s WHERE s.big_category = :bigCategory and s.sigoon = :sigoon and earth_distance(" +
                    "ll_to_earth(CAST(s.latitude as float), CAST(s.longitude as float))," +
                    "ll_to_earth(:latitude, :longitude)) < :radius",
            nativeQuery = true)
    Integer countByCategoryAndSigoonAndEarthDistance(@Param("bigCategory") String bigCategory,
                                                     @Param("sigoon") String sigoon, @Param("latitude") Float latitude,
                                                     @Param("longitude") Float longitude, @Param("radius") Integer radius);

    Page<Store> findByTitleContainingAndSigoon(Pageable pageable, String title, String sigoon);

    Page<Store> findByTitleContaining(Pageable pageable, String title);

    @Query(
            value = "SELECT * FROM store s WHERE s.big_category = :bigCategory and s.sigoon = :sigoon",
            nativeQuery = true)
    Page<Store> findBySigoonAndBigCategory(Pageable pageable, @Param("sigoon") String sigoon, @Param("bigCategory") String bigCategory);

    Page<Store> findBySigoon(Pageable pageable, String sigoon);
}
