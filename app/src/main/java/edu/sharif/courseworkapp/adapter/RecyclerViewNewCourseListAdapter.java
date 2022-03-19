package edu.sharif.courseworkapp.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import edu.sharif.courseworkapp.model.Course;

public class RecyclerViewNewCourseListAdapter extends RecyclerViewCourseListAdapter {
    public RecyclerViewNewCourseListAdapter(List<Course> courseList, Context context, String username) {
        super(courseList, context, username);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.getCourseNameTextView().setText(course.getName());

        holder.getProfNameTextView().setText(course.getProfessorName());
        holder.getImageView().setImageResource(course.getImage());

        holder.getCourseItem().setOnClickListener(
                view -> handleOnClickItem(position));
    }

    private void handleOnClickItem(int position) {
        Course course = courseList.get(position);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Join Course");
        alert.setMessage("Do you want to join " + course.getName() + "?");
        alert.setPositiveButton("Yes", (dialog, which) -> joinCourse(course));
        alert.setNegativeButton("No", null);
        alert.show();
    }

    private void joinCourse(Course course) {
        try {
            course.addStudent(username);
        } catch (Resources.NotFoundException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        updateCourse(course);
        ((Activity) context).finish();
    }

    private void updateCourse(Course course) {
        SharedPreferences.Editor sharedPreferencesEditor = context.getSharedPreferences("Courses", MODE_PRIVATE).edit();
        sharedPreferencesEditor.putString(course.getId(), course.encode());
        sharedPreferencesEditor.apply();
    }
}