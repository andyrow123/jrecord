package net.sf.JRecord.cg.common;

import java.util.Arrays;

import net.sf.JRecord.Common.Constants;
import net.sf.JRecord.Types.Type;

public class CCode {

    private static final String[] JAVA_TYPE_NAME = new String[Type.LAST_SYSTEM_TYPE];
    private static final String[] RECORD_TYPES = new String[20];
    private static final String[] IO_TYPE = new String[150];
    
    static {
    	Arrays.fill(JAVA_TYPE_NAME, null);
    	Arrays.fill(RECORD_TYPES, null);
    }

	public static String toSuffix(StringBuilder b) {
		if (b == null || b.length() == 0) {
			return "";
		}
		b.setCharAt(0, Character.toUpperCase(b.charAt(0)));
		return b.toString();
	}
	
	public static String toFieldName(StringBuilder b) {
		return toJavaId(b, 'f');
	}
	
	public static String toClassName(StringBuilder b) {
		return toJavaId(b, 'c');
	}

	
	private static String toJavaId(StringBuilder b, char pref) {
		if (b == null || b.length() == 0) {
			return "";
		}
		
		if (b.charAt(0) < 'a' || b.charAt(0) > 'z') {
			b.insert(0, pref);
		}
		return b.toString();
	}

	
	public static StringBuilder cobolName2JavaName(String cobolName) {
		String lcCobolName = cobolName.toLowerCase();
		String ucCobolName = cobolName.toUpperCase();
		int length = cobolName.length();
		StringBuilder b = new StringBuilder(length);

		boolean toUCase = false; 
		char c;
		
		for (int i = 0; i < length; i++) {
			c = lcCobolName.charAt(i);
			switch (c) {
			case ':':
			case ';':
			case '.':
			case '*':
			case '=':
			case '+':
			case '\'':
			case '\"':
			case '~':
			case '!':
			case '|':
			case '@':
			case '#':
			case '$':
			case '%':
			case ')':
			case '[':
			case ']':
				break;
			case '(':
			case ',':
				b.append('_');
				toUCase = false;
				break;
			case ' ':
			case '-':
			case '_':
				toUCase = true;
				break;
			default:
				if (toUCase) {
					b.append(ucCobolName.charAt(i));
					toUCase = false;
				} else {
					b.append(c);
				}
			}
		}
		return b;
	}
	
	/**
	 * Get the JRecord-Constant Type name for a type. This is for use in code generation
	 * 
	 * @param type JRecord Type
	 * 
	 * @return JRecord-Constant 
	 */
	public static String getJRecordTypeName(int type) {
		initTypeNames();
		
		if (type < 0 || type > JAVA_TYPE_NAME.length || JAVA_TYPE_NAME[type] == null) {
			return Integer.toString(type);
		}
		
		return "Type." + JAVA_TYPE_NAME[type];
	}
	
	@SuppressWarnings("deprecation")
	private static void initTypeNames() {
		if (JAVA_TYPE_NAME[0] == null || JAVA_TYPE_NAME [Type.ftHtmlField ] == null) {		
			JAVA_TYPE_NAME [Type.ftChar                     ] = "ftChar";
			JAVA_TYPE_NAME [Type.ftCharRightJust            ] = "ftCharRightJust";
			JAVA_TYPE_NAME [Type.ftCharNullTerminated       ] = "ftCharNullTerminated             ";
			JAVA_TYPE_NAME [Type.ftCharNullPadded           ] = "ftCharNullPadded";
			
			JAVA_TYPE_NAME [Type.ftHex                      ] = "ftHex";
			JAVA_TYPE_NAME [Type.ftNumLeftJustified         ] = "ftNumLeftJustified";
			JAVA_TYPE_NAME [Type.ftNumRightJustified        ] = "ftNumRightJustified                    ";
			JAVA_TYPE_NAME [Type.ftNumZeroPadded            ] = "ftNumZeroPadded";
			JAVA_TYPE_NAME [Type.ftAssumedDecimal           ] = "ftAssumedDecimal";
			JAVA_TYPE_NAME [Type.ftSignSeparateLead         ] = "ftSignSeparateLead";
			JAVA_TYPE_NAME [Type.ftSignSeparateTrail        ] = "ftSignSeparateTrail";
			JAVA_TYPE_NAME [Type.ftDecimal                  ] = "ftDecimal";
			JAVA_TYPE_NAME [Type.ftBinaryInt                ] = "ftBinaryInt";
			JAVA_TYPE_NAME [Type.ftPostiveBinaryInt         ] = "ftPostiveBinaryInt";
			JAVA_TYPE_NAME [Type.ftFloat                    ] = "ftFloat";
			JAVA_TYPE_NAME [Type.ftDouble                   ] = "ftDouble";
			JAVA_TYPE_NAME [Type.ftNumAnyDecimal            ] = "ftNumAnyDecimal";
			JAVA_TYPE_NAME [Type.ftPositiveNumAnyDecimal    ] = "ftPositiveNumAnyDecimal";
			JAVA_TYPE_NAME [Type.ftBit                      ] = "ftBit";
			JAVA_TYPE_NAME [Type.ftAssumedDecimalPositive   ] = "ftAssumedDecimalPositive";
			JAVA_TYPE_NAME [Type.ftBinaryIntPositive        ] = "ftBinaryIntPositive";

			JAVA_TYPE_NAME [Type.ftNumZeroPaddedPN          ] = "ftNumZeroPaddedPN";
			JAVA_TYPE_NAME [Type.ftNumZeroPaddedPositive    ] = "ftNumZeroPaddedPositive";
			JAVA_TYPE_NAME [Type.ftNumCommaDecimal          ] = "ftNumCommaDecimal";
			JAVA_TYPE_NAME [Type.ftNumCommaDecimalPN        ] = "ftNumCommaDecimalPN";
			JAVA_TYPE_NAME [Type.ftNumCommaDecimalPositive  ] = "ftNumCommaDecimalPositive";

			JAVA_TYPE_NAME [Type.ftNumRightJustifiedPN      ] = "ftNumRightJustifiedPN";
			
			JAVA_TYPE_NAME [Type.ftPackedDecimal            ] = "ftPackedDecimal";
			JAVA_TYPE_NAME [Type.ftZonedNumeric             ] = "ftZonedNumeric";
			JAVA_TYPE_NAME [Type.ftPackedDecimalPostive     ] = "ftPackedDecimalPostive";
			JAVA_TYPE_NAME [Type.ftBinaryBigEndian          ] = "ftBinaryBigEndian";                                                      
			JAVA_TYPE_NAME [Type.ftBinaryBigEndianPositive  ] = "ftBinaryBigEndianPositive";
			JAVA_TYPE_NAME [Type.ftPositiveBinaryBigEndian  ] = "ftPositiveBinaryBigEndian";
			JAVA_TYPE_NAME [Type.ftRmComp                   ] = "ftRmComp";
			JAVA_TYPE_NAME [Type.ftRmCompPositive           ] = "ftRmCompPositive";

			JAVA_TYPE_NAME [Type.ftFjZonedNumeric           ] = "ftFjZonedNumeric";
			JAVA_TYPE_NAME [Type.ftNumRightJustCommaDp      ] = "ftNumRightJustCommaDp";
			JAVA_TYPE_NAME [Type.ftNumRightJustCommaDpPN    ] = "ftNumRightJustCommaDpPN";

			JAVA_TYPE_NAME [Type.ftCharMultiLine            ] = "ftCharMultiLine";

			JAVA_TYPE_NAME [Type.ftDate                     ] = "ftDate";
			JAVA_TYPE_NAME [Type.ftDateYMD                  ] = "ftDateYMD";
			JAVA_TYPE_NAME [Type.ftDateYYMD                 ] = "ftDateYYMD";
			JAVA_TYPE_NAME [Type.ftDateDMY                  ] = "ftDateDMY";
			JAVA_TYPE_NAME [Type.ftDateDMYY                 ] = "ftDateDMYY";

			JAVA_TYPE_NAME [Type.ftCharRestOfFixedRecord    ] = "ftCharRestOfFixedRecord";
			JAVA_TYPE_NAME [Type.ftCharRestOfRecord         ] = "ftCharRestOfRecord";
			
//			javaTypeName [Type.ftProtoField               ] = "ftProtoField";
//			javaTypeName [Type.ftAvroField                ] = "ftAvroField";                                                                                                                                               
			JAVA_TYPE_NAME [Type.ftArrayField               ] = "ftArrayField";
			JAVA_TYPE_NAME [Type.ftComboItemField           ] = "ftComboItemField";                                                                         
//			javaTypeName [Type.ftAvroUnionField           ] = "ftAvroUnionField";
			
			JAVA_TYPE_NAME [Type.ftCheckBoxY                ] = "ftCheckBoxY";
			JAVA_TYPE_NAME [Type.ftCheckBoxTrue             ] = "ftCheckBoxTrue";
			JAVA_TYPE_NAME [Type.ftCheckBoxYN               ] = "ftCheckBoxYN";
			JAVA_TYPE_NAME [Type.ftCheckBoxTF               ] = "ftCheckBoxTF";
			JAVA_TYPE_NAME [Type.ftCheckBoxBoolean          ] = "ftCheckBoxBoolean";
			
			JAVA_TYPE_NAME [Type.ftCsvArray                 ] = "ftCsvArray";
			JAVA_TYPE_NAME [Type.ftXmlNameTag               ] = "ftXmlNameTag";
			JAVA_TYPE_NAME [Type.ftMultiLineEdit            ] = "ftMultiLineEdit";
			JAVA_TYPE_NAME [Type.ftMultiLineChar            ] = "ftMultiLineChar";
			JAVA_TYPE_NAME [Type.ftHtmlField                ] = "ftHtmlField";
		}
	}

	public static String getRecordTypeName(int recType) {
		
		if (RECORD_TYPES[RECORD_TYPES.length - 1] == null) {
		    RECORD_TYPES[Constants.rtBinaryRecord         ] = "rtBinaryRecord";        
		    RECORD_TYPES[Constants.rtRecordLayout         ] = "rtRecordLayout";        
		    RECORD_TYPES[Constants.rtDelimited            ] = "rtDelimited";           
		    RECORD_TYPES[Constants.rtDelimitedAndQuote    ] = "rtDelimitedAndQuote";   
		    RECORD_TYPES[Constants.RT_XML                 ] = "RT_XML";                
		    RECORD_TYPES[Constants.rtGroupOfRecords       ] = "rtGroupOfRecords";      
		    RECORD_TYPES[Constants.rtGroupOfBinaryRecords ] = "rtGroupOfBinaryRecords";
		    RECORD_TYPES[Constants.rtFixedLengthRecords   ] = "rtFixedLengthRecords";
		}
		
		if (recType < 0 || recType > RECORD_TYPES.length || RECORD_TYPES[recType] == null) {
			return Integer.toString(recType);
		}
		
		return "Constants." + RECORD_TYPES[recType];
	}
	
	public static String getJRecordIoTypeName(int type) {
		initIoTypes();
		
		if (type < 0 || type > IO_TYPE.length || IO_TYPE[type] == null) {
			return Integer.toString(type);
		}
		
		return "Constants." + IO_TYPE[type];
	}

	private static void initIoTypes() {

		if (IO_TYPE [Constants.IO_PROTO_SD_SINGLE_MESSAGE] == null) {
		    IO_TYPE [Constants.IO_DEFAULT              ] = "IO_DEFAULT";
		    IO_TYPE [Constants.IO_STANDARD_TEXT_FILE   ] = "IO_STANDARD_TEXT_FILE";
		    IO_TYPE [Constants.IO_FIXED_LENGTH_RECORDS ] = "IO_FIXED_LENGTH_RECORDS";
		    IO_TYPE [Constants.IO_BINARY_IBM_4680      ] = "IO_BINARY_IBM_4680";
		    IO_TYPE [Constants.IO_VB                   ] = "IO_VB";
		    IO_TYPE [Constants.IO_VB_DUMP              ] = "IO_VB_DUMP";
		    IO_TYPE [Constants.IO_VB_FUJITSU           ] = "IO_VB_FUJITSU";
		    IO_TYPE [Constants.IO_VB_OPEN_COBOL        ] = "IO_VB_OPEN_COBOL";
		    IO_TYPE [Constants.IO_BIN_TEXT             ] = "IO_BIN_TEXT";
		    IO_TYPE [Constants.IO_FIXED_LENGTH_CHAR    ] = "IO_FIXED_LENGTH_CHAR";
		    IO_TYPE [Constants.IO_UNKOWN_FORMAT        ] = "IO_UNKOWN_FORMAT";
		    IO_TYPE [Constants.IO_WIZARD               ] = "IO_WIZARD";                                         
		    IO_TYPE [Constants.IO_MICROFOCUS           ] = "IO_MICROFOCUS";
		    IO_TYPE [Constants.IO_CSV                  ] = "IO_CSV";
		    IO_TYPE [Constants.IO_BIN_CSV              ] = "IO_BIN_CSV";
		    IO_TYPE [Constants.IO_UNICODE_CSV          ] = "IO_UNICODE_CSV";
		    IO_TYPE [Constants.IO_CSV_NAME_1ST_LINE    ] = "IO_CSV_NAME_1ST_LINE";
		    IO_TYPE [Constants.IO_BIN_CSV_NAME_1ST_LINE] = "IO_BIN_CSV_NAME_1ST_LINE";
		    IO_TYPE [Constants.IO_NAME_1ST_LINE        ] = "IO_NAME_1ST_LINE";
		    IO_TYPE [Constants.IO_GENERIC_CSV          ] = "IO_GENERIC_CSV";
		    IO_TYPE [Constants.IO_BIN_NAME_1ST_LINE    ] = "IO_BIN_NAME_1ST_LINE";
		    IO_TYPE [Constants.IO_UNICODE_NAME_1ST_LINE] = "IO_UNICODE_NAME_1ST_LINE";
		    IO_TYPE [Constants.IO_XML_USE_LAYOUT       ] = "IO_XML_USE_LAYOUT";
		    IO_TYPE [Constants.IO_XML_BUILD_LAYOUT     ] = "IO_XML_BUILD_LAYOUT";
		    IO_TYPE [Constants.IO_PROTO_DELIMITED      ] = "IO_PROTO_DELIMITED";
		    IO_TYPE [Constants.IO_PROTO_SINGLE_MESSAGE ] = "IO_PROTO_SINGLE_MESSAGE";
		    IO_TYPE [Constants.IO_PROTO_SD_DELIMITED   ] = "IO_PROTO_SD_DELIMITED";
		    IO_TYPE [Constants.IO_THRIFT_FILE          ] = "IO_THRIFT_FILE";
		    IO_TYPE [Constants.IO_AVRO_FILE            ] = "IO_AVRO_FILE";
		    IO_TYPE [Constants.IO_GETTEXT_PO           ] = "IO_GETTEXT_PO";
		    IO_TYPE [Constants.IO_TIP                  ] = "IO_TIP";
		    IO_TYPE [Constants.IO_CONTINOUS_NO_LINE_MARKER   ] = "IO_CONTINOUS_NO_LINE_MARKER";
		    IO_TYPE [Constants.IO_UNICODE_CSV_NAME_1ST_LINE  ] = "IO_UNICODE_CSV_NAME_1ST_LINE";
		    IO_TYPE [Constants.IO_STANDARD_UNICODE_TEXT_FILE ] = "IO_STANDARD_UNICODE_TEXT_FILE";
		    IO_TYPE [Constants.IO_PROTO_SD_SINGLE_MESSAGE    ] = "IO_PROTO_SD_SINGLE_MESSAGE";
		}
	}
}