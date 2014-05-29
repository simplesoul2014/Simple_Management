package com.management.module.common.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成ExtJs的表格格式
 * @Author 伍锐凡
 * @Date 2012-3-31
 * @Version 1.0
 * @Remark
 */
public class Grid<T>
{
	private Long total = 0L;
	private Integer start = 0;
	private Map<String, Object> aParam = new HashMap<String, Object>();
	private List<T> data = new ArrayList<T>();
	
	public Long getTotal()
	{
		return total;
	}
	
	public void setTotal(Long total)
	{
		this.total = total;
	}
	
	public Integer getStart()
	{
		return start;
	}

	public void setStart(Integer start)
	{
		this.start = start;
	}	
	
	public List<T> getData()
	{
		return data;
	}
	
	public void setData(List<T> data)
	{
		this.data = data;
	}
	
	public void setParam(String sKey, Object oValue)
	{
		aParam.put(sKey, oValue);
	}
	
	public Map<String, Object> getParam()
	{
		return aParam;
	}
}
