package edu.sharif.courseworkapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.model.Course;
import edu.sharif.courseworkapp.utils.CourseImageUtils;

public class RecyclerViewCourseListAdapter extends RecyclerView.Adapter<
        RecyclerViewCourseListAdapter.CourseViewHolder> {

    private final List<Course> courseList;
    Context context;

    public RecyclerViewCourseListAdapter(List<Course> courseList, Context context) {
        this.courseList = courseList;
        this.context = context;
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView courseNameTextView;
        private final TextView profNameTextView;

        public CourseViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.idCourseImage);
            courseNameTextView = view.findViewById(R.id.idCourseName);
            profNameTextView = view.findViewById(R.id.idProfessorName);
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

        holder.getCourseNameTextView().setOnClickListener(view -> {
            String productName = courseList.get(position).getName();
            Toast.makeText(context, productName + " is selected", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}
