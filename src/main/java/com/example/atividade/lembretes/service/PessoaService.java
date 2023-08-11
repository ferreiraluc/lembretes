package com.example.atividade.lembretes.service;
import com.example.atividade.lembretes.entity.Pessoa;
import com.example.atividade.lembretes.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findByName(String nome) {
        return pessoaRepository.findByName(nome);
    }

    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    public List<Pessoa> findByAtivo(boolean ativo) {
        return pessoaRepository.findByAtivo(ativo);
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    private void validarNome(String nome) {
        if (nome == null || !nome.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Nome da pessoa inválido");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(Pessoa pessoa) {
        validarNome(pessoa.getNome());
        pessoaRepository.save(pessoa);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void atualizar(Long id, Pessoa pessoa) {
        validarNome(pessoa.getNome());

        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(id);

        if (pessoaExistente.isPresent()) {
            Pessoa pessoaAtualizada = pessoaExistente.get();
            pessoaRepository.save(pessoaAtualizada);
        } else {
            throw new IllegalArgumentException("Id inválido!");
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void excluir(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID da pessoa é nulo.");
        }
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada com o ID: " + id));

        if (!pessoa.getLembreteList().isEmpty()) {
            throw new IllegalStateException("A pessoa não pode ser excluída porque está vinculada a lembretes.");
        }

        pessoaRepository.deleteById(id);
    }
}