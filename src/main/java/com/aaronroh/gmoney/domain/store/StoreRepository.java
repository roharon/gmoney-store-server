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

    @Query(
            value = "SELECT * FROM store s WHERE s.sigoon = CAST(:sigoon as varchar) and s.title LIKE %:title%",
            nativeQuery = true)
    Page<Store> findByTitleLikeAndSigoon(Pageable pageable, @Param("title") String title, @Param("sigoon") String sigoon);

    @Query(
            value = "SELECT * FROM store s WHERE s.title LIKE %:title%",
            nativeQuery = true)
    Page<Store> findByTitleLike(Pageable pageable, @Param("title") String title);

    @Query(
            value = "SELECT * FROM store s WHERE s.sigoon = :sigoon and s.big_category = :bigCategory",
            nativeQuery = true)
    Page<Store> findBySigoonAndBigCategory(Pageable pageable, @Param("sigoon") String sigoon, @Param("bigCategory") String bigCategory);

    @Query(
            value = "SELECT * FROM store s WHERE s.sigoon = :sigoon",
            nativeQuery = true)
    Page<Store> findBySigoon(Pageable pageable, @Param("sigoon") String sigoon);
}
