package todo.zuzanna.com.todolist

import android.view.View
import org.jetbrains.anko.*

class MainActivityView : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        relativeLayout {
            lparams(width = matchParent, height = matchParent)
            listView {
                id = R.id.todo_list

            }.lparams(width = wrapContent, height = wrapContent)
        }
    }
}