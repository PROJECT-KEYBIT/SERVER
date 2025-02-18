package com.keybit.tag.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Document
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    public static Tag register(String name) {
        UUID uuid = UUID.randomUUID();
        return new Tag(uuid.toString(), name);
    }

    private Tag(String id, String name) {
        this.id = id;
        this.name = name;
    }
}