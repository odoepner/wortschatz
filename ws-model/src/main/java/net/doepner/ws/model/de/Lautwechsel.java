package net.doepner.ws.model.de;

/**
 * TODO
 */
public class Lautwechsel {

    private final String original;
    private final String ersetzung;

    private char origChar, ersatzChar;

    public Lautwechsel(String original, String ersetzung) {
        this.original = original;
        origChar = original.length() == 1 ? original.charAt(0) : 0;

        this.ersetzung = ersetzung;
        ersatzChar = ersetzung.length() == 1 ? ersetzung.charAt(0) : 0;
    }

    public String modify(String source) {
        if (origChar != 0 && ersatzChar != 0) {
            return replaceChar(source);
        }

        final int i = source.lastIndexOf(original);
        final StringBuilder sb = new StringBuilder();
        sb.append(source.substring(0, i));
        sb.append(ersetzung);
        sb.append(source.substring(i + original.length(), source.length()));
        return sb.toString();
    }

    private String replaceChar(String source) {
        for (int i = source.length() - 1; i >= 0; i--) {
            if (source.charAt(i) == origChar) {
                final StringBuilder sb = new StringBuilder(source);
                sb.setCharAt(i, ersatzChar);
                return sb.toString();
            }
        }
        return source;
    }

    public String getOriginal() {
        return original;
    }

    public String getErsetzung() {
        return ersetzung;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Lautwechsel)) {
            return false;
        }

        final Lautwechsel lw = (Lautwechsel) obj;

        return original.equals(lw.getOriginal())
                && ersetzung.equals(lw.getErsetzung());
    }
}
