package com.example.quiz_four;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Database helper class for managing customer data.
 * Handles creation, upgrade, and CRUD operations for the customers table.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database name constant
    private static final String DATABASE_NAME = "CustomerDB";
    // Database version constant
    private static final int DATABASE_VERSION = 1;

    // Table name for customers
    private static final String TABLE_CUSTOMERS = "customers";
    // Column for customer ID
    private static final String COLUMN_ID = "id";
    // Column for customer full names
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
        // Constructing the SQL statement for table creation
        String CREATE_CUSTOMERS_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAMES + " TEXT,"
                + COLUMN_GENDER + " TEXT,"
                + COLUMN_BIRTHDATE + " INTEGER,"
                + COLUMN_NATIONALITY + " TEXT,"
                + COLUMN_NID + " TEXT" + ")";
        // Executing the table creation SQL
        db.execSQL(CREATE_CUSTOMERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Dropping the old table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        // Re-creating the table on database upgrade
        onCreate(db);
    }

    /**
     * Adds a new customer to the database.
     * @param customer The customer object to add.
     * @return The ID of the newly inserted row.
     */
    public long addCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating ContentValues to store data for insertion
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMES, customer.getFullNames());
        values.put(COLUMN_GENDER, customer.getGender());
        values.put(COLUMN_BIRTHDATE, customer.getBirthDate().getTime());
        values.put(COLUMN_NATIONALITY, customer.getNationality());
        values.put(COLUMN_NID, customer.getCustomerNID());

        // Performing the insert operation
        long id = db.insert(TABLE_CUSTOMERS, null, values);
        db.close();
        return id;
    }

    /**
     * Retrieves all customers from the database.
     * @return A list of all customer objects.
     */
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        // Query to select all records from the table
        String selectQuery = "SELECT * FROM " + TABLE_CUSTOMERS;

        SQLiteDatabase db = this.getWritableDatabase();
        // Fetching all records using a cursor
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

    /**
     * Updates an existing customer in the database.
     * @param customer The customer object with updated information.
     * @return The number of rows affected.
     */
    public int updateCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMES, customer.getFullNames());
        values.put(COLUMN_GENDER, customer.getGender());
        values.put(COLUMN_BIRTHDATE, customer.getBirthDate().getTime());
        values.put(COLUMN_NATIONALITY, customer.getNationality());
        values.put(COLUMN_NID, customer.getCustomerNID());

        // Executing the update query
        return db.update(TABLE_CUSTOMERS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(customer.getCustomerID())});
    }

    /**
     * Deletes a customer from the database.
     * @param id The ID of the customer to delete.
     */
    public void deleteCustomer(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Executing the delete operation based on the ID
        db.delete(TABLE_CUSTOMERS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    /**
     * Retrieves a single customer by their ID.
     * @param id The ID of the customer to retrieve.
     * @return The customer object, or null if not found.
     */
    public Customer getCustomer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        // Querying a single record from the database
        Cursor cursor = db.query(TABLE_CUSTOMERS, new String[]{COLUMN_ID, COLUMN_NAMES, COLUMN_GENDER, COLUMN_BIRTHDATE, COLUMN_NATIONALITY, COLUMN_NID},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        Customer customer = null;
        if (cursor != null && cursor.moveToFirst()) {
            customer = new Customer();
            customer.setCustomerID(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            customer.setFullNames(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMES)));
            customer.setGender(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER)));
            customer.setBirthDate(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_BIRTHDATE))));
            customer.setNationality(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NATIONALITY)));
            customer.setCustomerNID(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NID)));
        }
        
        if(cursor != null) {
            cursor.close();
        }
        db.close();

        return customer;
    }
}
