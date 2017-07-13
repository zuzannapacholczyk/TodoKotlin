package todo.zuzanna.com.todolist.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.setContentView

import todo.zuzanna.com.todolist.R

class TodoItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TodoItemView().setContentView(this)
    }
}
