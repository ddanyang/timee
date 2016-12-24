package wecourse.utils.db;

/**
 * Created by ddany on 2016/5/12.
 */
public class Static_DB {
    //数据库版本号
    public static final int DB_VERSION = 1;
    //数据库名称
    public static final String DB_NAME = "weihe.db";
    //数据库表名
    public static final String DB_TABLE_ONE = "content";

    //数据库建表语句
    protected static final String EDB_CREATE_TABLE_ONE = "CREATE TABLE"+DB_TABLE_ONE+"(" +
            "_id integer primary key autoincrement not null," +
//            "date date not null," +
            "time long not null," +
            "content varchar(30) not null)";

}
