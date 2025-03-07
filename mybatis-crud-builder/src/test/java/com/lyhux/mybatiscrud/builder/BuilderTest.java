package com.lyhux.mybatiscrud.builder;

import com.lyhux.mybatiscrud.builder.vendor.MysqlGrammar;
import org.junit.jupiter.api.Test;

public class BuilderTest {
    @Test
    public void testBuilder() {
        var builder = new Builder(null, new MysqlGrammar());
        var test1 = true;
        var test2 = false;

        var selector = builder.selectQuery()
            .select()
            .from("users")
            .select("id", "username", "age")
            .where((query) -> {
                query.where("id", 1)
                     .whereIn("username", "haha", "baba")
                     .when(test1, (q) -> {
                         q.where("id", 3).orWhere("age", 2);
                     })
                     .when(test2, (q) -> {
                         q.where("id", 4).where("age", 1);
                     })
                ;
            })

        ;

        var result = selector.select();

        // selector.replaceSelect()
        //     .selectRaw("count(*) as aggregate");
        //
        // result = builder.compile();
        // System.out.println(result);
        // selector.recoverSelect();
        // result = builder.compile();
        // System.out.println(result);




        /*
        var res = builder.select((stmt) -> {
            stmt.from("user")
                .select("id", "name", "age")
                .where((query) -> {
                    query.where("id", 1)
                         .whereIn("name", "haha", "baba")
                         .when(test1, (q) -> {
                             q.where("id", 3);
                         })
                         .when(test2, (q) -> {
                             q.where("id", 4);
                         })
                    ;
                })

            ;
        }).compile();

        System.out.println(res);
         */
    }

}
