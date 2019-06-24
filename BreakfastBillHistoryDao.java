/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itrix.dao;

import com.itrix.bean.BreakfastBillHistoryBean;
import com.itrix.database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author itrix_wk1
 */
public class BreakfastBillHistoryDao {

    public ArrayList getBfMemberNameInCBDao() {
        Connection con=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ArrayList bfMemberList=new ArrayList();
        try{
            con= DbConnection.getConnection();
            String sql ="SELECT  DISTINCT customer_name FROM imess_db1.current_bill_mast where cust_type='B/F Member' ";
            pstmt=con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                bfMemberList.add(rs.getString(1));
            }            
        }catch (SQLException ex){
            ex.printStackTrace();
            Logger.getLogger(MemberBillDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally{
            try{
                rs.close();
                pstmt.close();
                con.close();
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return bfMemberList;
    }

    public BreakfastBillHistoryBean getbfMemCurrentBal(String bfMemberName) {
         Connection con=null;
       PreparedStatement pst=null;
       ResultSet rs=null;
       Double bfMemCurrentBal=null;
      // MemberBillBean reglist =new MemberBillBean();
        BreakfastBillHistoryBean breakfastBillHistoryBean= new BreakfastBillHistoryBean();
       try
       {
            con=DbConnection.getConnection();
          //  String sql="select mem_cur_amt where bfMemName='"+bfMemberName+"'";
             String sql = "SELECT mid\n"
                   + "FROM current_bill_mast\n"
                   + "WHERE customer_name ='"+bfMemberName+"' and cust_type='B/F Member' ";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next())
            {
               
              breakfastBillHistoryBean.setId(rs.getInt("mid"));
             return breakfastBillHistoryBean;
            }
       }
       catch(Exception e)
       {
           e.getMessage();
       }
         return breakfastBillHistoryBean;
    }

    public ArrayList getMemberNameInCBDao() {
        Connection con=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ArrayList bfMemberList=new ArrayList();
        try{
            con= DbConnection.getConnection();
            String sql ="SELECT DISTINCT customer_name FROM imess_db1.current_bill_mast where cust_type='Member' ";
            pstmt=con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                bfMemberList.add(rs.getString(1));
            }            
        }catch (SQLException ex){
            ex.printStackTrace();
            Logger.getLogger(MemberBillDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally{
            try{
                rs.close();
                pstmt.close();
                con.close();
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return bfMemberList;
    }
 public ArrayList getNonMemberNameInCBDao() {
        Connection con=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ArrayList NonMemberList=new ArrayList();
        try{
            con= DbConnection.getConnection();
            String sql ="SELECT DISTINCT customer_name FROM imess_db1.current_bill_mast where cust_type='Non-Member' ";
            pstmt=con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            System.out.println("praj123 "+pstmt);
            while(rs.next()){
                NonMemberList.add(rs.getString(1));
            }            
        }catch (SQLException ex){
            ex.printStackTrace();
            Logger.getLogger(MemberBillDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally{
            try{
                rs.close();
                pstmt.close();
                con.close();
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return NonMemberList;
    }
    public BreakfastBillHistoryBean getMemInfoDetail(String memberName) {
          Connection con=null;
       PreparedStatement pst=null;
       ResultSet rs=null;
       Double bfMemCurrentBal=null;
      // MemberBillBean reglist =new MemberBillBean();
        BreakfastBillHistoryBean breakfastBillHistoryBean= new BreakfastBillHistoryBean();
       try
       {
            con=DbConnection.getConnection();
          //  String sql="select mem_cur_amt where bfMemName='"+bfMemberName+"'";
             String sql = "SELECT mid\n"
                   + "FROM current_bill_mast\n"
                   + "WHERE customer_name ='"+memberName+"' and cust_type='Member' ";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next())
            {
               
              breakfastBillHistoryBean.setId(rs.getInt("mid"));
             return breakfastBillHistoryBean;
            }
       }
       catch(Exception e)
       {
           e.getMessage();
       }
         return breakfastBillHistoryBean;
    }

      public BreakfastBillHistoryBean getNonMemInfoDetail(String memberName) {
          Connection con=null;
       PreparedStatement pst=null;
       ResultSet rs=null;
       Double bfMemCurrentBal=null;
      // MemberBillBean reglist =new MemberBillBean();
        BreakfastBillHistoryBean breakfastBillHistoryBean= new BreakfastBillHistoryBean();
       try
       {
            con=DbConnection.getConnection();
          //  String sql="select mem_cur_amt where bfMemName='"+bfMemberName+"'";
             String sql = "SELECT mid\n"
                   + "FROM current_bill_mast\n"
                   + "WHERE customer_name ='"+memberName+"' and cust_type='Non-Member' ";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next())
            {
               
              breakfastBillHistoryBean.setId(rs.getInt("mid"));
             return breakfastBillHistoryBean;
            }
       }
       catch(Exception e)
       {
           e.getMessage();
       }
         return breakfastBillHistoryBean;
    }
    
    public ArrayList<BreakfastBillHistoryBean> getBillHistoryInfoDoa(BreakfastBillHistoryBean breakfastBillHistoryBean) {
       Connection con=null;
       PreparedStatement pst=null;
       ResultSet rs=null;
       Double bfMemCurrentBal=null;
      // MemberBillBean reglist =new MemberBillBean();
       String sql="";
        ArrayList<BreakfastBillHistoryBean> billHistoryList= new ArrayList<BreakfastBillHistoryBean>();
       try
       {
            con=DbConnection.getConnection();
     //   sql= "select sum(total_amt) as total from current_bill_mast where mid=? and (trans_date between ? and ?) and cust_type=?";
       sql="select * from current_bill_mast where mid=? and (trans_date between ? and ?) and cust_type=?";     
            pst=con.prepareStatement(sql);
            pst.setInt(1,breakfastBillHistoryBean.getId());
            pst.setString(2,breakfastBillHistoryBean.getFromDate());
            pst.setString(3,breakfastBillHistoryBean.getToDate());
            pst.setString(4,breakfastBillHistoryBean.getFilter());
           
            rs=pst.executeQuery();
            System.out.println("search query "+pst);
//            while(rs.next())
//            {
//               
//             breakfastBillHistoryBean.setTotal(rs.getInt("total"));
//             billHistoryList.add(breakfastBillHistoryBean);
//            }
            while(rs.next())
            {
             BreakfastBillHistoryBean breakfastBillHistoryListBean = new BreakfastBillHistoryBean();
             breakfastBillHistoryListBean.setTransDate(rs.getString("trans_date"));
             breakfastBillHistoryListBean.setTotal(rs.getInt("total_amt"));
             
             billHistoryList.add(breakfastBillHistoryListBean);
            }
       }
       catch(Exception e)
       {
           e.getMessage();
       }
         return billHistoryList;
    }
    
}
