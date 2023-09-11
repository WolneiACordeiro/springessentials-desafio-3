package com.devsuperior.desafio3.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    @NotBlank(message = "Field required")
    @Size(min = 3, max = 80, message = "Name must be 3 to 80 characters long")
    private String name;
    private String cpf;
    private Double income;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Field must be in the past or present")
    private LocalDate birthDate;
    private Integer children;
}
