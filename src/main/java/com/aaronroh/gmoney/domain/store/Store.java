package com.aaronroh.gmoney.domain.store;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Store {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length=500, nullable = false)
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
    private float latitude;

    @Column
    private float longitude;

    @Column
    private String updateDate;

    @Builder
    public Store(String title, String category, String sigoon, String address,
                 String phoneNumber, String postCode,
                 float latitude, float longitude) {
        this.title = title;
        this.category = category;
        this.sigoon = sigoon;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.postCode = postCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
