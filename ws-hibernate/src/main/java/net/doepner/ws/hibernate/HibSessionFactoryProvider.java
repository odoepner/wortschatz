package net.doepner.ws.hibernate;

import org.hibernate.SessionFactory;

/**
 * TODO
 */
public interface HibSessionFactoryProvider {

    SessionFactory getSessionFactory();
}
