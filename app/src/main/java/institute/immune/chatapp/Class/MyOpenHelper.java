package institute.immune.chatapp.Class;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper {

    private static final String script = "CREATE TABLE 'user' (_id INTEGER PRIMARY KEY AUTOINCREMENT, 'nickName' TEXT UNIQUE NOT NULL, 'mail' TEXT UNIQUE NOT NULL, 'password' TEXT NOT NULL, 'category' TEXT, 'online' INTEGER)";
    private SQLiteDatabase db;

    public MyOpenHelper(Context context) {
        super(context, "DiscussDB.SQLite", null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void updateDataBase(){
        db.setVersion(1);
    }

    public void crearUsuario(String nickName, String mail, String password){
        ContentValues cv = new ContentValues();
        cv.put("nickName", nickName);
        cv.put("mail", mail);
        cv.put("password", password);
        db.insert("user", null, cv);
    }

    public void borrarUsuario(int id){
        String[] args = new String[]{
                String.valueOf(id)
        };
        db.delete("user","_id = ?", args);
    }

    @SuppressLint("Range")
    public ArrayList<User> showUsuarios(){
        ArrayList<User> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                @SuppressLint("Range") User user = new User(cursor.getInt(cursor.getColumnIndex("_id")),
                        cursor.getString(cursor.getColumnIndex("nickName")),
                        cursor.getString(cursor.getColumnIndex("mail")),
                        cursor.getString(cursor.getColumnIndex("password")));
                userList.add(user);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return userList;
    }

    public Integer countUsers(){
        Cursor cursor = db.rawQuery("SELECT COUNT(_id) AS cuenta FROM 'user';", null);
        cursor.moveToFirst();

        @SuppressLint("Range") Integer count = cursor.getInt(cursor.getColumnIndex("cuenta"));
        cursor.close();

        return count;
    }

    public ArrayList<User> searchByCategory(String category){
        ArrayList<User> userList = new ArrayList<>();
        String[] args = new String[]{
                category
        };
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE 'category' == ?", args);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                @SuppressLint("Range") User user = new User(cursor.getInt(cursor.getColumnIndex("_id")),
                        cursor.getString(cursor.getColumnIndex("nickName")),
                        cursor.getString(cursor.getColumnIndex("mail")),
                        cursor.getString(cursor.getColumnIndex("password")));
                userList.add(user);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return userList;
    }

    public void replaceName(int id, String nickName){
        ContentValues cv = new ContentValues();
        String[] args = new String[]{
                String.valueOf(id)
        };
        cv.put("nickName", nickName);

        db.update("user", cv, "_id = ?", args);
    }

    public void replaceMail(int id, String mail){
        ContentValues cv = new ContentValues();
        String[] args = new String[]{
                String.valueOf(id)
        };
        cv.put("mail", mail);

        db.update("user", cv, "_id = ?", args);
    }

    public void replaceCategory(int id, String category){
        ContentValues cv = new ContentValues();
        String[] args = new String[]{
                String.valueOf(id)
        };
        cv.put("category", category);

        db.update("user", cv, "_id = ?", args);
    }

    public void replaceOnline(int id, Boolean online){
        ContentValues cv = new ContentValues();
        String[] args = new String[]{
                String.valueOf(id)
        };
        if (online){ cv.put("online", 1); }
        else { cv.put("online", 0); }

        db.update("user", cv, "_id = ?", args);
    }

    @SuppressLint("Range")
    public void RandomDb(){
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
        String[] categories = new String[]{
                "sport",
                "movies",
                "politic"
        };
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                replaceCategory(cursor.getInt(cursor.getColumnIndex("_id")), categories[(int) Math.round( Math.random() * categories.length)]);

            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
