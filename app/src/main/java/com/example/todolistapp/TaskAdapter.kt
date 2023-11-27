import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.TaskItem

class TaskAdapter(private val taskList: MutableList<TaskItem>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onDeleteClick(position: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        val itemTitleTextView: TextView = itemView.findViewById(R.id.itemTaskTitle)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskItem = taskList[position]
        holder.itemTitleTextView.text = taskItem.title
        holder.checkBox.isChecked = taskItem.isChecked

        holder.deleteButton.setOnClickListener {
            listener?.onDeleteClick(position)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}
