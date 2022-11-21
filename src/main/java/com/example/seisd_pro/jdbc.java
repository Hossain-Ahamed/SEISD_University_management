package com.example.seisd_pro;

import java.sql.Connection;
import java.sql.Statement;

public class jdbc {
    static Connection c1; static Statement s;

    jdbc(Connection c1, Statement s){
        this.c1 = c1;
        this.s = s;
    }


}
