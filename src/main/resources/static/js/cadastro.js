window.addEventListener('load', function () {
    var btnCadastrar = document.getElementById('btnCadastrar');

    var regexUsuario = /^[a-zA-Z0-9]{5,20}$/;
    var regexCPF = /^[0-9]{3}\.[0-9]{3}\.[0-9]{3}-[0-9]{2}$/;
    var regexEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    var regexTelefone = /^\(\d{2}\)\d{5}-\d{4}$/;
    var regexSenha = /^[a-zA-Z0-9]{5,20}$/;

    var txtUsuario = document.getElementById('nome_usuario');
    var txtCPF = document.getElementById('cpf_usuario');
    var txtEmail = document.getElementById("email");
    var txtTelefone = document.getElementById("telefone");
    var txtSenha = document.getElementById("senha");

    var formUser = document.getElementById('userreg');
    var formCPF = document.getElementById('cpfreg');
    var formEmail = document.getElementById('emailreg');
    var formTelefone = document.getElementById('telreg');
    var formSenha = document.getElementById('senhareg');

        btnCadastrar.addEventListener('click', function (event) {
            var isValid = true;
        
            if (!regexUsuario.test(txtUsuario.value)) {
                formUser.textContent = "Usuário inválido";
                isValid = false;
            } else if (!regexCPF.test(txtCPF.value)) {
                formCPF.textContent = "Cpf inválido";
                isValid = false;
            } else if (!regexEmail.test(txtEmail.value)) {
                formEmail.textContent = "Email inválido";
                isValid = false;
            } else if (!regexTelefone.test(txtTelefone.value)) {
                formTelefone.textContent = "Telefone inválido";
                isValid = false;
            } else if (!regexSenha.test(txtSenha.value)) {
                formSenha.textContent = "Senha inválida";
                isValid = false;
            } 
        
            //Se todas as validacoes passarem, vai para a login page
            if (!isValid) {
                event.preventDefault();
            }
        })  
});
