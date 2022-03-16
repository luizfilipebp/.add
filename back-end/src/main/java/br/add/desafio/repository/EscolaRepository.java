package br.add.desafio.repository;

import br.add.desafio.model.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EscolaRepository extends JpaRepository<Escola, Integer> {
}
