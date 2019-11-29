package com.example.cadastropessoa;

public class Usuario {

    private String nome;
    private String sobrenome;
    private String datanascimento;
    private String cpf;
    private String Estadocivil;

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(String datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEstadocivil() {
        return Estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.Estadocivil = estadocivil;
    }
}
