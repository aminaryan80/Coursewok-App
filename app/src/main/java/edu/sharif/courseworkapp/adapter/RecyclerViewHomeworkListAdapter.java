package edu.sharif.courseworkapp.adapter;

import android.annotation.SuppressLint;
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
import edu.sharif.courseworkapp.model.Homework;
import edu.sharif.courseworkapp.model.user.User;
import edu.sharif.courseworkapp.ui.homework.ProfessorHomeworkPageActivity;
import edu.sharif.courseworkapp.ui.homework.StudentHomeworkPageActivity;

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

        holder.getHomeworkItem().setOnClickListener(view -> handleOnClickItem(position));
    }

    private void handleOnClickItem(int position) {
        Homework homework = homeworkList.get(position);

        Intent intent;
        User user = User.getUserByUsername(username);
        assert user != null;
        if (user.isStudent()) {
            intent = new Intent(context, StudentHomeworkPageActivity.class);
        } else {
            intent = new Intent(context, ProfessorHomeworkPageActivity.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("username", username);
        intent.putExtra("homeworkId", homework.getId());
        context.startActivity(intent);
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