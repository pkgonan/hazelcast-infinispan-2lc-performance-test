package com.pkgonan.controller;

import com.pkgonan.domain.Coupon;
import com.pkgonan.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class CouponController {

    @Autowired
    private CouponRepository repository;


    @GetMapping("/coupons/{id}")
    public Coupon getCoupon(@PathVariable long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @PostMapping("/coupon")
    public void saveCoupon(@RequestBody Coupon coupon) {
        repository.save(coupon);
    }
}
