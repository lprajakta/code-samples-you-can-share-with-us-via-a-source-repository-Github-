/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itrix.dao;

import com.itrix.bean.ReportBean;
import com.itrix.database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author abhishek_yewle
 */
public class ReportDao {

  public ArrayList<ReportBean> getDebitDetailsDao(String fromDateAndTime, String toDateAndTime)
{
    ArrayList<ReportBean> debitDetailList=new ArrayList<ReportBean>();
    Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    String sql=null;
    try
    {
        con=DbConnection.getConnection();

        sql = "select * from(SELECT psd.sname as name, 'staff' as mem_type,'dbt' as transtype,psd.payingamt as amt,psd.trans_date as transdate,psd.description as description  \n"
                + "FROM  `staff_payment_mast` as psd  \n"
                + "where trans_date between '"+fromDateAndTime+"' and '"+toDateAndTime+"' \n"
                + "union all\n"
                + "SELECT oem.name as name, 'staff' as mem_type,'dbt' as transtype, oem.amt as amt, oem.date_of_trans as transdate, oem.description as description FROM `other_expense_mast` as oem\n"
                + "where date_of_trans between '"+fromDateAndTime+"' and '"+toDateAndTime+"' ) as a\n"
                + "order by transdate";
        pst=con.prepareStatement(sql);
     //  System.out.println("sql is "+sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
            ReportBean reportBean=new ReportBean();             
           reportBean.setName(rs.getString("name"));
           reportBean.setMembershipType(rs.getString("mem_type"));
           reportBean.setTransType(rs.getString("transtype"));
           reportBean.setAmt(rs.getDouble("amt"));
           reportBean.setDate(rs.getString("transdate"));
           reportBean.setDescription(rs.getString("description"));
         
           
           debitDetailList.add(reportBean);
        } 
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
        return debitDetailList;
    }
  
          public ArrayList<ReportBean> getCreditDetailsDao(String fromDateAndTime, String toDateAndTime)
    {
        ArrayList<ReportBean> creditDetailList = new ArrayList<ReportBean>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;
        try {
            con = DbConnection.getConnection();

//            sql = "select * from(\n"
//                    + "SELECT cbm.customer_name as name,cbm.cust_type as mem_type,'crdt' as transtype,cbm.total_amt as amt,cbm.trans_date as transdate,cbm.description as description \n"
//                    + "FROM `current_bill_mast` as cbm\n"
//                    + "where trans_date between '"+fromDateAndTime +"' and '"+toDateAndTime+"'\n"
//                    + "union all\n"
//                    + "select mm.mname as name,'Member' as mem_type,'crdt' as transtype, mbm.rcvd_amt as amt,mbm.trans_date as transdate,mbm.description as description from member_mast as mm\n"
//                    + "inner join member_bill_mast as mbm\n"
//                    + "on mm.mid=mbm.mid\n"
//                    + "where mbm.trans_date between '"+fromDateAndTime +"' and '"+toDateAndTime+"') as a\n"
//                    + "order by transdate asc";
                      sql = "SELECT * \n"
                    + "FROM (\n"
                    + "\n"
                    + "SELECT cbm.customer_name AS name, cbm.cust_type AS mem_type,  'crdt' AS transtype, cbm.total_amt AS amt, cbm.trans_date AS transdate, cbm.description AS description\n"
                    + "FROM  `current_bill_mast` AS cbm\n"
                    + "WHERE trans_date\n"
                    + "BETWEEN  '"+fromDateAndTime +"' "
                    + "AND  '"+toDateAndTime+"' "
                    + "UNION ALL \n"
                    + "SELECT mm.mname AS name,  'Member' AS mem_type,  'crdt' AS transtype, mbm.rcvd_amt AS amt, mbm.trans_date AS transdate, mbm.description AS description\n"
                    + "FROM member_mast AS mm\n"
                    + "INNER JOIN member_bill_mast AS mbm ON mm.mid = mbm.mid\n"
                    + "WHERE mbm.trans_date\n"
                    + "BETWEEN  '"+fromDateAndTime +"'"
                    + "AND  '"+toDateAndTime+"' "
                    + "UNION ALL \n"
                    + "SELECT bmm.bfMemName AS name,  'Breakfast Member' AS mem_type,  'crdt' AS transtype, bp.paid_amt AS amt, bp.trans_date AS transdate, bp.remark AS description\n"
                    + "FROM bf_mem_mast AS bmm\n"
                    + "INNER JOIN bf_payment AS bp ON bmm.bf_mem_id = bp.bf_mem_id\n"
                    + "WHERE bp.trans_date\n"
                    + "BETWEEN  '"+fromDateAndTime +"'"
                    + "AND  '"+toDateAndTime+"' "
                    + ") AS a\n"
                    + "ORDER BY transdate ASC";
            pst = con.prepareStatement(sql);
           // System.out.println("sql is " + sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                ReportBean reportBean = new ReportBean();
                reportBean.setName(rs.getString("name"));
                reportBean.setMembershipType(rs.getString("mem_type"));
                reportBean.setTransType(rs.getString("transtype"));
                reportBean.setAmt(rs.getDouble("amt"));
                reportBean.setDate(rs.getString("transdate"));
                reportBean.setDescription(rs.getString("description"));
                
              
                creditDetailList.add(reportBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return creditDetailList;
    }
    
           public ArrayList<ReportBean> getCreditAndDebitDetailsDao(String fromDateAndTime, String toDateAndTime) {
        ArrayList<ReportBean> creditAndDebitDetailList = new ArrayList<ReportBean>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;
        try {
            con = DbConnection.getConnection();

            sql = "select * from(\n"
                    + "SELECT cbm.customer_name as name,cbm.cust_type as mem_type,'crdt' as transtype,cbm.total_amt as amt,cbm.trans_date as transdate,cbm.description as description \n"
                    + "FROM `current_bill_mast` as cbm\n"
                    + "where trans_date between '" + fromDateAndTime + "' and '" + toDateAndTime + "'\n"
                    + "union all\n"
                    + "select mm.mname as name,'Member' as mem_type,'crdt' as transtype, mbm.rcvd_amt as amt,mbm.trans_date as transdate,mbm.description as description from member_mast as mm\n"
                    + "inner join member_bill_mast as mbm\n"
                    + "on mm.mid=mbm.mid\n"
                    + "where mbm.trans_date between '" + fromDateAndTime + "' and '" + toDateAndTime + "'\n"
                    + "union all\n"
                    + "SELECT psd.sname as name, 'staff' as mem_type,'dbt' as transtype,psd.payingamt as amt,psd.trans_date as transdate,psd.description as description  \n"
                    + "FROM  `staff_payment_mast` as psd  \n"
                    + "where trans_date between '" + fromDateAndTime + "' and '" + toDateAndTime + "' \n"
                    + "union all\n"
                    + "SELECT oem.name as name, 'staff' as mem_type,'dbt' as transtype, oem.amt as amt, oem.date_of_trans as transdate, oem.description as description FROM `other_expense_mast` as oem\n"
                    + "where date_of_trans between '" + fromDateAndTime + "' and '" + toDateAndTime + "' ) as a\n"
                    + "order by transdate asc";

            pst = con.prepareStatement(sql);
            //   System.out.println("credit and debit sql is " + sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                ReportBean reportBean = new ReportBean();
                reportBean.setName(rs.getString("name"));
                reportBean.setMembershipType(rs.getString("mem_type"));
                reportBean.setTransType(rs.getString("transtype"));
                reportBean.setAmt(rs.getDouble("amt"));
                reportBean.setDate(rs.getString("transdate"));
                reportBean.setDescription(rs.getString("description"));

                creditAndDebitDetailList.add(reportBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return creditAndDebitDetailList;
    }
}
