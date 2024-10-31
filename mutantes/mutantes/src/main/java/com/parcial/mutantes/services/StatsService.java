package com.parcial.mutantes.services;

import com.parcial.mutantes.dto.StatsResponse;
import com.parcial.mutantes.repositories.AdnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private final AdnRepository adnRepository;

    @Autowired
    public StatsService(AdnRepository adnRepository){
        this.adnRepository= adnRepository;
    }

    public StatsResponse getStats(){
        long countMutantAdn= adnRepository.countByEsMutante(true);
        long countHumanAdn= adnRepository.countByEsMutante(false);
        double ratio= countHumanAdn== 0 ? 0: (double) countMutantAdn/countHumanAdn;
        return new StatsResponse (countMutantAdn,countHumanAdn, ratio);

    }
}
