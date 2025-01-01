package com.example.multiexpenserv1.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.multiexpenserv1.muc_tieu.goal;
import com.example.multiexpenserv1.ql_balance.balance;
import com.example.multiexpenserv1.ql_expensive.expense;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String GOAL_TABLE_NAME = "GOAL";
    public static final String STATUS = "STATUS";
    public static final String EXPENSES_TABLE_NAME = "EXPENSES";
    public static final String TITLE = "TITLE";
    public static final String AMOUNT = "AMOUNT";
    public static final String DAY = "DAY";
    public static final String MONTH = "MONTH";
    public static final String YEAR = "YEAR";
    public static final String TYPE = "TYPE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String DATABASE_NAME = "multiexpenserdb";
    public static final String BALANCE_TABLE_NAME = "BALANCE";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Creating Expenses table structure
        String CreateTableExpenseStatement="CREATE TABLE EXPENSES(TITLE TEXT ,AMOUNT TEXT, DAY TEXT, MONTH TEXT, YEAR TEXT, DESCRIPTION TEXT);";
        //For executing the sql statement
        db.execSQL(CreateTableExpenseStatement);

        //Creating  Balance table structure;
       String CreateTableBalanceStatement= "CREATE TABLE BALANCE(TITLE TEXT ,AMOUNT TEXT, DAY TEXT, MONTH TEXT, YEAR TEXT ,STATUS TEXT);";
        //For executing the sql statement
        db.execSQL(CreateTableBalanceStatement);

        //Creating Goal Table structure
        String CreateTableGoalStatement= "CREATE TABLE  GOAL(TITLE TEXT,AMOUNT TEXT,TYPE TEXT, DAY TEXT, MONTH TEXT,YEAR TEXT,STATUS TEXT,ID INTEGER PRIMARY KEY, AUTO INCREMENT);";
        //For executing the sql statement
        db.execSQL(CreateTableGoalStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    // Write function for expenses
    public boolean addExpenseToDB(expense obj) {
        ContentValues cv = new ContentValues();

        // Putting content values with corresponding to the column names
        cv.put(TITLE, obj.getTitle());
        cv.put(AMOUNT, obj.getAmount());
        cv.put(DAY, obj.getDay());
        cv.put(MONTH, obj.getMonth());
        cv.put(YEAR, obj.getYear());
        cv.put(DESCRIPTION, obj.getDescription());

        //Inserting values into the table
        // Sử dụng try-with-resources cho SQLiteDatabase
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            long insert = db.insert(EXPENSES_TABLE_NAME, null, cv);
            return insert != -1;
        } catch (Exception e) {
            Log.e("DataBaseHelper", "Error adding expense", e);
            return false;
        }
    }

    // Write function for Balance
    public boolean addBalanceDetailsToDB(balance obj){
        ContentValues cv=new ContentValues();

        // Putting content values with corresponding to the column names
       cv.put(TITLE,obj.getTitle());
        cv.put(AMOUNT,obj.getAmount());
        cv.put(DAY,obj.getDay());
        cv.put(MONTH,obj.getMonth());
        cv.put(YEAR,obj.getYear());
       cv.put(STATUS,obj.getStatus());
        //Inserting values into the table
        // Sử dụng try-with-resources cho SQLiteDatabase
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            long insert = db.insert(BALANCE_TABLE_NAME, null, cv);
            return insert != -1;
        } catch (Exception e) {
            Log.e("DataBaseHelper", "Error adding balance", e);
            return false;
        }
    }
    // Write function for new Goal
    public boolean AddGoalToDB(goal obj){
        ContentValues cv=new ContentValues();

        // Putting content values with corresponding to the column names
        cv.put(TITLE,obj.getTitle());
        cv.put(AMOUNT,obj.getAmount());
        cv.put(TYPE,obj.getType());
        cv.put(DAY,obj.getDay());
        cv.put(MONTH,obj.getMonth());
        cv.put(YEAR,obj.getYear());
        cv.put(STATUS,obj.getStatus());
        //Inserting values into the table
        // Sử dụng try-with-resources cho SQLiteDatabase
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            long insert = db.insert(GOAL_TABLE_NAME, null, cv);
            return insert != -1;
        } catch (Exception e) {
            Log.e("DataBaseHelper", "Error adding goal", e);
            return false;
        }
    }

    // To get the data from the Expenses
    public  List<expense> getAllExpenses() {
        //Declaring list to return
        List<expense> returnList = new ArrayList<>();
        //Query to get data
        String query = "SELECT * FROM " + EXPENSES_TABLE_NAME;

        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery(query, null)) {
            //Check if the cursor has successfully fetched the results
            if (cursor.moveToFirst()) {
                //            // loop through the cursor and create a new class objects. put them into the return list
                do {
                    //                taking data from columns
                    String Title = cursor.getString(0);
                    String Amount = cursor.getString(1);
                    String Day = cursor.getString(2);
                    String Month = cursor.getString(3);
                    String Year = cursor.getString(4);
                    String Description = cursor.getString(5);
                    //                 create object using constructor
                    expense obj = new expense(Title, Amount, Day, Month, Year, Description);
                    returnList.add(obj);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DataBaseHelper", "Error fetching data", e);// Xử lý lỗi nếu có
        }
        return returnList;
    }

    // To get the data from the Balance
    public  List<balance> getAllBalance() {
        //Declaring list to return
        List<balance> returnList = new ArrayList<>();
        //Query to get data
        String query = "SELECT * FROM " + BALANCE_TABLE_NAME;
        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery(query, null)) {

            //Check if the cursor has successfully fetched the results
            if (cursor.moveToFirst()) {
//            // loop through the cursor and create a new class objects. put them into the return list
                do {
//                taking data from columns

                    String Title = cursor.getString(0);
                    String Amount = cursor.getString(1);
                    String Day = cursor.getString(2);
                    String Month = cursor.getString(3);
                    String Year = cursor.getString(4);
                    String Status = cursor.getString(5);

//                 create object using constructor
                    balance obj = new balance(Title, Amount, Day, Month, Year);
                    obj.setStatus(Status);
                    returnList.add(obj);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DataBaseHelper", "Error fetching data", e);// Xử lý lỗi nếu có
        }
        return returnList;
    }
    // To get the data from the Goals
    public  List<goal> getAllGoals() {
        //Declaring list to return
        List<goal> returnList = new ArrayList<>();
        //Query to get data
        String query = "SELECT * FROM " + GOAL_TABLE_NAME;

        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery(query, null)) {
            //Check if the cursor has successfully fetched the results
            if (cursor.moveToFirst()) {
//            // loop through the cursor and create a new class objects. put them into the return list
                do {
//                taking data from columns

                    String Title=cursor.getString(0);
                    String Type=cursor.getString(1);
                    String Amount = cursor.getString(2);
                    String Day = cursor.getString(3);
                    String Month = cursor.getString(4);
                    String Year = cursor.getString(5);
                    String Status=cursor.getString(6);
                    int Id=cursor.getInt(7);
//                 create object using constructor
                    goal obj = new goal(Title,Type,Amount, Day, Month, Year);
                    obj.setStatus(Status);
                    obj.setID(Id);
                    returnList.add(obj);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DataBaseHelper", "Error fetching data", e);// Xử lý lỗi nếu có
        }
        return returnList;
    }

    //Changing Goal status
    public void ChangeGoalStatus(int key){
        String query="UPDATE  GOAL SET  STATUS = 'ACHIEVED' WHERE  ID = "+ key +" ;";
        // Sử dụng try-with-resources cho SQLiteDatabase
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            db.execSQL(query);
        } catch (Exception e) {
            Log.e("DataBaseHelper", "Error changing goal status", e);
        }
    }
}