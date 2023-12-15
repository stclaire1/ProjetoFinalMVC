package br.edu.iftm.biblioteca.biblioteca.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.iftm.biblioteca.biblioteca.dao.LoginDao;
import br.edu.iftm.biblioteca.biblioteca.model.LoginModel;
import ch.qos.logback.classic.Logger;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;

    public void salvar(LoginModel login) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        login.setSenha(encoder.encode(login.getSenha()));

        loginDao.salvarCadastro(login);
    }


    Logger logger = (Logger) LoggerFactory.getLogger(LoginService.class);

    public LoginModel verificaSenha(LoginModel loginDigitado) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        LoginModel loginBanco = loginDao.getLoginModel(loginDigitado.getNome_usuario());
        if (loginBanco != null) {
            if (encoder.matches(loginDigitado.getSenha(), loginBanco.getSenha())) {
                return loginBanco;
            } else {
                logger.info("Senha não confere para o login " + loginDigitado.getNome_usuario());
            }
        }
        return null;
    }
}
