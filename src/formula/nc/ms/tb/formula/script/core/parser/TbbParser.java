// $ANTLR 2.7.7 (20060906): "TbbFormula.g" -> "TbbParser.java"$

package  nc.ms.tb.formula.script.core.parser;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

public class TbbParser extends antlr.LLkParser       implements TbbParserTokenTypes
 {

protected TbbParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public TbbParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected TbbParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public TbbParser(TokenStream lexer) {
  this(lexer,1);
}

public TbbParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final Expression  parse() throws RecognitionException, TokenStreamException {
		Expression expression = new Expression();
		
		
		try {      // for error handling
			
				ConditionalExpression conditionalexpression = condition();
				expression.setConditionalExpression(conditionalexpression);
				match(1);
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		return expression;
	}
	
	public final TbbFormulaExpression  tbbrule() throws RecognitionException, TokenStreamException {
		TbbFormulaExpression tbbExpression = new TbbFormulaExpression();
		
		Token  t1 = null;
		Token  t2 = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case SELECT:
			{
				match(SELECT);
				
//					 		tbbExpression.setExpressionType(TbbFormulaExpression.TYPE_SELECT);
//					 		ConditionalExpression conditionalexpression = condition();
//					 		tbbExpression.getBodyExpressionList().add(conditionalexpression); 
					 	
				{
				_loop4:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						ConditionalExpression otherExpression = condition();tbbExpression.getBodyExpressionList().add(otherExpression);
					}
					else {
						break _loop4;
					}
					
				} while (true);
				}
				match(FROM);
				t1 = LT(1);
				match(STRING_LITERAL);
				tbbExpression.setCubeCode(t1.getText());
				match(WHERE);
				ConditionalExpression whereExpression = condition(); tbbExpression.setWhereExpress(whereExpression);
				break;
			}
			case UPDATE:
			{
				match(UPDATE);
				t2 = LT(1);
				match(STRING_LITERAL);
				tbbExpression.setCubeCode(t2.getText());
				match(SET);
				
//					 		tbbExpression.setExpressionType(TbbFormulaExpression.TYPE_UPDATE);
					 		ConditionalExpression conditionalexpression = condition();
					 		tbbExpression.getBodyExpressionList().add(conditionalexpression); 
					 	
				match(WHERE);
				ConditionalExpression whereExpression = condition(); 
//				tbbExpression.setWhereExpress(whereExpression);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		return tbbExpression;
	}
	
	public final ConditionalExpression  condition() throws RecognitionException, TokenStreamException {
		ConditionalExpression conditionalexpression = new ConditionalExpression();
		
		
		try {      // for error handling
			
				ConditionalOrExpression conditionalorexpression = conditionOr();
				conditionalexpression.setCondition(conditionalorexpression);
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		return conditionalexpression;
	}
	
	public final ConditionalOrExpression  conditionOr() throws RecognitionException, TokenStreamException {
		ConditionalOrExpression conditionalorexpression = new ConditionalOrExpression();
		
		
		try {      // for error handling
			
					ConditionalAndExpression conditionalandexpression = conditionAnd();
					conditionalorexpression.addAnd(conditionalandexpression);
					ConditionalAndExpression conditionalandexpression1;
				
			{
			_loop8:
			do {
				if ((LA(1)==CONOR)) {
					match(CONOR);
					conditionalandexpression1 = conditionAnd();conditionalorexpression.addAnd(conditionalandexpression1);
				}
				else {
					break _loop8;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		return conditionalorexpression;
	}
	
	public final ConditionalAndExpression  conditionAnd() throws RecognitionException, TokenStreamException {
		ConditionalAndExpression conditionalandexpression = new ConditionalAndExpression();
		
		
		try {      // for error handling
			
			RelationExpression relationexpression = relation();
			conditionalandexpression.addRelation(relationexpression);
			RelationExpression relationexpression1;
				
			{
			_loop11:
			do {
				if ((LA(1)==CONAND)) {
					match(CONAND);
					
							
							relationexpression1 = relation();
							conditionalandexpression.addRelation(relationexpression1);
						
				}
				else {
					break _loop11;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		return conditionalandexpression;
	}
	
	public final RelationExpression  relation() throws RecognitionException, TokenStreamException {
		RelationExpression relationexpression = new RelationExpression();;
		
		Token  t1 = null;
		
		try {      // for error handling
			AddExpression addexpression = add();
				relationexpression.addAdd(addexpression);
				AddExpression addexpression1;
			{
			_loop14:
			do {
				if ((LA(1)==RELATION)) {
					t1 = LT(1);
					match(RELATION);
					relationexpression.addOp(t1.getText());addexpression1 = add();relationexpression.addAdd(addexpression1);
				}
				else {
					break _loop14;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		return relationexpression;
	}
	
	public final AddExpression  add() throws RecognitionException, TokenStreamException {
		AddExpression addexpression = new AddExpression();
		
		Token  t1 = null;
		Token  t2 = null;
		
		try {      // for error handling
			MultiExpression multiexpression = multi();
				addexpression.addMulti(multiexpression);
				MultiExpression multiexpression1;
				
			{
			_loop17:
			do {
				switch ( LA(1)) {
				case MINUS:
				{
					t1 = LT(1);
					match(MINUS);
					multiexpression1 = multi(); addexpression.addMulti(multiexpression1);addexpression.addOp(t1.getText());
					break;
				}
				case PLUS:
				{
					t2 = LT(1);
					match(PLUS);
					multiexpression1 = multi(); addexpression.addMulti(multiexpression1);addexpression.addOp(t2.getText());
					break;
				}
				default:
				{
					break _loop17;
				}
				}
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		return addexpression;
	}
	
	public final MultiExpression  multi() throws RecognitionException, TokenStreamException {
		MultiExpression multiexpression = new MultiExpression();
		
		Token  t1 = null;
		Token  t2 = null;
		Token  t3 = null;
		
		try {      // for error handling
			PowerExpression powerexpression = power();
					multiexpression.addPower(powerexpression);
					PowerExpression powerexpression1;
			{
			_loop20:
			do {
				switch ( LA(1)) {
				case MOD:
				{
					t1 = LT(1);
					match(MOD);
					powerexpression1 = power(); multiexpression.addPower(powerexpression1);  multiexpression.addOp(t1.getText());
					break;
				}
				case MULTI:
				{
					t2 = LT(1);
					match(MULTI);
					powerexpression1 = power(); multiexpression.addPower(powerexpression1);multiexpression.addOp(t2.getText());
					break;
				}
				case DIV:
				{
					t3 = LT(1);
					match(DIV);
					powerexpression1 = power(); multiexpression.addPower(powerexpression1);  multiexpression.addOp(t3.getText());
					break;
				}
				default:
				{
					break _loop20;
				}
				}
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		return multiexpression;
	}
	
	public final PowerExpression  power() throws RecognitionException, TokenStreamException {
		 PowerExpression powerexpression = new PowerExpression();
		
		
		try {      // for error handling
			
					UnaryExpression unaryexpression = unary();
					powerexpression.addUnary(unaryexpression);
					UnaryExpression unaryexpression1;
					
			{
			_loop23:
			do {
				if ((LA(1)==POWER)) {
					match(POWER);
					
								unaryexpression1 = unary();
							 	powerexpression.addUnary(unaryexpression1);
							
				}
				else {
					break _loop23;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		return powerexpression;
	}
	
	public final UnaryExpression  unary() throws RecognitionException, TokenStreamException {
		UnaryExpression unary = new UnaryExpression();
		
		Token  t1 = null;
		Token  t2 = null;
		Token  t3 = null;
		
		try {      // for error handling
			{
			if ((LA(1)==MINUS)) {
				t1 = LT(1);
				match(MINUS);
				unary.setOp(t1.getText());
			}
			else if ((LA(1)==PLUS)) {
				t2 = LT(1);
				match(PLUS);
				unary.setOp(t2.getText());
			}
			else if ((LA(1)==MINUS)) {
				t3 = LT(1);
				match(MINUS);
				unary.setOp(t3.getText());
			}
			else {
				//throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			Atom atom1 = atom();
			unary.setAtom(atom1);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		return unary;
	}
	
	public final Atom  atom() throws RecognitionException, TokenStreamException {
		Atom atom = new Atom();
		
		
		try {      // for error handling
			Object obj = null;
			{
			switch ( LA(1)) {
			case FIELD:
			case VARIABLE:
			{
				obj=ambiguity();
				break;
			}
			case STRING_LITERAL:
			case NUMBER_LITERAL:
			{
				obj=literal();
				break;
			}
			case FUNCTION:
			{
				obj=function();
				break;
			}
			case LCURLY:
			case TUPLE:
			{
				obj=tupleset();
				break;
			}
			case LPAREN:
			{
				match(LPAREN);
				obj=condition();
				match(RPAREN);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			atom.setObject(obj);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		return atom;
	}
	
	public final Ambiguity  ambiguity() throws RecognitionException, TokenStreamException {
		Ambiguity ambiguity =   new Ambiguity() ;
		
		Token  t1 = null;
		Token  t2 = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case FIELD:
			{
				t1 = LT(1);
				match(FIELD);
				ambiguity.setStatement(t1.getText());
				break;
			}
			case VARIABLE:
			{
				t2 = LT(1);
				match(VARIABLE);
				ambiguity.setStatement(t2.getText());
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		return ambiguity;
	}
	
	public final Literal  literal() throws RecognitionException, TokenStreamException {
		Literal literal =   new Literal() ;
		
		Token  t1 = null;
		Token  t2 = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case STRING_LITERAL:
			{
				t1 = LT(1);
				match(STRING_LITERAL);
				literal.setStatement(t1.getText());
				break;
			}
			case NUMBER_LITERAL:
			{
				t2 = LT(1);
				match(NUMBER_LITERAL);
				literal.setStatement(t2.getText());
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		return literal;
	}
	
	public final FunctionCall  function() throws RecognitionException, TokenStreamException {
		FunctionCall fc = new FunctionCall();;
		
		Token  f = null;
		
		try {      // for error handling
			f = LT(1);
			match(FUNCTION);
			
					fc.setName(f.getText().substring(0, f.getText().length()-1));
				
			
				
				 switch(LA(1))
			{
			case MULTI: // '\013'
			case DIV: // '\f'
			case MOD: // '\r'
			case POWER: // '\016'
//			default:
//			throw new NoViableAltException(LT(1), getFilename());
			
			case PLUS: // '\t'
			case MINUS: // '\n'
			case EXCL: // '\017'
			case LPAREN: // '\020'
			case FIELD: // '\022'
				case LCURLY:
			case STRING_LITERAL: // '\026'
			case NUMBER_LITERAL: // '\027'
			case FUNCTION: // '\030'
			ConditionalExpression conditionalexpression = condition();
			fc.addArgument(conditionalexpression);
			ConditionalExpression conditionalexpression1;
			for(; LA(1) == COMMA; fc.addArgument(conditionalexpression1))
			{
			match(COMMA);
			conditionalexpression1 = condition();
			}
			
			// fall through
			
			case RPAREN: // '\021'
			match(RPAREN);
			return fc;
			}
				
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
		return fc;
	}
	
	public final TupleSetNode  tupleset() throws RecognitionException, TokenStreamException {
		TupleSetNode tupleSet = new TupleSetNode();
		
		Token  t1 = null;
		Token  t2 = null;
		Token  t3 = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LCURLY:
			{
				match(LCURLY);
				{
				switch ( LA(1)) {
				case FUNCTION:
				{
					FunctionCall fc;
					fc=function();
					tupleSet.addFunction(fc);
					break;
				}
				case TUPLE:
				{
					{
					int _cnt31=0;
					_loop31:
					do {
						if ((LA(1)==TUPLE)) {
							t1 = LT(1);
							match(TUPLE);
							tupleSet.addTupleExpress(t1.getText());
						}
						else {
							if ( _cnt31>=1 ) { break _loop31; } else {throw new NoViableAltException(LT(1), getFilename());}
						}
						
						_cnt31++;
					} while (true);
					}
					break;
				}
				case RCURLY:
					
				break;
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				_loop36:
				do {
					if ((LA(1)==DIMJOIN)) {
						match(DIMJOIN);
						{
						switch ( LA(1)) {
						case FUNCTION:
						{
							FunctionCall otherFc;
							otherFc=function();
							tupleSet.addFunction(otherFc);
							break;
						}
						case TUPLE:
						{
							{
							int _cnt35=0;
							_loop35:
							do {
								if ((LA(1)==TUPLE)) {
									t2 = LT(1);
									match(TUPLE);
									tupleSet.addTupleExpress(t2.getText());
								}
								else {
									if ( _cnt35>=1 ) { break _loop35; } else {throw new NoViableAltException(LT(1), getFilename());}
								}
								
								_cnt35++;
							} while (true);
							}
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
					}
					else {
						break _loop36;
					}
					
				} while (true);
				}
				match(RCURLY);
				break;
			}
			case TUPLE:
			{
				{
				int _cnt38=0;
				_loop38:
				do {
					if ((LA(1)==TUPLE)) {
						t3 = LT(1);
						match(TUPLE);
						tupleSet.addTupleExpress(t3.getText());
					}
					else {
						if ( _cnt38>=1 ) { break _loop38; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt38++;
				} while (true);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		return tupleSet;
	}
	
	public final void reportError(RecognitionException paramRecognitionException) {
				throw  new  RuntimeException(paramRecognitionException);
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"SELECT",
		"COMMA",
		"FROM",
		"STRING_LITERAL",
		"WHERE",
		"UPDATE",
		"SET",
		"CONOR",
		"CONAND",
		"RELATION",
		"MINUS",
		"PLUS",
		"MOD",
		"MULTI",
		"DIV",
		"POWER",
		"LPAREN",
		"RPAREN",
		"LCURLY",
		"TUPLE",
		"DIMJOIN",
		"RCURLY",
		"FIELD",
		"VARIABLE",
		"NUMBER_LITERAL",
		"FUNCTION",
		"ASK",
		"COLON",
		"EXCL",
		"LETTER",
		"ALPHA",
		"DIGIT",
		"INT",
		"IN_STRING_QUOT",
		"QUOT",
		"AMP",
		"ABS",
		"POINT",
		"SEMI",
		"LBRACK",
		"RBRACK",
		"OR",
		"WS",
		"ESC"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 2097152L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 50331650L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	
	}
