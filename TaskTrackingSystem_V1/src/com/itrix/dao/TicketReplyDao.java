package com.itrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.itrix.utility.DbUtil;
import com.itrix.model.TicketModel;
import com.itrix.model.TicketReplyModel;

public class TicketReplyDao {

	public boolean createTicketReplyDao(TicketReplyModel ticketReplyModel) {
		boolean result = false;
		PreparedStatement pst = null;
		int res = 0;
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			String query = "insert into ticketdetails values(?,?,?,?,?)";
			pst = con.prepareStatement(query);
			pst.setInt(1, 0);
			pst.setInt(2, ticketReplyModel.getTicketId());
			pst.setString(3, ticketReplyModel.getReply());
			pst.setString(4, ticketReplyModel.getReplyBy());
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			pst.setTimestamp(5,date );
			res = pst.executeUpdate();
			if (res != 0) {
				result = true;
			} else {
				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<TicketReplyModel> getAllReplyDao(int ticketId) {
		ArrayList<TicketReplyModel> list = new ArrayList<TicketReplyModel>();
		PreparedStatement pst = null;
		Connection con = null;
		String query = "";
		ResultSet rs = null;
		try {
			con = DbUtil.getConnection();
			query = "select * from ticketdetails where ticketid=" + ticketId;
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				TicketReplyModel ticketModel = new TicketReplyModel();
				ticketModel.setReplyId(rs.getInt("id"));
				ticketModel.setReply(rs.getString("replymessage"));
				ticketModel.setReplyBy(rs.getString("replayby"));
				ticketModel.setReplyDate(rs.getString("replaydate"));
				list.add(ticketModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
