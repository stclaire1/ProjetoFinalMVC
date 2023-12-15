package br.edu.iftm.biblioteca.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.biblioteca.biblioteca.dao.LivroEmprestimoDao;
import br.edu.iftm.biblioteca.biblioteca.domain.Livro_Emprestimo;

@Service
public class LivroEmprestimoService {
    @Autowired
    private LivroEmprestimoDao livroEmpDao;

    public List<Livro_Emprestimo> getLivros(){
        return livroEmpDao.getAll();
    }

    public Livro_Emprestimo getById(int id){
        return livroEmpDao.getById(id);
    }

    public void inserirLivroEmprestimo(Livro_Emprestimo livroEmprestimo){
        livroEmpDao.inserirLivroEmprestimo(livroEmprestimo);
    }

    // public void atualizarLivroEmprestimo(Livro_Emprestimo livroEmprestimo){
    //     livroEmpDao.atualizarLivroEmprestimo(livroEmprestimo);
    // }

    // public void deletarLivroEmprestimo(int id){
    //     livroEmpDao.deletarLivroEmprestimo(id);
    // }
}
