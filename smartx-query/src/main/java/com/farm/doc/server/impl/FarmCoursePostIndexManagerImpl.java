package com.farm.doc.server.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farm.doc.server.FarmCoursePostIndexManager;
import com.farm.doc.server.FarmDocIndexInter;
import com.farm.lucene.result.DataResult;
import com.kec.smartx.param.dto.CoursePost;

/**
 * 课程索引管理
 * 
 * @author gz
 */
@Service
public class FarmCoursePostIndexManagerImpl implements FarmCoursePostIndexManager {
	private static final Logger log = Logger.getLogger(FarmCoursePostIndexManagerImpl.class);
	
	@Resource
	private FarmDocIndexInter farmDocIndexManagerImpl;

	@Override
	public DataResult search(String word,String sid, Integer pagenum) {
		DataResult result = null;
		try {
			result = farmDocIndexManagerImpl.search(word, sid, pagenum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Transactional
	public boolean createCoursePostIndex(CoursePost post) {
		try {
			{// 对创建的课程做索引
				farmDocIndexManagerImpl.addLuceneIndex(post);
			}
		} catch (Exception e) {
			log.error("课程索引创建失败创建失败：" + e + e.getMessage());
			throw new RuntimeException("课程索引创建失败创建失败：" + e + e.getMessage());
		}
		return true;
	}


	@Override
	public boolean deleteCoursePostIndex(String sid,String postId) {
		try {
			{// 对创建的课程做索引
				farmDocIndexManagerImpl.delLuceneIndex(sid,postId);
			}
		} catch (Exception e) {
			log.error("删除课程索引失败：" + e + e.getMessage());
			throw new RuntimeException("删除课程索引失败：" + e + e.getMessage());
		}
		
		return true;
	}

}
