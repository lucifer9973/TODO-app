package com.Rajshobhit.todo;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<String> tasks;

    public TaskAdapter(List<String> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.textView.setText(tasks.get(position));

        // Reset checkbox and text decoration state
        holder.checkBox.setChecked(false);
        holder.textView.setPaintFlags(holder.textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

        holder.deleteIcon.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                tasks.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
                Toast.makeText(v.getContext(), "Task deleted", Toast.LENGTH_SHORT).show();
            }
        });

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                holder.textView.setPaintFlags(holder.textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.textView.setPaintFlags(holder.textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView textView;
        ImageView deleteIcon;
        CheckBox checkBox;

        TaskViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.task_card);
            textView = itemView.findViewById(R.id.task_text);
            deleteIcon = itemView.findViewById(R.id.delete_icon);
            checkBox = itemView.findViewById(R.id.task_checkbox);
        }
    }
}