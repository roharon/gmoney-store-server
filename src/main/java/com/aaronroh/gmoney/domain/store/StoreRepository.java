package com.aaronroh.gmoney.domain.store;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(
            value = "SELECT * FROM store s WHERE earth_distance(" +
                    "ll_to_earth(CAST(s.latitude as float), CAST(s.longitude as float))," +
                    "ll_to_earth(:latitude, :longitude)) < :radius",
            nativeQuery = true)
    List<Store> findByEarthDistance(@Param("latitude") Float latitude, @Param("longitude") Float longitude,
        @Param("radius") Integer radius);
}
