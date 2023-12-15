package br.edu.iftm.biblioteca.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import br.edu.iftm.biblioteca.biblioteca.dao.EmprestimoDao;
import br.edu.iftm.biblioteca.biblioteca.domain.Emprestimo;

@Service
public class EmprestimoService {

    @Autowired
    // inicia o objeto emprestimoDao
    private EmprestimoDao emprestimoDao;

    // retorna todos os emprestimos
    public List<Emprestimo> getEmprestimos(){
        return emprestimoDao.getAll();
    }

    // retorna emprestimo por id
    public Emprestimo getById(int id){
        return emprestimoDao.getById(id);
    }

    // insere emprestimo no banco
    public void inserirEmprestimo(Emprestimo emprestimo){
        emprestimoDao.inserirEmprestimo(emprestimo);
    }

    // retorna o id do ultimo emprestimo inserido no banco
    public int getUltimo_id(){
        return emprestimoDao.getUltimo_id();
    }

    // public void atualizarEmprestimo(Emprestimo emprestimo){
    //     emprestimoDao.atualizarEmprestimo(emprestimo);
    // }

    // public void deletarEmprestimo(int id){
    //     emprestimoDao.deletarEmprestimo(id);
    // }
}
