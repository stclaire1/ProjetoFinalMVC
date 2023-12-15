package br.edu.iftm.biblioteca.biblioteca.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.edu.iftm.biblioteca.biblioteca.domain.Livro_Emprestimo;

@Component
public class LivroEmprestimoDao {
    @Autowired
    private JdbcTemplate bd;

    // pegar todos os livros_emprestimos do banco
    public List<Livro_Emprestimo> getAll(){
        String sql = "SELECT * FROM livro_emprestimo";
        return bd.query(sql, (row, rowNum) ->{
            return new Livro_Emprestimo(
                row.getInt("id_livro_emprestimo"),
                row.getInt("id_livro"),
                row.getInt("id_emprestimo")
            );
        });
    }

    // pegar livro_emprestimo do banco por id
    public Livro_Emprestimo getById(int id){
        String sql = "SELECT * FROM livro_emprestimo WHERE id_livro_emprestimo = ?";

        List<Livro_Emprestimo> livroEmprestimos = bd.query(sql, (row, rowNum) ->{
            return new Livro_Emprestimo(
                row.getInt("id_livro_emprestimo"),
                row.getInt("id_livro"),
                row.getInt("id_emprestimo")
            );
        }, id);

        if(livroEmprestimos!=null && livroEmprestimos.size() > 0){
            return livroEmprestimos.get(0);
        } else{
            return null;
        }
    }


    public void inserirLivroEmprestimo(Livro_Emprestimo livroEmprestimo){
        String sql = "INSERT INTO livro_emprestimo (id_livro_emprestimo, id_livro, id_emprestimo) VALUES (?, ?, ?)";
        bd.update(sql, livroEmprestimo.getId_livro_emprestimo(), livroEmprestimo.getId_livro(), livroEmprestimo.getId_emprestimo());
    }
    
    // public List<Livro_Emprestimo> getAllByEmprestimo(int id_emprestimo){
    //     String sql = "SELECT * FROM livro_emprestimo WHERE id_emprestimo = ?";
    //     return bd.query(sql, (row, rowNum) ->{
    //         return new Livro_Emprestimo(
    //             row.getInt("id_livro_emprestimo"),
    //             row.getInt("id_livro"),
    //             row.getInt("id_emprestimo")
    //         );
    //     }, id_emprestimo);
    // }

    // public void atualizarLivroEmprestimo(Livro_Emprestimo livroEmprestimo){
    //     String sql = "UPDATE livro_emprestimo SET id_livro = ?, id_emprestimo = ? WHERE id_livro_emprestimo = ?";
    //     bd.update(sql, livroEmprestimo.getId_livro(), livroEmprestimo.getId_emprestimo(), livroEmprestimo.getId_livro_emprestimo());
    // }

    // public void deletarLivroEmprestimo(int id){
    //     String sql = "DELETE FROM livro_emprestimo WHERE id_livro_emprestimo = ?";
    //     bd.update(sql, id);
    // }
}
