package com.parcial.mutantes.controllers;

import com.parcial.mutantes.dto.AdnRequest;
import com.parcial.mutantes.dto.AdnResponse;
import com.parcial.mutantes.services.AdnService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/mutante")
@CrossOrigin(origins = "*")
public class AdnController {

    private final AdnService adnService;

    @Autowired
    public AdnController( AdnService adnService){
        this.adnService=adnService;
    }

    @PostMapping (path = "/mutant")

    public ResponseEntity<AdnResponse> checkMutante ( @Valid @RequestBody AdnRequest adnRequest){
     boolean esMutante= adnService.analyzeAdn(adnRequest.getAdn());

    AdnResponse adnResponse= new AdnResponse(esMutante);
    if(esMutante){
        return ResponseEntity.ok(adnResponse);

    }else{
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(adnResponse);
    }

}
}
