package com.bluejaygm.arraypizz.cadastro;

import java.io.Serializable;

/***
 *
 * classe do objeto usuario
 *
 * @serial io
 * TODO: 16/06/2019
 * @version 1.0.0
 * @author MIKHAEL DE OLIVEIRA SILVA D'AMATO
 *
 *
 */

public class Usuario implements Serializable {

    private String nome;
    private String email;
    private String senha;

    public Usuario(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + " Email: " + getEmail() + " Senha: " + getSenha();
    }
}
