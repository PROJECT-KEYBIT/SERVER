package com.msa.order.domain.model.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(of = "no")
public class OrderNo {
    @Getter private String no;

    private OrderNo() {}

    private OrderNo(String no) {
        this.no = no;
    }

    public static OrderNo createOrderNo() {
        UUID uuid = UUID.randomUUID();
        String year = String.valueOf(LocalDate.now().getYear());
        String no = year + '_' + uuid;

        return new OrderNo(no);
    }
}
