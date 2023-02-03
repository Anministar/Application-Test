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
import com.korea1.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	// DataSource
   @Autowired
   DataSource ds;

   
   // BoardMapper
   @Autowired
   BoardMapper mapper;

   // DB
   @Test
   public void Conn() {
      log.info("CONN : " + ds);
   }

   // INSERT DB 테스트입니다!!
   @Test
   public void insert() throws SQLException {
      Connection conn = ds.getConnection();
      PreparedStatement pstmt = conn
            .prepareStatement("insert into tbl_board values(null,'TEST', 'test입니다!', 'tester', NOW(), NOW())");
      int result = pstmt.executeUpdate();
      log.info("===============================================================================================================");
      log.info("Insert Test Result : " + result);
      log.info("===============================================================================================================");
   }

   // SELECT(모든 게시물을 조회하여 보여줌.)
   @Test
   public void getListTest() {
      List<BoardVO> list = mapper.getList();
      log.info("===============================================================================================================");
      for (BoardVO vo : list) {
         log.info(vo.toString());
         log.info("===============================================================================================================");
      }
      
//      list.forEach(vo -> log.info("BoardVO : "+ vo.toString()));
   }

   // INSERT
   @Test
   public void insertTest() {
      // mapper 의 insert를 이용하여 DB에 BoardVO를 삽입하고
      // 결과 스크린샷으로 찍기

      BoardVO vo = new BoardVO(0, "TEST3", "세번째 test입니다!", "tester3",null, null);

      mapper.insert(vo);
      log.info("===============================================================================================================");
      log.info("INSERT : SUCESS");
      log.info("===============================================================================================================");
   }
   
   // InsertSelectKey + 셀렉트 후 MAX BNO
   @Test
   public void insertSelectKeyTest() {
	   BoardVO test = new BoardVO(0, "asdf", "asdf", "asdf6", null, null);
	   
	   mapper.insertSelectKey(test);
	   log.info("===============================================================================================================");
	   log.info("AfterBNO : " + test.getBno());
	   log.info("===============================================================================================================");
   }

   @Test
   public void readTest() {
      // mapper 의 read를 이용하여 DB에 게시물 하나를 받아와서
      // 콘솔창에 결과 확인하고 스크린샷으로 찍기

      BoardVO selectOne = mapper.read((long) 5);
      log.info("===============================================================================================================");
      log.info(selectOne.toString());
      log.info("===============================================================================================================");

   }

   @Test
   public void DeleteTest() {
      // mapper 의 delete를 이용하여 DB에 특정 게시물을 삭제하고
      // 결과 스크린샷으로 찍기

      int result = mapper.delete((long) 1);
      log.info("===============================================================================================================");
      log.info("DELETE RESULT : " + result);
      log.info("===============================================================================================================");
   }

   @Test
   public void UpdateTest() {
      // mapper 의 update를 이용하여 DB에 특정 게시물을 수정하고
      // 결과 스크린샷으로 찍기

      BoardVO vo = new BoardVO(2, "TEST4", "네번째 테스트입니다!!", "Tester4", null, null);
      int update = mapper.update(vo);
      log.info("===============================================================================================================");
      log.info("UPDATE RESULT : " + update);
      log.info("===============================================================================================================");
   }

   @Test
   public void getTotalCountTest() {
      // mapper 의 getTotalcount를 이용하여 DB에
      // 모든 게시물의 개수를 확인 결과 출력
	   
	   int result = mapper.getTotalCount();
	   log.info("===============================================================================================================");
	   log.info("TOTALCOUNT RESULT : " + result);
	   log.info("===============================================================================================================");
   }
}