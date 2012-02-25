package com.xrea.s268.ashphy.MathInterpreter;

/**
 * Instruction of stack machine
 * @author Kazuki Hamasaki
 */
public class Instruction
{
	public static final int NONE = 0;
	public static final int OPERATOR = 1;
	public static final int UNARY = 2;
	public static final int NUMERIC = 3;
	public static final int ID = 4;
	public static final int ASSIGNMENT = 5;
	public int type = NONE;
	public String message = "";

	public Instruction(int type)
	{
		this(type, "");
	}

	public Instruction(int type, String message)
	{
		this.type = type;
		this.message = message;
	}
}
