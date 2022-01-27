package com.spring.db.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.db.commons.ScoreMapper;
import com.spring.db.model.BoardVO;

@Repository
public class BoardDAO implements IBoardDAO {

	class BoardMapper implements RowMapper<BoardVO>{
		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO vo = new BoardVO();
			vo.setboard_no(rs.getInt("board_no"));
			vo.setWriter(rs.getString("writer"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			return vo;
		}
	}
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public void insertArticle(BoardVO board) {
		String sql = "INSERT INTO jdbc_board VALUES(bid_seq.NEXTVAL,?,?,?)";
		template.update(sql, board.getWriter(), board.getTitle(), board.getContent());
	}

	@Override
	public List<BoardVO> getArticles() {
		String sql = "SELECT * FROM jdbc_board ORDER BY board_no DESC";
		return template.query(sql, new BoardMapper());
//		return template.query(sql,(rs,rowNum) -> {
//			BoardVO vo = new BoardVO();
//			vo.setboard_no(rs.getInt("board_no"));
//			vo.setWriter(rs.getString("writer"));
//			vo.setTitle(rs.getString("title"));
//			vo.setContent(rs.getString("content"));
//			return vo;
//		});
	}

	@Override
	public BoardVO getArticle(int bId) {
		String sql = "SELECT * FROM jdbc_board WHERE board_no=?";
		return template.queryForObject(sql, new BoardMapper(), bId);
	}

	@Override
	public void deleteArticle(int bId) {
		String sql = "DELETE FROM jdbc_board WHERE board_no=?";
	    template.update(sql, bId);
	}

	@Override
	public void updateArticle(BoardVO vo, int bId) {
//		template.update(vo.getWriter(), getArticle(bId));
		String sql = "UPDATE jdbc_board SET writer=?, content=? WHERE board_no=?";
		template.update(sql, vo.getWriter(),vo.getContent(), bId);
				
	}

}
