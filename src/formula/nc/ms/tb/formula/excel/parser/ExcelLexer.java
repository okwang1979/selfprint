// $ANTLR : "Excel.g" -> "ExcelLexer.java"$

package nc.ms.tb.formula.excel.parser;

import java.io.InputStream;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.ANTLRException;
import java.io.Reader;
import java.util.Hashtable;
import antlr.CharScanner;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.CommonToken;
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.MismatchedCharException;
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.SemanticException;

public class ExcelLexer extends antlr.CharScanner implements
		ExcelParserTokenTypes, TokenStream {

	public ExcelLexer(InputStream in) {
		this(new ByteBuffer(in));
	}

	public ExcelLexer(Reader in) {
		this(new CharBuffer(in));
	}

	public ExcelLexer(InputBuffer ib) {
		this(new LexerSharedInputState(ib));
	}

	public ExcelLexer(LexerSharedInputState state) {
		super(state);
		caseSensitiveLiterals = true;
		setCaseSensitive(true);
		literals = new Hashtable();
	}

	@Override
	public void setTokenObjectClass(String paramString) {
		// TODO Auto-generated method stub
		this.tokenObjectClass = antlr.CommonToken.class;
	}

	public Token nextToken() throws TokenStreamException {
		Token theRetToken = null;
		tryAgain: for (;;) {
			Token _token = null;
			int _ttype = Token.INVALID_TYPE;
			resetText();
			try { // for char stream error handling
				try { // for lexical error handling
					switch (LA(1)) {
					case '?': {
						mASK(true);
						theRetToken = _returnToken;
						break;
					}
					case '<':
					case '=':
					case '>': {
						mRELATION(true);
						theRetToken = _returnToken;
						break;
					}
					case '+': {
						mPLUS(true);
						theRetToken = _returnToken;
						break;
					}
					case '*': {
						mMULTI(true);
						theRetToken = _returnToken;
						break;
					}
					case '/': {
						mDIV(true);
						theRetToken = _returnToken;
						break;
					}
					case '%': {
						mMOD(true);
						theRetToken = _returnToken;
						break;
					}
					case '^': {
						mPOWER(true);
						theRetToken = _returnToken;
						break;
					}
					case '(': {
						mLPAREN(true);
						theRetToken = _returnToken;
						break;
					}
					case ')': {
						mRPAREN(true);
						theRetToken = _returnToken;
						break;
					}
					case ':': {
						mCELLRANGE(true);
						theRetToken = _returnToken;
						break;
					}
					case '"': {
						mSTRING_LITERAL(true);
						theRetToken = _returnToken;
						break;
					}
					case ',': {
						mCOMMA(true);
						theRetToken = _returnToken;
						break;
					}
					case '$': {
						mABS(true);
						theRetToken = _returnToken;
						break;
					}
					case '.': {
						mPOINT(true);
						theRetToken = _returnToken;
						break;
					}
					case ';': {
						mSEMI(true);
						theRetToken = _returnToken;
						break;
					}
					case ']': {
						mRBRACK(true);
						theRetToken = _returnToken;
						break;
					}
					case '{': {
						mLCURLY(true);
						theRetToken = _returnToken;
						break;
					}
					case '}': {
						mRCURLY(true);
						theRetToken = _returnToken;
						break;
					}
					case '\t':
					case '\n':
					case '\r':
					case ' ': {
						mWS(true);
						theRetToken = _returnToken;
						break;
					}
					case '\\': {
						mESC(true);
						theRetToken = _returnToken;
						break;
					}
					default:
						if ((LA(1) == 's') && (LA(2) == 'e') && (LA(3) == 'l')) {
							mSELECT(true);
							theRetToken = _returnToken;
						} else if ((LA(1) == 'f') && (LA(2) == 'r')
								&& (LA(3) == 'o')) {
							mFROM(true);
							theRetToken = _returnToken;
						} else if ((LA(1) == 'u') && (LA(2) == 'p')
								&& (LA(3) == 'd')) {
							mUPDATE(true);
							theRetToken = _returnToken;
						} else if ((LA(1) == 's') && (LA(2) == 'e')
								&& (LA(3) == 't')) {
							mSET(true);
							theRetToken = _returnToken;
						} else if ((LA(1) == 'w') && (LA(2) == 'h')
								&& (LA(3) == 'e')) {
							mWHERE(true);
							theRetToken = _returnToken;
						} else if ((LA(1) == '|') && (LA(2) == '|')) {
							mCONOR(true);
							theRetToken = _returnToken;
						} else if ((LA(1) == '&') && (LA(2) == '&')) {
							mCONAND(true);
							theRetToken = _returnToken;
						} else if ((LA(1) == '-') && (LA(2) == '>')) {
							mDIMJOIN(true);
							theRetToken = _returnToken;
						} else if ((_tokenSet_0.member(LA(1)))
								&& (_tokenSet_1.member(LA(2))) && (true)) {
							mFUNCTION(true);
							theRetToken = _returnToken;
						} else if ((LA(1) == '[')
								&& (_tokenSet_2.member(LA(2)))) {
							mTUPLE(true);
							theRetToken = _returnToken;
						} else if ((LA(1) == '@') && (LA(2) == 'A') && (true)) {
							mALPHA(true);
							theRetToken = _returnToken;
						} else if ((LA(1) == '-') && (true)) {
							mMINUS(true);
							theRetToken = _returnToken;
						} else if (((LA(1) >= '0' && LA(1) <= '9')) && (true) && (true)) {
							mNUMBER_LITERAL(true);
							theRetToken = _returnToken;
						} else if (((LA(1) >= '0' && LA(1) <= '9')) && (true) && (true)) {
							mDIGIT(true);
							theRetToken = _returnToken;
						} else if (((LA(1) >= '0' && LA(1) <= '9')) && (true) && (true)) {
							mINT(true);
							theRetToken = _returnToken;
						} else if ((LA(1) == '&') && (true)) {
							mAMP(true);
							theRetToken = _returnToken;
						} else if ((LA(1) == '[') && (true)) {
							mLBRACK(true);
							theRetToken = _returnToken;
						} else if ((LA(1) == '|') && (true)) {
							mOR(true);
							theRetToken = _returnToken;
						} else {
							mFIELD(true);
							theRetToken = _returnToken;
						}
					}
					if (_returnToken == null)
						continue tryAgain; // found SKIP token
					_ttype = _returnToken.getType();
					_ttype = testLiteralsTable(_ttype);
					_returnToken.setType(_ttype);
					return _returnToken;
				} catch (RecognitionException e) {
					throw new TokenStreamRecognitionException(e);
				}
			} catch (CharStreamException cse) {
				if (cse instanceof CharStreamIOException) {
					throw new TokenStreamIOException(
							((CharStreamIOException) cse).io);
				} else {
					throw new TokenStreamException(cse.getMessage());
				}
			}
		}
	}

	public final void mSELECT(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = SELECT;
		int _saveIndex;

		match('s');
		match('e');
		match('l');
		match('e');
		match('c');
		match('t');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mFROM(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = FROM;
		int _saveIndex;

		match('f');
		match('r');
		match('o');
		match('m');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mUPDATE(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = UPDATE;
		int _saveIndex;

		match('u');
		match('p');
		match('d');
		match('a');
		match('t');
		match('e');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mSET(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = SET;
		int _saveIndex;

		match('s');
		match('e');
		match('t');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mWHERE(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = WHERE;
		int _saveIndex;

		match('w');
		match('h');
		match('e');
		match('r');
		match('e');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mASK(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = ASK;
		int _saveIndex;

		match('?');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mCONOR(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = CONOR;
		int _saveIndex;

		match('|');
		match('|');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mCONAND(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = CONAND;
		int _saveIndex;

		match('&');
		match('&');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mRELATION(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = RELATION;
		int _saveIndex;

		if ((LA(1) == '<') && (LA(2) == '>')) {
			match('<');
			match('>');
		} else if ((LA(1) == '<') && (LA(2) == '=')) {
			match('<');
			match('=');
		} else if ((LA(1) == '>') && (LA(2) == '=')) {
			match('>');
			match('=');
		} else if ((LA(1) == '=')) {
			match('=');
		} else if ((LA(1) == '>') && (true)) {
			match('>');
		} else if ((LA(1) == '<') && (true)) {
			match('<');
		} else {
			throw new NoViableAltForCharException((char) LA(1), getFilename(),
					getLine(), getColumn());
		}

		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mPLUS(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = PLUS;
		int _saveIndex;

		match('+');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mMINUS(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = MINUS;
		int _saveIndex;

		match('-');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mMULTI(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = MULTI;
		int _saveIndex;

		match('*');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mDIV(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = DIV;
		int _saveIndex;

		match('/');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mMOD(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = MOD;
		int _saveIndex;

		match('%');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mPOWER(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = POWER;
		int _saveIndex;

		match('^');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mDIMJOIN(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = DIMJOIN;
		int _saveIndex;

		match('-');
		match('>');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mLPAREN(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = LPAREN;
		int _saveIndex;

		match('(');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mRPAREN(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = RPAREN;
		int _saveIndex;

		match(')');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mFUNCTION(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = FUNCTION;
		int _saveIndex;

		{
			int _cnt316 = 0;
			_loop316: do {
				if (((LA(1) >= 'a' && LA(1) <= 'z'))
						&& (_tokenSet_1.member(LA(2))) && (true)) {
					matchRange('a', 'z');
				} else if (((LA(1) >= 'A' && LA(1) <= 'Z'))
						&& (_tokenSet_1.member(LA(2))) && (true)) {
					matchRange('A', 'Z');
				} else if ((LA(1) == '_') && (_tokenSet_1.member(LA(2)))
						&& (true)) {
					match('_');
				} else {
					if (_cnt316 >= 1) {
						break _loop316;
					} else {
						throw new NoViableAltForCharException((char) LA(1),
								getFilename(), getLine(), getColumn());
					}
				}

				_cnt316++;
			} while (true);
		}
		{
			_loop318: do {
				switch (LA(1)) {
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z': {
					matchRange('a', 'z');
					break;
				}
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z': {
					matchRange('A', 'Z');
					break;
				}
				case '_': {
					match('_');
					break;
				}
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9': {
					matchRange('0', '9');
					break;
				}
				default: {
					break _loop318;
				}
				}
			} while (true);
		}
		{
			if (LA(1) != '(') {
				if (LA(1) == '!') {
					match('!');
					mFIELD(false);
				}
				if (LA(1) == ':') {
					match(':');
					mFIELD(false);
				}
				if (LA(1) == '%') {
					match('%');
					if (_createToken && _token == null && _ttype != Token.SKIP) {
						_token = makeToken(NUMBER_LITERAL);
						_token.setText(new String(text.getBuffer(), _begin,
								text.length() - _begin));
					}
				}
				if (LA(1) != EOF_CHAR) {
					String nextValue = String.valueOf(LA(1));
					if (!"+-*/:&|<>,=)".contains(nextValue)) {
						mUNFIND(false);
					}
				}
				if (_createToken && _token == null && _ttype != Token.SKIP) {
					_token = makeToken(FIELD);
					_token.setText(new String(text.getBuffer(), _begin, text
							.length() - _begin));
				}
				_returnToken = _token;
				return;

			}
			match('(');
		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	/*     */protected Token makeToken(int paramInt)
	/*     */{
		/* 173 */Token localToken = new CommonToken();
		/* 174 */localToken.setType(paramInt);
		/* 175 */localToken.setColumn(this.inputState.getTokenStartColumn());
		/* 176 */localToken.setLine(this.inputState.getTokenStartLine());
		/*     */
		/* 178 */return localToken;
	}

	public final void mFIELD(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = FIELD;
		int _saveIndex;

		switch (LA(1)) {
		case '@': {
			mVARIABLE(false);
			break;
		}
		case '\'': {
			mSHEETNAME(false);
			mCOLUMNROW(false);
			{
				if ((LA(1) == ':')) {
					mCELLRANGE(false);
					mCOLUMNROW(false);
				} else {
				}

			}
			break;
		}
		default:
			if ((LA(1) == 't') && (LA(2) == 'r') && (LA(3) == 'u')) {
				match('t');
				match('r');
				match('u');
				match('e');
			} else if ((LA(1) == 'f') && (LA(2) == 'a') && (LA(3) == 'l')) {
				match('f');
				match('a');
				match('l');
				match('s');
				match('e');
			} else if ((_tokenSet_3.member(LA(1)))
					&& (_tokenSet_4.member(LA(2))) && (true)) {
				mCOLUMNROW(false);
				{
					if ((LA(1) == ':')) {
						mCELLRANGE(false);
						mCOLUMNROW(false);
					} else {
					}

				}
			} else {

				mUNFIND(false);

			}
		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	protected final void mVARIABLE(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = VARIABLE;
		int _saveIndex;

		match('@');
		{
			int _cnt348 = 0;
			_loop348: do {
				if ((_tokenSet_5.member(LA(1)))) {
					if (LA(1) == 'w' && LA(2) == 'h' && LA(3) == 'e') {
						break;
					}
					mLETTER(false);
				} else {
					if (_cnt348 >= 1) {
						break _loop348;
					} else {
						throw new NoViableAltForCharException((char) LA(1),
								getFilename(), getLine(), getColumn());
					}
				}

				_cnt348++;
			} while (true);
		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	protected final void mCOLUMNROW(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = COLUMNROW;
		int _saveIndex;

		{
			int _cnt340 = 0;
			_loop340: do {
				switch (LA(1)) {
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z': {
					matchRange('a', 'z');
					break;
				}
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z': {
					matchRange('A', 'Z');
					break;
				}
				default: {
					if (_cnt340 >= 1) {
						break _loop340;
					} else {
						throw new NoViableAltForCharException((char) LA(1),
								getFilename(), getLine(), getColumn());
					}
				}
				}
				_cnt340++;
			} while (true);
		}
		{
			matchRange('1', '9');
		}
		{
			_loop343: do {
				if (((LA(1) >= '0' && LA(1) <= '9'))) {
					matchRange('0', '9');
				} else {
					break _loop343;
				}

			} while (true);
		}
		// if(LA(1)!=EOF_CHAR){
		// String nextValue = String.valueOf(LA(1));
		// if(!"+-*/:)".contains(nextValue)){
		// mUNFIND(false);
		// }
		// }

		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mCELLRANGE(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = CELLRANGE;
		int _saveIndex;

		match(':');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	protected final void mSHEETNAME(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = SHEETNAME;
		int _saveIndex;

		match('\'');
		{
			int _cnt352 = 0;
			_loop352: do {
				if ((_tokenSet_6.member(LA(1)))) {
					{
						match(_tokenSet_6);
					}
				} else {
					if (_cnt352 >= 1) {
						break _loop352;
					} else {
						throw new NoViableAltForCharException((char) LA(1),
								getFilename(), getLine(), getColumn());
					}
				}

				_cnt352++;
			} while (true);
		}
		match('\'');
		mEXCELSELECT(false);
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mTUPLE(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = TUPLE;
		int _saveIndex;

		match('[');
		{
			int _cnt326 = 0;
			_loop326: do {
				if ((_tokenSet_2.member(LA(1)))) {
					{
						match(_tokenSet_2);
					}
				} else {
					if (_cnt326 >= 1) {
						break _loop326;
					} else {
						throw new NoViableAltForCharException((char) LA(1),
								getFilename(), getLine(), getColumn());
					}
				}

				_cnt326++;
			} while (true);
		}
		match(']');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mSTRING_LITERAL(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = STRING_LITERAL;
		int _saveIndex;

		match('"');
		{
			_loop330: do {
				if ((_tokenSet_7.member(LA(1)))) {
					{
						match(_tokenSet_7);
					}
				} else {
					break _loop330;
				}

			} while (true);
		}
		match('"');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mNUMBER_LITERAL(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = NUMBER_LITERAL;
		int _saveIndex;

		{
			int _cnt333 = 0;
			_loop333: do {
				if (((LA(1) >= '0' && LA(1) <= '9'))) {
					matchRange('0', '9');
				} else {
					if (_cnt333 >= 1) {
						break _loop333;
					} else {
						throw new NoViableAltForCharException((char) LA(1),
								getFilename(), getLine(), getColumn());
					}
				}

				_cnt333++;
			} while (true);
		}
		{
			if ((LA(1) == '.')) {
				match('.');
				{
					_loop336: do {
						if (((LA(1) >= '0' && LA(1) <= '9'))) {
							matchRange('0', '9');
						} else {
							break _loop336;
						}

					} while (true);
				}
			} else {
			}

		}
		{
			if ((LA(1) == '%')) {
				match('%');
			} else {
			}

		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	protected final void mEXCELSELECT(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = EXCELSELECT;
		int _saveIndex;

		match('!');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	protected final void mLETTER(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = LETTER;
		int _saveIndex;

		switch (LA(1)) {
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'f':
		case 'g':
		case 'h':
		case 'i':
		case 'j':
		case 'k':
		case 'l':
		case 'm':
		case 'n':
		case 'o':
		case 'p':
		case 'q':
		case 'r':
		case 's':
		case 't':
		case 'u':
		case 'v':
		case 'w':
		case 'x':
		case 'y':
		case 'z': {
			matchRange('a', 'z');
			break;
		}
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
		case 'J':
		case 'K':
		case 'L':
		case 'M':
		case 'N':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
		case 'S':
		case 'T':
		case 'U':
		case 'V':
		case 'W':
		case 'X':
		case 'Y':
		case 'Z': {
			matchRange('A', 'Z');
			break;
		}
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9': {
			matchRange('0', '9');
			break;
		}
		case '_': {
			match('_');
			break;
		}
		default: {
			throw new NoViableAltForCharException((char) LA(1), getFilename(),
					getLine(), getColumn());
		}
		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	protected final void mUNFIND(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = UNFIND;
		int _saveIndex;

		int i = 0;

		do {
			i = i + 1;
			if (LA(1) != '!' && LA(1) != EOF_CHAR) {
				{
					match(LA(1));
				}
			} else {
				if (LA(1) == EOF_CHAR) {
					_returnToken = makeToken(Token.EOF_TYPE);
					return;
				}
				break;
			}

		} while (true);
		match('!');
		mCOLUMNROW(false);
		if (LA(1) == ':') {
			match(':');
			mCOLUMNROW(false);
		}
		if (1 == 1)
			return;

		{
			_loop355: do {
				switch (LA(1)) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z':
				case '_':
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z': {
					mLETTER(false);
					break;
				}
				case '!': {
					match('!');
					break;
				}
				default: {
					break _loop355;
				}
				}
			} while (true);
		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mCOMMA(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = COMMA;
		int _saveIndex;

		match(',');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mALPHA(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = ALPHA;
		int _saveIndex;

		match('@');
		match('A');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mDIGIT(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = DIGIT;
		int _saveIndex;

		matchRange('0', '9');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mINT(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = INT;
		int _saveIndex;

		{
			int _cnt362 = 0;
			_loop362: do {
				if (((LA(1) >= '0' && LA(1) <= '9'))) {
					matchRange('0', '9');
				} else {
					if (_cnt362 >= 1) {
						break _loop362;
					} else {
						throw new NoViableAltForCharException((char) LA(1),
								getFilename(), getLine(), getColumn());
					}
				}

				_cnt362++;
			} while (true);
		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mAMP(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = AMP;
		int _saveIndex;

		match('&');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mABS(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = ABS;
		int _saveIndex;

		match('$');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mPOINT(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = POINT;
		int _saveIndex;

		match('.');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mSEMI(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = SEMI;
		int _saveIndex;

		match(';');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mLBRACK(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = LBRACK;
		int _saveIndex;

		match('[');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mRBRACK(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = RBRACK;
		int _saveIndex;

		match(']');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mLCURLY(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = LCURLY;
		int _saveIndex;

		match('{');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mRCURLY(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = RCURLY;
		int _saveIndex;

		match('}');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mOR(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = OR;
		int _saveIndex;

		match('|');
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mWS(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = WS;
		int _saveIndex;

		{
			int _cnt374 = 0;
			_loop374: do {
				switch (LA(1)) {
				case ' ': {
					match(' ');
					break;
				}
				case '\t': {
					match('\t');
					break;
				}
				case '\n': {
					match('\n');
					break;
				}
				case '\r': {
					match('\r');
					break;
				}
				default: {
					if (_cnt374 >= 1) {
						break _loop374;
					} else {
						throw new NoViableAltForCharException((char) LA(1),
								getFilename(), getLine(), getColumn());
					}
				}
				}
				_cnt374++;
			} while (true);
		}
		_ttype = Token.SKIP;
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mESC(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = ESC;
		int _saveIndex;

		match('\\');
		{
			switch (LA(1)) {
			case 'n': {
				match('n');
				break;
			}
			case 'r': {
				match('r');
				break;
			}
			case 't': {
				match('t');
				break;
			}
			case 'b': {
				match('b');
				break;
			}
			case 'f': {
				match('f');
				break;
			}
			case '"': {
				match('"');
				break;
			}
			case '\'': {
				match('\'');
				break;
			}
			case '\\': {
				match('\\');
				break;
			}
			case '0':
			case '1':
			case '2':
			case '3': {
				{
					matchRange('0', '3');
				}
				{
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						{
							matchRange('0', '9');
						}
						{
							if (((LA(1) >= '0' && LA(1) <= '9'))) {
								matchRange('0', '9');
							} else {
							}

						}
					} else {
					}

				}
				break;
			}
			case '4':
			case '5':
			case '6':
			case '7': {
				{
					matchRange('4', '7');
				}
				{
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						{
							matchRange('0', '9');
						}
					} else {
					}

				}
				break;
			}
			default: {
				throw new NoViableAltForCharException((char) LA(1),
						getFilename(), getLine(), getColumn());
			}
			}
		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	private static final long[] mk_tokenSet_0() {
		long[] data = new long[1025];
		data[1] = 576460745995190270L;
		return data;
	}

	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());

	private static final long[] mk_tokenSet_1() {
		long[] data = new long[1025];
		data[0] = 287950000686628864L;
		data[1] = 576460745995190270L;
		return data;
	}

	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());

	private static final long[] mk_tokenSet_2() {
		long[] data = new long[2048];
		data[0] = -1L;
		data[1] = -536870913L;
		for (int i = 2; i <= 1023; i++) {
			data[i] = -1L;
		}
		return data;
	}

	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());

	private static final long[] mk_tokenSet_3() {
		long[] data = new long[1025];
		data[1] = 576460743847706622L;
		return data;
	}

	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());

	private static final long[] mk_tokenSet_4() {
		long[] data = new long[1025];
		data[0] = 287667426198290432L;
		data[1] = 576460743847706622L;
		return data;
	}

	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());

	private static final long[] mk_tokenSet_5() {
		long[] data = new long[1025];
		data[0] = 287948901175001088L;
		data[1] = 576460745995190270L;
		return data;
	}

	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());

	private static final long[] mk_tokenSet_6() {
		long[] data = new long[2048];
		data[0] = -549755813889L;
		for (int i = 1; i <= 1023; i++) {
			data[i] = -1L;
		}
		return data;
	}

	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());

	private static final long[] mk_tokenSet_7() {
		long[] data = new long[2048];
		data[0] = -17179869185L;
		for (int i = 1; i <= 1023; i++) {
			data[i] = -1L;
		}
		return data;
	}

	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());

}
