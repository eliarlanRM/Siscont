package br.com.eliarlan.siscont.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eliarlan.siscont.model.Titulo;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {

    public List<Titulo> findByDescricaoContaining(String descricao);
}
