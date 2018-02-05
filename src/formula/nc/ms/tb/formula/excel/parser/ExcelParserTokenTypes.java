// $ANTLR : "Excel.g" -> "ExcelLexer.java"$

package  nc.ms.tb.formula.excel.parser;

public interface ExcelParserTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int CONOR = 4;
	int CONAND = 5;
	int RELATION = 6;
	int MINUS = 7;
	int PLUS = 8;
	int MOD = 9;
	int MULTI = 10;
	int DIV = 11;
	int POWER = 12;
	int LPAREN = 13;
	int RPAREN = 14;
	int LCURLY = 15;
	int TUPLE = 16;
	int DIMJOIN = 17;
	int RCURLY = 18;
	int FIELD = 19;
	int STRING_LITERAL = 20;
	int NUMBER_LITERAL = 21;
	int FUNCTION = 22;
	int SELECT = 23;
	int FROM = 24;
	int UPDATE = 25;
	int SET = 26;
	int WHERE = 27;
	int ASK = 28;
	int COLUMNROW = 29;
	int CELLRANGE = 30;
	int EXCELSELECT = 31;
	int VARIABLE = 32;
	int SHEETNAME = 33;
	int UNFIND = 34;
	int COMMA = 35;
	int LETTER = 36;
	int ALPHA = 37;
	int DIGIT = 38;
	int INT = 39;
	int AMP = 40;
	int ABS = 41;
	int POINT = 42;
	int SEMI = 43;
	int LBRACK = 44;
	int RBRACK = 45;
	int OR = 46;
	int WS = 47;
	int ESC = 48;
}
