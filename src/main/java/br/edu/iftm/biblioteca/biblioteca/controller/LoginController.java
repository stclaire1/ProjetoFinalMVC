package br.edu.iftm.biblioteca.biblioteca.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iftm.biblioteca.biblioteca.dao.LoginDao;
import br.edu.iftm.biblioteca.biblioteca.model.LoginModel;
import br.edu.iftm.biblioteca.biblioteca.model.Role;
import br.edu.iftm.biblioteca.biblioteca.service.LoginService;
import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private LoginDao loginDao;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("mensagem", "");
        return "login";
    }

    @RequestMapping("/cadastro")
    public String novo(Model model) {
        model.addAttribute("mensagem", "");
        return "cadastro";
    }

    @PostMapping("/cadastroUs")
    public String salvarLogin(LoginModel login, Model model) {
        if (loginDao.usuarioExiste(login.getNome_usuario())) {
            model.addAttribute("errorMessage", "Usuário já cadastrado");
            return "cadastro";
        } else {
            loginService.salvar(login);
            return "login";
        }
    }

    Logger logger = (Logger) LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
public String login(HttpSession session, LoginModel loginDigitado, Model model) {
    LoginModel loginBanco = loginService.verificaSenha(loginDigitado);
    if (loginBanco == null) {
        model.addAttribute("errorMessage", "Usuario ou senha inválidos");
        return "login";
    }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : loginBanco.getRoles()) {
            if (role != null) {
                logger.info("Registrando a role " + role.getNome() + " para o usuário logado " + loginBanco.getNome_usuario());
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getNome()));
            }
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginBanco.getNome_usuario(),
                loginBanco.getSenha(),
                authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
    return "redirect:/menu"; 
}
}

