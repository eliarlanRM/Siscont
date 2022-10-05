package br.com.eliarlan.siscont.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.eliarlan.siscont.model.Titulo;
import br.com.eliarlan.siscont.repository.TituloRepository;

@Service
@Transactional
public class TituloService {

    @Autowired
    private TituloRepository repository;

    public List<Titulo> buscarTodos() {
        return repository.findAll();
    }

    public void salvar(Titulo titulo) {
        try {
            repository.save(titulo);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Formato de data inválido");
        }
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
