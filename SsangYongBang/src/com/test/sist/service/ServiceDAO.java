package com.test.sist.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.sist.DBUtil;

public class ServiceDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public ServiceDAO() {
		
		//DB연결
		conn = DBUtil.open();
	}
	
	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	
	//업주 로그인 입력정보 확인하기
	public int login(ServiceDTO dto) {
		
		try {
			
			String sql = "select count(*) as cnt from tblFirm where email = ? and pw = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getEmail());
			pstat.setString(2, dto.getPw());
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("cnt"); // 1 or 0
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return 0;
	}

	
	//Login 서브릿 -> email 매개변수로 하여 회원정보를 읽어오라고 호출. 우선은 업체승인번호만 가져오기
	public ServiceDTO getService(String email) {
		
		try {
			
			//업체 승인번호를 이용한 업무가 잦아, 승인업체테이블과 조인하여 정보 가져와야 함ㄴ
			//select문 join테이블로 고친 이후로.. 오류 해결중
			String sql = "select b.seq as approvalFSeq from tblFirm a inner join tblApprovalF b on a.seq = b.seq where a.email = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, email);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				ServiceDTO dto = new ServiceDTO();
				
				dto.setApprovalFSeq(rs.getString("approvalFSeq")); //업체승인번호
				//부적합한 열 인덱스?.. 컨트롤 씨 브이 했기 때문에 동일함.
				
				return dto;	
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	

	
	
	

}
