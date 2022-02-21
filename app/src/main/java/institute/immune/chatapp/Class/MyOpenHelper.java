package institute.immune.chatapp.Class;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public void crearUsuario(String nickName, String mail, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        ContentValues cv = new ContentValues();

        cv.put("nickName", nickName);
        cv.put("mail", mail);
        cv.put("password", codificarPassword(password));
        db.insert("user", null, cv);
    }

    @SuppressLint("Range")
    public Boolean comprobarLogin(String mail, String password) throws NoSuchAlgorithmException {

            String[] args = new String[]{
                    mail
            };
            Cursor cursor = db.rawQuery("SELECT password FROM user WHERE mail = ?", args);

            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                if (cursor.getString(cursor.getColumnIndex("password")).equals(codificarPassword(password))){
                    return true;
                } else{
                    return false;
                }
            } else{
                return false;
            }
    }

    @SuppressLint("Range")
    public ArrayList<User> showUsuarios(){
        ArrayList<User> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                User user = new User(cursor.getInt(cursor.getColumnIndex("_id")),
                        cursor.getString(cursor.getColumnIndex("nickName")),
                        cursor.getString(cursor.getColumnIndex("mail")),
                        cursor.getString(cursor.getColumnIndex("password")),
                        cursor.getString(cursor.getColumnIndex("category")));
                userList.add(user);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return userList;
    }

    @SuppressLint("Range")
    public Integer searchByMail(String mail){
        String[] args = new String[]{
                mail
        };
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE mail = ?", args);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getInt(cursor.getColumnIndex("_id"));
        }else{
            return 1000;
        }
    }


    public ArrayList<User> searchByCategory(String category){
        ArrayList<User> userList = new ArrayList<>();
        String[] args = new String[]{
                category
        };
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE category = ?", args);


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
                replaceCategory(cursor.getInt(cursor.getColumnIndex("_id")), categories[(int) Math.round( Math.random() * (categories.length -1))]);

            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public String codificarPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(password.getBytes(StandardCharsets.UTF_8));

        return String.format("%040x", new BigInteger(1, digest.digest()));
    }
}
