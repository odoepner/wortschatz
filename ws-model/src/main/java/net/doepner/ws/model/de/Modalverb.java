package net.doepner.ws.model.de;

/**
 * TODO
 */
public class Modalverb extends DeutschesVerb {

    public Modalverb(String infinitiv,
                     String praeteritumStamm, 
                     String partizipPerfekt,
                     String praesensSingularStamm) {
        super(infinitiv, praeteritumStamm, partizipPerfekt);
        setPraesensSingularStamm(praesensSingularStamm);
    }
}
