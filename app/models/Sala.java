package models;

import javax.persistence.*;

/**
 * Created by Caio on 08/05/2015.
 */
@Entity
@Table(name = "SALA")
public class Sala {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nome;

    public Sala(){

    }

    public Sala(String nome) {
        this.nome = nome;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sala sala = (Sala) o;

        if (!nome.equals(sala.nome)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
