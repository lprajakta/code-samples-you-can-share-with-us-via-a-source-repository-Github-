package com.itrix.service;

import java.util.ArrayList;

import com.itrix.dao.TicketReplyDao;
import com.itrix.model.TicketReplyModel;

public class TicketReplyService {

	public boolean createTicketReplyService(TicketReplyModel ticketReplyModel) {
		// TODO Auto-generated method stub
		TicketReplyDao ticketReplyDao=new TicketReplyDao();
		return ticketReplyDao.createTicketReplyDao(ticketReplyModel);
	}
	public	ArrayList<TicketReplyModel>  getAllReplyService(int ticketId){
		ArrayList<TicketReplyModel> list=new ArrayList<>();
		TicketReplyDao ticketReplyDao=new TicketReplyDao();
		list=ticketReplyDao.getAllReplyDao(ticketId);
        return list;
		
	}
}
