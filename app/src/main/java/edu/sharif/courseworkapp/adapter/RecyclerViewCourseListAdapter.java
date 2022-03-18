package edu.sharif.courseworkapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.model.Course;
import edu.sharif.courseworkapp.model.user.User;
import edu.sharif.courseworkapp.ui.course.ProfessorCoursePage;
import edu.sharif.courseworkapp.ui.course.StudentCoursePage;
import edu.sharif.courseworkapp.utils.CourseImageUtils;

public class RecyclerViewCourseListAdapter extends RecyclerView.Adapter<
        RecyclerViewCourseListAdapter.CourseViewHolder> {
    private final String username;
    private final Context context;
    private final List<Course> courseList;

    public RecyclerViewCourseListAdapter(
            List<Course> courseList, Context context, String username
    ) {
        this.courseList = courseList;
        this.context = context;
        this.username = username;
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView courseNameTextView;
        private final TextView profNameTextView;
        private final LinearLayout courseItem;

        public CourseViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.idCourseImage);
            courseNameTextView = view.findViewById(R.id.idCourseName);
            profNameTextView = view.findViewById(R.id.idProfessorName);
            courseItem = view.findViewById(R.id.idCourseItem);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getCourseNameTextView() {
            return courseNameTextView;
        }

        public TextView getProfNameTextView() {
            return profNameTextView;
        }

        public LinearLayout getCourseItem() {
            return courseItem;
        }
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.course_item, parent, false
        );
        return new CourseViewHolder(courseView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.getCourseNameTextView().setText(course.getName());

        holder.getProfNameTextView().setText(course.getProfessorName());

        int randomImage = CourseImageUtils.getRandomImage();
        holder.getImageView().setImageResource(randomImage);

        holder.getCourseItem().setOnClickListener(
                view -> handleOnClickItem(position));
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    private void handleOnClickItem(int position) {
        Course course = courseList.get(position);

        Intent intent;
        User user = User.getUserByUsername(username);
        assert user != null;
        if (user.isStudent()) {
            intent = new Intent(context, StudentCoursePage.class);
        } else {
            intent = new Intent(context, ProfessorCoursePage.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("username", username);
        intent.putExtra("courseId", course.getId());
        context.startActivity(intent);
    }
}