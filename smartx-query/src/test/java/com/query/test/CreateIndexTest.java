package com.query.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.farm.doc.server.FarmCoursePostIndexManager;
import com.farm.lucene.result.DataResult;
import com.kec.smartx.param.dto.Course;
import com.kec.smartx.param.dto.CoursePost;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(inheritLocations = false, locations = { "classpath:smartquery-context.xml" })
public class CreateIndexTest {

	@Resource
	private FarmCoursePostIndexManager farmCoursePostIndexManager;

	@Test
	public void createIndex() {
		CoursePost cp = new CoursePost();
		Course course = new Course();
		cp.setCourse(course);
		cp.setSid("11e6-fa41-413d1906-b5ed-f47e44");
		cp.setId("11e7-ba14-0a2098e9-b08f-03d904");
		cp.getCourse().setTitle("1026大量题");
		cp.getCourse().setSpeaker("杨洋");
		cp.getCourse().setDes("我们具有稳定高性能的直播推流 SDK，播放器 SDK，连麦互动 SDK，并且覆盖 iOS、Android 、web、PC 等各大系统。");
		farmCoursePostIndexManager.createCoursePostIndex(cp);
	}

	@Test
	public void search() {
		DataResult result = farmCoursePostIndexManager.search("SDK","11e6-fa41-413d1906-b5ed-f47e44",null);
		System.out.println("result:"+result);
	}
}
