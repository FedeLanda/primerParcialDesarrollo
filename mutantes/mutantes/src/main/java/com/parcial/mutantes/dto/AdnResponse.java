package com.parcial.mutantes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AdnResponse {
    private boolean esMutante;

    public boolean esMutante(){
        return esMutante;
    }
}
