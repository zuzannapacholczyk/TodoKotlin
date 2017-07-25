package todo.zuzanna.com.todolist.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by zuzanna on 13.07.17.
 */
class TodoDbOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "TodoDb", null, 1) {

    companion object {
        private var instance: TodoDbOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): TodoDbOpenHelper {
            if (instance == null) {
                instance = TodoDbOpenHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
            // Here you create tables
            db.createTable("Todo", true,
                    "id" to INTEGER + PRIMARY_KEY,
                    "title" to TEXT + NOT_NULL)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable("Todo", true)
        onCreate(db)
    }
}

// Access property for Context
val Context.database: TodoDbOpenHelper
    get() = TodoDbOpenHelper.getInstance(getApplicationContext())