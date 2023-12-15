package br.edu.iftm.biblioteca.biblioteca.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.edu.iftm.biblioteca.biblioteca.domain.Livro;

@Component
public class LivroDao {
    @Autowired
    private JdbcTemplate bd;

    // pegar todos os livros do banco
    public List<Livro> getLivros(){
        String sql = "SELECT * FROM livro";
        return bd.query(sql, (row, rowNum) ->{
            return new Livro(
                row.getInt("id_livro"),
                row.getString("nome_livro"),
                row.getString("autor"),
                row.getString("disponibilidade")
            );
        });
    }

    // pegar os livros do banco que constam como disponiveis
    public List<Livro> getLivrosDisponiveis(){
        String sql = "SELECT * FROM livro WHERE disponibilidade = 'Disponivel'";
        return bd.query(sql, (row, rowNum) ->{
            return new Livro(
                row.getInt("id_livro"),
                row.getString("nome_livro"),
                row.getString("autor"),
                row.getString("disponibilidade")
            );
        });
    }

    //pegar livros do banco por nome e autor (usado para verificar se o livro já existe no banco)
    public List<Livro> getLivrosPorNomeEAutor(String nome_livro, String autor) {
        String sql = "SELECT * FROM livro WHERE nome_livro = ? AND autor = ?";
        return bd.query(sql, (row, rowNum) ->{
            return new Livro(
                row.getInt("id_livro"),
                row.getString("nome_livro"),
                row.getString("autor"),
                row.getString("disponibilidade")
            );
        }, nome_livro, autor);
    }

    // pegar livro do banco por id
    public Livro getById(int id){
        String sql = "SELECT * FROM livro WHERE id_livro = ?";

        List<Livro> livros = bd.query(sql, new BeanPropertyRowMapper<Livro>(Livro.class), id);

        if(livros!=null && livros.size() > 0){
            return livros.get(0);
        }else{
            return null;
        }
    }

    public void inserirLivro(Livro livro) {
        String sql = "INSERT INTO livro (nome_livro, autor, disponibilidade) VALUES (?, ?, ?)";
        bd.update(sql, livro.getNome_livro(), livro.getAutor(), livro.getDisponibilidade());
    }

    public void atualizarDisponibilidade(int id, String disponibilidade) {
        String sql = "UPDATE livro SET disponibilidade = ? WHERE id_livro = ?";
        bd.update(sql, disponibilidade, id);
    }

//    public void atualizarLivro(Livro livro){
//     String sql = "UPDATE livro SET nome_livro = ?, autor = ?, disponibilidade = ? WHERE id_livro = ?";
//     bd.update(sql, livro.getNome_livro(), livro.getAutor(), livro.getDisponibilidade(), livro.getId_livro());
//    }

//     public void deletarLivro(int id){
//         String sql = "DELETE FROM livro WHERE id_livro = ?";
//         bd.update(sql, id);
//     }
}
