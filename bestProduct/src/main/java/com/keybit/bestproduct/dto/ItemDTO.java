package com.keybit.bestproduct.dto;

import com.keybit.bestproduct.entity.vo.Item;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDTO {

    private Integer no;
    private String title;


    public ItemDTO create(Integer no, String title) {
        return new ItemDTO(no, title);
    }

    public ItemDTO toMapDTO(Item item) {
        return create(item.getNo(), item.getTitle());
    }

    public Item toMapEntity() {
        return Item.create(getNo(), getTitle());
    }

    private ItemDTO(Integer no, String title) {
        this.no = no;
        this.title = title;
    }
}
