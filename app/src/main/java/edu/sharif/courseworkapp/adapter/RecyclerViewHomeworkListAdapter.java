package edu.sharif.courseworkapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.model.Homework;

public class RecyclerViewHomeworkListAdapter extends RecyclerView.Adapter<
        RecyclerViewHomeworkListAdapter.HomeworkViewHolder> {
    private final String username;
    private final String courseId;
    private final Context context;
    private final List<Homework> homeworkList;

    public RecyclerViewHomeworkListAdapter(
            List<Homework> homeworkList, Context context,
            String username, String courseId
    ) {
        this.homeworkList = homeworkList;
        this.context = context;
        this.username = username;
        this.courseId = courseId;
    }

    public static class HomeworkViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView questionTextView;

        public HomeworkViewHolder(@NonNull View view) {
            super(view);
            this.nameTextView = view.findViewById(R.id.idHomeworkName);
            this.questionTextView = view.findViewById(R.id.idQuestion);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getQuestionTextView() {
            return questionTextView;
        }
    }

    @NonNull
    @Override
    public HomeworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View homeworkView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.homework_item, parent, false
        );
        return new HomeworkViewHolder(homeworkView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeworkViewHolder holder, int position) {
        Homework homework = homeworkList.get(position);
        holder.getNameTextView().setText(homework.getName());
        holder.getQuestionTextView().setText(homework.getQuestion());

        // TODO: open homework page
        holder.getNameTextView().setOnClickListener(view -> {
            String productName = homeworkList.get(position).getName();
            Toast.makeText(context, productName + " is selected", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return homeworkList.size();
    }
}
