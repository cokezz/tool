package com.farm.lucene.util;

import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

import com.farm.lucene.adapter.DocMap;
import com.kec.smartx.param.dto.CoursePost;

/**
 * 生成全文检索时辅助生成索引元数据对象
 * 
 * @author gz
 * 
 */
public class LuceneCoursePostUtil {
	public static DocMap getDocMap(CoursePost post) {
		DocMap map = new DocMap(post.getId());
		
		map.put("SPEAKER", post.getCourse().getSpeaker(), Store.YES, Index.ANALYZED);
		map.put("TITLE", post.getCourse().getTitle(), Store.YES, Index.ANALYZED);
		map.put("DES", post.getCourse().getDes(), Store.YES, Index.ANALYZED);
		map.put("POSTID", post.getId(), Store.YES, Index.ANALYZED);
		return map;
	}

	public static DocMap convertFileMap(DocMap docmap, String fileId, String title, String text) {
		docmap.put("DOCID",docmap.getValue("ID"), Store.YES, Index.ANALYZED);
		docmap.put("ID", fileId, Store.YES, Index.ANALYZED);
		docmap.put("DOMTYPE", "file", Store.YES, Index.ANALYZED);
		docmap.put("TEXT", text, Store.YES, Index.ANALYZED);
		docmap.put("TITLE", title, Store.YES, Index.ANALYZED);
		return docmap;
	}
}