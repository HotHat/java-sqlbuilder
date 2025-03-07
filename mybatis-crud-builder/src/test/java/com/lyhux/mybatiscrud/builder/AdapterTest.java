package com.lyhux.mybatiscrud.builder;

import com.lyhux.mybatiscrud.builder.vendor.MysqlGrammar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AdapterTest {
    static final String DB_URL = "jdbc:mysql://localhost/xapp";
    static final String USER = "root";
    static final String PASSWORD = "";

    private Connection conn;

    @BeforeEach
    public void setUp() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

        conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }


    @AfterEach
    protected void tearDown() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }

    @Test
    public void testInsert() throws SQLException {
        var adapter = new InsertAdapter(conn, new MysqlGrammar());

        Long primaryKey = adapter
               .table("users")
               .columns("username", "password", "gender", "email", "created_at", "updated_at")
               .values((group -> {
                   group
                       .add("momo5")
                       .add("123456")
                       .add(2)
                       .add("yaha2@gmail.com")
                       .add(LocalDateTime.now())
                       // .add(Timestamp.from(Instant.now()))
                       .addNull();

               }))
            .insert();

        System.out.printf("Inserted record: %s\n", primaryKey);
    }

    @Test
    public void testUpdate() throws SQLException {
        var adapter = new UpdateAdapter(conn, new MysqlGrammar());
        var rows = adapter
            .table("users")
            .set((wrapper) -> {
                wrapper
                    .set("username", "linux")
                    .setRaw("email", "hello@gmail.com")
                ;

            })
            .where(wrapper -> {
                wrapper.where("id", ">", 1);
            })
            .update();

        System.out.printf("Updated record count: %s\n", rows);

    }

    @Test
    public void testDelete() throws SQLException {
        var adapter = new DeleteAdapter(conn, new MysqlGrammar());
        var rows = adapter
            .table("users")
            .where(wrapper -> {
                wrapper.where("id", ">", 3);
            })
            .delete();

        System.out.printf("Updated record count: %s\n", rows);

    }

    @Test
    public void testSelect() throws Exception {
        SelectAdapter.beginLogQuery();

        var adapter = new SelectAdapter(conn, new MysqlGrammar());
        var lst = adapter
            .from("users")
            .get();

        System.out.println(lst);

        adapter = new SelectAdapter(conn, new MysqlGrammar());

        var user = adapter
            .from("users")
            .first();

        // var result = user.getQuery();
        // System.out.printf("Selected record: %s\n%s", result.statement(), result.bindings());

        System.out.println(user);

        var logs = SelectAdapter.getLogQuery();
        for (var log : logs) {
            System.out.printf("sql: %s\nbindings:%s\n", log.statement(), log.bindings());
        }
    }

    @Test
    public void testTransaction() throws SQLException {
        var builder = new Builder(conn, new MysqlGrammar());
        builder.beginTransaction();

        var rowCount = builder.updateQuery()
               .table("users")
               .set((wrapper) -> {
                   wrapper.set("username", "tr1");
               })
               .where(wrapper -> {
                   wrapper.where("id", 6);
               }).update();

        System.out.printf("Updated record count: %s\n", rowCount);

        rowCount = builder.updateQuery()
               .table("users")
               .set((wrapper) -> {
                   wrapper.set("username", "tr2");
               })
               .where(wrapper -> {
                   wrapper.where("id", 7);
               }).update();
        System.out.printf("Updated record count: %s\n", rowCount);

        builder.commit();
    }


    @Test
    public void testTransactionLevels() throws SQLException {
        var builder = new Builder(conn, new MysqlGrammar());
        // level 1
        builder.beginTransaction();
            // level 2 commit
            builder.beginTransaction();
                // level rollback
                builder.beginTransaction();
        var rowCount = builder.updateQuery()
                          .table("users")
                          .set((wrapper) -> {
                              wrapper.set("username", "rollback");
                          })
                          .where(wrapper -> {
                              wrapper.where("id", 8);
                          }).update();
        System.out.printf("transaction rollback count: %s\n", rowCount);

                builder.rollback();
        rowCount = builder.updateQuery()
                          .table("users")
                          .set((wrapper) -> {
                              wrapper.set("username", "commit");
                          })
                          .where(wrapper -> {
                              wrapper.where("id", 9);
                          }).update();
        System.out.printf("transaction commit count: %s\n", rowCount);

            builder.commit();

        // end top
        builder.commit();

        // after transaction
        rowCount = builder.updateQuery()
                          .table("users")
                          .set((wrapper) -> {
                              wrapper.set("username", "commit");
                          })
                          .where(wrapper -> {
                              wrapper.where("id", 6);
                          }).update();
        System.out.printf("auto commit count: %s\n", rowCount);

    }
}
