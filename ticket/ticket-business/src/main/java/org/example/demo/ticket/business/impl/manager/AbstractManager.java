package org.example.demo.ticket.business.impl.manager;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.demo.ticket.consumer.contract.DaoFactory;
import org.springframework.transaction.PlatformTransactionManager;


public abstract class AbstractManager {
	
	@Inject
	@Named("txManagerTicket")
	private PlatformTransactionManager platformTransactionManager;
	protected PlatformTransactionManager getPlatformTransactionManager() {
		return platformTransactionManager;
	}

	@Inject
	private DaoFactory daoFactory;
	protected DaoFactory getDaoFactory() {
		return daoFactory;
	}
	
	

}
