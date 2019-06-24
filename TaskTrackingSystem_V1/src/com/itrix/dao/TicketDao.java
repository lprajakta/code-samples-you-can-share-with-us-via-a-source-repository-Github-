package com.itrix.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.itrix.utility.DbUtil;

import com.itrix.model.TicketModel;

public class TicketDao {

	public boolean createTicketDao(TicketModel ticketModel) {
		boolean result = false;
		PreparedStatement pst = null;
		int res = 0;
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			String query = "insert into ticketmast values(?,?,?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(query);
			pst.setInt(1, 0);
			pst.setString(2, ticketModel.getSubject());
			pst.setString(3, ticketModel.getMessage());
			char priority = ticketModel.getPriority();
			if (priority == 'H') {
				pst.setString(4, "H");
			}
			if (priority == 'M') {
				pst.setString(4, "M");
			}
			if (priority == 'L') {
				pst.setString(4, "L");
			}
			pst.setString(5, "O");
			java.sql.Date date = new Date(new java.util.Date().getTime());
			pst.setDate(6,date );
			pst.setInt(7, ticketModel.getFirmID());
			pst.setInt(8, ticketModel.getProjectId());
			pst.setInt(9, ticketModel.getCatId());
			pst.setInt(10, ticketModel.getTaskId());
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

	public ArrayList<TicketModel> getAllTicketsDao() {
		// TODO Auto-generated method stub
		ArrayList<TicketModel> list = new ArrayList<>();
		PreparedStatement pst = null;
		Connection con = null;
		String query = "";
		ResultSet rs = null;
		try {
			con = DbUtil.getConnection();
			query = "select  tm.ticketid,tm.subject,tm.message,tm.priority,tm.status,tm.createddate,"
					+ " firmmast.firmname,projectmast.projname,categorymast.name,taskmast.name from ticketmast as tm "
					+ " inner join firmmast on tm.firmID=firmmast.firmid "
					+ " inner join projectmast on tm.projectId=projectmast.projid "
					+ " inner join  taskmast on tm.taskId=taskmast.taskid"
					+ " inner join categorymast on tm.catId=categorymast.catgid";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				TicketModel ticketModel = new TicketModel();
				ticketModel.setId(rs.getInt("tm.ticketid"));
				ticketModel.setSubject(rs.getString("tm.subject"));
				ticketModel.setMessage(rs.getString("tm.message"));

				String priority = rs.getString("tm.priority");
				if (priority.equals("H")) {
					ticketModel.setPriorityStr("High");
				} else if (priority.equals("M")) {
					ticketModel.setPriorityStr("Mediaum");
				} else {
					ticketModel.setPriorityStr("Low");
				}

				String status = rs.getString("tm.status");
				if (status.equals("O")) {
					ticketModel.setStatusStr("Open");
				} else if (priority.equals("S")) {
					ticketModel.setStatusStr("Suspend");
				} else {
					ticketModel.setStatusStr("Close");
				}
				
				ticketModel.setCreatedDate(rs.getString("tm.createddate"));
				ticketModel.setFirmName(rs.getString("firmmast.firmname"));
				ticketModel.setCatName(rs.getString("categorymast.name"));
				ticketModel.setTaskName(rs.getString("taskmast.name"));
				ticketModel.setProjectName(rs.getString("projectmast.projname"));
				
				
				list.add(ticketModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean deleteTicketDao(int id) {
		boolean result = false;
		PreparedStatement pst = null;
		int res = 0;
		Connection con = null;
		String query = "";
		try {
			con = DbUtil.getConnection();
			query = "delete from ticketmast where ticketid=" + id;
			pst = con.prepareStatement(query);
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

	public ArrayList<TicketModel> editTicketDao(int id) {
		ArrayList<TicketModel> list = new ArrayList<>();
		PreparedStatement pst = null;
		Connection con = null;
		String query = "";
		ResultSet rs = null;
		try {
			con = DbUtil.getConnection();
			query = "select * from ticketmast where ticketid=" + id;
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				TicketModel ticketModel = new TicketModel();
				ticketModel.setId(rs.getInt("ticketid"));
				ticketModel.setSubject(rs.getString("subject"));
				ticketModel.setMessage(rs.getString("message"));
				
				String priority = rs.getString("priority");
				if (priority.equals("H")) {
					ticketModel.setPriority('H');
				} else if (priority.equals("M")) {
					ticketModel.setPriority('M');
				} else {
					ticketModel.setPriority('L');
				}

				String status = rs.getString("status");
				if (status.equals("O")) {
					ticketModel.setStatus('O');
				} else if (priority.equals("S")) {
					ticketModel.setStatus('S');
				} else {
					ticketModel.setStatus('C');
				}
				
				ticketModel.setCreatedDate(rs.getString("createddate"));
				list.add(ticketModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean updateTicketDao(TicketModel ticketModel) {
		boolean result = false;
		PreparedStatement pst = null;
		int res = 0;
		Connection con = null;
		try {
			con = DbUtil.getConnection();
			String query = "update ticketmast set subject=?,message=?,priority=?,status=? where ticketid="+ticketModel.getId();
			pst = con.prepareStatement(query);
			pst.setString(1, ticketModel.getSubject());
			pst.setString(2, ticketModel.getMessage());
			char priority = ticketModel.getPriority();
			if (priority=='H') {
				pst.setString(3, "H");
			}
			else if (priority == 'M') {
				pst.setString(3, "M");
			}
			else {
				pst.setString(3, "L");
			}
			char status = ticketModel.getStatus();
			if (status == 'O') {
				pst.setString(4, "O");
			}
			else if (status == 'S') {
				pst.setString(4, "S");
			}
			else {
				pst.setString(4, "C");
			}
			
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

}
