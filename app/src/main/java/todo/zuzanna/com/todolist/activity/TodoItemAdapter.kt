package todo.zuzanna.com.todolist.activity

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.db.delete
import todo.zuzanna.com.todolist.R
import todo.zuzanna.com.todolist.db.database

class TodoItemAdapter(val list: ArrayList<String> = ArrayList<String>()) : BaseAdapter() {

    override fun getView(i: Int, v: View?, parent: ViewGroup?): View {
        return with(parent!!.context) {
            linearLayout {
                lparams(width = matchParent, height = matchParent)
                orientation = LinearLayout.HORIZONTAL
                textView {
                    id = R.id.taskTitle
                    text = list[i]
                    textSize = 20f
                    padding = dip(10)
                }
                button {
                    id = R.id.taskDelete
                    text = "Done"
                    setOnClickListener { l ->
                        removeTodo(i);

                    }
                }
            }
        }
    }

    private fun Context.removeTodo(i: Int) {
        database.use {
            delete("Todo",
                    "title = {title}", Pair("title", list[i]));

        }
        list.removeAt(i);
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): String {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        //can be used to return the item's ID column of table
        return 0L
    }
}