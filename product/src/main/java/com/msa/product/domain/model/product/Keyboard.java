package com.msa.product.domain.model.product;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@DiscriminatorValue("KEYBOARD")
public class Keyboard extends Product {

    protected Keyboard() {}
}
