
package com.ankesh.instasplit.Database;
import android.provider.BaseColumns;


public final class InstaSplitContract {
    public static  final int DATBASE_VERSION = 1;
    public static  final String DATBASE_NAME = "instasplit";
    public static  final String TEXT_TYPE = " TEXT ";
    public static  final String PRIMARY_KEY = "PRIMARY_KEY";
    public static  final String COMMA_SEP = ",";
    public static  final String UNIQUE = " UNIQUE";
    public static  final String REPLACE = " ON CONFLICT REPLACE";

    public static class Users implements BaseColumns{

        public static final String TABLE_NAME = "Users";
        public static final String COL_NAME_1 = "id";
        public static final String COL_NAME_2 = "firstname";
        public static final String COL_NAME_3 = "lastname";
        public static final String COL_NAME_4 = "email";
        public static final String COL_NAME_5 = "mobnum";
        public static final String COL_NAME_6 = "displaypic";
        public final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_NAME_1 + TEXT_TYPE + PRIMARY_KEY + UNIQUE + REPLACE +  COMMA_SEP +
                COL_NAME_2 + TEXT_TYPE + COMMA_SEP +
                COL_NAME_3 + TEXT_TYPE + COMMA_SEP +
                COL_NAME_4 + TEXT_TYPE + COMMA_SEP +
                COL_NAME_5 + TEXT_TYPE + COMMA_SEP +
                COL_NAME_6 + TEXT_TYPE + ")";

    }
    public static class Friends implements BaseColumns{

        public static final String TABLE_NAME = "Friends";
        public static final String COL_NAME_1 = "id";
        public static final String COL_NAME_2 = "friend_id";
        public static final String COL_NAME_3 = "date";
        public static final String COL_NAME_4 = "moneyowes";

        public final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_NAME_1 + TEXT_TYPE + PRIMARY_KEY + COMMA_SEP +
                COL_NAME_2 + TEXT_TYPE + PRIMARY_KEY + COMMA_SEP +
                COL_NAME_3 + TEXT_TYPE + COMMA_SEP +
                COL_NAME_4 + TEXT_TYPE  + COMMA_SEP+ UNIQUE + "(" + COL_NAME_1 + COMMA_SEP + COL_NAME_2 + ")" + REPLACE + ")";

    }
    public static class Activities implements BaseColumns{

        public static final String TABLE_NAME = "Activities";
        public static final String COL_NAME_1 = "id";
        public static final String COL_NAME_2 = "added_by";
        public static final String COL_NAME_3 = "date";
        public final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_NAME_1 + TEXT_TYPE + PRIMARY_KEY + UNIQUE + REPLACE + COMMA_SEP +
                COL_NAME_2 + TEXT_TYPE + COMMA_SEP +
                COL_NAME_3 + TEXT_TYPE + ")";


    }
    public static class ActivitiesPartners implements BaseColumns{

        public static final String TABLE_NAME = "ActivitiesPartners";
        public static final String COL_NAME_1 = "actvity_id";
        public static final String COL_NAME_2 = "user_id";
        public static final String COL_NAME_3 = "money_owed";
        public static final String COL_NAME_4 = "split_value";

        public final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_NAME_1 + TEXT_TYPE +  PRIMARY_KEY + COMMA_SEP +
                COL_NAME_2 + TEXT_TYPE +  PRIMARY_KEY + COMMA_SEP +
                COL_NAME_3 + TEXT_TYPE + COMMA_SEP +
                COL_NAME_4 + TEXT_TYPE + COMMA_SEP + UNIQUE + "(" + COL_NAME_1 + COMMA_SEP + COL_NAME_2 + ")" + REPLACE + ")";

    }
    public static class ActivitiesPartnersPaid implements BaseColumns{

        public static final String TABLE_NAME = "ActivitiesPartnersPaid";
        public static final String COL_NAME_1 = "actvity_id";
        public static final String COL_NAME_2 = "user_id_paid";
        public static final String COL_NAME_3 = "money_paid";
        public static final String COL_NAME_4 = "split_type";

        public final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_NAME_1 + TEXT_TYPE +  PRIMARY_KEY + COMMA_SEP +
                COL_NAME_2 + TEXT_TYPE +  PRIMARY_KEY + COMMA_SEP +
                COL_NAME_3 + TEXT_TYPE + COMMA_SEP +
                COL_NAME_4 + TEXT_TYPE + COMMA_SEP +  UNIQUE + "(" + COL_NAME_1 + COMMA_SEP + COL_NAME_2 + ")" + REPLACE + ")";

    }
    public static class Groups implements BaseColumns{

        public static final String TABLE_NAME = "Groups";
        public static final String COL_NAME_1 = "id";
        public static final String COL_NAME_2 = "name";
        public static final String COL_NAME_3 = "created_by";
        public static final String COL_NAME_4 = "created_on";
        public final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_NAME_1 + TEXT_TYPE +  PRIMARY_KEY + UNIQUE + REPLACE +   COMMA_SEP +
                COL_NAME_2 + TEXT_TYPE + COMMA_SEP +
                COL_NAME_3 + TEXT_TYPE + COMMA_SEP +
                COL_NAME_4 + TEXT_TYPE + ")";

    }
    public static class GroupPartners implements BaseColumns{

        public static final String TABLE_NAME = "GroupPartners";
        public static final String COL_NAME_1 = "group_id";
        public static final String COL_NAME_2 = "user_id";
        public static final String COL_NAME_3 = "money_owed";
        public static final String COL_NAME_4 = "added_by";
        public static final String COL_NAME_5 = "added_on";

        public final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_NAME_1 + TEXT_TYPE +  PRIMARY_KEY + COMMA_SEP +
                COL_NAME_2 + TEXT_TYPE +  PRIMARY_KEY + COMMA_SEP +
                COL_NAME_3 + TEXT_TYPE + COMMA_SEP +
                COL_NAME_4 + TEXT_TYPE + COMMA_SEP +
                COL_NAME_5 + TEXT_TYPE + COMMA_SEP + UNIQUE + "(" + COL_NAME_1 + COMMA_SEP + COL_NAME_2 + ")" + REPLACE + ")";

    }



}