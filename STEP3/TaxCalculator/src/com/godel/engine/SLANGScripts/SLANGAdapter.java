package com.godel.engine.SLANGScripts;

import Compilation.TModule;
import Context.COMPILATION_CONTEXT;
import Context.RUNTIME_CONTEXT;
import Lexer.SymbolInfo;
import Parser.RDParser;
import Scope.SymbolTable;
import Visitor.IExpressionVisitor;
import Visitor.TreeEvaluatorVisitor;
import com.godel.dto.TaxDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SLANGAdapter  {
    static COMPILATION_CONTEXT context = new COMPILATION_CONTEXT();

    public static boolean executeScript(String fileName, TaxDTO taxDTO) {
        // -------------- Read the contents from the file
        try {
            StringBuilder content = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append("\n\r");
            }
            reader.close();
            COMPILATION_CONTEXT context = new COMPILATION_CONTEXT();
            SLANGScriptHandler.setSymbolInfo(context, taxDTO);
            //---------------- Creates the Parser Object
            // With Program text as argument
            RDParser pars = null;
            pars = new RDParser(content.toString());
            TModule p = null;
            p = pars.doParse(context);
            IExpressionVisitor visitor = new TreeEvaluatorVisitor();
            if (p == null)
            {
                System.out.println("Parse Process Failed");
                return false;
            }
            //
            //  Now that Parse is Successul...
            //  Do a recursive interpretation...!
            //
            RUNTIME_CONTEXT f = new RUNTIME_CONTEXT(p, context.getTable());
            SymbolTable fp = p.Execute(visitor, f);
            taxDTO.setLiability(fp.get("RESULT").dblValue);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;

    }

}
