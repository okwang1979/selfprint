//grammar TbbFormula;
header {
package  nc.ms.tb.formula.excel.parser;
}
{
	import nc.ms.tb.formula.script.core.parser.*;
}

class ExcelColumnRowLexer extends Lexer;
options{
charVocabulary='\u0000'..'\uFFFF';
k=3;
}

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
FIELD	:	't''r''u''e'|'f''a''l''s''e'


;




STRING_LITERAL
	:	'"' (~('"'))* '"'
	 
	;


NUMBER_LITERAL	:('0'..'9')+('.'('0'..'9')*)?('%')?;
FUNCTION
	:('a'..'z'|'A'..'Z'|'_')+('a'..'z'|'A'..'Z'|'_'|'0'..'9')*  


		{if(LA(1)!='('){
		if(LA(1)=='!'){
			match('!');
			mCOLUMNROW(false);
		}
		if(LA(1)==':'){
			match(':');
			mCOLUMNROW(false);
		}
		else{
			if(LA(1)=='%'){
					match('%');
			}
			if(LA(1)=='.'){
				match('.');
				mNUMBER_LITERAL(false);
			}
		
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(NUMBER_LITERAL);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		}
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(COLUMNROW);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
		return;
		
	}}

	

'('
	;
//excel 
//EXCELSELECT :'!';
//
//SHEETNAME : '\''(~('\''))+'\'';

COLUMNROW : '\''(~('\''))+'\'' '!'('a'..'z'|'A'..'Z')+('1'..'9')('0'..'9')*(':' ('a'..'z'|'A'..'Z')+('1'..'9')('0'..'9')*)?
|
('a'..'z'|'A'..'Z')+('1'..'9')('0'..'9')*(':' ('a'..'z'|'A'..'Z')+('1'..'9')('0'..'9')*)?
|
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
		
	
	}

;

CELLRANGE:':';

VARIABLE	:	'@'({if(LA(1)=='w'&&LA(2)=='h'&&LA(3)=='e'){ break;}}LETTER)+;

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
IN_STRING_QUOT
	:	'\'''\'''\'';
protected QUOT	:	'\'';
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


