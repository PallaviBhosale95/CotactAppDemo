package contact.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper{
    static final String DBName = "contacts.db";
    static  final String tableName = "contact";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_NUMBER = "mobileNo";
    static final String COLUMN_ID = "id";
    static final String CREATE_TABLE_QUERY = "create table contact(id integer primary key, name text, mobileNo text)";

    public MyDatabase(Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

   public  long insertContact(ContactItem contactItem){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",contactItem.getName());
        contentValues.put("mobileNo",contactItem.getMobileNo());
        long insertedId=database.insert(tableName,null,contentValues);
        return insertedId;

    }



    public ArrayList<ContactItem> getAllContacts(){
        ArrayList<ContactItem> contact=new ArrayList<>();
        SQLiteDatabase database=getReadableDatabase();

        Cursor cursor=database.query(tableName,null,null,null,null,null,null);
         cursor.moveToFirst();

        while (true){
            if (cursor.isAfterLast())
                break;
                String name=cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String mobileNo=cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER));
                Long id=cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                ContactItem contactItem=new ContactItem(name,mobileNo);
                contactItem.setName(name);
                contactItem.setMobileNo(mobileNo);
                contactItem.setId(id);
                contact.add(contactItem);
                cursor.moveToNext();



        }
        cursor.close();
        return contact;
    }

    public void delete(long id){
        SQLiteDatabase database=getWritableDatabase();
        database.delete(tableName,"id="+id,null);
    }
}
