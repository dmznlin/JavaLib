package com.runsoft;

import java.util.*;

public class SQLBuilder extends LibraryBase {
    public enum DatabaseType {
        dtDB2,
        dtMySQL,
        dtOracle,
        dtSQLServer,
        dtPostgreSQL
    }

    public enum FieldType {
        sfStr,              //字符串
        sfVal,              //数值
        sfDate,             //日期
        sfTime,             //时间
        sfDateTime          //日期 + 时间
    }

    static class NullFieldException extends RuntimeException{
        @Override
        public String getMessage() {
            return "DBHelper: field list is empty.";
        }
    }

    static class TableSameNameException extends RuntimeException{
        @Override
        public String getMessage() {
            return "DBHelper: two tables have same name.";
        }
    }

    static class TableNotExistsException extends RuntimeException{
        @Override
        public String getMessage() {
            return "DBHelper: table(alias) is not exists.";
        }
    }

    public static class SQLFieldItem {
        private String FieldName;
        //字段名称
        private Object FieldValue;
        //字段取值
        private FieldType FieldType;
        //字段类型
    }

    public static DatabaseType defaultDatabaseType;
    //默认数据库类型
    public static Map<String, String> TableList;
    //数据表映射
    public static boolean TableAlias;
    //默认使用数据表映射别称

    private DatabaseType DBType;
    //数据库类型
    private String TableName;
    //表名称
    private List<SQLFieldItem> FieldItems = new ArrayList<>();
    //字段列表

    static {
        defaultDatabaseType = DatabaseType.dtSQLServer;
        TableAlias = false;
        TableList = new HashMap<>();
    }

    /**
     * 添加数据表
     * @param table 表名称
     * @param alias 表名别称
     */
    public static void setTable(String alias, String table){
        if (TableList.containsKey(alias)){
            throw new TableSameNameException();
        }

        TableList.put(alias, table);
    }

    /**
     * 获取别名对应的数据表名称
     * @param alias
     * @return
     */
    public static String getTable(String alias){
        String table = TableList.get(alias);
        if (table == null || table.trim().equals("")) {
            throw new TableNotExistsException();
        }

        return table;
    }

    /**
     * 创建一个SQL构建器
     * @param table 表名称
     * @param alias 是否使用别称
     * @return
     */
    public static SQLBuilder NewBuilder(String table, boolean alias){
        if (alias){
            table = getTable(table);
        }

        SQLBuilder sb = new SQLBuilder();
        sb.TableName = table;
        sb.DBType = defaultDatabaseType;

        return sb;
    }

    public static SQLBuilder NewBuilder(String table){
        return NewBuilder(table, TableAlias);
    }

    /**
     * 设置数据库类型
     * @param type 类型标识
     * @return
     */
    public SQLBuilder setDBType(DatabaseType type){
        this.DBType = type;
        return this;
    }

    /**
     * 构建一个SQL字段
     * @param field 字段名
     * @param value 字段值
     * @param type 数据类型
     * @return
     */
    public static SQLFieldItem SF(String field, Object value, FieldType type){
        SQLFieldItem nField = new SQLFieldItem();
        nField.FieldName = field;
        nField.FieldValue = value;
        nField.FieldType = type;
        return nField;
    }

    /**
     * 默认字符串操作
     */
    public static SQLFieldItem SF(String field, Object value){
        return SF(field, value, FieldType.sfStr);
    }

    /**
     * 添加一个字段
     */
    public SQLBuilder setField(String field, Object value, FieldType type){
        FieldItems.add(SF(field, value, type));
        return this;
    }

    /**
     * 添加字符串字段
     */
    public SQLBuilder setField(String field, Object value){
        FieldItems.add(SF(field, value, FieldType.sfStr));
        return this;
    }

    /**
     * 依据字段类型格式化字段取值
     * @param item
     * @return
     */
    public String getFieldValue(SQLFieldItem item){
        String value = "\'\'";
        //default

        if (DBType == DatabaseType.dtSQLServer) {
            switch (item.FieldType){
                case sfStr: //字符串数据
                    value = "\'" + item.FieldValue.toString() + "\'";
                    break;
                case sfVal: //数值
                    value = item.FieldValue.toString();
                    break;
                case sfDate: //日期
                    value = "\'" + DateTimeHelper.Date2Str((Date) item.FieldValue, true) + "\'";
                    break;
                case sfTime: //时间
                    value = "\'" + DateTimeHelper.Time2Str((Date) item.FieldValue, true) + "\'";
                    break;
                case sfDateTime: //日期 + 时间
                    value = "\'" + DateTimeHelper.DateTime2Str((Date) item.FieldValue) + "\'";
                    break;
            }
        }

        return value;
    }

    /**
     * 构建insert sql
     * @return
     */
    public String BuildInsert(){
        if (FieldItems.size() == 0){ //no fields
            throw new NullFieldException();
        }

        StringBuilder nPrefix = new StringBuilder();
        //sql 前缀
        StringBuilder nSuffix = new StringBuilder();
        //sql 后缀

        SQLFieldItem last = FieldItems.get(FieldItems.size() - 1);
        nPrefix.append(String.format("insert into %s(", TableName));
        nSuffix.append(") values(");

        for (SQLFieldItem item: FieldItems){
            nPrefix.append("\'" + item.FieldName + "\'");
            nSuffix.append(getFieldValue(item));

            if (item != last) {
                nPrefix.append(",");
                nSuffix.append(",");
            }
        }

        nSuffix.append(")");
        return nPrefix.toString() + nSuffix.toString();
    }

    /**
     * 构建update sql
     * @param where 更新条件
     * @return
     */
    public String BuildUpdate(String where){
        if (FieldItems.size() == 0){ //no fields
            throw new NullFieldException();
        }

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("update %s set ", TableName));
        SQLFieldItem last = FieldItems.get(FieldItems.size() - 1);

        for (SQLFieldItem item: FieldItems){
            String fmt;
            if (item == last) {
                fmt = "%s=%s";
            } else {
                fmt = "%s=%s,";
            }

            builder.append(String.format(fmt, item.FieldName, getFieldValue(item)));
        }

        if ((where != null) && (!where.trim().equals(""))){
            builder.append(" where " + where);
        }
        return builder.toString();
    }

    public String BuildUpdate(String where, SQLFieldItem ...items){
        return BuildUpdate(BuildWhere(where, items));
    }

    /**
     * 构建where条件
     * @param where 原始语句
     * @param items 替换项
     * @return
     */
    public String BuildWhere(String where, SQLFieldItem ...items){
        if (items == null || items.length == 0){
            return where;
        }

        for (SQLFieldItem item: items){
            where = where.replaceAll(String.format("(?i)#%s#", item.FieldName), getFieldValue(item));
        }

        return where;
    }

    /**
     * 重置清空
     */
    public void Reset(){
        FieldItems.clear();
    }
}
