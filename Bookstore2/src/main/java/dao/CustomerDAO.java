package dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DBHelper;
import vo.CustomerVO;

public class CustomerDAO extends DBHelper{

	private static CustomerDAO instance = new CustomerDAO();
	public static CustomerDAO getInstance() {
		return instance;
	}
	private CustomerDAO() {}
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertCustomer(CustomerVO vo) {
		try {
			logger.info("selectCustomer");
			conn = getConnection();
			psmt = conn.prepareStatement("insert into `customer` (`name`,`address`,`phone`) values (?,?,?)");
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getAddress());
			psmt.setString(3, vo.getPhone());
			psmt.executeUpdate();
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	public CustomerVO selectCustomer(String custId) {
		
		CustomerVO vo = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("select * from `customer` where `custId`=?");
			psmt.setString(1, custId);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new CustomerVO();
				vo.setCustId(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setAddress(rs.getString(3));
				vo.setPhone(rs.getString(4));
			}
			close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	public List<CustomerVO> selectCustomers() {
		List<CustomerVO> customers = new ArrayList<>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `customer`");
			
			while(rs.next()) {
				CustomerVO vo  = new CustomerVO();
				vo.setCustId(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setAddress(rs.getString(3));
				vo.setPhone(rs.getString(4));
				
				customers.add(vo);
			}
			close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customers;
	}
	public void updateCustomer(CustomerVO vo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("update `customer` set `name`=?, `address`=?, `phone`=? where `custId`=?");
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getAddress());
			psmt.setString(3, vo.getPhone());
			psmt.setInt(4, vo.getCustId());
			psmt.executeUpdate();
			close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteCustomer(String custId) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("delete from `customer` where `custId`=?");
			psmt.setString(1, custId);
			psmt.executeUpdate();
			close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
