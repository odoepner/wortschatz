package net.doepner.ws.model.de;

import net.doepner.ws.model.Categories.Numerus;
import net.doepner.ws.model.Categories.Person;


/**
 * TODO
 */
public abstract class Hilfsverb extends DeutschesVerb {

    public Hilfsverb(String infinitiv,
                     String praeteritumStamm,
                     String partizipPerfekt) {
        this(infinitiv, praeteritumStamm, partizipPerfekt, false);
    }

    public Hilfsverb(String infinitiv,
                     String praeteritumStamm,
                     String partizipPerfekt,
                     boolean hilfsverbSein) {
        super(infinitiv, praeteritumStamm, partizipPerfekt, hilfsverbSein);
        init();
    }

    protected abstract void init();

    public static final Hilfsverb HABEN =
            new Hilfsverb("haben", "hatte", "gehabt") {
                @Override
                protected void init() {
                    setPraesensForm(Numerus.SINGULAR , Person.P2, "hast");
                    setPraesensForm(Numerus.SINGULAR , Person.P3, "hat");
                }
            };

    public static final Hilfsverb WERDEN =
            new Hilfsverb("werden", "wurde", "geworden", true) {
                @Override
                protected void init() {
                    setPraesensForm(Numerus.SINGULAR , Person.P2, "wirst");
                    setPraesensForm(Numerus.SINGULAR , Person.P3, "wird");
                }
            };

    public static final Hilfsverb SEIN =
            new Hilfsverb("sein", "war", "gewesen", true) {
                @Override
                protected void init() {
                    setPraesensForm(Numerus.SINGULAR , Person.P1, "bin");
                    setPraesensForm(Numerus.SINGULAR , Person.P2, "bist");
                    setPraesensForm(Numerus.SINGULAR , Person.P3, "ist");
                    setPraesensForm(Numerus.PLURAL , Person.P1, "sind");
                    setPraesensForm(Numerus.PLURAL , Person.P2, "seid");
                    setPraesensForm(Numerus.PLURAL , Person.P3, "sind");
                }
            };
}
