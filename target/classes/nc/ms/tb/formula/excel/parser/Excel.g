//grammar TbbFormula;
header {
package  nc.ms.tb.formula.excel.parser;
}
{
	import nc.ms.tb.formula.script.core.parser.*;
}
class ExcelParser extends Parser;


parse returns[Expression expression = new Expression()]:
{
	ConditionalExpression conditionalexpression = condition();
	expression.setConditionalExpression(conditionalexpression);
	//match(1);
}
;
 
 
 
 

condition returns[ConditionalExpression conditionalexpression = new ConditionalExpression()]
	:	
	{
	ConditionalOrExpression conditionalorexpression = conditionOr();
	conditionalexpression.setCondition(conditionalorexpression);
	}
	;

conditionOr returns[ConditionalOrExpression conditionalorexpression = new ConditionalOrExpression()]
	:
	{
		ConditionalAndExpression conditionalandexpression = conditionAnd();
		conditionalorexpression.addAnd(conditionalandexpression);
		ConditionalAndExpression conditionalandexpression1;
	}
	(
	CONOR	{conditionalandexpression1 = conditionAnd();conditionalorexpression.addAnd(conditionalandexpression1);}
	)*	
	;
conditionAnd returns[ConditionalAndExpression conditionalandexpression = new ConditionalAndExpression()]:
	{
        RelationExpression relationexpression = relation();
        conditionalandexpression.addRelation(relationexpression);
        RelationExpression relationexpression1;
	}
	(
	CONAND{
		
		relationexpression1 = relation();
		conditionalandexpression.addRelation(relationexpression1);
	}
	
	)*
;

relation returns[RelationExpression relationexpression = new RelationExpression();]:	
	{AddExpression addexpression = add();
	relationexpression.addAdd(addexpression);
	AddExpression addexpression1;}
	(
		t1:RELATION{relationexpression.addOp(t1.getText());addexpression1 = add();relationexpression.addAdd(addexpression1);}
	)*
;
add returns[AddExpression addexpression = new AddExpression()]:
	{MultiExpression multiexpression = multi();
	addexpression.addMulti(multiexpression);
	MultiExpression multiexpression1;
	}
	(
		t1:MINUS{multiexpression1 = multi(); addexpression.addMulti(multiexpression1);addexpression.addOp(t1.getText());}|
		t2:PLUS{multiexpression1 = multi(); addexpression.addMulti(multiexpression1);addexpression.addOp(t2.getText());}
	)*
	;
	
multi returns[MultiExpression multiexpression = new MultiExpression()]:
		{PowerExpression powerexpression = power();
		multiexpression.addPower(powerexpression);
		PowerExpression powerexpression1;}
		(
			t1:MOD{powerexpression1 = power(); multiexpression.addPower(powerexpression1);  multiexpression.addOp(t1.getText());}|
			t2:MULTI{powerexpression1 = power(); multiexpression.addPower(powerexpression1);multiexpression.addOp(t2.getText());}|
			t3:DIV{powerexpression1 = power(); multiexpression.addPower(powerexpression1);  multiexpression.addOp(t3.getText());}
		)*
	
	
	;
	

power	returns [ PowerExpression powerexpression = new PowerExpression()]:
		{
		UnaryExpression unaryexpression = unary();
		powerexpression.addUnary(unaryexpression);
		UnaryExpression unaryexpression1;
		}
		(POWER{
			unaryexpression1 = unary();
		 	powerexpression.addUnary(unaryexpression1);
		})*	
;


unary returns [UnaryExpression unary = new UnaryExpression()]:
(t1:MINUS{ unary.setOp(t1.getText());}	|
t2:PLUS{ unary.setOp(t1.getText());}	|
t3:MINUS{ unary.setOp(t1.getText());})*
{        Atom atom1 = atom();
        unary.setAtom(atom1);}
	;
	
atom	returns [Atom atom = new Atom()]:
	{Object obj = null;}	
	(obj= ambiguity
	|obj=literal
	|obj=function	
	|obj=tupleset
	|LPAREN obj=condition RPAREN)

{ atom.setObject(obj);}

;	
tupleset returns [TupleSetNode tupleSet = new TupleSetNode()]:
LCURLY
(  {FunctionCall fc; } fc=function {tupleSet.addFunction(fc);} |(t1:TUPLE{tupleSet.addTupleExpress(t1.getText());})+)(DIMJOIN ( {FunctionCall otherFc; } otherFc=function {tupleSet.addFunction(otherFc);}|(t2:TUPLE{tupleSet.addTupleExpress(t2.getText());})+))*
RCURLY
|(t3:TUPLE{tupleSet.addTupleExpress(t3.getText());})+


;
ambiguity returns [Ambiguity ambiguity =   new Ambiguity() ]	:
 t1:FIELD{ambiguity.setStatement(t1.getText());}
//|t2:VARIABLE{ambiguity.setStatement(t2.getText());}
//|t3:COLUMNROW{ambiguity.setStatement(t3.getText());}  (t9:CELLRANGE{ambiguity.setStatement(ambiguity.getStatement()+t9.getText());} t10:COLUMNROW{ambiguity.setStatement(ambiguity.getStatement()+t10.getText());})*
//|t4:SHEETNAME{ambiguity.setStatement(t4.getText());}  t5:EXCELSELECT{ambiguity.setStatement(ambiguity.getStatement()+t5.getText());} t6:COLUMNROW{ambiguity.setStatement(ambiguity.getStatement()+t6.getText());} (t7:CELLRANGE{ambiguity.setStatement(ambiguity.getStatement()+t7.getText());} t8:COLUMNROW{ambiguity.setStatement(ambiguity.getStatement()+t8.getText());})?
 ;
	
literal returns [Literal literal =   new Literal() ]
:

 t1:STRING_LITERAL{literal.setStatement(t1.getText());}
|t2:NUMBER_LITERAL{literal.setStatement(t2.getText());}
//|t3:VARIABLE{literal1.setStatement(t3.getText());}

;
function returns [FunctionCall fc = new FunctionCall();]:	

	f:FUNCTION 
	{
		fc.setName(f.getText().substring(0, f.getText().length()-1));
	}
	
	
	{
	
	 switch(LA(1))
        {
        case MULTI: // '\013'
        case DIV: // '\f'
        case MOD: // '\r'
        case POWER: // '\016'
        default:
            throw new NoViableAltException(LT(1), getFilename());

        case PLUS: // '\t'
        case MINUS: // '\n'
      //  case EXCL: // '\017'
        case SHEETNAME:
        case COLUMNROW:
        case LPAREN: // '\020'
        case FIELD: // '\022'
	    case LCURLY:
        case STRING_LITERAL: // '\026'
        case NUMBER_LITERAL: // '\027'
        case FUNCTION: // '\030'
            ConditionalExpression conditionalexpression = condition();
            fc.addArgument(conditionalexpression);
            ConditionalExpression conditionalexpression1;
          	for(; LA(1) == COMMA;)
			{
				match(COMMA);
				if(LA(1)==COMMA){
					match(COMMA);
					 fc.addArgument("");
				}else if(LA(1)==RPAREN){
				 fc.addArgument("");
				}else{
					conditionalexpression1 = condition();
					 fc.addArgument(conditionalexpression1);
				}
		
			}

            // fall throughr

        case RPAREN: // '\021'
            match(RPAREN);
            return fc;
        }
	
	}

	;
//reportError [RecognitionException paramRecognitionException]:
//	{
//	throw  new  RuntimeException(paramRecognitionException);
//	};
	
	
//	({ ConditionalExpression conditionalexpression ;}
//	conditionalexpression=condition{   fc.addArgument(conditionalexpression);}
//	(COMMA condition{ ConditionalExpression conditionalexpression1;conditionalexpression1 = condition();fc.addArgument(conditionalexpression1);})*)
	
	
	
	//RPAREN	
 

class ExcelLexer extends Lexer;
options{
charVocabulary='\u0000'..'\uFFFF';
k=3;
}
SELECT	:	's''e''l''e''c''t';
FROM	:	'f''r''o''m';
UPDATE 	:	'u''p''d''a''t''e';
SET	:	's''e''t';
WHERE	:	'w''h''e''r''e';
//
ASK 
: '?';


	
CONOR	:'|''|';
CONAND	:'&''&';

RELATION:
		'<''>' 
	|	'<''='
	|	'>''='
	|	'='
	|	'>'
	|	'<'

;


 
PLUS	:	'+';
MINUS	:	'-';
MULTI	:	'*';
DIV	:	'/';
MOD	:	'%';
POWER	:	'^';

DIMJOIN	:	'-''>';
LPAREN:	'('
	;
RPAREN:	')'
	;

FUNCTION
	:('a'..'z'|'A'..'Z'|'_')+('a'..'z'|'A'..'Z'|'_'|'0'..'9')* ({if(LA(1)!='('){
		if(LA(1)=='!'){
			match('!');
			mFIELD(false);
		}
		if(LA(1)==':'){
			match(':');
			mFIELD(false);
		}
		if(LA(1)=='%'){
			match('%');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(NUMBER_LITERAL);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		}
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(FIELD);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
		return;
		
	}}'(')
	;

 FIELD	:	't''r''u''e'|'f''a''l''s''e'


|VARIABLE
|COLUMNROW  ( CELLRANGE   COLUMNROW )?
|SHEETNAME     COLUMNROW  ( CELLRANGE   COLUMNROW )?
|{
	mUNFIND(false);
	}
//{if(_token.getText().endsWith("(")){
//	_ttype  = FUNCTION ;
//}}



;
TUPLE	:		'['(~(']'))+']';



STRING_LITERAL
	:	'"' (~('"'))* '"'
	 
	;

NUMBER_LITERAL	:('0'..'9')+('.'('0'..'9')*)?('%')?;
protected COLUMNROW : ('a'..'z'|'A'..'Z')+('1'..'9')('0'..'9')*;
CELLRANGE:':';

//excel 
protected EXCELSELECT :'!';





protected VARIABLE	:	'@'({if(LA(1)=='w'&&LA(2)=='h'&&LA(3)=='e'){ break;}}LETTER)+;

protected SHEETNAME : '\''(~('\''))+'\''EXCELSELECT
//|('a'..'z'
//	|	'A'..'Z')+(LETTER)*'!'
;

protected UNFIND:
{
		int i=0;
		
			do {
				 i = i+1;
			if (LA(1)!='!'&&String.valueOf(LA(1)).getBytes()[0]!=63) {
				{
				match(LA(1));
				}
			}
			else {
				if(String.valueOf(LA(1)).getBytes()[0]==63){
					_returnToken = makeToken(Token.EOF_TYPE); 
					return ;
				}
				break;
			}
			
		} while (true);
		match('!');
		mCOLUMNROW(false);
		if(LA(1)==':'){
			match(':');
			mCOLUMNROW(false);
		}
		if(1==1) return;
	
	}
(  LETTER |'!')*
;

COMMA : ','
   ;


   
protected LETTER	:	'a'..'z'
	|	'A'..'Z'
	|       '0'..'9'
	|'_';
ALPHA:  '@''A';
DIGIT	:	'0'..'9'	;
	
INT 
	: ('0'..'9')+
	;
//protected IN_STRING_QUOT
//	:	'\'''\'''\'';
//protected QUOT	:	'\'';
AMP	:	'&';

ABS	:	'$';
POINT	:	'.';	
SEMI	:	';';

LBRACK	:	'[';

RBRACK	:	']';

LCURLY	:	'{';
RCURLY	:	'}';

OR	:	'|';


WS : ( 	
' '|'\t'
	|	'\n'
	|	'\r')+
		{ _ttype = Token.SKIP; };


ESC	:	'\\'
		(	'n'
		|	'r'
		|	't'
		|	'b'
		|	'f'
		|	'"'
		|	'\''
		|	'\\'
		|	('0'..'3')
			(
				options {
					warnWhenFollowAmbig = false;
				}
			:	('0'..'9')
				(	
					options {
						warnWhenFollowAmbig = false;
					}
				:	'0'..'9'
				)?
			)?
		|	('4'..'7')
			(
				options {
					warnWhenFollowAmbig = false;
				}
			:	('0'..'9')
			)?
		)
	;
 



//DOUBLE_NUMBER	:(DIGIT)+'.'(DIGIT)+	;


