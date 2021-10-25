package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.AdminSiteRepositoryException;
import com.douzone.mysite.vo.AdminSiteVo;

@Repository
public class AdminSiteRepository {
	
	@Autowired
	private SqlSession sqlSession;
	public AdminSiteVo findAll() throws AdminSiteRepositoryException{
		return sqlSession.selectOne("adminsite.findAll");
	}
	public void update(AdminSiteVo adminSiteVo) {
		sqlSession.update("adminsite.update", adminSiteVo);
	}


}
