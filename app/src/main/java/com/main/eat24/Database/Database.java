package com.main.eat24.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.main.eat24.Common.Common;
import com.main.eat24.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;



public class Database extends SQLiteAssetHelper{

    private static final String DB_NAME = "EatItDB.db";
    private static final int DB_VER = 1;

    public Database(Context context) {
        super(context, DB_NAME,null, DB_VER);
    }

    public List<Order> getCart() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ProductId", "ProductName", "Quantity", "Price", "Discount"};
        String sqlTable = "OrderDetail";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<Order> result = new ArrayList<>();
        cursor.moveToFirst();

        int ProductId_index = cursor.getColumnIndex(sqlSelect[0]);
        int ProductName_index = cursor.getColumnIndex(sqlSelect[1]);
        int Quantity_index = cursor.getColumnIndex(sqlSelect[2]);
        int Price_index = cursor.getColumnIndex(sqlSelect[3]);
        int Discount_index = cursor.getColumnIndex(sqlSelect[4]);


        while (!cursor.isAfterLast()) {

            result.add(new Order(cursor.getString(ProductId_index)
                    , cursor.getString(ProductName_index)
                    , cursor.getString(Quantity_index)
                    , cursor.getString(Price_index)
                    , cursor.getString(Discount_index)));

            cursor.moveToNext();
        }
        return result;
    }

    public void addToCart(Order order) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(ProductId, ProductName,Quantity, Price, Discount) VALUES('%s', '%s', '%s', '%s', '%s');"
                , order.getProductId()
                , order.getProductName()
                , order.getQuantity()
                , order.getPrice()
                , order.getDiscount());

        db.execSQL(query);
    }

    public void cleanToCart() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");

        db.execSQL(query);
    }

    public boolean checkData(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        String sql ="SELECT ProductId FROM OrderDetail where ProductId="+Common.currentId;
        cursor=db.rawQuery(sql,null);

        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }else{
            cursor.close();
            return false;
        }

    }

    public boolean checkEmpty(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        String sql ="SELECT * FROM OrderDetail";
        cursor=db.rawQuery(sql,null);

        if(cursor.getCount()>0){
            cursor.close();
            return false;
        }else{
            cursor.close();
            return true;
        }
    }
}