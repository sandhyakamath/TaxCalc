package com.godel.engine.SLANGScripts;

import Context.COMPILATION_CONTEXT;
import Lexer.SymbolInfo;
import Lexer.TYPE_INFO;
import com.godel.dto.TaxDTO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SLANGScriptHandler {

    public static void setSymbolInfo(COMPILATION_CONTEXT context, TaxDTO taxDTO ) {
        try {
            Field[] fields = TaxDTO.class.getDeclaredFields();
            Method[] methods = TaxDTO.class.getDeclaredMethods();
            for (Method m :methods ) {
                String methodName = m.getName();
                if (methodName.startsWith("get") ) {
                    String fieldName = methodName.substring(3);
                   // if (Arrays.asList(fields).contains(fieldName.toLowerCase())) {
                        String symbolName = fieldName.toUpperCase();
                        if (m.getReturnType().getTypeName().equals("boolean")) {
                            collectBoolSymbols(symbolName, (Boolean) m.invoke(taxDTO), context);
                        } else if (m.getReturnType().getTypeName().equals("java.lang.String")) {
                            collectStringSymbols(symbolName, (String)m.invoke(taxDTO), context);
                        } else if (m.getReturnType().getTypeName().equals("char")) {
                            collectStringSymbols(symbolName, String.valueOf( m.invoke(taxDTO)), context);
                        }  else if (m.getReturnType().getTypeName().equals("double")) {
                        collectNumericSymbols(symbolName, (Double) m.invoke(taxDTO), context);
                        } else if (m.getReturnType().getTypeName().equals("float")) {
                        collectNumericSymbols(symbolName, (Float) m.invoke(taxDTO), context);
                        } else if (m.getReturnType().getTypeName().equals("int")) {
                            collectNumericSymbols(symbolName, (Integer) m.invoke(taxDTO), context);
                        }
                   // }

                }
            }
        } catch(InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean collectStringSymbols(String str, String value, COMPILATION_CONTEXT context)
    {
        SymbolInfo st = new SymbolInfo();
        st.symbolName = str;
        st.type = TYPE_INFO.TYPE_STRING;
        st.bolValue = false;
        st.strValue = value;
        return context.getTable().add(st);
    }

    public static boolean collectBoolSymbols(String str, boolean value, COMPILATION_CONTEXT context)
    {
        SymbolInfo st = new SymbolInfo();
        st.symbolName = str;
        st.type = TYPE_INFO.TYPE_BOOL;
        st.bolValue = value;
        return context.getTable().add(st);
    }

    public static boolean collectNumericSymbols(String str, double value, COMPILATION_CONTEXT context)
    {
        SymbolInfo st = new SymbolInfo();
        st.symbolName = str;
        st.type = TYPE_INFO.TYPE_NUMERIC ;
        st.dblValue = value;
        st.bolValue = false;
        return context.getTable().add(st);
    }
}
