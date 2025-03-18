package com.keybit.tag.domain.tag.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@ToString
@Getter
@Document(collection = "tags")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    private Set<String> productIds = new HashSet<>();

    public static Tag register(String name, String productIds) {
        UUID uuid = UUID.randomUUID();
        return new Tag(uuid.toString(), name, productIds);
    }

    public Tag(String id, String name , String productIds) {
        this.id = id;
        this.name = name;
        this.productIds.add(productIds);
    }

    public boolean doesNotContainProductId(String productId) {
        return !productIds.contains(productId);
    }

    public void addProductId(String productId) {
        productIds.add(productId);
    }
}