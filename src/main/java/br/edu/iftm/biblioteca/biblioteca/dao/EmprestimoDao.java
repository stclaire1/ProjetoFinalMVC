package br.edu.iftm.biblioteca.biblioteca.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.edu.iftm.biblioteca.biblioteca.domain.Emprestimo;

@Component
public class EmprestimoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // seleciona todos os emprestimos do banco
    public List<Emprestimo> getAll(){
        String sql = "SELECT * FROM emprestimo";
        return jdbcTemplate.query(sql, (row, rowNum) -> new Emprestimo(
                row.getInt("id_emprestimo"),
                row.getDate("data_retirada"),
                row.getDate("data_devolucao"),
                row.getString("status"),
                // row.getString("nome_usuario"),
                row.getInt("id_livro"),
                row.getInt("id_unidade")
        ));
    }

    // seleciona emprestimo do banco por id
    public Emprestimo getById(int id){
        String sql = "SELECT * FROM emprestimo WHERE id_emprestimo = ?";

        List<Emprestimo> emprestimos = jdbcTemplate.query(sql, (row, rowNum) -> new Emprestimo(
                row.getInt("id_emprestimo"),
                row.getDate("data_retirada"),
                row.getDate("data_devolucao"),
                row.getString("status"),
                // row.getString("nome_usuario"),
                row.getInt("id_livro"),
                row.getInt("id_unidade")
        ), id);

        return emprestimos.isEmpty() ? null : emprestimos.get(0);
    }

    // insere emprestimo no banco
    public void inserirEmprestimo(Emprestimo emprestimo){
        String sql = "INSERT INTO emprestimo (data_retirada, data_devolucao, status, id_livro) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                emprestimo.getData_retirada(),
                emprestimo.getData_devolucao(),
                emprestimo.getStatus(),
                // emprestimo.getNome_usuario(),
                emprestimo.getId_livro()
        );
    }

    // retorna o ultimo id inserido no banco
    public Integer getUltimo_id() {
        String sql = "SELECT LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    // public void atualizarEmprestimo(Emprestimo emprestimo){
    //     String sql = "UPDATE emprestimo SET data_retirada = ?, data_devolucao = ?, status = ?, id_livro = ?, id_unidade = ? WHERE id_emprestimo = ?";
    //     jdbcTemplate.update(sql,
    //             emprestimo.getData_retirada(),
    //             emprestimo.getData_devolucao(),
    //             emprestimo.getStatus(),
    //             emprestimo.getId_livro(),
    //             emprestimo.getId_unidade(),
    //             emprestimo.getId_emprestimo()
    //     );
    // }

//     public void deletarEmprestimo(int id){
//         String sql = "DELETE FROM emprestimo WHERE id_emprestimo = ?";
//         jdbcTemplate.update(sql, id);
//     }
 }
