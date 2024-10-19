package com.keybit.bestproduct.domain.model;

import java.util.UUID;

public class bestProduct {
    private String id;
    private Item item;
    private long salesVolume;


    public bestProduct register(Item item) {
        UUID uuid = UUID.randomUUID();
        return new bestProduct(uuid.toString(), item, 1L);
    }

    public Long increaseSalesVolume() {
        long increasedSalesVolume = getSalesVolume() + 1L;
        setSalesVolume(increasedSalesVolume);
        return getSalesVolume();
    }

    private bestProduct(String id, Item item, long salesVolume) {
        this.id = id;
        this.item = item;
        this.salesVolume = salesVolume;
    }

    public String getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public long getSalesVolume() {
        return salesVolume;
    }

    private void setSalesVolume(long salesVolume) {
        this.salesVolume = salesVolume;
    }
}
