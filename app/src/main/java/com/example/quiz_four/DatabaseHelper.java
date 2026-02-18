package com.example.quiz_four;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CustomerDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CUSTOMERS = "customers";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMES = "full_names";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_BIRTHDATE = "birth_date";
    private static final String COLUMN_NATIONALITY = "nationality";
    private static final String COLUMN_NID = "customer_nid";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CUSTOMERS_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAMES + " TEXT,"
                + COLUMN_GENDER + " TEXT,"
                + COLUMN_BIRTHDATE + " INTEGER," // Store date as long (timestamp)
                + COLUMN_NATIONALITY + " TEXT,"
                + COLUMN_NID + " TEXT" + ")";
        db.execSQL(CREATE_CUSTOMERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        onCreate(db);
    }

    public long addCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMES, customer.getFullNames());
        values.put(COLUMN_GENDER, customer.getGender());
        values.put(COLUMN_BIRTHDATE, customer.getBirthDate().getTime());
        values.put(COLUMN_NATIONALITY, customer.getNationality());
        values.put(COLUMN_NID, customer.getCustomerNID());

        long id = db.insert(TABLE_CUSTOMERS, null, values);
        db.close();
        return id;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CUSTOMERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                customer.setCustomerID(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                customer.setFullNames(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMES)));
                customer.setGender(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER)));
                customer.setBirthDate(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_BIRTHDATE))));
                customer.setNationality(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NATIONALITY)));
                customer.setCustomerNID(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NID)));
                customerList.add(customer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return customerList;
    }

    public int updateCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMES, customer.getFullNames());
        values.put(COLUMN_GENDER, customer.getGender());
        values.put(COLUMN_BIRTHDATE, customer.getBirthDate().getTime());
        values.put(COLUMN_NATIONALITY, customer.getNationality());
        values.put(COLUMN_NID, customer.getCustomerNID());

        return db.update(TABLE_CUSTOMERS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(customer.getCustomerID())});
    }

    public void deleteCustomer(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CUSTOMERS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public Customer getCustomer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CUSTOMERS, new String[]{COLUMN_ID, COLUMN_NAMES, COLUMN_GENDER, COLUMN_BIRTHDATE, COLUMN_NATIONALITY, COLUMN_NID},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Customer customer = new Customer();
        customer.setCustomerID(cursor.getInt(0));
        customer.setFullNames(cursor.getString(1));
        customer.setGender(cursor.getString(2));
        customer.setBirthDate(new Date(cursor.getLong(3)));
        customer.setNationality(cursor.getString(4));
        customer.setCustomerNID(cursor.getString(5));
        cursor.close();
        return customer;
    }
}
