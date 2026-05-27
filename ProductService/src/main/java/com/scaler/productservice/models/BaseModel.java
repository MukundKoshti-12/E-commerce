package com.scaler.productservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass // This will define the Mapped Superclass Inheritance pattern of Entity
public class BaseModel implements Serializable {
    @Id
    private Long id;
    private Date creationDate;
    private Date lastModifiedDate;
}
