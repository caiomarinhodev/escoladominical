package controllers;

import models.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Caio on 08/05/2015.
 */
public class SGDB {

    private static GenericDAO dao = new GenericDAO();

    public static Aluno getAluno(long id){
        return dao.findByEntityId(Aluno.class,id);
    }

    public static void addAluno(Aluno a){
        dao.persist(a);
        dao.flush();
    }

    public static void removeAluno(long id){
        Aluno a = getAluno(id);
        if(a!=null){
            dao.remove(a);
            dao.flush();
        }
    }

    public static void alterarAluno(Aluno a){
        dao.merge(a);
        dao.flush();
    }

    public static List<Aluno> getListaAllAlunos(){
        return dao.findAllByClassName(Aluno.class.getName());
    }

    public static List<Aluno> getListaAlunosPorSala(long salaid){
        return dao.findByAttributeName(Aluno.class.getName(),"salaid", String.valueOf(salaid));
    }

    //finish aluno



    public static List<Aula> getListaAllAulas(){
        return dao.findAllByClassName(Aula.class.getName());
    }

    public static Aula getAula(long id){
        return dao.findByEntityId(Aula.class,id);
    }

    public static Aula getAula(String data){
        List<Aula> l = dao.findByAttributeName(Aula.class.getName(),"data",data);
        if(l.size()>0){
            return l.get(0);
        }
        return null;
    }

    public static void addAula(Aula a){
        dao.persist(a);
        dao.flush();
    }

    public static void removeAula(Long id){
        Aula a = getAula(id);
        List<Presenca> lp = getPresencasNaData(a.getData());
        List<Falta> lf = getFaltasNaData(a.getData());
        if(a!=null){
            for (Presenca p : lp) {
                dao.remove(p);
            }
            for (Falta f : lf) {
                dao.remove(f);
            }
            dao.remove(a);
            dao.flush();
        }
    }

    public static void removeAula(String data){
        List<Presenca> lp = getPresencasNaData(data);
        List<Falta> lf = getFaltasNaData(data);
        Aula a = getAula(data);
        if(a!=null) {
            for (Presenca p : lp) {
                dao.remove(p);
            }
            for (Falta f : lf) {
                dao.remove(f);
            }
            dao.remove(a);
            dao.flush();
        }
    }


    //finish aula



    public static Falta getFalta(long id){
        return dao.findByEntityId(Falta.class,id);
    }

    public static List<Falta> getFaltasDoAluno(long alunoid){
        return dao.findByAttributeName(Falta.class.getName(),"alunoid",String.valueOf(alunoid));
    }

    public static List<Falta> getListaAllFaltas(){
        return dao.findAllByClassName(Falta.class.getName());
    }

    public static boolean existe(Falta f){
        List<Falta> l =getListaAllFaltas();
        for(Falta fa: l){
            if(fa.equals(f)){
                return true;
            }
        }
        return false;
    }

    public static void addFalta(Falta f){
        if(!existe(f)){
            dao.persist(f);
        }
    }

    public static void removeFalta(long id){
        Falta f = getFalta(id);
        if(f!=null){
            dao.remove(f);
            dao.flush();
        }
    }

    public static List<Falta> getFaltasNaData(String data){
        return dao.findByAttributeName(Falta.class.getName(),"data",data);
    }


    //finish faltas



    public static Presenca getPresenca(long id){
        return dao.findByEntityId(Presenca.class,id);
    }

    public static List<Presenca> getPresencaDoAluno(long alunoid){
        return dao.findByAttributeName(Presenca.class.getName(),"alunoid",String.valueOf(alunoid));
    }

    public static List<Presenca> getListaAllPresenca(){
        return dao.findAllByClassName(Presenca.class.getName());
    }

    public static boolean existePresenca(Presenca f){
        List<Presenca> l =getListaAllPresenca();
        for(Presenca fa: l){
            if(fa.equals(f)){
                return true;
            }
        }
        return false;
    }

    public static void addPresenca(Presenca f){
        if(!existePresenca(f)){
            dao.persist(f);
        }
    }

    public static void removePresenca(long id){
        Presenca f = getPresenca(id);
        if(f!=null){
            dao.remove(f);
            dao.flush();
        }
    }

    public static List<Presenca> getPresencasNaData(String data){
        return dao.findByAttributeName(Presenca.class.getName(),"data",data);
    }


    //finish presenças


    public static void addSala(String nome){
        Sala s = new Sala(nome);
        dao.persist(s);
        dao.flush();
    }

    public static Sala getSala(long id){
        return dao.findByEntityId(Sala.class,id);
    }

    public static Sala getSala(String nome){
        List<Sala> l = dao.findByAttributeName(Sala.class.getName(),"nome",nome);
        if(l.size()>0){
            return l.get(0);
        }
        return null;
    }

    public static void removeSala(long id){
        Sala s = getSala(id);
        if(s!=null){
            dao.remove(s);
            dao.flush();
        }
    }

    public static void removeSala(String nome){
        Sala s = getSala(nome);
        if(s!=null){
            dao.remove(s);
            dao.flush();
        }
    }

    public static List<Sala> getListaAllSalas(){
        return dao.findAllByClassName(Sala.class.getName());
    }


    //finish Sala



    public static Tarefa getTarefa(long id){
        return dao.findByEntityId(Tarefa.class,id);
    }

    public static List<Tarefa> getListaAllTarefas(){
        return dao.findAllByClassName(Tarefa.class.getName());
    }

    public static void addTarefa(Tarefa t){
        dao.persist(t);
        dao.flush();
    }

    public static void removeTarefa(long id){
        Tarefa t = getTarefa(id);
        if(t!=null){
            dao.remove(t);
            dao.flush();
        }
    }


    //finish tarefa



    public static int getTotalBD(){
        return 10000;
    }


    public static int getUsedBD(){
        int sum = getListaAllAlunos().size()+getListaAllAulas().size()+getListaAllFaltas().size()+getListaAllPresenca().size()
                +getListaAllSalas().size()+getListaAllTarefas().size();
        return sum;
    }

    public static int getPorcentagemPresencaAluno(long idaluno){
        int pa = getPresencaDoAluno(idaluno).size();
        int t = getListaAllAulas().size();
        if(t==0){
            return 0;
        }else{
            return ((pa*100)/t);
        }

    }

    public static String getToDay(){
        Date d = new Date();
        return d.toLocaleString();
    }

    private static double convertStringToDouble(String s){
        String ss = s.replaceAll("," , ".");
        double d = Double.parseDouble(ss);
        return d;
    }

    public static String getCaixaAtual(){
        List<Aula> l = getListaAllAulas();
        double sum=0;
        for(Aula al: l){
            double d = convertStringToDouble(al.getOferta());
            sum+=d;
        }
        double val = sum- getDescontos();
        String t = String.valueOf(val);
        if(t.length()>=5){
            String ini = t.substring(0,t.indexOf('.')+3);
            return ini;
        }
        return t;

    }

    public static double getDescontos(){
        List<Tarefa> l = getListaAllTarefas();
        double sum = 0;
        for(Tarefa t: l){
            double d = convertStringToDouble(t.getValor());
            sum+= d;
        }
        return sum;
    }

    public static List<Temp> getListaTempsPresentes(){
        List<Aula> la = getListaAllAulas();
        List<Temp> lt = new ArrayList<>();
        for(Aula aula: la){
            List<Presenca> lp = getPresencasNaData(aula.getData());
            Temp t = new Temp(aula.getId(),aula.getData(),lp.size());
            lt.add(t);
        }
        return lt;
    }

    public static List<Temp> getListaTempsFaltosos(){
        List<Aula> la = getListaAllAulas();
        List<Temp> lt = new ArrayList<>();
        for(Aula aula: la){
            List<Falta> lf = getFaltasNaData(aula.getData());
            Temp t = new Temp(aula.getId(),aula.getData(),lf.size());
            lt.add(t);
        }
        return lt;
    }


    public static int verificaSeAlunoPresenteNaAulaNaData(Aula a, Aluno aluno){
        List<Presenca> li = getPresencaDoAluno(aluno.getId());
        for(Presenca p: li){
            if(p.getData().equals(a.getData())){
                return 1;
            }
        }
        return 0;
    }







}
