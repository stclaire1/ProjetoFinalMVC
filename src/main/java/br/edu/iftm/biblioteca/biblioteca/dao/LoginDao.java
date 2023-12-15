package br.edu.iftm.biblioteca.biblioteca.dao;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.edu.iftm.biblioteca.biblioteca.model.LoginModel;
import br.edu.iftm.biblioteca.biblioteca.model.Role;
import ch.qos.logback.classic.Logger;

@Component
public class LoginDao {
    @Autowired
     private JdbcTemplate db;

    public boolean usuarioExiste(String nome_usuario) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE nome_usuario = ?";
        Integer count = db.queryForObject(sql, new Object[] { nome_usuario }, (res, rowNum) -> res.getInt(1));
        return count != null && count > 0;
    }

    public void salvarCadastro(LoginModel login) {
        String sql = "insert into usuario(nome_usuario, cpf_usuario, email, telefone, senha) values(?,?,?,?,?)";
        db.update(sql, new Object[] { login.getNome_usuario(), login.getCpf_usuario(), login.getEmail(), login.getTelefone() ,login.getSenha() });
    }

    Logger logger = (Logger) LoggerFactory.getLogger(LoginDao.class);

    public LoginModel getLoginModel(String nome_usuario) {

        String sql = "select nome_usuario,senha from usuario where nome_usuario = ?";

        try {
            LoginModel login = db.queryForObject(sql,
                    (res, rowNum) -> {
                        return new LoginModel(
                                res.getString("nome_usuario"),
                                res.getString("senha"));
                    },
                    new Object[] {nome_usuario});
            login.setRoles(getRoles(nome_usuario));
            return login; 

        } catch (EmptyResultDataAccessException e) {
            logger.info("User not found " + nome_usuario + " message: " + e);
            return null;

        }

    }

    // public LoginModel getLoginModel(String nome_usuario) {
    // String sql = "select nome_usuario, senha from usuario where nome_usuario =?";
    // List<LoginModel> logins = db.query(sql,
    // (res, rowNum) -> {
    // return new LoginModel(
    // res.getString("nome_usuario"),
    // res.getString("senha"));
    // },
    // new Object[] { nome_usuario });
    // System.out.println(logins.size());
    // if (!logins.isEmpty()) {
    // return logins.get(0);
    // } else {
    // System.out.println("NULL");
    // return null;
    // }

    // }

    public List<Role> getRoles(String nome_usuario) {
        String sql = "select id,nome from tb_role where id in (select role_id from tb_role_user where nome_usuario = ?)";
        return db.query(sql,
                (res, rowNum) -> {
                    return new Role(
                            res.getInt("id"),
                            res.getString("nome"));
                },
                new Object[] {nome_usuario});

    }
}
