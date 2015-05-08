import controllers.SGDB;
import models.Sala;
import org.junit.After;
import org.junit.Before;
import play.GlobalSettings;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.test.FakeApplication;
import play.test.Helpers;
import scala.Option;

import javax.persistence.EntityManager;
import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 17/03/2015.
 */
public abstract class AbstractTest {

    public EntityManager em;

    @Before
    public void setUp(){
        FakeApplication app = Helpers.fakeApplication(new GlobalSettings());
        Helpers.start(app);
        Option<JPAPlugin> jpaPlugin = app.getWrappedApplication().plugin(JPAPlugin.class);
        em = jpaPlugin.get().em("default");
        JPA.bindForCurrentThread(em);
        em.getTransaction().begin();
        iniciaInstancias();
    }

    @After
    public void tearDown() {
        em.getTransaction().commit();
        JPA.bindForCurrentThread(null);
        em.close();
    }

    private void iniciaInstancias() {
        SGDB.addSala("Adultos");
        SGDB.addSala("Firmando Passos");
        SGDB.addSala("Jovens");
        SGDB.addSala("Kids");
        SGDB.addSala("Juniores");
        SGDB.addSala("Discipulado");
    }
}