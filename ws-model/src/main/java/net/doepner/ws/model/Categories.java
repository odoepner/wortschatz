package net.doepner.ws.model;

/**
 * Language independent grammatical categories 
 */
public class Categories {
    
    public static enum Tempus {
        PRESENT, PAST, PERFECT, PAST_PERFECT, FUTURE_1, FUTURE_2 
    } 
    
    public enum Numerus {
        SINGULAR, PLURAL
    }
    
    public enum Person {
        P1, P2, P3
    }
}
