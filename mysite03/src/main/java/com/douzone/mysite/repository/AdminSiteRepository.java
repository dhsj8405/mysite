package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.AdminSiteRepositoryException;
import com.douzone.mysite.vo.AdminSiteVo;

@Repository
public class AdminSiteRepository {
	
	@Autowired
	private SqlSession sqlSession;
	public List<AdminSiteVo> findAll() throws AdminSiteRepositoryException{
		System.out.println("2");
		List<AdminSiteVo> result = sqlSession.selectList("adminsite.findAll");
		System.out.println(result);
		System.out.println(result.get(0));
		

		System.out.println("3");
		return result;
	}


}
