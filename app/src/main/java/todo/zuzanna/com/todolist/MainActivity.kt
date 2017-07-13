package todo.zuzanna.com.todolist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    var TAG = "Main Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityView().setContentView(this)
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
                        Log.d(TAG, "Task to add: " + task)
                    })
                    noButton { title = "Cancel" }
                }
            }
        }.show()
    }
}
