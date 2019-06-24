package com.itrix.service;

import java.util.ArrayList;

import com.itrix.dao.TicketDao;
import com.itrix.model.TicketModel;

public class TicketService {

	public boolean createTicketService(TicketModel ticketModel) {
		// TODO Auto-generated method stub
		boolean res = false;
		try {
			TicketDao ticketDao = new TicketDao();

			res = ticketDao.createTicketDao(ticketModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<TicketModel> getAllTicketService() {
		ArrayList<TicketModel> list = new ArrayList<>();
		try {
			TicketDao ticketDao = new TicketDao();

			list = ticketDao.getAllTicketsDao();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean deleteTicketSrvice(int id) {
		boolean result = false;
		TicketDao ticketDao = new TicketDao();
		result = ticketDao.deleteTicketDao(id);
		return result;
	}

	public ArrayList<TicketModel> editTicketSrvice(int id) {
		ArrayList<TicketModel> list = new ArrayList<>();
		try {
			TicketDao ticketDao = new TicketDao();

			list = ticketDao.editTicketDao(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean updateTicketService(TicketModel ticketModel) {
		// TODO Auto-generated method stub
		boolean res = false;
		try {
			TicketDao ticketDao = new TicketDao();

			res = ticketDao.updateTicketDao(ticketModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
