package com.example.c12104a3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.c12104a3.ui.theme.C12104A3Theme


data class Task(
    val title: String,
    var isCompleted: Boolean = false
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoApp() {
    var taskInput by remember { mutableStateOf("") }
    val taskList = remember { mutableStateListOf<Task>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "ToDo Task App") }
            )
        }
    ) {
        innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)

        ) {
            // Challenge 1
            OutlinedTextField(
                value = taskInput,
                onValueChange = { taskInput = it },
                label = { Text(text = "Enter Task") },
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(modifier = Modifier.height(12.dp))

            // Challenge 2
            Button(
                onClick = {
                    if (taskInput.isNotBlank()) {
                        taskList.add(Task(title = taskInput.trim()))
                        taskInput = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = "Add Task")

            }
            Spacer(modifier = Modifier.height(16.dp))

            // Challenge 3
            if (taskList.isEmpty()) {
                Text(
                    text = "No tasks to yet",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn (
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(taskList) { index, task ->
                        TaskItem(
                            task = task,
                            onCheckedChange = { isChecked ->
                                taskList[index] = task.copy(isCompleted = isChecked)
                            },
                            onDeleteClick = {
                                taskList.removeAt(index)
                            }
                        )
                    }
                }
            }
        }
    }
}

// Challenge 4
@Composable
fun TaskItem(
    task: Task,
    onCheckedChange: (Boolean) -> Unit,
    onDeleteClick: () -> Unit
) {
    // Challenge 5
    Card(
        modifier = Modifier.fillMaxWidth()
    )  {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ){
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = onCheckedChange
                )

                Text(
                    text = task.title,
                    modifier = Modifier.padding(top = 14.dp),
                    textDecoration = if (task.isCompleted) {
                        TextDecoration.LineThrough
                    } else {
                        TextDecoration.None
                    }
                )
            }
            // Challenge 6
            Button(
                onClick = onDeleteClick
            ){
                Text(text = "Delete")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoAppPreview() {
    ToDoApp()
}


