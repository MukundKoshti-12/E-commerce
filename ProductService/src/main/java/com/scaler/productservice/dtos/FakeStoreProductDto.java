package com.scaler.productservice.dtos;

import com.scaler.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter // This will automatically define the getter function of all the attributes listed in the below class
@Setter // This will automatically define the setter function of all the attributes listed in the below class
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
}
