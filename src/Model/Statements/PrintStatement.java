package Model.Statements;

import Model.Expressions.Expression;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Structures.MyIList;
import Model.Structures.ProgramState;
import Model.Types.IType;
import Model.Values.IValue;

public class PrintStatement implements IStatement {
    Expression expression;

    public PrintStatement(Expression expr) {
        expression = expr;
    }


    @Override
    public String toString() {return "print(" + expression.toString() + ");";};

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        MyIList<IValue> outputList = state.getOutputList();
        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();

        outputList.add(expression.evaluation(symbolTable, heap));

        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws Exception {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }
}
