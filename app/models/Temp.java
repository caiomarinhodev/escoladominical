package models;

//Classe temporaria responsavel por armazenar dados para Presenças ou Faltas em conjunto por Data;
    public class Temp{
        private long id;
        private String data;
        private int qtd;
        public Temp(long id, String data, int qtd){
            this.id = id;
            this.data = data;
            this.qtd = qtd;
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

        public int getQtd() {
            return qtd;
        }

        public void setQtd(int qtd) {
            this.qtd = qtd;
        }
    }