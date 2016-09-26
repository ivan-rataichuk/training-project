package ua.nure.rataichuk.SummaryTask4.service;

import ua.nure.rataichuk.SummaryTask4.viewEntitys.Message;

public class Response {
	
	public Message checkAuthorization(Object o) {
		Message mes = new Message();
		
		if (o == null) {
			mes.setError(true);
			mes.setCause(ResponseMessages.AUTHORIZATION_ERROR);
		}
		
		return mes;
	}

}
