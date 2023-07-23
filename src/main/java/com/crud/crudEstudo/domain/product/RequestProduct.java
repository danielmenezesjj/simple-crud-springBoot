package com.crud.crudEstudo.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestProduct(@NotBlank String name,  @NotNull  Integer prince_in_cents) {
}
