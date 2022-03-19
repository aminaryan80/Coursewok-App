package edu.sharif.courseworkapp.adapter;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import edu.sharif.courseworkapp.model.Homework;

public class RecyclerViewProfessorHomeworkListAdapter extends RecyclerViewHomeworkListAdapter {
    public RecyclerViewProfessorHomeworkListAdapter(List<Homework> homeworkList, Context context, String username, String courseId) {
        super(homeworkList, context, username, courseId);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeworkViewHolder holder, int position) {
        Homework homework = homeworkList.get(position);
        holder.getNameTextView().setText(homework.getName());

        holder.getHomeworkImageView().setImageResource(homework.getImage());

        // TODO: open homework page
        holder.getHomeworkItem().setOnClickListener(view -> {
            String productName = homeworkList.get(position).getName();
            Toast.makeText(context, productName + " is selected2", Toast.LENGTH_SHORT).show();
        });
    }
}