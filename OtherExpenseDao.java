/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itrix.dao;

import com.itrix.bean.OtherExpenseBean;
import com.itrix.database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author abhishek_yewle
 */
public class OtherExpenseDao {
    public int regOtherExpenseInfo(OtherExpenseBean otherExpenseBean){
            Connection con=null;
            PreparedStatement pst=null;
            String query="";
            
        try{
           con=DbConnection.getConnection();
           query="insert into other_expense_mast(Id,name,description,amt,reg_date) values(?,?,?,?,?)";         
           pst = con.prepareStatement(query);
          pst.setInt(1,0);
           pst.setString(2,otherExpenseBean.getName());
           pst.setString(3,otherExpenseBean.getDescription());
           pst.setDouble(4,otherExpenseBean.getAmt());
           pst.setString(5,otherExpenseBean.getDate());
          
           int result=pst.executeUpdate();
           if(result!=0){
               return 1;
           }else{
               return 2;
           }
        }catch(Exception e){
            System.out.println("something is wrong 1");
              JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try{
                con.close();
                pst.close();
            }catch(Exception e){
                 System.out.println("something is wrong 2");
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return 1;
    }   
    public ArrayList<OtherExpenseBean> getAllRecordForDisplay(){
         ArrayList<OtherExpenseBean> otherExpenseDaoList=new ArrayList<OtherExpenseBean>();
         Connection con=null;
         PreparedStatement pst=null;
         ResultSet rs=null;
         String sql=null;
         try{
             con=DbConnection.getConnection();
             sql="Select * from other_expense_mast";
             pst=con.prepareStatement(sql);
             rs =pst.executeQuery();
          //  System.out.println(rs);
              while(rs.next()){
                 OtherExpenseBean otherExpenseBean= new OtherExpenseBean(); 
            //     user = new User(rs.getInt("id"),rs.getString("fname"),rs.getString("lname"),rs.getInt("age"));
                    otherExpenseBean.setId(rs.getInt("Id"));
                    otherExpenseBean.setName(rs.getString("name"));
                    otherExpenseBean.setDescription(rs.getString("description"));
                    otherExpenseBean.setAmt(rs.getDouble("amt"));
                    otherExpenseBean.setDate(rs.getString("reg_date"));
                    otherExpenseDaoList.add(otherExpenseBean);
             }
                
            }catch(Exception e){
              JOptionPane.showMessageDialog(null, e);
            }finally{
            try{
                con.close();
                pst.close();
            }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
            }
        }
         return otherExpenseDaoList;   
    }   
      
            public int updateOtherExpenseDetail(OtherExpenseBean otherExpenseBean) {
            Connection con=null;
            PreparedStatement pst=null;
            String query=null;
            int id=otherExpenseBean.getId();
           
        try{
           con=DbConnection.getConnection();
           query="update other_expense_mast set name=? , description=? , amt=? , reg_date=?  where Id="+id;
           pst = con.prepareStatement(query);
           pst.setString(1,otherExpenseBean.getName());
           pst.setString(2,otherExpenseBean.getDescription());
           pst.setDouble(3,otherExpenseBean.getAmt());
           pst.setString(4,otherExpenseBean.getDate());
         
           
           int result=pst.executeUpdate();
            if(result!=0){
               return 1;
            }else{
               return 2;
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
        return 1;
     }
    
            public int deleteOtherExpenseDetail(final int Id){
            Connection con=null;
            PreparedStatement pst=null;
            String query=null;
        try{
           con=DbConnection.getConnection();
           query="delete from other_expense_mast where Id="+Id;
           pst = con.prepareStatement(query);
           int result=pst.executeUpdate();
           if(result!=0){
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
}
