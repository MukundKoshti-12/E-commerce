package com.scaler.productservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // Defines below class as entity
public class Category extends BaseModel{
    private String title;
}
