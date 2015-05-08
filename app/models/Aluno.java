package models;

import javax.persistence.*;

/**
 * Created by Caio on 08/05/2015.
 */
@Entity
@Table(name = "ALUNO")
public class Aluno {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nome, idade;
    @Column
    private String login;
    @Column
    private String senha;
    @Column
    private String endereco;
    @Column
    private String telefone;
    @Column
    private long salaid;

    public Aluno(){

    }

    public Aluno(String nome, String login, String idade, String senha, String endereco, String telefone, long salaid) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.idade = idade;
        this.endereco = endereco;
        this.telefone = telefone;
        this.salaid = salaid;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getSalaid() {
        return salaid;
    }

    public void setSalaid(long salaid) {
        this.salaid = salaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aluno aluno = (Aluno) o;

        if (salaid != aluno.salaid) return false;
        if (nome != null ? !nome.equals(aluno.nome) : aluno.nome != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (int) (salaid ^ (salaid >>> 32));
        return result;
    }
}
