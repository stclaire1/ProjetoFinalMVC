package br.edu.iftm.biblioteca.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.biblioteca.biblioteca.domain.Livro;
import br.edu.iftm.biblioteca.biblioteca.service.LivroService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

@Controller
public class LivroController {
    @Autowired
    private LivroService livroService;

    @RequestMapping("/livros")
    public String livros(Model model) {
        List<Livro> livros = livroService.getLivros();
        model.addAttribute("livros", livros);
        return "listaLivros";
    }

    @RequestMapping("/cadastroLivro")
    public String cadastrarLivro(Model model) {
        model.addAttribute("mensagem", "");
        return "cadastroLivro";
    }

    @PostMapping("/cadastroLivro")
public String novoLivro(@ModelAttribute Livro livro, Model model, RedirectAttributes redirectAttributes) {
    // verifica se o livro já existe no banco
    if (livroService.livroExiste(livro.getNome_livro(), livro.getAutor())) {
        redirectAttributes.addFlashAttribute("errorMessage", "Livro já existe no banco de dados");
        return "redirect:/menu";
    }

    // insere o livro no banco
    livroService.inserirLivro(livro);

    // atualiza a lista de livros diretamente na model e em seguida retorna a lista
    model.addAttribute("livros", livroService.getLivros());

    redirectAttributes.addFlashAttribute("successMessageAddLivro", "Livro cadastrado com sucesso!");

    // Retorna a página que exibe a lista de livros
    return "listaLivros";
}

    @RequestMapping("/login/sair")
    public String customLogoutMethod(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {

            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "/login";
    }

}
