@RequestMapping(value = "exportCourseStatExcel.json")
	public void  exportCourseStatExcel(String courseId, HttpServletResponse response){
		OutputStream out = null;
		try{
			List<PostExamItemStat> lis=loadPostExamItemStat(courseId);
			byte[] bytes = ExportUtils.toPostExitemStatExcel("课程统计", lis);
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("课程统计.xls", "UTF-8"));
			response.addHeader("Content-Length", "" + bytes.length);
			response.setContentType("application/octet-stream");
			out = response.getOutputStream();
			out.write(bytes);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			
		}
	}