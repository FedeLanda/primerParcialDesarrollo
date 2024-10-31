package com.parcial.mutantes.repositories;

import com.parcial.mutantes.entities.Adn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdnRepository extends JpaRepository<Adn,Long> {
    Optional<Adn> findByAdn(String adnSequence);

    long countByEsMutante (boolean esMutante);

}
