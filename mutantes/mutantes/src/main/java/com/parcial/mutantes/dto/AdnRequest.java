package com.parcial.mutantes.dto;

import com.parcial.mutantes.validators.ValidAdn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdnRequest {
    @ValidAdn
    private String[] adn;

}
