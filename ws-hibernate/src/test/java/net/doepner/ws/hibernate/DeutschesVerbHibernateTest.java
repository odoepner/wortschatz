package net.doepner.ws.hibernate;

import net.doepner.ws.model.de.DeutschesVerb;
import net.doepner.ws.model.de.Lautwechsel;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;

/**
 * TODO
 */
public class DeutschesVerbHibernateTest {

    @Test
    public void testHibernate() throws Exception {
        final SessionFactory sessionFactory = new HibSessionFactoryHolder().getSessionFactory();

        final DeutschesVerb verb =
            new DeutschesVerb("laufen", "lief", "gelaufen");
        verb.setLautwechsel(new Lautwechsel("a", "ä"));
        verb.setHilfsverbSein(true);

        final Long id = saveDeutschesVerb(sessionFactory, verb);
        final DeutschesVerb verb2 = getDeutschesVerb(sessionFactory, id);

        assertEquals("same verb", verb, verb2);
    }

    private Long saveDeutschesVerb(SessionFactory sessionFactory,
                                   DeutschesVerb verb) {
        final Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(verb.getLautwechsel());
        final Long result = (Long) session.save(verb);

        tx.commit();
        session.close();

        return result;
    }

    private DeutschesVerb getDeutschesVerb(SessionFactory sessionFactory,
                                           Long id) {
        final Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        final DeutschesVerb result =
            (DeutschesVerb) session.get(DeutschesVerb.class, id);
        result.getLautwechsel();

        tx.commit();
        session.close();

        return result;
    }

}
