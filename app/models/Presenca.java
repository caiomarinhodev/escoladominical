package models;

import javax.persistence.*;

/**
 * Created by Caio on 08/05/2015.
 */
@Entity
@Table(name = "PRESENCA")
public class Presenca {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long alunoid;
    @Column
    private String data;

    public Presenca(){

    }

    public Presenca(String data, long alunoid) {
        this.data = data;
        this.alunoid = alunoid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAlunoid() {
        return alunoid;
    }

    public void setAlunoid(long alunoid) {
        this.alunoid = alunoid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Presenca presenca = (Presenca) o;

        if (alunoid != presenca.alunoid) return false;
        if (!data.equals(presenca.data)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (alunoid ^ (alunoid >>> 32));
        result = 31 * result + data.hashCode();
        return result;
    }
}
