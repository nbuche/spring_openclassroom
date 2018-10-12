package org.example.demo.ticket.batch;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.demo.ticket.business.contract.manager.TicketManager;
import org.example.demo.ticket.business.impl.ManagerFactoryImpl;
import org.example.demo.ticket.model.exception.TechnicalException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Classe Principale de lancement des Batches.
 *
 * @author lgu
 */
public class Main {

    /** Logger pour la classe */
    private static final Log LOGGER = LogFactory.getLog(Main.class);

    /**
     * The entry point of application.
     *
     * @param pArgs the input arguments
     * @throws TechnicalException sur erreur technique
     */
    public static void main(String[] pArgs) throws TechnicalException {
    	
    	ApplicationContext vApplicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    	
    	ManagerFactoryImpl vManagerFactory = vApplicationContext.getBean("managerFactory", ManagerFactoryImpl.class);
    	
    	String exportUrl = vApplicationContext.getEnvironment().getProperty("export.url");
        try {
            if (pArgs.length < 1) {
                throw new TechnicalException("Veuillez préciser le traitement à effectuer !");
            }

            String vTraitementId = pArgs[0];
            if ("ExportTicketStatus".equals(vTraitementId)) {
                LOGGER.info("Execution du traitement : ExportTicketStatus");
                TicketManager vTicketManager = vManagerFactory.getTicketManager();
                
                vTicketManager.exportTicketStatut(exportUrl);
                LOGGER.info("Fin du traitement : ExportTicketStatus");
                
            } else {
                throw new TechnicalException("Traitement inconnu : " + vTraitementId);
            }
        } catch (Throwable vThrowable) {
            LOGGER.error(vThrowable);
            System.exit(1);
        }
    }
}
