package com.farm.doc.server.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farm.doc.server.FarmDocIndexInter;
import com.farm.lucene.FarmLuceneFace;
import com.farm.lucene.result.DataResult;
import com.farm.lucene.server.DocIndexInter;
import com.farm.lucene.server.DocQueryImpl;
import com.farm.lucene.server.DocQueryInter;
import com.farm.lucene.util.HtmlUtils;
import com.farm.lucene.util.LuceneCoursePostUtil;
import com.kec.smartx.param.dto.CoursePost;

@Service
public class FarmDocIndexManagerImpl implements FarmDocIndexInter {

	private final static Logger log = Logger.getLogger(FarmDocIndexManagerImpl.class);


	@Override
	@Transactional
	public DataResult search(String word, String sid, Integer pagenum) throws Exception {
		// TITLE,PUBTIME,VISITNUM,TYPENAME,AUTHOR,TAGKEY,DOCDESCRIBE,TEXT
		List<File> files = new ArrayList<File>();
		File file = FarmLuceneFace.inctance().getIndexPathFile("cp" + sid);
		if (file.isDirectory()) {
			files.add(file);
		}
		DocQueryInter query = DocQueryImpl.getInstance(files);
		String iql = null;
		word = HtmlUtils.HtmlRemoveTag(word.trim());
		if (word.indexOf("DES:") >= 0 && iql == null) {
			word = word.substring(word.indexOf("DES:") + 5).replaceAll(":", "");
			iql = "WHERE(DES=" + word + ")";
		}
		if (word.indexOf("SPEAKER:") >= 0 && iql == null) {
			word = word.substring(word.indexOf("SPEAKER:") + 5).replaceAll(":", "");
			iql = "WHERE(SPEAKER=" + word + ")";
		}
		if (word.indexOf("TITLE:") >= 0 && iql == null) {
			word = word.substring(word.indexOf("TITLE:") + 6).replaceAll(":", "");
			iql = "WHERE(TITLE=" + word + ")";
		}
		if (iql == null) {
			// word.substring(word.indexOf("TYPE:"));
			iql = "WHERE(TITLE,DES,SPEAKER=" + word + ")";
		}
		if (pagenum == null) {
			pagenum = 1;
		}
		//WebHotCase.putCase(word);
		DataResult result = query.queryByMultiIndex(iql, pagenum, 10).getDataResult();
		/*for (Map<String, Object> node : result.getResultList()) {
			String tags = node.get("DES").toString();
			String text = node.get("SPEAKER").toString();
			String speaker = node.get("SPEAKER").toString();
			node.put("DOCDESCRIBE", text.length() > 256 ? text.substring(0, 256) : text);
			if (tags != null && tags.trim().length() > 0) {
				String[] tags1 = tags.trim().replaceAll("，", ",").replaceAll("、", ",").split(",");
				node.put("TAGKEY", Arrays.asList(tags1));
			} else {
				node.put("TAGKEY", new ArrayList<String>());
			}
		}*/
		return result;
	}

	/**
	 * 添加索引
	 * 
	 * @param entity
	 */
	@Override
	@Transactional
	public void addLuceneIndex(CoursePost post) {
		// 做索引
		{
			DocIndexInter sidIndex = null;
			
			try {
				FarmLuceneFace luceneImpl = FarmLuceneFace.inctance();
					// 创建分类索引
				sidIndex = luceneImpl.getDocIndex(luceneImpl.getIndexPathFile("cp" + post.getSid()));
				sidIndex.indexDoc(LuceneCoursePostUtil.getDocMap(post));
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if (sidIndex != null) {
						sidIndex.close();
					}
				} catch (Exception e1) {
					log.error("lucene error:" + e1);
				}
			}
		}
	}


	@Override
	@Transactional
	public void delLuceneIndex(String sid,String postId) {
		// 做索引
		{
			DocIndexInter sidIndex = null;
			try {
				FarmLuceneFace luceneImpl = FarmLuceneFace.inctance();
				// 删除分类索引
				sidIndex = luceneImpl.getDocIndex(luceneImpl.getIndexPathFile("cp" + sid));
				sidIndex.deleteFhysicsIndex(postId);
			} catch (Exception e) {
				throw new RuntimeException(e + "删除索引");
			} finally {
				try {
					if (sidIndex != null) {
						sidIndex.close();
					}
				} catch (Exception e1) {
					log.error("lucene error:" + e1);
				}
			}
		}

	}

	/**
	 * 重做索引
	 * 
	 * @param entity
	 */
	@Override
	@Transactional
	public void reLuceneIndex(String oldIndexId, CoursePost newdoc) {
		delLuceneIndex(newdoc.getSid(),oldIndexId);
		addLuceneIndex(newdoc);
	}
}
