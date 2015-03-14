/*
 * Verb
 *
 * This file is part of the project "The "wortschatz" program."
 * (c) 2006, 2007
 * Oliver Doepner
 */

package net.doepner.ws.model;

import net.doepner.ws.model.Categories.Numerus;
import net.doepner.ws.model.Categories.Person;

/**
 * TODO
 */
public interface Verb {
    
    String getBaseform();

    String getSimplePresent(Numerus numerus, Person person);

    String getSimplePast(Numerus numerus, Person person);
    
    String getPastParticiple();
    
    Verb getAuxiliaryVerb();
}
