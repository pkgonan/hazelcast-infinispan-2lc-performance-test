package com.pkgonan.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Cacheable
@Entity
public class Coupon {

    @Id
    @Column
    private long id;

    @Column
    private long userId;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private boolean usable;

    public Coupon(Long id, Long userId, String name, Integer price) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.price = price;
        this.usable = false;
    }
}
