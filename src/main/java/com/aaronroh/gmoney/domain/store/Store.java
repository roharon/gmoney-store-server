package com.aaronroh.gmoney.domain.store;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Store {

    public enum BigCategory {animal_hospital, beauty, car_service, cvs, education, fashion,
                                    hospital, lodge, market, personal, pharmacy, restaurant, sports}
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(length=500)
    private String title;

    @Column(length=500)
    private String category;

    @Column
    private String sigoon;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @Column
    private String postCode;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

    @Column
    private String updateDate;

    @Column
    @Enumerated(EnumType.STRING)
    private BigCategory bigCategory;

    @Builder
    public Store(String title, String category, String sigoon, String address,
                 String phoneNumber, String postCode,
                 float latitude, float longitude, BigCategory bigCategory) {
        this.title = title;
        this.category = category;
        this.sigoon = sigoon;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.postCode = postCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bigCategory = bigCategory;
    }
}
