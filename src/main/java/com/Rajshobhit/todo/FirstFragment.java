package com.Rajshobhit.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {
    private List<String> tasks;
    private TaskAdapter adapter;
    private EditText taskInput;
    private RecyclerView taskList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        tasks = new ArrayList<>();
        taskInput = view.findViewById(R.id.taskInput);
        taskList = view.findViewById(R.id.taskList);
        MaterialButton addButton = view.findViewById(R.id.addButton);

        adapter = new TaskAdapter(tasks);
        taskList.setLayoutManager(new LinearLayoutManager(requireContext()));
        taskList.setAdapter(adapter);

        addButton.setOnClickListener(v -> addTask());

        return view;
    }

    private void addTask() {
        String taskText = taskInput.getText().toString().trim();
        if (!taskText.isEmpty()) {
            tasks.add(taskText);
            adapter.notifyItemInserted(tasks.size() - 1);
            taskInput.setText("");
            Toast.makeText(getContext(), "Task added", Toast.LENGTH_SHORT).show();
        }
    }
}
