package br.edu.iftm.biblioteca.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.biblioteca.biblioteca.domain.Emprestimo;
import br.edu.iftm.biblioteca.biblioteca.domain.Livro_Emprestimo;
import br.edu.iftm.biblioteca.biblioteca.domain.Livro;
import br.edu.iftm.biblioteca.biblioteca.service.EmprestimoService;
import br.edu.iftm.biblioteca.biblioteca.service.LivroEmprestimoService;
import br.edu.iftm.biblioteca.biblioteca.service.LivroService;

@Controller
public class BibliotecaController {
    @Autowired
    private LivroService livroService;

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private LivroEmprestimoService livroEmprestimoService;

    @GetMapping("/emprestimo")
    public String emprestimo(Model model) {
        model.addAttribute("livrosDisponiveis", livroService.getLivrosDisponiveis());
        model.addAttribute("emprestimo", new Emprestimo());
        return "emprestimo";
    }

    @PostMapping("/emprestimo")
    public String realizarEmprestimo(@ModelAttribute Emprestimo emprestimo, RedirectAttributes redirectAttributes) {
        System.out.println("Método realizarEmprestimo acionado!"); // teste para verificar se o método está sendo acionado
        // lógica de realizar emprestimo
        Livro livroSelecionado = livroService.getById(emprestimo.getId_livro());

        if (livroSelecionado != null && "Disponivel".equals(livroSelecionado.getDisponibilidade())) {
            // atualiza disponibilidade do livro
            livroService.atualizarDisponibilidade(livroSelecionado.getId_livro(), "Indisponivel");

            // atualiza status do empréstimo
            emprestimo.setStatus("Aguardando Devolução");

            // inserir empréstimo
            emprestimoService.inserirEmprestimo(emprestimo);

            // pega o id do ultimo emprestimo inserido
            int idEmprestimo = emprestimoService.getUltimo_id();

            // implementa a relação livro-emprestimo
            Livro_Emprestimo livroEmprestimo = new Livro_Emprestimo();
            livroEmprestimo.setId_livro(livroSelecionado.getId_livro());
            livroEmprestimo.setId_emprestimo(idEmprestimo);
            livroEmprestimoService.inserirLivroEmprestimo(livroEmprestimo);

            redirectAttributes.addFlashAttribute("successMessage", "Empréstimo realizado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Empréstimo não realizado!");
        }

        return "redirect:/menu";
    }

}
