package com.example.todolistapp

import TaskAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter
    private val taskList = mutableListOf<TaskItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(binding.recyclerView.id)
        recyclerView.layoutManager = LinearLayoutManager(this)

        taskAdapter = TaskAdapter(taskList)
        recyclerView.adapter = taskAdapter

        val fab: FloatingActionButton = findViewById(binding.fab.id)
        fab.setOnClickListener {
            val newTask = TaskItem("Nova Tarefa", false)
            taskList.add(newTask)
            taskAdapter.notifyItemInserted(taskList.size - 1)
        }

        taskAdapter.setOnItemClickListener(object : TaskAdapter.OnItemClickListener {
            override fun onDeleteClick(position: Int) {
                removeTask(position)
            }
        })
    }

    fun removeTask(position: Int) {
        taskList.removeAt(position)
        taskAdapter.notifyItemRemoved(position)
    }
}
