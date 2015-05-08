import controllers.SGDB;
import models.Aluno;
import models.GenericDAO;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;

import java.util.List;

/**
 * Created by caio on 24/03/15.
 */

public class Global extends GlobalSettings {

    private static GenericDAO dao = new GenericDAO();

    @Override
    public void onStart(Application app) {
        Logger.info("inicializada...");

        JPA.withTransaction(new play.libs.F.Callback0() {

            public void invoke() throws Throwable {


                if (SGDB.getListaAllSalas().size() == 0) {
                    SGDB.addSala("Adultos");
                    SGDB.addSala("Jovens");
                    SGDB.addSala("Discipulado");
                    SGDB.addSala("Kids");
                    SGDB.addSala("Juniores");
                    SGDB.addSala("Firmando Passos");

                    dao.flush();
                    Logger.info("Inserindo dados no BD.");
                }
            }
        });
    }

    public void onStop(Application app) {
        Logger.info("desligada...");
    }

}