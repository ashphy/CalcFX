/* Generated By:JavaCC: Do not edit this line. MathParser.java */
package com.xrea.s268.ashphy.MathParser;

import com.xrea.s268.ashphy.MathInterpreter.Instruction;
import com.xrea.s268.ashphy.MathInterpreter.StackMachine;
import com.xrea.s268.ashphy.MathInterpreter.StringReader;
import java.io.Reader;

public class MathParser implements MathParserConstants {
	private static StackMachine machine = new StackMachine();
	static private boolean jj_initialized_once = false;
	
	/** Generated Token Manager. */
	static public MathParserTokenManager token_source;
	static SimpleCharStream jj_input_stream;

	static final private int[] jj_la1 = new int[20];
	static final private JJCalls[] jj_2_rtns = new JJCalls[2];

	static {
		Reader stream = new StringReader("");

		if (jj_initialized_once) {
			System.out.println("ERROR: Second call to constructor of static parser. ");
			System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
			System.out.println("       during parser generation.");
			throw new Error();
		}
		jj_initialized_once = true;
		jj_input_stream = new SimpleCharStream(stream, 1, 1);
		token_source = new MathParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 20; i++) jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
	}

	public static String parse()
	{
		try
		{
			MathParser.Expression();
		}
		catch(ParseException e)
		{
			System.out.println("Math Parser Version 0.2Beta:  Encountered errors during parse.");
			e.printStackTrace();
		}
		return machine.getResult();
	}

	private static void add(Instruction t)
	{
		System.out.println("TYPE=" + t.type + ", MES=" + t.message);
		machine.add(t);
	}

  static final public void Expression() throws ParseException {
    AssignmentExpression();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 35:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(35);
      AssignmentExpression();
    }
  }

//���
  static final public void AssignmentExpression() throws ParseException {
        String op = null;
    if (jj_2_1(2147483647)) {
      UnaryExpression();
      op = AssignmentOperator();
      AssignmentExpression();
           add(new Instruction(Instruction.ASSIGNMENT , op));
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER_LITERAL:
      case FLOATING_POINT_LITERAL:
      case CHARACTER_LITERAL:
      case STRING_LITERAL:
      case IDENTIFIER:
      case PLUS:
      case MINUS:
      case 47:
        ConstantExpression();
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

//���Z�q
  static final public String AssignmentOperator() throws ParseException {
        String op = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 36:
      jj_consume_token(36);
               op="set";
      break;
    case 37:
      jj_consume_token(37);
               op="mul";
      break;
    case 38:
      jj_consume_token(38);
               op="div";
      break;
    case 39:
      jj_consume_token(39);
               op="rem";
      break;
    case 40:
      jj_consume_token(40);
               op="add";
      break;
    case 41:
      jj_consume_token(41);
               op="sub";
      break;
    case 42:
      jj_consume_token(42);
                op="lshift";
      break;
    case 43:
      jj_consume_token(43);
                op="rshift";
      break;
    case 44:
      jj_consume_token(44);
               op="and";
      break;
    case 45:
      jj_consume_token(45);
               op="xor";
      jj_consume_token(46);
               op="or";
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
           {if (true) return op;}
    throw new Error("Missing return statement in function");
  }

//�]����
  static final public void ConstantExpression() throws ParseException {
    InclusiveORExpression();
  }

//�_���a
  static final public void InclusiveORExpression() throws ParseException {
    ExclusiveORExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OR:
      jj_consume_token(OR);
      InclusiveORExpression();
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
  }

//�r���I�_���a
  static final public void ExclusiveORExpression() throws ParseException {
    ANDExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case XOR:
      jj_consume_token(XOR);
      ExclusiveORExpression();
      break;
    default:
      jj_la1[4] = jj_gen;
      ;
    }
  }

//�_����
  static final public void ANDExpression() throws ParseException {
    ShiftExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case AND:
      jj_consume_token(AND);
      ANDExpression();
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
  }

//�V�t�g���Z
  static final public void ShiftExpression() throws ParseException {
    AdditiveExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LSHIFT:
    case RSHIFT:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LSHIFT:
        jj_consume_token(LSHIFT);
        break;
      case RSHIFT:
        jj_consume_token(RSHIFT);
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      ShiftExpression();
      break;
    default:
      jj_la1[7] = jj_gen;
      ;
    }
  }

//�����Z
  static final public void AdditiveExpression() throws ParseException {
        String op;
    MultiplicativeExpression();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
                               op="PLUS";
        break;
      case MINUS:
        jj_consume_token(MINUS);
                                                     op="MINUS";
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      MultiplicativeExpression();
         add(new Instruction(Instruction.OPERATOR , op));
    }
  }

//��]�Z
  static final public void MultiplicativeExpression() throws ParseException {
        String op;
    Power();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TIMES:
      case DIV:
      case REMAINDER:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TIMES:
        jj_consume_token(TIMES);
                                op="TIMES";
        break;
      case DIV:
        jj_consume_token(DIV);
                                                     op="DIV";
        break;
      case REMAINDER:
        jj_consume_token(REMAINDER);
                                                                              op="REMAINDER";
        break;
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      Power();
         add(new Instruction(Instruction.OPERATOR , op));
    }
  }

//�P����
  static final public void UnaryExpression() throws ParseException {
        Instruction t = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
    case IDENTIFIER:
    case 47:
      PostfixExpression();
      break;
    case PLUS:
    case MINUS:
      t = UnaryOperator();
      PostfixExpression();
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
         if(t != null)add(t);
  }

//�P�����Z�q
  static final public Instruction UnaryOperator() throws ParseException {
        String op;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
      jj_consume_token(PLUS);
                 op="PLUS";
      break;
    case MINUS:
      jj_consume_token(MINUS);
                                       op="MINUS";
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
         {if (true) return new Instruction(Instruction.UNARY , op);}
    throw new Error("Missing return statement in function");
  }

//�֐��Ăяo��
  static final public void PostfixExpression() throws ParseException {
        Token t = null;
    t = PrimaryExpression();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 47:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_4;
      }
      jj_consume_token(47);
      if (jj_2_2(2147483647)) {
        ArgumentExpressionList();
      } else {
        ;
      }
      jj_consume_token(48);
    }
         if(t != null)add(new Instruction(Instruction.ID , t.image));
  }

//�ׂ���
  static final public void Power() throws ParseException {
    Factorial();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case POWER:
      jj_consume_token(POWER);
      Power();
                                       add(new Instruction(Instruction.OPERATOR, "POWER"));
      break;
    default:
      jj_la1[15] = jj_gen;
      ;
    }
  }

//�K�扉�Z�q
  static final public void Factorial() throws ParseException {
    UnaryExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FACTORIAL:
      jj_consume_token(FACTORIAL);
                                         add(new Instruction(Instruction.UNARY , "FACTORIAL"));
      break;
    default:
      jj_la1[16] = jj_gen;
      ;
    }
  }

//primary-expression �͎��ʎq, �����萔, �����萔, ���������_�萔, �萔������, �񋓒萔, ���ʂł�����ꂽ���̂����ꂩ
  static final public Token PrimaryExpression() throws ParseException {
        Token t = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      t = jj_consume_token(IDENTIFIER);
                            {if (true) return t;}
      break;
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
      Constant();
                      {if (true) return t;}
      break;
    case 47:
      jj_consume_token(47);
      Expression();
      jj_consume_token(48);
                               {if (true) return t;}
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

//��̃��X�g
  static final public void ArgumentExpressionList() throws ParseException {
    AssignmentExpression();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 35:
        ;
        break;
      default:
        jj_la1[18] = jj_gen;
        break label_5;
      }
      jj_consume_token(35);
      AssignmentExpression();
    }
  }

//�萔
  static final public void Constant() throws ParseException {
        Token t = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER_LITERAL:
      t = jj_consume_token(INTEGER_LITERAL);
      break;
    case FLOATING_POINT_LITERAL:
      t = jj_consume_token(FLOATING_POINT_LITERAL);
      break;
    case CHARACTER_LITERAL:
      t = jj_consume_token(CHARACTER_LITERAL);
      break;
    case STRING_LITERAL:
      t = jj_consume_token(STRING_LITERAL);
      break;
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
         add(new Instruction(Instruction.NUMERIC , t.image));
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_3R_47() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(33)) {
    jj_scanpos = xsp;
    if (jj_scan_token(34)) return true;
    }
    if (jj_3R_44()) return true;
    return false;
  }

  static private boolean jj_3R_48() {
    if (jj_3R_50()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_51()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_45() {
    if (jj_scan_token(AND)) return true;
    if (jj_3R_42()) return true;
    return false;
  }

  static private boolean jj_3R_22() {
    if (jj_scan_token(35)) return true;
    if (jj_3R_21()) return true;
    return false;
  }

  static private boolean jj_3R_49() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_52()) {
    jj_scanpos = xsp;
    if (jj_3R_53()) return true;
    }
    if (jj_3R_48()) return true;
    return false;
  }

  static private boolean jj_3R_40() {
    if (jj_scan_token(OR)) return true;
    if (jj_3R_36()) return true;
    return false;
  }

  static private boolean jj_3R_46() {
    if (jj_3R_48()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_49()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_43() {
    if (jj_scan_token(XOR)) return true;
    if (jj_3R_39()) return true;
    return false;
  }

  static private boolean jj_3R_44() {
    if (jj_3R_46()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_47()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_37() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(12)) {
    jj_scanpos = xsp;
    if (jj_scan_token(16)) {
    jj_scanpos = xsp;
    if (jj_scan_token(18)) {
    jj_scanpos = xsp;
    if (jj_scan_token(19)) return true;
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_42() {
    if (jj_3R_44()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_45()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_59() {
    if (jj_scan_token(FACTORIAL)) return true;
    return false;
  }

  static private boolean jj_3R_39() {
    if (jj_3R_42()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_43()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_8() {
    if (jj_3R_21()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_22()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_58() {
    if (jj_scan_token(REMAINDER)) return true;
    return false;
  }

  static private boolean jj_3R_36() {
    if (jj_3R_39()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_40()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_33() {
    if (jj_3R_37()) return true;
    return false;
  }

  static private boolean jj_3R_34() {
    if (jj_scan_token(47)) return true;
    if (jj_3R_38()) return true;
    if (jj_scan_token(48)) return true;
    return false;
  }

  static private boolean jj_3R_32() {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  static private boolean jj_3R_27() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_32()) {
    jj_scanpos = xsp;
    if (jj_3R_33()) {
    jj_scanpos = xsp;
    if (jj_3R_34()) return true;
    }
    }
    return false;
  }

  static private boolean jj_3R_55() {
    if (jj_scan_token(POWER)) return true;
    if (jj_3R_50()) return true;
    return false;
  }

  static private boolean jj_3R_31() {
    if (jj_3R_36()) return true;
    return false;
  }

  static private boolean jj_3_2() {
    if (jj_3R_8()) return true;
    return false;
  }

  static private boolean jj_3R_54() {
    if (jj_3R_6()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_59()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_20() {
    if (jj_scan_token(45)) return true;
    if (jj_scan_token(46)) return true;
    return false;
  }

  static private boolean jj_3R_30() {
    if (jj_scan_token(MINUS)) return true;
    return false;
  }

  static private boolean jj_3R_19() {
    if (jj_scan_token(44)) return true;
    return false;
  }

  static private boolean jj_3R_18() {
    if (jj_scan_token(43)) return true;
    return false;
  }

  static private boolean jj_3R_17() {
    if (jj_scan_token(42)) return true;
    return false;
  }

  static private boolean jj_3R_57() {
    if (jj_scan_token(DIV)) return true;
    return false;
  }

  static private boolean jj_3R_16() {
    if (jj_scan_token(41)) return true;
    return false;
  }

  static private boolean jj_3R_35() {
    if (jj_3R_8()) return true;
    return false;
  }

  static private boolean jj_3R_50() {
    if (jj_3R_54()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_55()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_15() {
    if (jj_scan_token(40)) return true;
    return false;
  }

  static private boolean jj_3R_14() {
    if (jj_scan_token(39)) return true;
    return false;
  }

  static private boolean jj_3R_41() {
    if (jj_scan_token(35)) return true;
    if (jj_3R_21()) return true;
    return false;
  }

  static private boolean jj_3R_13() {
    if (jj_scan_token(38)) return true;
    return false;
  }

  static private boolean jj_3R_12() {
    if (jj_scan_token(37)) return true;
    return false;
  }

  static private boolean jj_3R_11() {
    if (jj_scan_token(36)) return true;
    return false;
  }

  static private boolean jj_3R_10() {
    if (jj_3R_24()) return true;
    if (jj_3R_23()) return true;
    return false;
  }

  static private boolean jj_3R_28() {
    if (jj_scan_token(47)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_35()) jj_scanpos = xsp;
    if (jj_scan_token(48)) return true;
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_3R_6()) return true;
    if (jj_3R_7()) return true;
    return false;
  }

  static private boolean jj_3R_7() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_11()) {
    jj_scanpos = xsp;
    if (jj_3R_12()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) {
    jj_scanpos = xsp;
    if (jj_3R_14()) {
    jj_scanpos = xsp;
    if (jj_3R_15()) {
    jj_scanpos = xsp;
    if (jj_3R_16()) {
    jj_scanpos = xsp;
    if (jj_3R_17()) {
    jj_scanpos = xsp;
    if (jj_3R_18()) {
    jj_scanpos = xsp;
    if (jj_3R_19()) {
    jj_scanpos = xsp;
    if (jj_3R_20()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_23() {
    if (jj_3R_27()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_28()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_53() {
    if (jj_scan_token(MINUS)) return true;
    return false;
  }

  static private boolean jj_3R_26() {
    if (jj_3R_31()) return true;
    return false;
  }

  static private boolean jj_3R_21() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_25()) {
    jj_scanpos = xsp;
    if (jj_3R_26()) return true;
    }
    return false;
  }

  static private boolean jj_3R_25() {
    if (jj_3R_6()) return true;
    if (jj_3R_7()) return true;
    if (jj_3R_21()) return true;
    return false;
  }

  static private boolean jj_3R_29() {
    if (jj_scan_token(PLUS)) return true;
    return false;
  }

  static private boolean jj_3R_24() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_29()) {
    jj_scanpos = xsp;
    if (jj_3R_30()) return true;
    }
    return false;
  }

  static private boolean jj_3R_56() {
    if (jj_scan_token(TIMES)) return true;
    return false;
  }

  static private boolean jj_3R_38() {
    if (jj_3R_21()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_41()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_9() {
    if (jj_3R_23()) return true;
    return false;
  }

  static private boolean jj_3R_6() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_9()) {
    jj_scanpos = xsp;
    if (jj_3R_10()) return true;
    }
    return false;
  }

  static private boolean jj_3R_52() {
    if (jj_scan_token(PLUS)) return true;
    return false;
  }

  static private boolean jj_3R_51() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_56()) {
    jj_scanpos = xsp;
    if (jj_3R_57()) {
    jj_scanpos = xsp;
    if (jj_3R_58()) return true;
    }
    }
    if (jj_3R_50()) return true;
    return false;
  }

  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;

  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x19d1000,0x0,0x80000000,0x0,0x40000000,0x0,0x0,0x1800000,0x1800000,0x32000000,0x32000000,0x19d1000,0x1800000,0x0,0x4000000,0x8000000,0x1d1000,0x0,0xd1000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x8,0x8000,0x3ff0,0x0,0x1,0x0,0x6,0x6,0x0,0x0,0x0,0x0,0x8000,0x0,0x8000,0x0,0x0,0x8000,0x8,0x0,};
   }

  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public MathParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public MathParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(); }
    token_source = new MathParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public MathParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new MathParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public MathParser(MathParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(MathParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List jj_expentries = new java.util.ArrayList();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[49];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 20; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 49; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 2; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
