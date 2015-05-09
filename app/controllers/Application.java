package controllers;

import models.*;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
    @Transactional
    public static Result login(){
        return ok(login.render());
    }
    @Transactional
    public static Result newSession(){
        session().put("user", "admin");
        return renderDashboard();
    }
    @Transactional
    public static Result index() {
        String u = session().get("user");
        if(u==null){
            return login();
        }
        return renderDashboard();
    }
    @Transactional
    public static Result renderDashboard(){
        return ok(dashboard.render(1, SGDB.getListaAllTarefas(), SGDB.getListaAllAulas()));
    }
    @Transactional
    public static Result renderSala(String sala){
        if(sala.equals("Firmando")){
            sala = "Firmando Passos";
        }

        Sala s = SGDB.getSala(sala);

        return ok(views.html.sala.render(getInt(s.getNome()),s,SGDB.getListaAlunosPorSala(s.getId())));
    }

    private static int getInt(String nome){
        if(nome.equals("Adultos")){
            return 2;
        }else if(nome.equals("Jovens")){
            return 3;
        }else if(nome.equals("Discipulado")){
            return 4;
        }else if(nome.equals("Kids")){
            return 5;
        }else if(nome.equals("Juniores")){
            return 6;
        }else if(nome.equals("Firmando Passos")){
            return 7;
        }else{
            return 2;
        }
    }
    @Transactional
    public static Result renderAddTarefa(){
        return ok(addtarefa.render(10));
    }

    @Transactional
    public static Result addTarefa(){
        DynamicForm r = Form.form().bindFromRequest();
        String data = r.get("datahoje");
        String datafinal = r.get("datafinal");
        String comentario = r.get("comentario");
        String valor = r.get("valor");
        Tarefa t = new Tarefa(data,datafinal,comentario,valor);
        SGDB.addTarefa(t);
        return renderDashboard();
    }

    @Transactional
    public static Result removeTarefa(long id){
        SGDB.removeTarefa(id);
        return renderDashboard();
    }

    @Transactional
    public static Result addAula(){
        DynamicForm r = Form.form().bindFromRequest();
        String data = r.get("data");
        String oferta = r.get("oferta");

        for(Aluno a: SGDB.getListaAllAlunos()){
            if(r.get(String.valueOf(a.getId()))!=null){
                Logger.info("Value "+a.getNome()+" :" + r.get(String.valueOf(a.getId())));
                Presenca p = new Presenca(data, a.getId());
                SGDB.addPresenca(p);
            }else{
                Logger.info("Value "+a.getNome()+" :" + r.get(String.valueOf(a.getId())));
                Falta f = new Falta(data,a.getId());
                SGDB.addFalta(f);
            }
        }
        Aula a = new Aula(data,oferta,null);
        SGDB.addAula(a);
        return renderDashboard();
    }
    @Transactional
    public static Result renderAddAula(){
        return ok(addaula.render(9));
    }

    @Transactional
    public static Result renderAddAluno(){
        return ok(addaluno.render(8));
    }

    @Transactional
    public static Result addAluno(){
        DynamicForm r = Form.form().bindFromRequest();
        String nome = r.get("nome");
        String idade = r.get("idade");
        String end = r.get("endereco");
        String tel = r.get("telefone");
        long salaid = Long.parseLong(r.get("salaid"));
        Aluno a = new Aluno(nome,null,idade,null,end,tel,salaid);
        SGDB.addAluno(a);
        return renderDashboard();
    }

    @Transactional
    public static Result removerAluno(long id){
        SGDB.removeAluno(id);
        return renderDashboard();
    }

    @Transactional
    public static Result renderAlterarAluno(long id){
        return ok(editaraluno.render(1,SGDB.getAluno(id)));
    }

    @Transactional
    public static Result altAluno(){
        DynamicForm r = Form.form().bindFromRequest();
        long id = Long.parseLong(r.get("alunoid"));
        Aluno a = SGDB.getAluno(id);
        a.setEndereco(r.get("endereco"));
        a.setIdade(r.get("idade"));
        a.setNome(r.get("nome"));
        a.setSalaid(Long.parseLong(r.get("salaid")));
        a.setTelefone(r.get("telefone"));
        SGDB.alterarAluno(a);
        return renderDashboard();
    }



    @Transactional
    public static Result renderlistarAulas(){
        return ok(listaraulas.render(11));
    }

    @Transactional
    public static Result removeAula(long id){
        return ok();
    }

    @Transactional
    public static Result verAula(long id){
        Aula aula = SGDB.getAula(id);
        return ok(veraula.render(11,aula));
    }

    @Transactional
    public static Result searchAula(){
        DynamicForm r = Form.form().bindFromRequest();
        String data = r.get("data");
        Aula aula = SGDB.getAula(data);
        if(aula != null){
            return verAula(aula.getId());
        }
        return renderlistarAulas();
    }

}
