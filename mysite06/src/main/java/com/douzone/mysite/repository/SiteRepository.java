package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.SiteRepositoryException;
import com.douzone.mysite.vo.SiteVo;

@Repository
public class SiteRepository {
	
	@Autowired
	private SqlSession sqlSession;
	public SiteVo findAll() throws SiteRepositoryException{
		return sqlSession.selectOne("site.findAll");
	}
	public void update(SiteVo siteVo) {
		
		sqlSession.update("site.update", siteVo);
	}


}
