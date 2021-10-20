package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	public List<BoardVo> find(int pageIndex) {
		System.out.println(pageIndex);
		return boardRepository.findAll(pageIndex);
	}

	public int findTotalPage() {
		return boardRepository.findTotalPage();
	}

	public BoardVo findByNo(Long boardNo) {
		return boardRepository.findByNo(boardNo);
	}

	public void updateHit(Long boardNo) {
		boardRepository.updateHit(boardNo);
	}

	public void insert(BoardVo vo) {
		boardRepository.insert(vo);
	}
	
}
