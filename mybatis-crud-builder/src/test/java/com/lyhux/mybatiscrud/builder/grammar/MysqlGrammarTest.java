package com.lyhux.mybatiscrud.builder.grammar;

import com.lyhux.mybatiscrud.builder.grammar.insert.AssignListExpr;
import com.lyhux.mybatiscrud.builder.grammar.insert.ColumnExpr;
import com.lyhux.mybatiscrud.builder.grammar.insert.ValueGroupExpr;
import com.lyhux.mybatiscrud.builder.grammar.select.ForExpr;
import com.lyhux.mybatiscrud.builder.grammar.select.GroupByExpr;
import com.lyhux.mybatiscrud.builder.grammar.select.SelectExpr;
import com.lyhux.mybatiscrud.builder.vendor.MysqlGrammar;
import org.junit.jupiter.api.BeforeEach;

public class MysqlGrammarTest {
    protected MysqlGrammar grammar;


    @BeforeEach
    public void setUp() throws Exception {
        grammar = new MysqlGrammar();
    }

    public void print(SelectExpr expr) {
        var result = grammar.compile(expr);
        System.out.println(result);
    }

    public void print(WhereClauseExpr expr) {
        var result = grammar.compile(expr);
        System.out.println(result.statement());
        System.out.println(result.bindings());
    }

    public void print(ExprResult result) {
        System.out.println(result.statement());
        System.out.println(result.bindings());
    }

    public void print(TableNameExpr expr) {
        var result = grammar.compile(expr);
        System.out.println(result);
    }

    public void print(TableSubExpr expr) {
        var result = grammar.compile(expr);
        System.out.println(result.statement());
        System.out.println(result.bindings());
    }

    public void print(TableFactorExpr expr) {
        var result = grammar.compile(expr);
        System.out.println(result.statement());
        System.out.println(result.bindings());
    }

    public void print(TableJoinedExpr expr) {
        var result = grammar.compile(expr);
        System.out.println(result.statement());
        System.out.println(result.bindings());
    }

    public void print(TableRefExpr expr) {
        var result = grammar.compile(expr);
        System.out.println(result.statement());
        System.out.println(result.bindings());
    }

    public void print(TableRefsExpr expr) {
        var result = grammar.compile(expr);
        System.out.println(result.statement());
        System.out.println(result.bindings());
    }

    public void print(SelectStmt stmt) {
        var result = grammar.compile(stmt);
        System.out.println(result.statement());
        System.out.println(result.bindings());
    }

    public void print(GroupByExpr stmt) {
        var result = grammar.compile(stmt);
        System.out.println(result.statement());
        System.out.println(result.bindings());
    }

    public void print(OrderByExpr expr) {
        var result = grammar.compile(expr);
        System.out.println(result);
    }

    public void print(LimitExpr expr) {
        var result = grammar.compile(expr);
        System.out.println(result);
    }

    public void print(ForExpr expr) {
        var result = grammar.compile(expr);
        System.out.println(result);
    }

    // insert statements
    public void print(ColumnExpr expr) {
        var result = grammar.compile(expr);
        System.out.println(result);
    }

    public void print(ValueGroupExpr stmt) {
        var result = grammar.compile(stmt);
        System.out.println(result.statement());
        System.out.println(result.bindings());
    }

    public void print(AssignListExpr expr) {
        var result = grammar.compile(expr);
        System.out.println(result);
    }

    public void print(InsertStmt stmt) {
        var result = grammar.compile(stmt);
        System.out.println(result.statement());
        System.out.println(result.bindings());
    }

    public void print(com.lyhux.mybatiscrud.builder.grammar.update.AssignListExpr stmt) {
        var result = grammar.compile(stmt);
        System.out.println(result.statement());
        System.out.println(result.bindings());
    }

    public void print(UpdateStmt stmt) {
        var result = grammar.compile(stmt);
        System.out.println(result.statement());
        System.out.println(result.bindings());
    }

    public ExprResult print(DeleteStmt stmt) {
        var result = grammar.compile(stmt);
        System.out.println(result.statement());
        System.out.println(result.bindings());
        return result;
    }
}
