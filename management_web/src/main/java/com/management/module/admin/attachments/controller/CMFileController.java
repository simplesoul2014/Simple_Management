package com.management.module.admin.attachments.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.core.util.DownloadUtil;
import com.core.util.StrUtil;
import com.core.util.UploadUtil;
import com.management.module.admin.attachments.service.CMFileService;
import com.management.module.common.form.FormResponse;
import com.management.module.common.grid.Grid;
import com.management.pojo.admin.attachments.CMFile;

/**
 * 附件管理控制层
 * 
 * @Author 伍锐凡
 * @Date 2012-4-24
 * @Version 1.0
 * @Remark
 */
@Controller
public class CMFileController
{
	@Resource(name = "CMFileService")
	private CMFileService m_oCMFileService;
	
	/**
	 * 获取附件列表
	 * @param oRequest
	 * @return Grid<CMFile>
	 */
	@RequestMapping(value = "/module/admin/attachments/getCMFile.do", method = RequestMethod.POST)
	@ResponseBody
	public Grid<CMFile> getCMFile(HttpServletRequest oRequest)
	{
		String sFileId = StrUtil.toStr(oRequest.getParameter("sFileId"), "0");
		
		List<CMFile> aCMFile = m_oCMFileService.getCMFile(sFileId);
		
		Grid<CMFile> oGrid = new Grid<CMFile>();
		oGrid.setTotal(Long.valueOf(String.valueOf(aCMFile.size())));
		oGrid.setData(aCMFile);
		return oGrid;		
	}
	
	/**
	 * 下载文件
	 * @param oRequest
	 * @param oResponse
	 */
	@RequestMapping(value = "/module/admin/attachments/downloadCMFile.do", method = RequestMethod.GET)
	public void getDownloadCMFile(HttpServletRequest oRequest, HttpServletResponse oResponse)
	{
		Integer nCMFileId = StrUtil.toInt(oRequest.getParameter("nCMFileId"), 0);
		if(nCMFileId > 0)
		{
			CMFile oCMFile = m_oCMFileService.getCMFile(nCMFileId);
			DownloadUtil oDownloadUtil = new DownloadUtil(oCMFile.getPath());
			try
			{
				if(oDownloadUtil.isExists(oCMFile.getAName()))
				{
					oDownloadUtil.download(oResponse, oCMFile.getAName(), oCMFile.getSName());
				}
			} catch (Exception e)
			{
			}			
		}
	}
	
	/**
	 * 增加附件
	 * 
	 * @param oRequest
	 * @param oFile
	 * @return String
	 */
	@RequestMapping(value = "/module/attachments/addCMFile.do", method = RequestMethod.POST)
	public String addCMFile(HttpServletRequest oRequest,
			@RequestParam("attachment") MultipartFile oFile)
	{
		String sPath = StrUtil.toStr(oRequest.getParameter("sPath"), "");
		Integer nCMFileId = m_oCMFileService.addCMFile();
		
		UploadUtil oUploaUtil = new UploadUtil(sPath);
		boolean bIsSuccess = oUploaUtil
				.upload(oFile, String.valueOf(nCMFileId));
		if (bIsSuccess)
		{
			m_oCMFileService.editCMFile(nCMFileId,
					oUploaUtil.getOriginalFilename(),
					oUploaUtil.getSrvFileName(), oUploaUtil.getFileSize(),
					oUploaUtil.getExtName(), oUploaUtil.getUploadDir());
		}
		
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(bIsSuccess ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(bIsSuccess ? "附件增加成功" : "附件增加失败");
		oFormResponse.setParam("nCMFileId", nCMFileId);
		oRequest.setAttribute("sResponse", oFormResponse);
		return "/admin/common/form_response";
	}
}
