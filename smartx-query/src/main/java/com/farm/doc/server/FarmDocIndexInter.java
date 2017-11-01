package com.farm.doc.server;

import com.farm.lucene.result.DataResult;
import com.kec.smartx.param.dto.CoursePost;

/**
 * 文档全文检索服务
 * 
 * @author gz
 * @version 2017-10-30
 */
public interface FarmDocIndexInter {

	/**
	 * 全文检索
	 * 
	 * @param word
	 * @param pagenum
	 * @return
	 */
	DataResult search(String word, String sid, Integer pagenum) throws Exception;

	/**
	 * 删除文档索引,用指定的id，doc是用来区分权限的
	 * 
	 * @param doc
	 */
	public void delLuceneIndex(String sid,String postId);

	/**
	 * 添加文档索引
	 * 
	 * @param entity
	 */
	public void addLuceneIndex(CoursePost post);


	/**
	 * 重做文档索引
	 * 
	 * @param entity
	 */
	public void reLuceneIndex(String oldIndexId, CoursePost newdoc);
}
