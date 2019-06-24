/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itrix.dao;
import com.itrix.bean.MemberBillBean;
import com.itrix.database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author abhishek_yewle
 */
public class MemberBillDao {

   public MemberBillBean currentShipTypeAndCurrentAmtInfo(String mNamefor)
   {
       Connection con=null;
       PreparedStatement pst=null;
       ResultSet rs=null;
       MemberBillBean reglist =new MemberBillBean();
       try
       {
            con=DbConnection.getConnection();
            String sql="select mid,mmembership_type,mcur_amt from member_mast where mname='"+mNamefor+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next())
            {
                reglist.setmMembershipType(String.valueOf(rs.getString("mmembership_type")));
                reglist.setmCurrentAmt(rs.getDouble("mcur_amt"));
                reglist.setMid(rs.getInt("mid"));
                  
            }
       }
       catch(Exception e)
       {
           e.getMessage();
       }
    return reglist;
   }

public  int insertMemberDetail(MemberBillBean memberBillBean)
   {
       Connection con=null;
       PreparedStatement pst=null;
       String sql=null;
       int result=0;
       try{
            con=DbConnection.getConnection();
            sql="insert into member_bill_mast(bill_id,mid,month,cur_bal,total_bill_amt,amt_to_rec,rcvd_amt,rem_amt,description) values(?,?,?,?,?,?,?,?,?)";
            System.out.println("sql is="+sql);
            pst=con.prepareStatement(sql);
            pst.setInt(1,0);
            pst.setInt(2,memberBillBean.getMid());
            pst.setString(3,memberBillBean.getMonthSelected());
            pst.setDouble(4,memberBillBean.getmCurrentAmt());
            pst.setDouble(5,memberBillBean.getTotal_bill_amt());
            pst.setDouble(6,memberBillBean.getAmt_to_recv());
            pst.setDouble(7,memberBillBean.getRecv_amt());
            pst.setDouble(8,memberBillBean.getRem_amt());
            pst.setString(9,memberBillBean.getDesc());
            result=pst.executeUpdate();
            if(result!=0){
               return 1;
           }else{
               return 2;
           }
          }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(null, ex);
        }
       return 1;
   }




    public ArrayList getMemberNameInCBDao(){
        Connection con=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ArrayList memberList=new ArrayList();
        try{
            con= DbConnection.getConnection();
            String sql ="SELECT mname FROM imess_db1.member_mast;";
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


}






