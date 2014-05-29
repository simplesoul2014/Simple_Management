package com.management.module.common.downLoad;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.core.util.DownloadUtil;
import com.core.util.StrUtil;
import com.management.module.admin.attachments.service.CMFileService;
import com.management.pojo.admin.attachments.CMFile;
 
 
/**
 * 文件下载
 * @Author 伍锐凡
 * @Date 2012-3-15
 * @Version 1.0
 * @Remark 
 */
@Controller
public class FileDownLoadController
{

	@Resource(name = "CMFileService")
	private CMFileService m_oCMFileService;
	
	/**
	 * 技能鉴定 首面 【下载文件】
	 * 
	 * @param oRequest
	 * @return
	 */
	@RequestMapping(value = "/module/file/fileDownLoad.do", method = RequestMethod.GET)
	public void fileDownLoad(HttpServletResponse oResponse,
			HttpServletRequest oRequest) throws Exception
	{
		int nFileId = StrUtil.toInt(oRequest.getParameter("nFileId"), 0); 
		CMFile CMFile = m_oCMFileService.getCMFile(nFileId);
		if (null == CMFile)
		{
			throw new Exception("找不着文件数据!");
		}
		String downfileNameNew = CMFile.getAName();
		String downfileNameOld = CMFile.getSName();
		String downfilePath = CMFile.getPath().replaceAll("\\\\", "/");
		DownloadUtil DownloadUtil = new DownloadUtil(downfilePath);
		DownloadUtil.download(oResponse, downfileNameNew, downfileNameOld);
	}

}
