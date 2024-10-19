package com.keybit.bestproduct.domain.model;

import java.io.Serial;
import java.io.Serializable;

public class Item implements Serializable {
    @Serial
    private static final long serialVersionUID = -8583402381356475091L;

    private Integer no;
    private String title;

    public Item create(int no, String title) {
        return new Item(no, title);
    }

    protected Item() {}
    private Item(Integer no, String title) {
        this.no = no;
        this.title = title;
    }
}
