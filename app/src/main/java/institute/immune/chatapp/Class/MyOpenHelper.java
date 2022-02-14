package institute.immune.chatapp.Class;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String script = "CREATE TABLE 'user' (_id INTEGER PRIMARY KEY AUTOINCREMENT, 'name' TEXT, 'mail' TEXT)";
    private SQLiteDatabase db;

    public MyOpenHelper(@Nullable Context context) {
        super(context, "users.sqlite", null, 1);
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

    public ArrayList<Usuario> obtenerUsuarios(){
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                @SuppressLint("Range") Usuario usuario = new Usuario(cursor.getInt(cursor.getColumnIndex("_id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("mail")));
                listaUsuarios.add(usuario);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return listaUsuarios;
    }
}
