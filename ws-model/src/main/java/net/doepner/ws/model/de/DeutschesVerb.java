package net.doepner.ws.model.de;

import net.doepner.ws.model.Verb;
import net.doepner.ws.model.Word;
import net.doepner.ws.model.Categories.*;

/**
 * Deutsches Verb mit kompletter Konjugation
 */
public class DeutschesVerb implements Verb {

    private static final String[][][] ENDUNG = {
        { { "e", "st", "t" }, { "en", "t", "en" } },
        { {  "", "st",  "" }, {  "n", "t",  "n" } }
    };

    private final String[][] praesensForm =
        new String[Numerus.values().length][Person.values().length];
    
    private final String[][] praeteritumForm = 
        new String[Numerus.values().length][Person.values().length];

    private final String infinitiv;
    private final String praesensStamm;
    private final String praeteritumStamm;
    private final String partizipPerfekt;

    private String praesensSingularStamm;
    private boolean hilfsverbSein;
    
    private Lautwechsel lautwechsel;

    public DeutschesVerb(final String infinitiv,
                         final String praeteritumStamm,
                         final String partizipPerfekt) {
        this(infinitiv, praeteritumStamm, partizipPerfekt, false);
    }
    
    public DeutschesVerb(final String infinitiv,
                         final String praeteritumStamm,
                         final String partizipPerfekt,
                         final boolean hilfsverbSein) {
        this.infinitiv = infinitiv;
        this.praesensStamm = getPraesensStamm(infinitiv);
        this.praeteritumStamm = praeteritumStamm;
        this.partizipPerfekt = partizipPerfekt;
        this.hilfsverbSein = hilfsverbSein;
    }    
   
    @Override
    public String getBaseform() {
        return infinitiv;
    }
    
    @Override
    public Verb getAuxiliaryVerb() {
        return hilfsverbSein ? Hilfsverb.SEIN : Hilfsverb.HABEN;
    }

    @Override
    public String getPastParticiple() {
        return partizipPerfekt;
    }
    
    @Override
    public String getSimplePresent(Numerus n, Person p) {
        if (praesensForm[n.ordinal()][p.ordinal()] == null) {
            praesensForm[n.ordinal()][p.ordinal()] = 
                getPraesensForm(n, p);
        }
        return praesensForm[n.ordinal()][p.ordinal()];
    }
    
    @Override
    public String getSimplePast(Numerus n, Person p) {
        if (praeteritumForm[n.ordinal()][p.ordinal()] == null) {
            praeteritumForm[n.ordinal()][p.ordinal()] = 
                getPraeteritumForm(n, p);
        }
        return praeteritumForm[n.ordinal()][p.ordinal()];
    }
    
    void setPraesensForm(Numerus n, Person p, String form) {
        praesensForm[n.ordinal()][p.ordinal()] = form;
    }
    
    private String getPraesensForm(Numerus n, Person p) {
        if (n == Numerus.PLURAL && p != Person.P2) {
            return getInfinitiv();
        }
        
        final boolean modalverbKonjugation =
            n == Numerus.SINGULAR && praesensSingularStamm != null;

        final String stamm = modalverbKonjugation ? praesensSingularStamm
                                                  : praesensStamm;

        final StringBuilder sb = new StringBuilder();

        if (lautwechsel != null && n == Numerus.SINGULAR && p != Person.P1) {
            sb.append(lautwechsel.modify(stamm));
            
        } else {
            sb.append(stamm);

            final boolean eErweiterungFall = (n == Numerus.SINGULAR)
                    ? p != Person.P1
                    : p == Person.P2;

            if (eErweiterungNoetig(stamm) && eErweiterungFall) {
                sb.append('e');
            }
        }
        
        if (!(p == Person.P3 && lastChar(sb) == 't')) {
            final String endung = modalverbKonjugation
                                  ? getEndung(Tempus.PAST, n, p)
                                  : getEndung(Tempus.PRESENT, n, p);
                                  
            final Lautkette lautkette = new LautketteImpl(sb);

            if ("st".equals(endung) && lautkette.endsWithSlaut()) {
                sb.append("t");
            } else {
                sb.append(endung);
            }
        }
        
        return sb.toString();
    }
    
    private String getPraeteritumForm(Numerus n, Person p) {
        final String stamm = praeteritumStamm;

        final StringBuilder sb = new StringBuilder(stamm);

        final boolean eRequired = (p == Person.P2)
                ? eErweiterungNoetig(stamm)
                : !stamm.endsWith("e");

        if (n == Numerus.PLURAL && eRequired) {
            sb.append('e');
        }

        sb.append(getEndung(Tempus.PAST, n, p));

        return sb.toString();
    }
    
    private static String getEndung(Tempus t, Numerus n, Person p) {
        return ENDUNG[t.ordinal()][n.ordinal()][p.ordinal()];
    }

    private static String getPraesensStamm(String infinitiv) {
        if (infinitiv.endsWith("en")) {
            return removeLastChars(infinitiv, 2);
        } 
        if (infinitiv.endsWith("n")) {
            return removeLastChars(infinitiv, 1);
        } 
        throw new IllegalArgumentException("infinitiv must end with 'n'");
    }
    
    private static boolean eErweiterungNoetig(String stamm) {
        switch (lastChar(stamm)) {
            case 't':
            case 'd': {
                return true;
            }
            case 'n':
            case 'm': {
                final Lautkette s = new LautketteImpl(removeLastChars(stamm, 1));
                return s.endsWithVerschlussLaut() || s.endsWithZischlaut();
            }
            default: {
                return false;
            }
        }
    }

    public String getInfinitiv() {
        return infinitiv;
    }

    public String getPraeteritumStamm() {
        return praeteritumStamm;
    }

    public String getPartizipPerfekt() {
        return partizipPerfekt;
    }

    public Lautwechsel getLautwechsel() {
        return lautwechsel;
    }

    public boolean isHilfsverbSein() {
        return hilfsverbSein;
    }

    public String getPraesensSingularStamm() {
        return praesensSingularStamm;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        
        if (!(obj instanceof DeutschesVerb)) {
            return false;
        }
        
        final DeutschesVerb verb = (DeutschesVerb) obj;
               
        return praesensStamm.equals(verb.praesensStamm)
                && praeteritumStamm.equals(verb.getPraeteritumStamm())
                && partizipPerfekt.equals(verb.getPartizipPerfekt())
                && hilfsverbSein == verb.isHilfsverbSein()
                && lautwechsel == null
                   ? verb.getLautwechsel() == null
                   : lautwechsel.equals(verb.getLautwechsel());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("praesensStamm: ").append(praesensStamm).append(", ");
        sb.append("praeteritumStamm: ").append(praeteritumStamm).append(", ");
        sb.append("partizipPerfekt: ").append(partizipPerfekt).append(", ");
        sb.append("hilfsverbSein: ").append(hilfsverbSein);
        return sb.toString();
    }

    private static char lastChar(CharSequence s) {
        return s.charAt(s.length() - 1);
    }

    private static String removeLastChars(String s, int n) {
        return s.substring(0, s.length() - n);
    }

    public void setLautwechsel(Lautwechsel lautwechsel) {
        this.lautwechsel = lautwechsel;
    }

    public void setPraesensSingularStamm(String s) {
        praesensSingularStamm = s;
    }

    public void setHilfsverbSein(boolean b) {
        hilfsverbSein = b;
    }
}
