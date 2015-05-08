package models;

import javax.persistence.*;

/**
 * Created by Caio on 08/05/2015.
 */
@Entity
@Table(name = "AULA")
public class Aula {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String data;
    @Column
    private String oferta;
    @Column
    private String comentario;

    public Aula(){

    }

    public Aula(String data, String oferta, String comentario) {
        this.data = data;
        this.oferta = oferta;
        this.comentario = comentario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOferta() {
        return oferta;
    }

    public void setOferta(String oferta) {
        this.oferta = oferta;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
