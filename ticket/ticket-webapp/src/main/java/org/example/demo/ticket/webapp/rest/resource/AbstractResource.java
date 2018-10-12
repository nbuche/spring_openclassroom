package org.example.demo.ticket.webapp.rest.resource;


import org.example.demo.ticket.business.impl.ManagerFactoryImpl;

public abstract class AbstractResource {

    private static ManagerFactoryImpl managerFactory;

    protected static ManagerFactoryImpl getManagerFactory() {
        return managerFactory;
    }
    public static void setManagerFactory(ManagerFactoryImpl pManagerFactory) {
        managerFactory = pManagerFactory;
    }
}
