/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xrea.s268.ashphy.calcfx;

import com.xrea.s268.ashphy.MathInterpreter.StringReader;
import com.xrea.s268.ashphy.MathParser.MathParser;

/**
 * Math Interpreter Wrapper Class betbween Java to JavaFX
 * @author ashphy
 */
public class MathInterpreter {
	
	public String parse(String expression) {

		String res;
		try
		{
			MathParser.ReInit(new StringReader(expression));
			res = MathParser.parse();
		}
		catch(Exception e)
		{
			res = "Error";
		}

		return res;
	}
}
