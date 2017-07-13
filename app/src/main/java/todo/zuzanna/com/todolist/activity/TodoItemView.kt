package todo.zuzanna.com.todolist.activity

import android.view.View
import org.jetbrains.anko.*
import todo.zuzanna.com.todolist.R

class TodoItemView : AnkoComponent<TodoItemActivity> {
    override fun createView(ui: AnkoContext<TodoItemActivity>) = with(ui) {
        relativeLayout {
            lparams(width  = matchParent, height = matchParent)
            textView {
                id = R.id.taskTitle
                text = "what to do"
                textSize = 20f
            }.lparams(width  = matchParent, height = matchParent)
            button {
                id = R.id.taskDelete
                text = "Done"
            }.lparams(width  = matchParent, height = matchParent)
        }
    }
}