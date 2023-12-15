package br.edu.iftm.biblioteca.biblioteca.domain;
import lombok.Data;

@Data
public class Livro_Emprestimo {
    private Integer id_livro_emprestimo;
    private Integer id_livro;
    private Integer id_emprestimo;

    public Livro_Emprestimo() {};

    public Livro_Emprestimo(Integer id_livro_emprestimo, Integer id_livro, Integer id_emprestimo) {
        this.id_livro_emprestimo = id_livro_emprestimo;
        this.id_livro = id_livro;
        this.id_emprestimo = id_emprestimo;
    }
}


