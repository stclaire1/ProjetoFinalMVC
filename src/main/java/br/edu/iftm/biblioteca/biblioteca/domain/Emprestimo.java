package br.edu.iftm.biblioteca.biblioteca.domain;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emprestimo {
    private Integer id_emprestimo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_retirada;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_devolucao;
    private String status;
    private Integer id_livro;
    private Integer id_unidade;
}
