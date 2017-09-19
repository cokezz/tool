@RequestMapping("/download.json")
	public void download(String filename, String filePath,HttpServletRequest request, HttpServletResponse response) {
		
		OutputStream out = null;
		try {
			byte[] bytes = HttpRequestUtil.read(filePath);
			String agent = request.getHeader("USER-AGENT");  
	        if(agent != null && agent.toLowerCase().indexOf("firefox") > 0)//»ðºüä¯ÀÀÆ÷
	        {
	        	filename = new String(filename.getBytes("GB2312"),"ISO-8859-1");    
	        }
	        else
	        {
	        	filename = URLEncoder.encode(filename, "UTF-8");
	        }
			response.addHeader("Content-Disposition", "attachment;filename=" + filename);
			response.addHeader("Content-Length", "" + bytes.length);
			response.setContentType("application/octet-stream");
			out = response.getOutputStream();
			out.write(bytes);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

		}
	}