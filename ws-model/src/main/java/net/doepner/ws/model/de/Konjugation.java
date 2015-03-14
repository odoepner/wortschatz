/*
 * Konjugation
 *
 * This file is part of the project "The "wortschatz" program."
 * (c) 2006, 2007
 * Oliver Doepner
 */

package net.doepner.ws.model.de;

import net.doepner.ws.model.Categories.*;
import net.doepner.ws.model.Verb;

/**
 * TODO
 */
public class Konjugation {
    
    public static String getVerbform(Verb v, Tempus t, Numerus n, Person p) {
        final Hilfsverb futureAuxiliary = Hilfsverb.WERDEN;

        switch (t) {
            case PRESENT: {
                return v.getSimplePresent(n, p);
            }
            case PAST: {
                return v.getSimplePast(n, p);
            }
            case PERFECT: {
                final StringBuilder sb = new StringBuilder();
                sb.append(v.getAuxiliaryVerb().getSimplePresent(n, p));
                sb.append(' ');
                sb.append(v.getPastParticiple());
                return sb.toString();
            }
            case PAST_PERFECT: { 
                final StringBuilder sb = new StringBuilder();
                sb.append(v.getAuxiliaryVerb().getSimplePast(n, p));
                sb.append(' ');
                sb.append(v.getPastParticiple());
                return sb.toString();
            }
            case FUTURE_1: {
                final StringBuilder sb = new StringBuilder();
                sb.append(futureAuxiliary.getSimplePresent(n, p));
                sb.append(' ');
                sb.append(v.getBaseform());
                return sb.toString();
            }
            case FUTURE_2: {
                final StringBuilder sb = new StringBuilder();
                sb.append(futureAuxiliary.getSimplePresent(n, p));
                sb.append(' ');
                sb.append(v.getPastParticiple());
                sb.append(' ');
                sb.append(v.getAuxiliaryVerb().getBaseform());
                return sb.toString();
            }
        }
        throw new IllegalStateException("unknown Tempus");
    }
}
