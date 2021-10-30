package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	private static final int LIST_SIZE = 5; //리스팅되는 게시물의 수
	private static final int PAGE_SIZE = 5; //페이지 리스트의 페이지 수
	
	@Autowired
	private BoardRepository boardRepository;
	
	public boolean addContents( BoardVo boardVo ) {
		if( boardVo.getGroupNo() != null ) {
			increaseGroupOrderNo( boardVo );
		}
		return boardRepository.insert( boardVo ) == 1;
	}
	
	public BoardVo getContents( Long no ) {
		BoardVo boardVo = boardRepository.findByNo( no );
		
		if( boardVo != null ) {
			boardRepository.updateHit( no );
		}
		
		return boardVo;
	}

	public BoardVo getContents( Long no, Long userNo ) {
		BoardVo boardVo = boardRepository.findByNoAndUserNo( no, userNo );
		return boardVo;
	}
	
	public boolean modifyContents( BoardVo boardVo ) {
		int count = boardRepository.update( boardVo );
		return count == 1;
	}
	
	public boolean deleteContents( Long boardNo, Long userNo ) {
		int count = boardRepository.delete( boardNo, userNo );
		return count == 1;
	}
	
	public Map<String, Object> getContentsList( int currentPage, String keyword ){
		
		//1. 페이징을 위한 기본 데이터 계산
		int totalCount = boardRepository.getTotalCount( keyword ); 	//총 게시글 수
		int pageCount = (int)Math.ceil( (double)totalCount / LIST_SIZE ); // 게시글 5개씩 출력시 총 페이지 수 
		int blockCount = (int)Math.ceil( (double)pageCount / PAGE_SIZE );	// 페이지 5개가 1블럭(화살표로 넘기기위해 필요)
		int currentBlock = (int)Math.ceil( (double)currentPage / PAGE_SIZE );
		
		//2. 파라미터 page 값  검증 : 마지막 페이지보다 높은 페이지값이 파라미터로 들어오면 마지막 페이지로 세팅
		if( currentPage > pageCount ) {
			currentPage = pageCount;
			currentBlock = (int)Math.ceil( (double)currentPage / PAGE_SIZE );
		}		
		//2-1 : 파라미터에 1보다 작은 페이지가 들어오면 1페이지로 세팅 
		if( currentPage < 1 ) {
			currentPage = 1;
			currentBlock = 1;
		}
		
		//3. view에서 페이지 리스트를 렌더링 하기위한 데이터 값 계산
		int beginPage = currentBlock == 0 ? 1 : (currentBlock - 1) * PAGE_SIZE + 1; 	// 1, 6, 11 	: 페이징 맨왼쪽 출력되는 페이지
		int prevPage = ( currentBlock > 1 ) ? ( currentBlock - 1 ) * PAGE_SIZE : 0;		//    5, 10, 	: 왼쪽 화살표 눌렸을때 나오는 페이지  
		int nextPage = ( currentBlock < blockCount ) ? currentBlock * PAGE_SIZE + 1 : 0; //6, 11, 16 	:오른쪽 화살표 눌렸을 때 나오는 페이지
		int endPage = ( nextPage > 0 ) ? ( beginPage - 1 ) + LIST_SIZE : pageCount;		// 5, 10, 15 : 페이징 맨 오른쪽에 출력되는 페이지 
		
		//4. 리스트 가져오기
		List<BoardVo> list = boardRepository.findAllByPageAndKeword( keyword, currentPage, LIST_SIZE );
		
		//5. 리스트 정보를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put( "list", list );
		map.put( "totalCount", totalCount );
		map.put( "listSize", LIST_SIZE );
		map.put( "currentPage", currentPage );
		map.put( "beginPage", beginPage );
		map.put( "endPage", endPage );
		map.put( "prevPage", prevPage );
		map.put( "nextPage", nextPage );
		map.put( "keyword", keyword );

		return map;
	}
	
	public boolean increaseGroupOrderNo( BoardVo boardVo ) {
		return boardRepository.updateOrderNo( boardVo.getGroupNo(), boardVo.getOrderNo() ) > 0;
	}
}