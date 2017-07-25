package todo.zuzanna.com.todolist

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import org.jetbrains.anko.*
import todo.zuzanna.com.todolist.activity.TodoItemAdapter
import todo.zuzanna.com.todolist.db.database


class MainActivity : AppCompatActivity() {

    var TAG = "Main Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityView().setContentView(this)

        updateUI()
    }

    fun updateUI() {
        val mTaskListView = findViewById(R.id.todo_list) as ListView
        var tasks = mutableListOf<String>()
        database.use {
            val cursor = query("Todo",
                    arrayOf<String>("id", "title"), null, null, null, null, null)
            while (cursor.moveToNext()) {
                val idx = cursor.getColumnIndex("title")
                tasks.add(cursor.getString(idx))
            }
            cursor.close()
        }

        mTaskListView.adapter = TodoItemAdapter(ArrayList(tasks))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_add_task -> {
                createAlert()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun createAlert(): Unit {
        alert(message = "What do you want to do?", title = "Add new todo") {
            customView {
                verticalLayout {
                    val taskToDo = editText()
                    positiveButton("Add", {
                        _ ->
                        val task = taskToDo.text.toString()
                        val values = ContentValues()
                        values.put("title", task)
                        database.use {
                            insertWithOnConflict("Todo", null, values,
                                    SQLiteDatabase.CONFLICT_REPLACE)
                        }
                        updateUI()
                    })
                    noButton { title = "Cancel" }
                }
            }
        }.show()
    }
}
