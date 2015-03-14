package net.doepner.ws.model;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * TODO
 */
public class Word {

    private final String text;
    private final String ipa;
    
    private final Locale locale;
    
    private final PartOfSpeech partOfSpeech;
    private final Set<Label> labelSet = new HashSet<Label>();

    public Word(String text, String ipa,
                String languageCode,
                String countryCode,
                PartOfSpeech partOfSpeech) {
        this.text = text;
        this.ipa = ipa;
        this.partOfSpeech = partOfSpeech;

        locale = countryCode == null
                ? new Locale(languageCode)
                : new Locale(languageCode, countryCode);
    }

    public Locale getLocale() {
        return locale;
    }

    public String getText() {
        return text;
    }

    public String getIpa() {
        return ipa;
    }

    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }

    public Set<Label> getLabelSet() {
        return labelSet;
    }
}
