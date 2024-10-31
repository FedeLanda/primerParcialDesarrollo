package com.parcial.mutantes.services;

import com.parcial.mutantes.entities.Adn;
import com.parcial.mutantes.repositories.AdnRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdnServiceTest {

    @Mock
    private AdnRepository adnRepository;

    @InjectMocks
    private AdnService adnService;

    @BeforeEach
    void setUp() {
        // Inicialización si es necesario
    }


    @Test
    public void testRows(){
        String[] adn= {
                "ATAATG",
                "GTTAGT",
                "GGCTCG",
                "TTGATG",
                "GTAGTC",
                "AGTCAA"
        };
        assertTrue(AdnService.esMutante(adn));
    }

    @Test
    public void testColumns(){
        String[] adn= {
                "AGAATG",
                "TGCAGT",
                "GCTTCC",
                "GTCCTC",
                "GTAGTC",
                "GGTCAC"
        };
        assertTrue(AdnService.esMutante(adn));
    }
    @Test
    public void testMainDiagonals(){
        String[] adn= {
                "AGAATG",
                "TACAGT",
                "GCAGCC",
                "TTGATG",
                "GTAGTC",
                "AGTCAA"
        };
        assertTrue(AdnService.esMutante(adn));
    }
    @Test
    public void testSecondaryLeftDiagonals(){
        String[] adn= {
                "ATAATG",
                "GTTAGT",
                "GGCTCG",
                "TTGATG",
                "GTAGTC",
                "AGTCAA"
        };
        assertTrue(AdnService.esMutante(adn));
    }

    @Test
    public void testSecondaryRightDiagonals(){
        String[] adn= {
                "ATAATG",
                "GTATGA",
                "GCTTAG",
                "TTTAGG",
                "GTAGTC",
                "AGTCAA"
        };
        assertTrue(AdnService.esMutante(adn));
    }

    @Test
    public void testTertiaryLeftDiagonals(){
        String[] adn= {
                "ATGATG",
                "GTAGTA",
                "CCTTGG",
                "TCTAGG",
                "GGCGTC",
                "AGTCAA"
        };
        assertTrue(AdnService.esMutante(adn));
    }
    @Test
    public void testTertiayRightDiagonals(){
        String[] adn= {
                "ATGATG",
                "GTATTA",
                "AATTGG",
                "ACTAGT",
                "GGAGTC",
                "AGGCAA"
        };
        assertTrue(AdnService.esMutante(adn));
    }
    @Test
    public void testNoMutante(){
        String[] adn= {
                "ATGATG",
                "GTCTTA",
                "AATTGG",
                "ACTAGT",
                "GGATTC",
                "AGGCAA"
        };
        assertTrue(AdnService.esMutante(adn));
    }

    @Test
    public void testMutant1(){
        String[] adn= {
                "AAAA",
                "CCCC",
                "TCAG",
                "GGTC"

        };
        assertTrue(AdnService.esMutante(adn));
    }
    @Test
    public void testMutant2(){
        String[] adn= {
                "TGAC",
                "AGCC",
                "TGAC",
                "GGTC"

        };
        assertTrue(AdnService.esMutante(adn));
    }
    @Test
    void testAnalyzeAdn_WithNewSequence() {
        String[] adn = {"ATGC", "GTAC", "CGTG", "TGAAT"};
        Adn newAdn = new Adn();
        when(adnRepository.findByAdn(anyString())).thenReturn(Optional.empty());
        when(adnRepository.save(any(Adn.class))).thenReturn(newAdn);

        boolean result = adnService.analyzeAdn(adn);
        assertTrue(result);
    }
    @Test
    void testAnalyzeAdn_WithExistingSequence() {
        String[] adn = {"ATGC", "GTAC", "CGTG", "TGAAT"};
        Adn existingAdn = new Adn();
        existingAdn.setEsMutante(true);
        when(adnRepository.findByAdn(anyString())).thenReturn(Optional.of(existingAdn));

        boolean result = adnService.analyzeAdn(adn);
        assertTrue(result);
    }
}
