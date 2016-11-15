package ru.menkin.ea.lec6.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping( "/recipes" )
public class RecipesController
{
	@RequestMapping( value = "", method = RequestMethod.GET )
	public ModelAndView securities( )
		throws Exception
	{
		ModelAndView mav = new ModelAndView( "recipes" );
		
		return mav;
	}
}