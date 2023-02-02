package com.korea1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.korea1.domain.BoardVO;
import com.korea1.domain.Criteria;

public interface BoardMapper {
//
	Class<?> Integer = null;

	@Select("select * from tbl_board where bno>0")
	public List<BoardVO> getList();
	
	
	@Insert
	("insert into tbl_board values(null, #{title},#{content},#{writer}, NOW(), NOW())")
	public void insert(BoardVO board);
	
	
	@SelectKey
	(
			statement="select max(bno) from tbl_board",
			keyProperty="bno",
			before=false,
			resultType=long.class
	)
	@Insert
	("insert into tbl_board values(null, #{title},#{content},#{writer}, NOW(), NOW())")
	public long insertSelectKey(BoardVO board);
	
	
	@Select("select * from tbl_board where bno=#{bno}")
	public BoardVO read(Long bno);
	
	
	@Delete("delete from tbl_board where bno=#{bno}")
	public int delete (Long bno);
	
	
	@Update
	("update tbl_board set title=#{title},content=#{content},writer=#{writer},updateDate=NOW() where bno=#{bno}")
	public int update(BoardVO board);

	
	@Select("select count(*) from tbl_board where bno>0")
	public int getTotalCount(Criteria cri);
}
