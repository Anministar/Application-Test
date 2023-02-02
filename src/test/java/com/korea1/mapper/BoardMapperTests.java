package com.korea1.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.korea1.domain.BoardVO;

import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Autowired
	DataSource ds;
	
	@Autowired
	private SqlSession session;
	
	
	@Autowired
	BoardMapper mapper;
	
	// DB 연동 테스트입니다!!
	@Test
	public void Conn() {
		log.info("CONN : " + ds); 
	}
	
	// INSERT DB 테스트입니다!!
	@Test
	public void insert() throws SQLException {
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_board values(null,'TEST', 'test입니다!', 'tester', NOW(), NOW())");
		int result = pstmt.executeUpdate();
		log.info("result : " + result);
	}
	
	
	// SELECT(모든 게시물을 조회하여 보여줌.)
	@Test
	public void getListTest() {
		List<BoardVO> list = mapper.getList();
		for(BoardVO vo : list) {
			log.info(vo.toString());
			log.info("-----------------------------------------");
		}
//		list.forEach(vo -> log.info("BoardVO : "+ vo.toString()));
	}
	
	
	// INSERT
	@Test
	public void insertTest() {
		// mapper 의 insert를 이용하여 DB에 BoardVO를 삽입하고
		// 결과 스크린샷으로 찍기
		
//		BoardVO vo = new BoardVO(0, "TEST2", "두번째 test입니다!", "tester2", new Date(), new Date());
		BoardVO vo = new BoardVO(null, "TEST3", "세번째 test입니다!", "tester3", new Date(), new Date());
		
		mapper.insert(vo);
		log.info("BNO : " + vo.getBno());
//		mapper.insertSelectKey(vo);
//		log.info("AfterBNO : " + vo.getBno());
		
	}
	@Test
	public void readTest() {
		// mapper 의 read를 이용하여 DB에 특정 게시물을 삭제하고
		// 결과 스크린샷으로 찍기
		
	}
	@Test
	public void DeleteTest() {
		// mapper 의 delete를 이용하여 DB에 특정 게시물을 삭제하고
		// 결과 스크린샷으로 찍기
	}
	@Test
	public void UpdateTest() {
		// mapper 의 update를 이용하여 DB에 특정 게시물을 수정하고
		// 결과 스크린샷으로 찍기
	}
	@Test
	public void getTotalCountTest() {
		// mapper 의 getTotalcount를 이용하여 DB에
		// 모든 게시물의 개수를 확인 결과 출력
	}
}






