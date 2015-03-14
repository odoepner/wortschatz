package net.doepner.ws.model.de;

import net.doepner.ws.model.Categories;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;

/**
 * Testet die Konjugation (komplexe Verbformen) 
 */
public class KonjugationTest {

    @Test
    public void testKomplexeKonjugation() throws Exception {
        assertEquals("Perfektbildung von sein mit sein",
                "bin gewesen",
                Konjugation.getVerbform(Hilfsverb.SEIN,
                        Categories.Tempus.PERFECT,
                        Categories.Numerus.SINGULAR,
                        Categories.Person.P1));

        assertEquals("Perfektbildung von haben mit haben",
                "habe gehabt",
                Konjugation.getVerbform(Hilfsverb.HABEN,
                        Categories.Tempus.PERFECT,
                        Categories.Numerus.SINGULAR,
                        Categories.Person.P1));
    }
}

