package models;

import javax.persistence.*;
import java.io.File;

/**
 * Created by Caio on 08/05/2015.
 */
@Entity
@Table(name = "TAREFA")
public class Tarefa {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String data;
    @Column
    private String datafinal;
    @Column
    private String valor;
    @Column
    private String comentario;
    @Lob
    private String foto;

    public Tarefa(){

    }

    public Tarefa(String data, String datafinal, String comentario, String valor, String foto) {
        this.data = data;
        this.datafinal = datafinal;
        this.comentario = comentario;
        this.valor = valor;
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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

    public String getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(String datafinal) {
        this.datafinal = datafinal;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
