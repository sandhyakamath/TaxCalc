package Parser;

import AST.*;
import AST.Binary.BinaryDiv;
import AST.Binary.BinaryMinus;
import AST.Binary.BinaryMul;
import AST.Binary.BinaryPlus;
import AST.Boolean.LogicalExp;
import AST.Boolean.LogicalNot;
import AST.Boolean.RelationalExp;
import AST.Constant.BooleanConstant;
import AST.Constant.NumericConstant;
import AST.Constant.StringLiteral;
import AST.Unary.UnaryMinus;
import AST.Unary.UnaryPlus;
import ASTStatements.*;
import Builder.ProcedureBuilder;
import Builder.TModuleBuilder;
import Compilation.Procedure;
import Compilation.TModule;
import Context.COMPILATION_CONTEXT;
import Helper.CParserException;
import Helper.CSyntaxErrorLog;
import Lexer.Lexer;
import Lexer.TOKEN;
import Lexer.SymbolInfo;
import Lexer.TYPE_INFO;
import Lexer.RELATION_OPERATOR;
import Visitor.IExpressionVisitor;
import Visitor.TreeEvaluatorVisitor;

import java.util.ArrayList;

public class RDParser extends Lexer {

    TModuleBuilder prog = null;

    public RDParser(String str)
    {
        super(str);
        prog = new TModuleBuilder();

    }

    protected TOKEN getNextToken() throws Exception {
        lastToken = currentToken;
        currentToken = getToken();
        return currentToken;
    }

    /// <summary>
    ///
    /// </summary>
    /// <returns></returns>
    public Expression callExpr( ProcedureBuilder context) throws Exception {
        currentToken = getToken();
        return expression(context);
    }

    public ArrayList parse(IExpressionVisitor visitor, ProcedureBuilder context) throws Exception, CParserException {
        getNextToken();  // Get the Next Token
        //
        // Parse all the statements
        //
        return statementList(visitor, context);
    }

    public TModule doParse(COMPILATION_CONTEXT context) throws Exception {
        try
        {
            IExpressionVisitor visitor = new TreeEvaluatorVisitor();
            ProcedureBuilder p = new ProcedureBuilder("MAIN", context);
            ArrayList stmts = parse(visitor, p);

            for (Object s : stmts) {
                Statement stmt = (Statement) s;
                p.addStatement(stmt);
            }

            Procedure pc = p.getProcedure();

            prog.add(pc);
            return prog.getProgram();
        }
        catch (Exception e)
        {
            throw new Exception(e);
        } catch (CParserException e) {
            throw new RuntimeException(e);
        }

    }

    private ArrayList statementList(IExpressionVisitor visitor, ProcedureBuilder context) throws Exception, CParserException {
        ArrayList arr = new ArrayList();
        while ((currentToken != TOKEN.TOK_ELSE) &&
                (currentToken != TOKEN.TOK_ENDIF) &&
                (currentToken != TOKEN.TOK_WEND) &&
                (currentToken != TOKEN.TOK_NULL)) {
            Statement temp = statement(visitor, context);
            if (temp != null)
            {
                arr.add(temp);
            }
        }
        return arr;
    }

    private Statement statement(IExpressionVisitor visitor, ProcedureBuilder context) throws Exception, CParserException {
        Statement retval = null;
        switch (currentToken)
        {
            case TOK_VAR_STRING:
            case TOK_VAR_NUMBER:
            case TOK_VAR_BOOL:
                retval = parseVariableDeclStatement(visitor, context);
                getNext();
                return retval;
            case TOK_PRINT:
                retval = parsePrintStatement(context);
                getNextToken();
                break;
            case TOK_PRINTLN:
                retval = parsePrintLNStatement(context);
                getNextToken();
                break;
            case TOK_UNQUOTED_STRING:
                retval = parseAssignmentStatement(visitor, context);
                getNext();
                return retval;
            case TOK_IF:
                retval = parseIfStatement(visitor, context);
                getNext();
                return retval;

            case TOK_WHILE:
                retval = parseWhileStatement(visitor, context);
                getNext();
                return retval;
            default:
                throw new Exception("Invalid statement");
        }
        return retval;
    }

    private Statement parsePrintStatement(ProcedureBuilder context) throws Exception {
        getNextToken();
        Expression a = BExpr(context);
        a.typeCheck(context.getContext());
        if (currentToken != TOKEN.TOK_SEMI)
        {
            throw new Exception("; is expected");
        }
        return new PrintStatement(a);
    }

    private Statement parsePrintLNStatement(ProcedureBuilder context) throws Exception {
        getNextToken();
        Expression exp = expression(context);

        if (currentToken != TOKEN.TOK_SEMI)
        {
            throw new Exception("; is expected");
        }
        return new PrintLineStatement(exp);
    }

    public Statement parseVariableDeclStatement(IExpressionVisitor visitor, ProcedureBuilder ctx) throws CParserException {

        //--- Save the Data type
        TOKEN tok = currentToken;
        getNext();

        if (currentToken == TOKEN.TOK_UNQUOTED_STRING)
        {
            SymbolInfo symb = new SymbolInfo();
            symb.symbolName = super.lastStr;
            symb.type = (tok == TOKEN.TOK_VAR_BOOL) ?
                    TYPE_INFO.TYPE_BOOL: (tok == TOKEN.TOK_VAR_NUMBER) ?
                    TYPE_INFO.TYPE_NUMERIC : TYPE_INFO.TYPE_STRING;
            //---------- Skip to Expect the SemiColon

            getNext();



            if (currentToken == TOKEN.TOK_SEMI)
            {
                // ----------- Add to the Symbol Table
                // for type analysis
                ctx.getTable().add(symb);

                // --------- return the Object of type
                // --------- VariableDeclStatement
                // This will just store the Variable name
                // to be looked up in the above table
                return new VariableDeclStatement(symb);
            }
            else
            {
                CSyntaxErrorLog.addLine("; expected");
                CSyntaxErrorLog.addLine(getCurrentLine(saveIndex()));
                throw new CParserException(-100, ", or ; expected", saveIndex());
            }
        }
        else
        {

            CSyntaxErrorLog.addLine("invalid variable declaration");
            CSyntaxErrorLog.addLine(getCurrentLine(saveIndex()));
            throw new CParserException(-100, ", or ; expected", saveIndex());
        }


    }

    public Statement parseAssignmentStatement(IExpressionVisitor visitor, ProcedureBuilder ctx) throws Exception, CParserException {

        //
        // Retrieve the variable and look it up in
        // the symbol table ..if not found throw exception
        //
        String variable = super.lastStr;
        SymbolInfo s = ctx.getTable().get(variable);
        if (s == null)
        {
            CSyntaxErrorLog.addLine("Variable not found  " + lastStr);
            CSyntaxErrorLog.addLine(getCurrentLine(saveIndex()));
            throw new CParserException(-100, "Variable not found", saveIndex());

        }

        //------------ The next token ought to be an assignment
        // expression....

        getNext();

        if (currentToken != TOKEN.TOK_ASSIGN)
        {

            CSyntaxErrorLog.addLine("= expected");
            CSyntaxErrorLog.addLine(getCurrentLine(saveIndex()));
            throw new CParserException(-100, "= expected", saveIndex());

        }

        //-------- Skip the token to start the expression
        // parsing on the RHS
        getNext();
        Expression exp = BExpr(ctx);

        //------------ Do the type analysis ...

        if (exp.typeCheck(ctx.getContext()) != s.type)
        {
            throw new Exception("Type mismatch in assignment");

        }

        // -------------- End of statement ( ; ) is expected
        if (currentToken != TOKEN.TOK_SEMI)
        {
            CSyntaxErrorLog.addLine("; expected");
            CSyntaxErrorLog.addLine(getCurrentLine(saveIndex()));
            throw new CParserException(-100, " ; expected", -1);

        }
        // return an instance of AssignmentStatement node..
        //   s => Symbol info associated with variable
        //   exp => to evaluated and assigned to symbol_info
        return new AssignmentStatement(s, exp);

    }

    public Statement parseIfStatement(IExpressionVisitor visitor, ProcedureBuilder pb) throws Exception, CParserException {
        getNext();
        ArrayList true_part = null;
        ArrayList false_part = null;
        Expression exp = BExpr(pb);  // Evaluate Expression


        if (pb.typeCheck(exp) != TYPE_INFO.TYPE_BOOL)
        {
            throw new Exception("Expects a boolean expression");

        }


        if (currentToken != TOKEN.TOK_THEN)
        {
            CSyntaxErrorLog.addLine(" Then Expected");
            CSyntaxErrorLog.addLine(getCurrentLine(saveIndex()));
            throw new CParserException(-100, "Then Expected", saveIndex());

        }

        getNext();

        true_part = statementList(visitor, pb);

        if (currentToken == TOKEN.TOK_ENDIF)
        {
            return new IfStatement(exp, true_part, false_part);
        }


        if (currentToken != TOKEN.TOK_ELSE)
        {

            throw new Exception("ELSE expected");
        }

        getNext();
        false_part = statementList(visitor, pb);

        if (currentToken != TOKEN.TOK_ENDIF)
        {
            throw new Exception("END IF EXPECTED");

        }

        return new IfStatement(exp, true_part, false_part);

    }

    public Statement parseWhileStatement(IExpressionVisitor visitor, ProcedureBuilder pb) throws Exception, CParserException {

        getNext();

        Expression exp = BExpr(pb);
        if (pb.typeCheck(exp) != TYPE_INFO.TYPE_BOOL)
        {
            throw new Exception("Expects a boolean expression");

        }

        ArrayList body = statementList(visitor, pb);
        if ((currentToken != TOKEN.TOK_WEND))
        {
            CSyntaxErrorLog.addLine("Wend Expected");
            CSyntaxErrorLog.addLine(getCurrentLine(saveIndex()));
            throw new CParserException(-100, "Wend Expected", saveIndex());

        }


        return new WhileStatement(exp, body);

    }
    /// <summary>
    ///    Convert a token to Relational Operator
    /// </summary>
    /// <param name="tok"></param>
    /// <returns></returns>
    private RELATION_OPERATOR getRelOp(TOKEN tok)
    {
        if (tok == TOKEN.TOK_EQ)
            return RELATION_OPERATOR.TOK_EQ;
        else if (tok == TOKEN.TOK_NEQ)
            return RELATION_OPERATOR.TOK_NEQ;
        else if (tok == TOKEN.TOK_GT)
            return RELATION_OPERATOR.TOK_GT;
        else if (tok == TOKEN.TOK_GTE)
            return RELATION_OPERATOR.TOK_GTE;
        else if (tok == TOKEN.TOK_LT)
            return RELATION_OPERATOR.TOK_LT;
        else
            return RELATION_OPERATOR.TOK_LTE;


    }
    public Expression BExpr(ProcedureBuilder pb) throws Exception {
        TOKEN lToken;
        Expression RetValue = LExpr(pb);
        while (currentToken == TOKEN.TOK_AND || currentToken == TOKEN.TOK_OR)
        {
            lToken = currentToken;
            currentToken = getNext();
            Expression e2 = LExpr(pb);
            RetValue = new LogicalExp(lToken, RetValue, e2);

        }
        return RetValue;

    }

    public Expression LExpr(ProcedureBuilder pb) throws Exception {
        TOKEN lToken;
        Expression retValue = expression(pb);
        while (currentToken == TOKEN.TOK_GT ||
                currentToken == TOKEN.TOK_LT ||
                currentToken == TOKEN.TOK_GTE ||
                currentToken == TOKEN.TOK_LTE ||
                currentToken == TOKEN.TOK_NEQ ||
                currentToken == TOKEN.TOK_EQ)
        {
            lToken = currentToken;
            currentToken = getNext();
            Expression e2 = expression(pb);
            RELATION_OPERATOR relop = getRelOp(lToken);
            retValue = new RelationalExp(relop, retValue, e2);


        }
        return retValue;

    }

    /// <summary>
    ///
    /// </summary>
    /// <returns></returns>
    public Expression expression(ProcedureBuilder context) throws Exception {
        TOKEN l_token;
        Expression retValue = term(context);
        while (currentToken == TOKEN.TOK_PLUS || currentToken == TOKEN.TOK_SUB)
        {
            l_token = currentToken;
            currentToken = getToken();
            Expression e1 = expression(context);
            if (l_token == TOKEN.TOK_PLUS)
                retValue = new BinaryPlus(retValue, e1);
            else
                retValue = new BinaryMinus(retValue, e1);
        }

        return retValue;

    }
    /// <summary>
    ///
    /// </summary>
    public Expression term(ProcedureBuilder context) throws Exception {
        TOKEN l_token;
        Expression retValue = factor(context);

        while (currentToken == TOKEN.TOK_MUL || currentToken == TOKEN.TOK_DIV)
        {
            l_token = currentToken;
            currentToken = getToken();


            Expression e1 = term(context);
            if (l_token == TOKEN.TOK_MUL)
                retValue = new BinaryMul(retValue, e1);
            else
                retValue = new BinaryDiv(retValue, e1);

        }

        return retValue;
    }

    /// <summary>
    ///
    /// </summary>
    public Expression factor(ProcedureBuilder context) throws Exception {
        TOKEN l_token;
        Expression retValue = null;
        if (currentToken == TOKEN.TOK_NUMERIC)
        {

            retValue = new NumericConstant(getNumber());
            currentToken = getToken();

        }
        else if (currentToken == TOKEN.TOK_STRING)
        {
            retValue = new StringLiteral(lastStr);
            currentToken = getToken();
        }
        else if (currentToken == TOKEN.TOK_BOOL_FALSE ||
                currentToken == TOKEN.TOK_BOOL_TRUE)
        {
            retValue = new BooleanConstant(
                    currentToken == TOKEN.TOK_BOOL_TRUE ? true : false);
            currentToken = getToken();
        }
        else if (currentToken == TOKEN.TOK_OPAREN)
        {

            currentToken = getToken();

            retValue = BExpr(context);  // Recurse

            if (currentToken != TOKEN.TOK_CPAREN)
            {
                System.out.println("Missing Closing Parenthesis\n");
                throw new Exception();

            }
            currentToken = getToken();
        }
        else if (currentToken == TOKEN.TOK_PLUS || currentToken == TOKEN.TOK_SUB)
        {
            l_token = currentToken;
            currentToken = getToken();
            retValue = factor(context);

            if (l_token == TOKEN.TOK_PLUS)
                retValue = new UnaryPlus(retValue);
            else
                retValue = new UnaryMinus(retValue);
        } else if (currentToken == TOKEN.TOK_NOT)
        {
            l_token = currentToken;
            currentToken = getToken();
            retValue = factor(context);

            retValue = new LogicalNot(retValue);
        } else if (currentToken == TOKEN.TOK_UNQUOTED_STRING) {
            ///
            ///  Variables
            ///
            String str = super.lastStr;
            SymbolInfo info = context.getTable().get(str);

            if (info == null)
                throw new Exception("Undefined symbol");

            getNextToken();
            retValue = new Variable(info);
        }
        else
        {

            System.out.println("Illegal Token");
            throw new Exception();
        }


        return retValue;

    }


}
