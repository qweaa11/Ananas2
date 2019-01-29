package com.spring.bookmanage.chart.YMHcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bookmanage.chart.YMHservice.YMHChartService;

@Controller
public class ChartYMHController 
{
	@Autowired
	private YMHChartService service;
	
	
	@RequestMapping(value="/libraryOverdueRank.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> libraryOverdueRank(HttpServletRequest request, HttpServletResponse response) 
	{
		
		List<HashMap<String, Object>> returnmapList = new ArrayList<HashMap<String, Object>>();
				 
		List<HashMap<String, String>> list = service.libraryOverdueRankList();
		
		if(list != null)
		{
			for(HashMap<String, String> datamap : list)
			{
				HashMap<String, Object> submap = new HashMap<String, Object>();
				submap.put("", value);
				submap.put("", value);
				submap.put("", value);
				submap.put("", value);
				
				returnmapList.add(submap);
			}
		}
		return returnmapList;
	}// end of libraryOverdueRank()------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
