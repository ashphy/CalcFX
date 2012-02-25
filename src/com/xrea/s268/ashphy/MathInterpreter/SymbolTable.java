package com.xrea.s268.ashphy.MathInterpreter;

import java.util.Hashtable;

/**
 * �L���\
 * @author Kazuki Hamasaki
 */
public class SymbolTable
{
	/**�L���\*/
	private Hashtable hashtable;

	public SymbolTable()
	{
		hashtable = new Hashtable();

		initSymbols();
	}

	/**����V���{����ǉ�����*/
	private void initSymbols()
	{
		//�O��̓���
		add("ans", 0, false);

		//�~��
		add("PI", Math.PI, true);

		//�l�C�s�A��
		add("E", Math.E, true);
	}

	public void add(String name, double value, boolean isReadOnly)
	{
		VariableData variable = new VariableData(isReadOnly, value);
		hashtable.put(name, variable);
	}

	public void set(String name, double value)
	{
		VariableData varb = (VariableData)hashtable.get(name);
		if(varb != null)
		{
			//renew value into verb
			varb.set(value);
			System.out.println(name + " set " + value);
		}
		else
		{
			//if new verb add
			System.out.println(name + " new set " + value);
			add(name, value, false);
		}
	}

	public Instruction get(String name)
	{
		VariableData variable = (VariableData)hashtable.get(name);
		if(variable == null)
		{
			return new Instruction(Instruction.ID, name);
		}
		else
		{
			return variable.get();
		}
	}

	public double getValue(String name)
	{
		return Double.parseDouble(get(name).message);
	}
}

class VariableData
{
	/**�萔���ǂ���*/
	boolean isReadOnly = false;

	/**�ϐ��̒l*/
	double value;

	public VariableData(boolean isReadOnly, double value)
	{
		this.isReadOnly = isReadOnly;
		this.value = value;
	}

	public void set(double v)
	{
		if(!isReadOnly)
		{
			this.value = v;
		}
	}

	public Instruction get()
	{
		//�l�������ł��邩�m���߂�
		if((long)value == value)
			return new Instruction(Instruction.NUMERIC, String.valueOf((long)this.value));
		else
			return new Instruction(Instruction.NUMERIC, String.valueOf(this.value));
	}
}
