package com.keybit.bestproduct.entity.vo;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class Item implements Serializable {
    @Serial
    private static final long serialVersionUID = -8583402381356475091L;

    private Integer no;
    private String title;

    public static Item create(int no, String title) {
        return new Item(no, title);
    }

    protected Item() {}
    private Item(Integer no, String title) {
        this.no = no;
        this.title = title;
    }
}
