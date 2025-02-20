package com.keybit.bestproduct.entity;

import com.keybit.bestproduct.entity.vo.Item;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class BestProduct {

    @Id
    private String id;
    private Item item;
    private long salesVolume;


    public static BestProduct register(Item item) {
        UUID uuid = UUID.randomUUID();
        return new BestProduct(uuid.toString(), item, 1L);
    }

    public Long increaseSalesVolume() {
        long increasedSalesVolume = getSalesVolume() + 1L;
        setSalesVolume(increasedSalesVolume);
        return getSalesVolume();
    }

    private BestProduct(String id, Item item, long salesVolume) {
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
