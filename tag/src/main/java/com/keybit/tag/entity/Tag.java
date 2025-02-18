package com.keybit.tag.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Tag {

    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    public Tag(String title) {
        this.title = title;
    }
}