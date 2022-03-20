package edu.sharif.courseworkapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import edu.sharif.courseworkapp.model.Course;
import edu.sharif.courseworkapp.model.Homework;
import edu.sharif.courseworkapp.model.user.User;
import edu.sharif.courseworkapp.ui.course.ProfessorCoursePage;
import edu.sharif.courseworkapp.ui.course.StudentCoursePage;
import edu.sharif.courseworkapp.ui.homework.ProfessorHomeworkPage;
import edu.sharif.courseworkapp.ui.homework.StudentHomeworkPage;

public class RecyclerViewProfessorHomeworkListAdapter extends RecyclerViewHomeworkListAdapter {
    public RecyclerViewProfessorHomeworkListAdapter(List<Homework> homeworkList, Context context, String username, String courseId) {
        super(homeworkList, context, username, courseId);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeworkViewHolder holder, int position) {
        Homework homework = homeworkList.get(position);
        holder.getNameTextView().setText(homework.getName());

        holder.getHomeworkImageView().setImageResource(homework.getImage());

        holder.getHomeworkItem().setOnClickListener(view -> handleOnClickItem(position));
    }

    private void handleOnClickItem(int position) {
        Homework homework = homeworkList.get(position);

        Intent intent;
        User user = User.getUserByUsername(username);
        assert user != null;
        if (user.isStudent()) {
            intent = new Intent(context, StudentHomeworkPage.class);
        } else {
            intent = new Intent(context, ProfessorHomeworkPage.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("username", username);
        intent.putExtra("homeworkId", homework.getId());
        context.startActivity(intent);
    }
}