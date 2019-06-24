/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itrix.dao;

import com.itrix.bean.BreakfastMemberBean;
import com.itrix.bean.CurrentBillBean;
import com.itrix.bean.MemberBillBean;
import com.itrix.database.DbConnection;
import com.itrix.service.CurrentBillService;
//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author abhishek_yewle
 */
public class CurrentBillDao {
    
        
    ResourceBundle dbTable1=ResourceBundle.getBundle("dbTable");
    ResourceBundle CurrentBillCol=ResourceBundle.getBundle("CurrentBillCol");
    ResourceBundle CurrentBillDetail=ResourceBundle.getBundle("CurrentBillDetail");
    
    String currentBillMast=dbTable1.getString("currentBillMast");
    String currentBillMastDetail=dbTable1.getString("currentBillMastDetail");
    
    String billId=CurrentBillCol.getString("billId");
    String mId=CurrentBillCol.getString("mId");
    String customerName=CurrentBillCol.getString("customerName");
    String custType=CurrentBillCol.getString("custType");
    String transDate=CurrentBillCol.getString("transDate");
    String totalAmt=CurrentBillCol.getString("totalAmt");
    String desc=CurrentBillCol.getString("desc");
    
    String billDetailID=CurrentBillDetail.getString("billDetailID");
    String detailBillId=CurrentBillDetail.getString("billId");
    String itemId=CurrentBillDetail.getString("itemId");
    String itemName=CurrentBillDetail.getString("itemName");
    String Qty=CurrentBillDetail.getString("Qty");
    String Rate=CurrentBillDetail.getString("Rate");
    String Amt=CurrentBillDetail.getString("Amt");
 
      public ArrayList getMemberNameInCBDao(){
        Connection con=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ArrayList memberList=new ArrayList();
        try{
            con= DbConnection.getConnection();
            String sql ="SELECT mname FROM imess_db1.member_mast ;";
            pstmt=con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                memberList.add(rs.getString(1));
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
        return memberList;
    }
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
      
         public CurrentBillBean getItemPriceDao(String itemName,String itemUnit)
   {
       Connection con=null;
       PreparedStatement pst=null;
       ResultSet rs=null;
       CurrentBillBean currentBillBean =new CurrentBillBean();
     //  ArrayList itemList=new ArrayList();
       try
       {
            con=DbConnection.getConnection();
            String sql="select unit,price from item_mast where name='"+itemName+"' and unit='"+itemUnit+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next())
            {
                
                currentBillBean.setQty(rs.getInt("unit"));
                currentBillBean.setRate(rs.getInt("price"));
               // System.out.println("price is "+rs.getInt("price"));
                  
            }
       }
       catch(Exception e)
       {
           e.getMessage();
       }
    return currentBillBean;
   }
        public ArrayList getItemNameInCBDao(){
        Connection con=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ArrayList itemList=new ArrayList();
        try{
            con= DbConnection.getConnection();
            String sql ="SELECT name,unit FROM imess_db1.item_mast order by name;";
           //  String sql ="SELECT name FROM imess_db1.item_mast order by name;";
            pstmt=con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
              //  itemList.add(rs.getString("name"));
                String itemName= rs.getString("name");
                String unit= rs.getString("unit");
                itemList.add(itemName+" " +unit);
                
                
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
        return itemList;
    }




public int SaveCurrentBillDao(CurrentBillBean current_Bill_Bean,String customerType) {
      Connection con = null;
      PreparedStatement pst=null;
      String sql=null;
      int result=0;
      int maxBillId=0;
      try{
         con=DbConnection.getConnection();
         sql="insert into "+currentBillMast+"(mid,customer_name,cust_type,total_amt,description) values(?,?,?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setInt(1,current_Bill_Bean.getMid());
            pst.setString(2,current_Bill_Bean.getCust_Name());
            pst.setString(3,customerType);
            pst.setInt(4,current_Bill_Bean.getTotal_Amount());
            pst.setString(5,current_Bill_Bean.getDescription());
            result=pst.executeUpdate();

             if (result != 0) {

              String sqlForId = "select max(bill_id) as maxBillId  from current_bill_mast ";
              pst = con.prepareStatement(sqlForId);
              ResultSet rss = pst.executeQuery();
              while (rss.next()) {
                  maxBillId = rss.getInt("maxBillId");

              }

              return maxBillId;

          }
            else 
           {
                maxBillId=0;
                return maxBillId;
           }
          }
        catch(Exception e){
              e.printStackTrace();
              }
         finally{
          try{
              con.close();
              pst.close();
            }
          catch(Exception e) {
            e.printStackTrace();
            }
        }
       return maxBillId;
    }

    public DefaultTableModel createInfo(String itmname)
   {
         Connection Con=null;
         PreparedStatement pst=null;
         ResultSet rs = null;
         DefaultTableModel dtm=null;
        try
        {
         Con=DbConnection.getConnection();
         String sql = "select * from currentbill_mast_detail where name like ? order by name";
         pst = Con.prepareStatement(sql);
         pst.setString(1,itmname+"%");
         rs=pst.executeQuery();
         dtm =(DefaultTableModel)DbUtils.resultSetToTableModel(rs);
        }
        catch(Exception e)
        {
        JOptionPane.showMessageDialog(null,e);
        }
        finally
        {
            try
            {
                Con.close();
                pst.close();
                rs.close();
            }
    catch(Exception e)
    {
        e.getMessage();
    }
}
    return dtm;
}


    public boolean resetDetailDao(){
        Connection con=null;
        PreparedStatement pst=null;
        String sql=null;
        int rs=0;
        boolean result=false;
        try{
            con=DbConnection.getConnection();
            sql="delete from currentbill_mast_detail";
            pst=con.prepareStatement(sql);
          //  rs=pst.executeUpdate();
            if(rs!=0){
                result=true;
            }else{
                 result=false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    public BreakfastMemberBean getbfMemCurrentBal(String bfMemberName) {
        Connection con=null;
       PreparedStatement pst=null;
       ResultSet rs=null;
       Double bfMemCurrentBal=null;
      // MemberBillBean reglist =new MemberBillBean();
        BreakfastMemberBean breakfastMemberBean= new BreakfastMemberBean();
       try
       {
            con=DbConnection.getConnection();
          //  String sql="select mem_cur_amt where bfMemName='"+bfMemberName+"'";
             String sql = "SELECT mem_cur_amt,bf_mem_id\n"
                   + "FROM bf_mem_mast\n"
                   + "WHERE bfMemName =  '"+bfMemberName+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next())
            {
               
              breakfastMemberBean.setBfMemId( rs.getInt("bf_mem_id"));
              breakfastMemberBean.setBfMemCurrentBal(rs.getDouble("mem_cur_amt"));
              
              
               return breakfastMemberBean;
                  
            }
       }
       catch(Exception e)
       {
           e.getMessage();
       }
         return breakfastMemberBean;
     
    }

    public CurrentBillBean getNameTotalBillDao(int maxBillId) {
         Connection con=null;
       PreparedStatement pst=null;
       ResultSet rs=null;
       Double bfMemCurrentBal=null;
       String sql=null;
      // MemberBillBean reglist =new MemberBillBean();
       CurrentBillBean currentBillBean=new CurrentBillBean();
       try
       {
            con=DbConnection.getConnection();
          sql = "select customer_name,cust_type,trans_date,total_amt,description FROM  `current_bill_mast`  where bill_id='"+maxBillId+"' ";
           pst=con.prepareStatement(sql);
             // System.out.println("sql query is "+pst);
            rs=pst.executeQuery();
            while(rs.next())
            {
                currentBillBean.setCust_Name(rs.getString("customer_name"));
                currentBillBean.setCust_type(rs.getString("cust_type"));
                currentBillBean.setDate(rs.getString("trans_date"));
                currentBillBean.setTotal_Amount(rs.getInt("total_amt"));
                currentBillBean.setDescription(rs.getString("description"));
            }
       }
       catch(Exception ex)
       {
          ex.printStackTrace();
       }
         return currentBillBean;
    }

    public ArrayList<CurrentBillBean> getDishDetailList(int maxBillId) {
        ArrayList<CurrentBillBean> detailDishList = new ArrayList<CurrentBillBean>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;
        try {
            con = DbConnection.getConnection();
            sql = "select item_name,qty,rate,amt from `current_bill_detail`  where bill_id='" + maxBillId + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                CurrentBillBean currentBillBean = new CurrentBillBean();

                currentBillBean.setItem_Name(rs.getString("item_name"));
                currentBillBean.setQty(rs.getInt("qty"));
                currentBillBean.setRate(rs.getInt("rate"));
                currentBillBean.setAmount(rs.getInt("amt"));

                detailDishList.add(currentBillBean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                pst.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return detailDishList;
    }

    public MemberBillBean getMemberDetailInfoDao(String memberName) {
         Connection con=null;
       PreparedStatement pst=null;
       ResultSet rs=null;
       Double bfMemCurrentBal=null;
      // MemberBillBean reglist =new MemberBillBean();
        MemberBillBean memberBillBean= new MemberBillBean();
       try
       {
            con=DbConnection.getConnection();
          //  String sql="select mem_cur_amt where bfMemName='"+bfMemberName+"'";
             String sql = "SELECT * FROM  `member_mast` where mname='"+memberName+"' ";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next())
            {
               
              memberBillBean.setMid(rs.getInt("mid"));
              
              
              
               return memberBillBean;
                  
            }
       }
       catch(Exception e)
       {
           e.getMessage();
       }
         return memberBillBean;
    }
}
