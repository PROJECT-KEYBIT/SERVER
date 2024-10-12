package com.msa.order.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Embeddable
@EqualsAndHashCode(of = "no")
public class OrderNo implements Serializable {

    @Serial
    private static final long serialVersionUID = -4025347149735302974L;

    @Getter private String no;

    protected OrderNo() {}

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
