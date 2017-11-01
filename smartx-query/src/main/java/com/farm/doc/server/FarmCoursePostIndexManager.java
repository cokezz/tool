package com.farm.doc.server;

import com.farm.lucene.result.DataResult;
import com.kec.smartx.param.dto.CoursePost;

/**
 * 索引管理
 * 
 * @author gz
 * 
 */
public interface FarmCoursePostIndexManager {
	
	public DataResult search(String word,String sid, Integer pagenum);

	/**
	 * 删除课程索引(带权限)
	 * 
	 * @param entity
	 */
	public boolean deleteCoursePostIndex(String sid,String postId);

	/**
	 * 创建文档
	 * 
	 * @param entity
	 *            标题、发布时间、内容类型是必填 texts中的TEXT1中存放超文本内容
	 * @param currentUser
	 * @return
	 */
	public boolean createCoursePostIndex(CoursePost post);
	
	
	
}