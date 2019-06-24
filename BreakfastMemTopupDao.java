/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itrix.dao;

import com.itrix.bean.BreakfastMemTopupBean;
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
public class BreakfastMemTopupDao {
    public ArrayList getBfMemberNameInCBDao(){
        Connection con=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ArrayList bfMemberList=new ArrayList();
        try{
            con= DbConnection.getConnection();
            String sql ="SELECT bfMemName FROM imess_db1.bf_mem_mast ;";
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
    
    
    public BreakfastMemTopupBean getbfMemCurrentBal(String bfMemberName) {
        Connection con=null;
       PreparedStatement pst=null;
       ResultSet rs=null;
       Double bfMemCurrentBal=null;
      // MemberBillBean reglist =new MemberBillBean();
       BreakfastMemTopupBean list=new BreakfastMemTopupBean();
       try
       {
            con=DbConnection.getConnection();
          //  String sql="select mem_cur_amt where bfMemName='"+bfMemberName+"'";
             String sql = "SELECT * "
                   + "FROM bf_mem_mast\n"
                   + "WHERE bfMemName =  '"+bfMemberName+"'";
           
            
            pst=con.prepareStatement(sql);
              System.out.println("sql query is "+pst);
            rs=pst.executeQuery();
            
            while(rs.next())
            {
                
              
                
              
                list.setCurrentBal(rs.getDouble("mem_cur_amt"));
                list.setBfMemId(rs.getInt("bf_mem_id"));
                  
                  
            }
       }
       catch(Exception e)
       {
           e.getMessage();
       }
         return list;
     
    }
    
     public int saveBfMemDetail(BreakfastMemTopupBean breakfastMemTopupBean){
            Connection con=null;
            PreparedStatement pst=null;
            String query=null;
            String sql=null;
            
        try{
           con=DbConnection.getConnection();
          query="insert into bf_payment(bf_mem_id,current_bal_amt,total_bill_amt,paid_amt,bal_amt,remark) values(?,?,?,?,?,?)";
           //query="insert into "+tableName+"("+mid+","+mname+","+mbatch+","+maddress+","+mmobile_no+","+memail_id+","+mmembership_type+","+mcur_amt+","+ mopening_amt+","+ mactivestatus_flag+") values(?,?,?,?,?,?,?,?,?,?)";
         
           pst = con.prepareStatement(query);
           pst.setInt(1,breakfastMemTopupBean.getBfMemId());
           pst.setDouble(2,breakfastMemTopupBean.getCurrentBal());
           pst.setDouble(3,breakfastMemTopupBean.getTotalBillAmt());
           pst.setDouble(4,breakfastMemTopupBean.getPaidAmt());
           pst.setDouble(5,breakfastMemTopupBean.getBal());
           pst.setString(6,breakfastMemTopupBean.getRemark());
           
           int result=pst.executeUpdate();
           if(result!=0){
               sql="update bf_mem_mast set mem_cur_amt=? where bf_mem_id=? ";
               pst = con.prepareStatement(sql);
                pst.setDouble(1,breakfastMemTopupBean.getBal());
           pst.setInt(2,breakfastMemTopupBean.getBfMemId());
           
           int result1=pst.executeUpdate();
               return 1;
           }else{
               return 2;
           }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try{
                con.close();
                pst.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return 1;
    } 
     
     
     public ArrayList<BreakfastMemTopupBean> getParticularBreakfastMemRecordForDisplay(int bfMemId){
         ArrayList<BreakfastMemTopupBean> breakfastMemBeanList=new ArrayList<BreakfastMemTopupBean>();
         Connection con=null;
         PreparedStatement pst=null;
         ResultSet rs=null;
         String sql=null;
         try {
             con = DbConnection.getConnection();
             sql = "SELECT bmm.bfMemName,bp.current_bal_amt,bp.total_bill_amt,bp.paid_amt,bp.bal_amt,bp.remark,bp.trans_date FROM `bf_mem_mast` as bmm\n"
                     + "inner join bf_payment as bp\n"
                     + "on bmm.bf_mem_id=bp.bf_mem_id\n"
                     + "where bp.bf_mem_id='"+bfMemId+"'\n"
                     + "order by bp.trans_date desc";
             pst=con.prepareStatement(sql);
             rs =pst.executeQuery();
        
              while(rs.next()){
                 BreakfastMemTopupBean breakfastMemTopupBean= new BreakfastMemTopupBean(); 
         
                    breakfastMemTopupBean.setBfMemName(rs.getString("bfMemName"));
                    breakfastMemTopupBean.setCurrentBal(rs.getDouble("current_bal_amt"));
                    breakfastMemTopupBean.setTotalBillAmt(rs.getDouble("total_bill_amt"));
                    breakfastMemTopupBean.setPaidAmt(rs.getDouble("paid_amt"));
                    breakfastMemTopupBean.setBal(rs.getDouble("bal_amt"));
                    breakfastMemTopupBean.setRemark(rs.getString("remark"));
                    breakfastMemTopupBean.setTransDate(rs.getString("trans_date"));
                 breakfastMemBeanList.add(breakfastMemTopupBean);
             }
                
            }catch(Exception e){
                e.printStackTrace();
            }finally{
            try{
                con.close();
                pst.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
         return breakfastMemBeanList;   
    } 
}
