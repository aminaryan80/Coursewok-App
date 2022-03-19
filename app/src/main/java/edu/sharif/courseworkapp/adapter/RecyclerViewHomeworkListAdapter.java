package edu.sharif.courseworkapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.model.Homework;

public class RecyclerViewHomeworkListAdapter extends RecyclerView.Adapter<
        RecyclerViewHomeworkListAdapter.HomeworkViewHolder> {
    protected final String username;
    protected final String courseId;
    protected final Context context;
    protected List<Homework> homeworkList;

    public RecyclerViewHomeworkListAdapter(
            List<Homework> homeworkList, Context context,
            String username, String courseId
    ) {
        this.username = username;
        this.courseId = courseId;
        this.context = context;
        this.homeworkList = homeworkList;
    }

    public static class HomeworkViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final ImageView homeworkImageView;
        private final LinearLayout homeworkItem;

        public HomeworkViewHolder(@NonNull View view) {
            super(view);
            this.homeworkImageView = view.findViewById(R.id.idHomeworkImage);
            this.nameTextView = view.findViewById(R.id.idHomeworkName);
            this.homeworkItem = view.findViewById(R.id.idHomeworkItem);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public ImageView getHomeworkImageView() {
            return homeworkImageView;
        }

        public LinearLayout getHomeworkItem() {
            return homeworkItem;
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

        holder.getHomeworkImageView().setImageResource(homework.getImage());

        // TODO: open homework page
        holder.getHomeworkItem().setOnClickListener(view -> {
            String productName = homeworkList.get(position).getName();
            Toast.makeText(context, productName + " is selected", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return homeworkList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFilter(List<Homework> filtered) {
        this.homeworkList = filtered;
        notifyDataSetChanged();
    }
}