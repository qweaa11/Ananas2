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
	{	// 도서관별 연체율을 알려주기 위한 메소드
		
		List<HashMap<String, Object>> returnmapList = new ArrayList<HashMap<String, Object>>();
		
		// 도서관의 연체율을 알아오기 위한 메소드
		List<HashMap<String, String>> list = service.libraryOverdueRankList();
		
		if(list != null)
		{
			for(HashMap<String, String> datamap : list)
			{
				// 해쉬맵에 도서관 이름과 퍼센티지를 넣어보낸다
				HashMap<String, Object> submap = new HashMap<String, Object>();
				submap.put("LIBNAME", datamap.get("LIBNAME"));
				submap.put("PERCENTAGE", datamap.get("PERCENTAGE"));
				
				returnmapList.add(submap);
			}
		}
		return returnmapList;
	}// end of libraryOverdueRank()------------------------------------------------
	
	
	@RequestMapping(value="/overdueOfLibraryByGenre.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> overdueOfLibraryByGenre(HttpServletRequest request, HttpServletResponse response) 
	{
		// 도서관 이름 받아오기
		String libname = request.getParameter("libname");
		
		System.out.println(libname);
		
		List<HashMap<String, Object>> jsonMapList = new ArrayList<HashMap<String, Object>>();
				 
		if(libname != null && !libname.trim().isEmpty())
		{
			// 도서관 장르별로 연체율을 알아오기 위한 메소드
			List<HashMap<String, String>> overdueOfGenreList = service.getoverdueByGenreList(libname);
			
			if(overdueOfGenreList != null)
			{
				for(HashMap<String, String> map : overdueOfGenreList)
				{
					HashMap<String, Object> jsonmap = new HashMap<String, Object>();
					
					String gname = map.get("GNAME");
					String percent = map.get("PERCENT");
					
					jsonmap.put("GNAME", gname);
					jsonmap.put("PERCENT",  percent);
					
					jsonMapList.add(jsonmap);
				}
			}
		}
		return jsonMapList;
	}// end of libraryOverdueRank()------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
