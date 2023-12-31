package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Structures.*;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Values.BoolValue;
import Model.Values.IValue;


public class IfStatement implements IStatement {
    Expression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement (Expression e, IStatement t, IStatement el) {
        expression = e;
        thenStatement = t;
        elseStatement = el;
    }

    @Override
    public String toString(){ return "(IF("+ expression.toString()+") THEN(" + thenStatement.toString() +")ELSE("+ elseStatement.toString()+"))";}

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIStack<IStatement> stack = state.getExeStack();
        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();

        IValue condition = expression.evaluation(symbolTable, heap);

        if (!condition.getType().equals(new BoolType())) {
            throw new MyException("Conditional expression not a boolean");
        } else if (((BoolValue) condition).getValue()) {
            stack.push(thenStatement);
        } else {
            stack.push(elseStatement);
        }
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws Exception {
        IType typeExpression = expression.typeCheck(typeEnv);
        if (typeExpression.equals(new BoolType())) {
            thenStatement.typeCheck(((TypeTable)typeEnv).deepCopy());
            elseStatement.typeCheck(((TypeTable)typeEnv).deepCopy());
            return typeEnv;
        }
        else {
            throw new MyException("The condition of IF has not the type bool");
        }
    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(expression.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }
}
