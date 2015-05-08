package models;

import javax.persistence.*;

/**
 * Created by Caio on 08/05/2015.
 */
@Entity
@Table(name = "FALTAS")
public class Falta {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long alunoid;
    @Column
    private String data;

    public Falta(){

    }

    public Falta(String data, long alunoid) {
        this.data = data;
        this.alunoid = alunoid;
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

        Falta falta = (Falta) o;

        if (alunoid != falta.alunoid) return false;
        if (data != null ? !data.equals(falta.data) : falta.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (alunoid ^ (alunoid >>> 32));
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
