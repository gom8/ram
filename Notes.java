package net.gommette.ram;

import android.content.Context;
import android.content.SharedPreferences;

public class Notes {
    public static final String PREFS_NAME = "net.gommette.nexnotes.PREFERENCES";
    public static SharedPreferences prefs;
    public static SharedPreferences.Editor editor;

    public static void getPreferences(Context context){
        prefs = context.getSharedPreferences(Notes.PREFS_NAME, context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public static String getNotes(Context context) {
        return prefs.getString("notes", "");
    }

    public static boolean getHidden(Context context) {
        return prefs.getBoolean("hidden", false);
    }

    public static void setNotes(String notes){
        editor.putString("notes", notes);
        editor.commit();
    }

    public static void setHidden(boolean hidden){
        editor.putBoolean("hidden", hidden);
        editor.commit();
    }

}
