package br.edu.iftm.biblioteca.biblioteca.domain;
import lombok.Data;

@Data
public class Livro {
    private Integer id_livro;
    private String nome_livro;
    private String autor;
    private String disponibilidade;

    public Livro () {};

    public Livro (Integer id_livro, String nome_livro, String autor, String disponibilidade) {
        this.id_livro = id_livro;
        this.nome_livro = nome_livro;
        this.autor = autor;
        this.disponibilidade = disponibilidade;
    }
}
