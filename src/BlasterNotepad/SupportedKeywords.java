package BlasterNotepad;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <h1>A class to store the programming language keywords and provide access to
 * them.</h1>
 *
 * <p>
 * Makes multiple language support possible and makes adding new language
 * support convenient. To add more keywords, add a string array and getters to
 * this class. Then, update the switch statement in UI.java.</p>
 */
public class SupportedKeywords {

    private final String[] java = {"abstract", "assert", "boolean",
        "break", "byte", "case", "catch", "char", "class", "const",
        "continue", "default", "do", "double", "else", "extends", "false",
        "final", "finally", "float", "for", "goto", "if", "implements",
        "import", "instanceof", "int", "System", "out", "print()", "println()",
        "new", "null", "package", "private", "protected", "public", "interface",
        "long", "native", "return", "short", "static", "strictfp", "super", "switch",
        "synchronized", "this", "throw", "throws", "transient", "true",
        "try", "void", "volatile", "while"};

    private final String[] C_sharp = {"abstract", "as", "base", "bool",
        "break", "byte", "case", "catch", "char", "checked", "class", "const",
        "continue", "default", "do", "delegate", "decimal", "double", "else", "event", "explicit", "enum", "extern", "false",
        "fixed", "finally", "float", "for", "foreach", "goto", "if", "implicit", "in", "long",
        "using", "internal", "int", "is", "lock", "namespace", "out", "overide", "partial", "params", "object", "operator",
        "new", "null", "package", "private", "protected", "public", "randomly", "ref", "sbyte", "sealed", "interface",
        "long", "native", "return", "short", "static", "struct", "stackalloc", "string", "sizeof", "super", "switch",
        "synchronized", "this", "throw", "throws", "transient", "true", "typeof",
        "try", "uint", "unsafe", "ulong", "unchecked", "ushort", "virtual", "void", "volatile", "while", "add", "alias", "ascending", "by", "descending", "equals", "from", "get", "global", "group", "into", "join", "let", "on", "orderby", "remove", "select", "set", "value",
        "var", "where", "yield"};

    private final String[] cpp = {"auto", "const", "double", "float", "int", "short",
        "struct", "unsigned", "break", "continue", "else", "for", "long", "signed",
        "switch", "void", "case", "default", "enum", "goto", "register", "sizeof",
        "typedef", "volatile", "char", "do", "extern", "if", "return", "static",
        "union", "while", "asm", "dynamic_cast", "namespace", "reinterpret_cast", "try",
        "bool", "explicit", "new", "static_cast", "typeid", "catch", "false", "operator",
        "template", "typename", "class", "friend", "private", "this", "using", "const_cast",
        "inline", "public", "throw", "virtual", "delete", "mutable", "protected", "true", "wchar_t"};

    private final String[] brackets = {"{", "("};
    private final String[] bCompletions = {"}", ")"};

    public String[] getJavaKeywords() {
        return java;
    }

    public String[] getCppKeywords() {
        return cpp;
    }

    public String[] getC_SharpKeywords() {
        return C_sharp;
    }

    public String[] getAnyKeywords(String text) {
        switch (text) {
            case ".cpp":
                return cpp;

            case ".java":
                return java;

            case "C_Sharp":
                return C_sharp;
        }

        return null;
    }

    public ArrayList<String> getbracketCompletions() {
        ArrayList<String> al = new ArrayList<>();
        al.addAll(Arrays.asList(bCompletions));
        return al;
    }

    public ArrayList<String> getbrackets() {
        ArrayList<String> al = new ArrayList<>();
        al.addAll(Arrays.asList(brackets));
        return al;
    }

    public ArrayList<String> setKeywords(String[] arr) {
        ArrayList<String> al = new ArrayList<>();
        al.addAll(Arrays.asList(arr));
        return al;
    }
}
