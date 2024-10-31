package com.parcial.mutantes.entities;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class Adn extends Base implements Serializable {
    private String adn;
    private boolean esMutante;

}
