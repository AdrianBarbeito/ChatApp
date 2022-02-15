package institute.immune.chatapp.Class;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String script = "CREATE TABLE 'user' (_id INTEGER PRIMARY KEY AUTOINCREMENT, 'name' TEXT, 'mail' TEXT)";
    private SQLiteDatabase db;

    public MyOpenHelper(Context context) {
        super(context, "usersDb.SQLite", null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void crearUsuario(String nombre, String mail){
        ContentValues cv = new ContentValues();
        cv.put("name", nombre);
        cv.put("mail", mail);
        db.insert("user", null, cv);
    }

    public void borrarUsuario(int id){
        String[] args = new String[]{
                String.valueOf(id)
        };
        db.delete("user","_id = ?", args);
    }

    public ArrayList<User> showUsuarios(){
        ArrayList<User> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                @SuppressLint("Range") User user = new User(cursor.getInt(cursor.getColumnIndex("_id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("mail")));
                userList.add(user);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return userList;
    }

    public void setName(String name){
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        db.insert("user", null, cv);
    }

    public void setMail(String mail){
        ContentValues cv = new ContentValues();
        cv.put("mail", mail);
        db.insert("user", null, cv);
    }

    public void setCategory(String category){
        ContentValues cv = new ContentValues();
        cv.put("category", category);
        db.replace("user", null, cv);
    }

    public void setOnline(Boolean online){
        ContentValues cv = new ContentValues();
        cv.put("online", online);
        db.insert("user", null, cv);
    }
}
