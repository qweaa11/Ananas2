package com.spring.bookmanage.library.KGBcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class KGBLibraryController {

	@RequestMapping(value="/index.ana", method= {RequestMethod.GET})
	public String index() {

		return "library/index.tiles1";
	}// end of index

}