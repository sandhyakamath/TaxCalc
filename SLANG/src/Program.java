import Compilation.TModule;
import Context.COMPILATION_CONTEXT;
import Context.RUNTIME_CONTEXT;
import Lexer.SymbolInfo;
import Parser.RDParser;
import Scope.SymbolTable;
import Visitor.IExpressionVisitor;
import Visitor.TreeEvaluatorVisitor;

import java.io.BufferedReader;
import java.io.FileReader;

public class Program {
    static void InterpretScript(String fileName) throws Exception {

        // -------------- Read the contents from the file
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append("\n\r");
        }
        reader.close();

        //---------------- Creates the Parser Object
        // With Program text as argument
        RDParser pars = null;
        pars = new RDParser(content.toString());
        TModule p = null;
        COMPILATION_CONTEXT context = new COMPILATION_CONTEXT();
        p = pars.doParse(context);
        IExpressionVisitor visitor = new TreeEvaluatorVisitor();
        if (p == null)
        {
            System.out.println("Parse Process Failed");
            return;
        }
        //
        //  Now that Parse is Successul...
        //  Do a recursive interpretation...!
        //
        RUNTIME_CONTEXT f = new RUNTIME_CONTEXT(p, context.getTable());
        SymbolTable fp = p.Execute(visitor, f);

    }

    /// <summary>
    ///
    /// </summary>
    /// <param name="args"></param>
    public static void main(String args[]) throws Exception {
        final String fileName = System.getenv("fileName");
        System.out.println(fileName);
        if (fileName == null) {
            System.out.println("Enter the file name");
            return;
        }

        InterpretScript(fileName);
        //------------- Wait for the Key Press

    }
}