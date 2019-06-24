package com.itrix.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



import com.itrix.utility.DbUtil;

import com.itrix.model.UserMastBean;
import com.itrix.service.EncryptService;

public class LoginUserDao {
	ResourceBundle tablNames=ResourceBundle.getBundle("dbTableNames");
	ResourceBundle UserMastcolumnNames=ResourceBundle.getBundle("TblUserMastColm");
	/************************Dao to check username password ******************************/
	/**@author:Rohit Ruikar
	 * @since :7 August 2014
	 * @method:checkUserPass()
	 * @return: boolean
	 * @param:bean object
	 */
	public boolean checkUserPass(UserMastBean userBean) {
		Connection con=null;
		PreparedStatement ps=null;
		boolean resultflag=false;
		ResultSet rs;
		try {
				/***************************get table and column names from propeties file*************************/
				String userName=UserMastcolumnNames.getString("userName");
				String passwd=UserMastcolumnNames.getString("passwd");
				String tblUserMast=tablNames.getString("user_Mast");
				/***********************************get db connection***********************************************/
				con=DbUtil.getConnection();
				/***********************************select query************************************/
				String username=userBean.getUsername();
				String password=userBean.getPassword();
				String checkUserPassQuery="select * from "+tblUserMast+" where "+userName+"=? and "+passwd+"=?";
				ps=con.prepareStatement(checkUserPassQuery);
				ps.setString(1, username);
				ps.setString(2, password);
				rs=ps.executeQuery();
				if (rs.next())
				{
					resultflag=true;
				}
				else 
				{
				    resultflag=false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				try {
					ps.close();
					con.close();
					} catch (SQLException e) {
						e.printStackTrace();
				}
			}
			return resultflag;
		}
	/************************Dao to check email id if exist ******************************/
	/**@author:Rohit Ruikar
	 * @since :11 August 2014
	 * @method:checkEmailId()
	 * @return: boolean
	 * @param:bean object
	 */
	public boolean checkEmailId(UserMastBean loginBean) {
		Connection con=null;
		PreparedStatement ps=null;
		boolean resultflag=false;
		ResultSet rs;
		try {
				/***************************get table and column names from propeties file*************************/
				String tblUserMast=tablNames.getString("user_Mast");
				String email_Id=UserMastcolumnNames.getString("email_Id");
				/***********************************get db connection***********************************************/
				con=DbUtil.getConnection();
				/***********************************select query************************************/
				String emailId=loginBean.getEmailId();
				String checkEmailQuery="select * from "+tblUserMast+" where "+email_Id+"='"+emailId+"'";
				ps=con.prepareStatement(checkEmailQuery);
				rs=ps.executeQuery();
					if (rs.next())
					{
						resultflag=true;
					}
					else {
						resultflag=false;
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				try {
					ps.close();
					con.close();
					} catch (SQLException e) {
						e.printStackTrace();
				}
			}
			return resultflag;
	}
	/************************Dao to check get user name and password on email id ******************************/
	/**@author:Rohit Ruikar
	 * @since :11 August 2014
	 * @method:getUsernmPassword()
	 * @return: Arraylist
	 * @param:bean object
	 */
	public Boolean SendEmailOnForgotPwd(UserMastBean loginBean) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Boolean resutlSendMail=false;
		try {
			/***************************get table and column names from propeties file*************************/
			String tblUserMast=tablNames.getString("user_Mast");
			String email_Id=UserMastcolumnNames.getString("email_Id");
			String userName=UserMastcolumnNames.getString("userName");
			String passwd=UserMastcolumnNames.getString("passwd");
			/***********************************get db connection***********************************************/
			con=DbUtil.getConnection();
			/***********************************select query************************************/
			String emailId=loginBean.getEmailId();
			String getUsernmPassQuery="select * from "+tblUserMast+" where "+email_Id+"='"+emailId+"'";
			ps=con.prepareStatement(getUsernmPassQuery);			
			rs=ps.executeQuery();
			if(rs.next())
			{
				UserMastBean loginUserBean=new UserMastBean();
				loginUserBean.setUsername(rs.getString(userName));
				String password=rs.getString(passwd);
				String key="itrix";
				EncryptService encryptService=new EncryptService();
				String decryptPassword = encryptService.decrypt(key, password);				 
				loginUserBean.setPassword(decryptPassword);
				loginUserBean.setEmailId(rs.getString(email_Id));
				resutlSendMail=SendForgotPwdEmail(loginUserBean);
				if(resutlSendMail){
					return resutlSendMail=true;
				}else{
					return resutlSendMail=false;
				}
			}else{
				return resutlSendMail=false;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
				ps.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}			
		}		
		return resutlSendMail;	
	}
	/************************Dao to check email id if exist ******************************/
	/**@author:Rohit Ruikar
	 * @since :11 August 2014
	 * @method:SendForgotPwdEmail()
	 * @return: boolean
	 * @param:bean object
	 */
	public boolean SendForgotPwdEmail(UserMastBean sentPassBean) {
		boolean resflg=false;
		try
		{
			String EmailId=sentPassBean.getEmailId();
			String userName=sentPassBean.getUsername();
			String Pwd=sentPassBean.getPassword();			
			
			String subject="SmartCSM password recovery";
			ResourceBundle columnEmailID=ResourceBundle.getBundle("EmailSenderDetails");
			final String uname = columnEmailID.getString("UserName");
			final String psd = columnEmailID.getString("Password");			
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			Session session1 = Session.getInstance(props,
					  new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(uname, psd);
						}
					  });

			
				Message message = new MimeMessage(session1);
				message.setFrom(new InternetAddress(uname));
				message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(EmailId));
				message.setSubject(subject);////Subject line
				message.setContent("<h3 style='color:#B2B2B2;font-weight: bold;text-transform: uppercase;'>Login Information</h3>" +
						"<table style='border:1px solid #DCB2DC;border-collapse: collapse;width:300px;background-color:#F3E6F3;'>" +
						"<tr style='border:1px solid #DCB2DC;'><td width='30%' style='border:1px solid #DCB2DC;'><label style='margin-left:20px;'>Username</label></td><td><label>"+userName+"</label></td></tr>"+
						"<tr style='border:1px solid #DCB2DC;'><td width='30%' style='border:1px solid #DCB2DC;'><label style='margin-left:20px;'>Password</label></td><td><label>"+Pwd+"</label></td></tr>"+
						"</table><br/>"+
						"<div style='border:1px solid #A5D3FF;font-size:9px;text-align: justify;background-color: #E8F4FF;'>You received this e-mail from www.itrix.com ." +
						"This is a system-generated e-mail. If you are not belongs to SmartCSM please ignore this e-mail." +
						"</div>","text/html");					
				Transport.send(message);			
				resflg=true;	
		}
		catch(Exception e)
		{
			System.out.println("DAO MSG:Exception occur at userMasterDao Class in SendEmailPwdUser method");
			resflg=false;
			e.printStackTrace();
		}		
		return resflg;
	}
	
	/********************************************Dao to change password***************************************/
	/**@author:Rohit Ruikar
	 * @since :12 August 2014
	 * @method:changePassword()
	 * @return: boolean
	 * @param:bean object
	 */

	public boolean changePassword(UserMastBean userBean) {
		Connection con=null;
		PreparedStatement ps=null;
		boolean resultflag=false;
		int i;
		try {
				/***************************get table and column names from propeties file*************************/
				String tblUserMast=tablNames.getString("user_Mast");
				String userName=UserMastcolumnNames.getString("userName");
				String passwd=UserMastcolumnNames.getString("passwd");
				/***********************************get db connection***********************************************/
				con=DbUtil.getConnection();
				/***********************************Update query************************************/
				String user_name=userBean.getUsername();
				String current_pass=userBean.getCurrentPassword();
				String new_pass=userBean.getNewPassword();
				String changePwdQuery="update "+tblUserMast+" set "+passwd+"=? where "+userName+"=? and "+passwd+"=?";
				ps=con.prepareStatement(changePwdQuery);
				ps.setString(1, new_pass);
				ps.setString(2, user_name);
				ps.setString(3, current_pass);
				System.out.println("\n"+ps);
				i=ps.executeUpdate();
			    if (i==0)
				{
					 return resultflag;
				}
				else {
					resultflag=true;
				}
				} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				try {
					ps.close();
					con.close();
					} catch (SQLException e) {
						e.printStackTrace();
				}
			}
			return resultflag;
		}
	/********************************************Dao to get user details ***************************************/
	/**@author:Rohit Ruikar
	 * @since :12 August 2014
	 * @method:getUserDetails()
	 * @return: arraylist
	 * @param:bean object
	 */
	public ArrayList<UserMastBean> getUserDetails(UserMastBean userBean) {
		Connection con=null;
		PreparedStatement ps=null;
		ArrayList<UserMastBean> userList=new ArrayList<UserMastBean>();
		ResultSet rs;
		try {
				/***************************get table and column names from propeties file*************************/
			    String userID=UserMastcolumnNames.getString("user_Id");
			    String userName=UserMastcolumnNames.getString("userName");
				String passwd=UserMastcolumnNames.getString("passwd");
				String role_Flg=UserMastcolumnNames.getString("role_Flag");
				String Email_id=UserMastcolumnNames.getString("email_Id");
				String tblUserMast=tablNames.getString("user_Mast");
				/***********************************get db connection***********************************************/
				con=DbUtil.getConnection();
				/***********************************select query************************************/
				String username=userBean.getUsername();
				String password=userBean.getPassword();
				String checkUserPassQuery="select * from "+tblUserMast+" where "+userName+"=? and "+passwd+"=?";
				ps=con.prepareStatement(checkUserPassQuery);
				ps.setString(1, username);
				ps.setString(2, password);
				rs=ps.executeQuery();
					while (rs.next())
					{
							UserMastBean userListBean=new UserMastBean();
							userListBean.setUserId(rs.getInt(userID));
							userListBean.setUsername(rs.getString(userName));			
							userListBean.setEmailId(rs.getString(Email_id));
							userListBean.setRoleFlag(rs.getString(role_Flg).charAt(0));
							userList.add(userListBean);	
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				try {
					ps.close();
					con.close();
					} catch (SQLException e) {
						e.printStackTrace();
				}
			}
			return userList;
	}
}