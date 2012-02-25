package com.xrea.s268.ashphy.MathInterpreter;

import java.util.Stack;

import com.xrea.s268.ashphy.ExMath.ExMath;

/**
 * ���z�X�^�b�N�}�V��
 * @author Kazuki Hamasaki
 */
public class StackMachine
{
	private Stack stack;
	private SymbolTable symbols;

	public StackMachine()
	{
		this.stack = new Stack();
		this.symbols = new SymbolTable();
	}

	/**Add instruction into stack*/
	public void add(Instruction t)
	{
		if(t != null)
		{
			switch (t.type)
			{
				case Instruction.NUMERIC:
					stack.push(t);
					break;

				case Instruction.UNARY:
					stack.push(unary(t));
					break;

				case Instruction.OPERATOR:
					stack.push(operator(t));
					break;

				case Instruction.ID:
					stack.push(id(t));
					break;

				case Instruction.ASSIGNMENT:
					stack.push(assignment(t));
					break;
			}
		}
	}

	/**Unary operator*/
	private Instruction unary(Instruction t)
	{
		if(t.message.equals("MINUS"))
		{
			return unaryMinus(t);
		}
		else if(t.message.equals("FACTORIAL"))
		{
			return factorial();
		}
		else
		{
			return (Instruction)stack.pop();
		}
	}

	/**Unary minus operator*/
	private Instruction unaryMinus(Instruction t)
	{
		double x = popNum();
		return makeInstruction(-x);
	}

	private Instruction factorial()
	{
		int x = Integer.parseInt(((Instruction)stack.pop()).message);
		long result = 1;

		for(int i = 1; i < x + 1; i++)
		{
			result *= i;
		}

		return makeInstruction(result);
	}

	/**identifier*/
	private Instruction id(Instruction t)
	{
		if(t.message.equals("sin"))
		{
			return sin();
		}
		else if(t.message.equals("cos"))
		{
			return cos();
		}
		else if(t.message.equals("tan"))
		{
			return tan();
		}
		else if(t.message.equals("log"))
		{
			return log();
		}
		else if(t.message.equals("ln"))
		{
			return ln();
		}
		else if(t.message.equals("sqrt"))
		{
			return sqrt();
		}
		else if(t.message.equals("gcd"))
		{
			return gcd();
		}
		else if(t.message.equals("lcm"))
		{
			return lcm();
		}
		else
		{
			return variable(t);
		}
	}

	/**Assignment operator*/
	private Instruction assignment(Instruction t)
	{
		double num = popNum();
		String id = popStr();

		double result = num;

		if(t.message.equals("set"))
		{
			//=
			//no op
		}
		else if(t.message.equals("add"))
		{
			//+=
			result = Double.parseDouble(symbols.get(id).message) + num;
		}
		else if(t.message.equals("sub"))
		{
			//-=
			result = Double.parseDouble(symbols.get(id).message) - num;
		}
		else if(t.message.equals("mul"))
		{
			//*=
			result = Double.parseDouble(symbols.get(id).message) * num;
		}
		else if(t.message.equals("div"))
		{
			// /=
			result = Double.parseDouble(symbols.get(id).message) / num;
		}
		else if(t.message.equals("rem"))
		{
			//%=
			result = Double.parseDouble(symbols.get(id).message) % num;
		}

		symbols.set(id, result);
		return makeInstruction(result);
	}

	/**Variable processing*/
	private Instruction variable(Instruction t)
	{
		return t;
	}

	private Instruction sin()
	{
		double s = popNum();
		return makeInstruction(Math.sin(s));
	}

	private Instruction cos()
	{
		double s = popNum();
		return makeInstruction(Math.cos(s));
	}

	private Instruction tan()
	{
		double s = popNum();
		return makeInstruction(Math.tan(s));
	}

	private Instruction sqrt()
	{
		double x = popNum();
		return makeInstruction(Math.sqrt(x));
	}

	private Instruction log()
	{
		double base = popNum();
		double x = popNum();
		return makeInstruction(ExMath.log(x, base));
	}

	private Instruction ln()
	{
		double x = popNum();
		return makeInstruction(ExMath.ln(x));
	}

	/**exponential*/
	private Instruction exp()
	{
		double x = popNum();
		return makeInstruction(ExMath.exp(x));
	}

	/**Greatest Common Divisor*/
	private Instruction gcd()
	{
		long y = popLng();
		long x = popLng();
		long result = 0;

		if(x > y)
			result = gcd(x, y);
		else
			result = gcd(y, x);

		return makeInstruction(result);
	}

	private long gcd(long p, long q)
	{
		if(q == 0)
			return p;
		else
			return gcd(q, p % q);
	}

	/**Least Common Multiple*/
	private Instruction lcm()
	{
		long y = popLng();
		long x = popLng();
		long result = 0;

		if(x > y)
			result = x * y / gcd(x, y);
		else
			result = x * y / gcd(y, x);

		return makeInstruction(result);
	}

	/**Process operator*/
	private Instruction operator(Instruction t)
	{
		if(t.message.equals("PLUS"))
		{
			return plus();
		}
		else if(t.message.equals("MINUS"))
		{
			return minus();
		}
		else if(t.message.equals("TIMES"))
		{
			return times();
		}
		else if(t.message.equals("DIV"))
		{
			return div();
		}
		else if(t.message.equals("POWER"))
		{
			return power();
		}
		else if(t.message.equals("REMAINDER"))
		{
			return reminder();
		}
		else
		{
			return t;
		}
	}

	/**Addition*/
	private Instruction plus()
	{
		double y = popNum();
		double x = popNum();

		return makeInstruction(x + y);
	}

	/**Subtraction*/
	private Instruction minus()
	{
		double y = popNum();
		double x = popNum();

		return makeInstruction(x - y);
	}

	/**Multiplication*/
	private Instruction times()
	{
		double y = popNum();
		double x = popNum();

		return makeInstruction(x * y);
	}

	/**Division*/
	private Instruction div()
	{
		double y = popNum();
		double x = popNum();

		return makeInstruction(x / y);
	}

	/**Power*/
	private Instruction power()
	{
		double base = popNum();
		double x = popNum();

		double result = ExMath.pow(x, base);

		return makeInstruction(result);
	}

	/**Remainder*/
	private Instruction reminder()
	{
		long y = popLng();
		long x = popLng();

		return makeInstruction(x % y);
	}

	/**Convert number poped from stack to double*/
	private double popNum()
	{
		Instruction t = (Instruction)stack.pop();
		if(t.type == Instruction.NUMERIC)
		{
			return Double.parseDouble(t.message);
		}
		else if(t.type == Instruction.ID)
		{
			return symbols.getValue(t.message);
		}

		//TODO throw
		return 0;
	}

	/**Convert number poped from stack to long*/
	private long popLng()
	{
		Instruction t = (Instruction)stack.pop();
		if(t.type == Instruction.NUMERIC)
		{
			return Long.parseLong(t.message);
		}
		else if(t.type == Instruction.ID)
		{
			return (long)symbols.getValue(t.message);
		}

		//TODO throw
		return 0;
	}

	/**Return the string from pop*/
	private String popStr()
	{
		return ((Instruction)stack.pop()).message;
	}

	/**Make instruction use double value*/
	private Instruction makeInstruction(double value)
	{
		if((long)value == value)
		{
			return new Instruction(Instruction.NUMERIC, String.valueOf((long)value));
		}
		else
		{
			return new Instruction(Instruction.NUMERIC, String.valueOf(value));
		}
	}

	/**Make instruction use long value*/
	private Instruction makeInstruction(long value)
	{
		return new Instruction(Instruction.NUMERIC, String.valueOf(value));
	}

	public String getResult()
	{
		this.symbols.set("ans", popNum());
		return this.symbols.get("ans").message;
	}
}
