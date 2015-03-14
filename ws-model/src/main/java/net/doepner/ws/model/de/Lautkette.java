package net.doepner.ws.model.de;

/**
 * Eigenschaften einer deutschen Lautkette
 */
public interface Lautkette {

    boolean endsWithVerschlussLaut();

    boolean endsWithZischlaut();

    boolean endsWithSlaut();
}
