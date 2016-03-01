package org.midnightas.midnight;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MidnightLang {

	public static Character[] ALPHABET;

	public static final void main(String[] args) throws IOException {
		ALPHABET = new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
				'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		List<Character> NEWALPHABET = new LinkedList<Character>(Arrays.asList(ALPHABET));
		for (Character c : ALPHABET)
			NEWALPHABET.add(Character.toUpperCase(c.charValue()));
		ALPHABET = NEWALPHABET.toArray(new Character[NEWALPHABET.size()]);
		interpret(new String(Files.readAllBytes(Paths.get(new File(args[0]).toURI()))).toCharArray());
	}

	public static void interpret(char[] content) {
		HashMap<String, Object> vars = new HashMap<String, Object>();
		boolean whileStatement = false;
		int whileIndex = 0;
		int whileCount = 0;
		for (int c = 0; c < content.length; c++) {
			if (content[c] == '>' && content[c + 1] == '"') {
				String toPrint = "";
				for (int c1 = c + 1; c1 < content.length; c1++) {
					if (content[c1 + 1] == ';' && (content.length == c1 + 2 || content[c1 + 2] != ';')) {
						System.out.println(toPrint.substring(1));
						break;
					}
					toPrint = toPrint + content[c1];
				}
			} else if (content[c] == '>' && content[c + 1] == '#') {
				String toPrint = "";
				for (int c1 = c + 2; c1 < content.length; c1++) {
					if (content[c1] == ';') {
						System.out.println(Double.parseDouble(toPrint));
						break;
					}
					toPrint = toPrint + content[c1];
				}
			} else if (content[c] == '>' && content[c + 1] != '#') {
				String varToPrint = "";
				for (int c1 = c + 1; c1 < content.length; c1++) {
					if (content[c1] == ';') {
						if (vars.containsKey(varToPrint))
							System.out.println(vars.get(varToPrint));
						break;
					}
					varToPrint = varToPrint + content[c1];
				}
			} else if (content[c] == '=' && content[c + 1] != '=' && content[c + 1] != '#') {
				String varName = "";
				String varValue = "";
				int varValueIndex = 0;
				for (int c1 = c + 1; c1 < content.length; c1++) {
					if (content[c1] == ' ') {
						varValueIndex = c1;
						break;
					}
					varName = varName + content[c1];
				}
				for (int c1 = varValueIndex; c1 < content.length; c1++) {
					if (content[c1] == '"' && content[c1 + 1] == ';') {
						varValue = varValue.substring(2);
						break;
					}
					varValue = varValue + content[c1];
				}
				if (vars.containsKey(varName))
					vars.remove(varName);
				vars.put(varName, varValue);
			} else if (content[c] == '#' && content[c - 1] == '=') {
				String varName = "";
				String varValue = "";
				int varValueIndex = 0;
				for (int c1 = c; c1 < content.length; c1++) {
					if (content[c1] == ' ') {
						varValueIndex = c1 + 1;
						break;
					}
					varName = varName + content[c1];
				}
				for (int c1 = varValueIndex; c < content.length; c1++) {
					if (content[c1] == ';') {
						varName = varName.substring(1);
						break;
					}
					varValue = varValue + content[c1];
				}
				if (vars.containsKey(varName))
					vars.remove(varName);
				vars.put(varName, Double.parseDouble(varValue));
			} else if (content[c] == '@') {
				if (!whileStatement) {
					whileStatement = true;
					whileIndex = c + 1;
					whileCount = 0;
					vars.put("whileIndex", new Double(whileCount));
				} else {
					c = whileIndex;
					whileCount++;
					vars.remove("whileIndex");
					vars.put("whileIndex", new Double(whileCount));
				}
			} else if (content[c] == '!') {
				if (whileStatement) {
					whileStatement = false;
				}
			} else if(content[c] == '?') {
				String varName = "";
				String varValue = "";
				int nextIndex = 0;
				for(int c1 = c + 1; c1 < content.length; c1++) {
					if(content[c1] == '=') {
						nextIndex = c1 + 1;
						break;
					}
					varName = varName + content[c1];
				}
				for(int c1 = nextIndex; c1 < content.length; c1++) {
					if(content[c1] == ';') {
						nextIndex = c1 + 1;
						break;
					}
					varValue = varValue + content[c1];
				}
				if(!vars.get(varName).toString().equals(varValue)) {
					for(int c1 = nextIndex; c1 < content.length; c1++) {
						if(content[c1] == '/') {
							c = c1 + 1;
							break;
						}
					}
				}
			}
		}
	}
	
	public static boolean isNumber(String s) {
		try {
			Double.parseDouble(s);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isLatinLetter(char c) {
		return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
	}

}
