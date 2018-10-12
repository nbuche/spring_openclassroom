package org.example.demo.ticket.webapp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.example.demo.ticket.business.impl.ManagerFactoryImpl;
import org.example.demo.ticket.business.impl.manager.ProjetManagerImpl;
import org.example.demo.ticket.business.impl.manager.TicketManagerImpl;
import org.example.demo.ticket.webapp.rest.resource.AbstractResource;


public class DependencyInjectionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent pServletContextEvent) {
        ManagerFactoryImpl vManagerFactory = new ManagerFactoryImpl();
        // On ajoute l'injection de l'implémentation des Managers dans la ManagerFactory
        vManagerFactory.setProjetManager(new ProjetManagerImpl());
        vManagerFactory.setTicketManager(new TicketManagerImpl());
        //...
        AbstractResource.setManagerFactory(vManagerFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent pServletContextEvent) {
        // NOP
    }
}