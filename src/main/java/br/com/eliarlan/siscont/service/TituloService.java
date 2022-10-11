package br.com.eliarlan.siscont.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.eliarlan.siscont.model.StatusTitulo;
import br.com.eliarlan.siscont.model.Titulo;
import br.com.eliarlan.siscont.repository.TituloRepository;
import br.com.eliarlan.siscont.repository.filter.TituloFilter;

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

    public String receber(Long id) {
        Titulo titulo = repository.findById(id).get();
        titulo.setStatus(StatusTitulo.RECEBIDO);
        repository.save(titulo);

        return StatusTitulo.RECEBIDO.getDescricao();
    }

    public List<Titulo> filtrar(TituloFilter filtro) {
        String descricao = filtro.getDescricao() == null ? "" : filtro.getDescricao();
        return repository.findByDescricaoContaining(descricao);
    }

}
