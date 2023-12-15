package br.edu.iftm.biblioteca.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.edu.iftm.biblioteca.biblioteca.dao.LivroDao;
import br.edu.iftm.biblioteca.biblioteca.domain.Livro;

@Service
public class LivroService {
    @Autowired
    private LivroDao livroDao;

    // retorna todos os livros do banco
    public List<Livro> getLivros(){
        return livroDao.getLivros();
    }

    // retorna os livros do banco que constam como disponiveis
    public List<Livro> getLivrosDisponiveis(){
        return livroDao.getLivrosDisponiveis();
    }
    
    // retorna o livro do banco por id
    public Livro getById(int id){
        return livroDao.getById(id);
    }

    // verifica se o livro já existe no banco
    public boolean livroExiste(String nomeLivro, String autor) {
        // verificar se o livro já existe no banco
        List<Livro> livros = livroDao.getLivrosPorNomeEAutor(nomeLivro, autor);
        return !livros.isEmpty();
    }

    // inserir livro no banco
    public void inserirLivro(Livro livro){
        livroDao.inserirLivro(livro);
    }

    // atualizar disponibilidade do livro
    public void atualizarDisponibilidade(int id, String disponibilidade){
        livroDao.atualizarDisponibilidade(id, disponibilidade);
    }

    // public void atualizarLivro(Livro livro){
    //     livroDao.atualizarLivro(livro);
    // }

    // public void deletarLivro(int id){
    //     livroDao.deletarLivro(id);
    // }
}