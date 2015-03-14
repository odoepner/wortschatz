package net.doepner.ws.model.de;


/**
 * Default implementation einer Lautkette
 */
public final class LautketteImpl implements Lautkette {
    
    private final CharSequence cs;

    public LautketteImpl(final CharSequence cs) {
        this.cs = cs;
    }
    
    @Override
    public boolean endsWithVerschlussLaut() {
        switch (lastChar()) {
            case 't':
            case 'd':
            case 'g':
            case 'k':
            case 'p':
            case 'b': {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    @Override
    public boolean endsWithZischlaut() {
        switch (lastChar()) {
            case 'f':
            case 's':
            case 'x':
            case 'z':
            case 'w': {
                return true;
            }
            default: {
                return endsWith("sch") || endsWith("ch");
            }
        }
    }
    
    @Override
    public boolean endsWithSlaut() {
        switch (lastChar()) {
            case 's':
            case 'ß':
            case 'x':
            case 'z': {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    private boolean endsWith(String s) {
        if (cs.length() < s.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (lastNthChar(cs, i) != lastNthChar(s, i)) {
                return false;
            }
        }
        return true;
    }

    private int lastChar() {
        return (int) lastNthChar(cs, 1);
    }

    private static char lastNthChar(final CharSequence charSequence,
                                    final int i) {
        return charSequence.charAt(charSequence.length() - i);
    }

    @Override
    public String toString() {
        return cs.toString();
    }
}
