package com.itrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.itrix.bean.OrgBranchCnctBean;

public class GetBranchInfoDao {
	public ArrayList<OrgBranchCnctBean> getBranchInfoDao(final int userId)
	{
		ArrayList<OrgBranchCnctBean> serviceBeanList=new ArrayList<OrgBranchCnctBean>();
		ResourceBundle dbTables= ResourceBundle.getBundle("dbTables");
		ResourceBundle branchMast=ResourceBundle.getBundle("OrgBranchMast"); 
		
		String branchAddressMast=dbTables.getString("orgBranchMast");
		String branchIdStr=branchMast.getString("branch_id");
		String userIdStr=branchMast.getString("user_id");
		String branchName=branchMast.getString("branchName");
		String addr=branchMast.getString("address");
		String headName=branchMast.getString("headName");
		String contact=branchMast.getString("contact");
		String email=branchMast.getString("email");
		
		Connection con=null;
		PreparedStatement pst=null;
		String sql="";
		ResultSet rs=null;
		try{
			con=DBConnection.getConnection();
			sql="Select * From "+branchAddressMast+" where "+userIdStr+"="+userId;
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				OrgBranchCnctBean branchBean=new OrgBranchCnctBean();
				branchBean.setBranchId(rs.getInt(branchIdStr));
				branchBean.setBranchName(rs.getString(branchName));
				branchBean.setBranchAddr(rs.getString(addr));
				branchBean.setHeadName(rs.getString(headName));
				branchBean.setContact(rs.getString(contact));
				branchBean.setEmail(rs.getString(email));
				serviceBeanList.add(branchBean);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(con!=null){
					pst.close();
					rs.close();
					con.close();
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return serviceBeanList;
	}

}
