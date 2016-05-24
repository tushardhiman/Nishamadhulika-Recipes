package info.androidhive.materialdesign.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	Context context;
	public static final String DB_NAME = "com_nishamadhulika";
	static final int DB_VERSION = 1;
	public static final String TABLE_CATEGORIES = "recipe_categories";
	public static final String TABLE_SUBCATEGORIES= "recipe_subcategories";
	
	
	public static final String COL_ID="id";
	
	public static final String COL_NAME="name";
	public static final String COL_ARTICLECOUNT="articlecount";
	
	public static final String COL_TITLE="title";
	public static final String COL_BITMAP_URL="bitmapurl";
	public static final String COL_HTML_URL="htmlurl";
	public static final String COL_VIDEO_URL="videourl";
	public static final String COL_PAGE_NO="pageno";
	public static final String COL_HTML_STRING="htmlstring";
	

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE " +TABLE_CATEGORIES+ " ("+COL_ID+ " INTEGER,"+COL_NAME+" TEXT,"+COL_ARTICLECOUNT+" INTEGER)");
		db.execSQL("CREATE TABLE " +TABLE_SUBCATEGORIES+ " ("+COL_ID+ " INTEGER,"+COL_TITLE+" TEXT,"+COL_BITMAP_URL+" TEXT,"+COL_HTML_URL+" TEXT,"+COL_VIDEO_URL+" TEXT,"+COL_PAGE_NO+" INTEGER,"+COL_HTML_STRING+" TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP "+TABLE_CATEGORIES+" IF EXISTS");
		db.execSQL("DROP "+TABLE_SUBCATEGORIES+" IF EXISTS");
	}

}
