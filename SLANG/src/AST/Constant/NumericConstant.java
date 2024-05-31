package AST.Constant;

/////////////////////////////////////////////////
// Class that stores number
//

import AST.Expression;
import Builder.ProcedureBuilder;
import Context.COMPILATION_CONTEXT;
import Context.RUNTIME_CONTEXT;
import Lexer.SymbolInfo;
import Lexer.TYPE_INFO;
import Visitor.IExpressionVisitor;

public class NumericConstant extends Expression {

    private SymbolInfo info;

    public NumericConstant(double value) {
        info = new SymbolInfo();
        info.symbolName = null;
        info.dblValue = value;
        info.type = TYPE_INFO.TYPE_NUMERIC;

    }


    public SymbolInfo getInfo() {
        return info;
    }

    public void setInfo(SymbolInfo info) {
        this.info = info;
    }

    @Override
    public SymbolInfo accept(IExpressionVisitor visitor, RUNTIME_CONTEXT context) throws Exception {
        return visitor.visit(this, context);
    }

    @Override
    public TYPE_INFO accept(IExpressionVisitor visitor, COMPILATION_CONTEXT context) {
        return visitor.visit(this, context);
    }

    @Override
    public TYPE_INFO typeCheck(COMPILATION_CONTEXT context) throws Exception {
        return info.type;
    }

}
