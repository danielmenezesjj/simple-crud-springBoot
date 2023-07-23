package com.crud.crudEstudo.domain.product;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "product")
@Entity(name = "product")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer prince_in_cents;
    private Boolean active;

    public Product(RequestProduct requestProduct){
        this.name = requestProduct.name();
        this.prince_in_cents = requestProduct.prince_in_cents();
        this.active = true;
    }

}
