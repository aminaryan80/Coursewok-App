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
import edu.sharif.courseworkapp.model.Answer;
import edu.sharif.courseworkapp.model.Homework;
import edu.sharif.courseworkapp.model.user.User;
import edu.sharif.courseworkapp.ui.homework.ProfessorHomeworkPage;
import edu.sharif.courseworkapp.ui.homework.StudentHomeworkPage;

public class RecyclerViewProfessorHomeworkAnswersListAdapter extends RecyclerView.Adapter<
        RecyclerViewProfessorHomeworkAnswersListAdapter.AnswerViewHolder> {
    protected final String username;
    protected final String homeworkId;
    protected final Context context;
    protected List<Answer> answerList;

    public RecyclerViewProfessorHomeworkAnswersListAdapter(
            List<Answer> answerList, Context context,
            String username, String homeworkId
    ) {
        this.username = username;
        this.homeworkId = homeworkId;
        this.context = context;
        this.answerList = answerList;
    }

    public static class AnswerViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView answerPreviewTextView;
        private final ImageView answerImageView;
        private final LinearLayout answerItem;

        public AnswerViewHolder(@NonNull View view) {
            super(view);
            this.answerImageView = view.findViewById(R.id.idAnswerImage);
            this.nameTextView = view.findViewById(R.id.idStudentName);
            this.answerPreviewTextView = view.findViewById(R.id.idAnswerPreview);
            this.answerItem = view.findViewById(R.id.idAnswerItem);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getAnswerPreviewTextView() {
            return answerPreviewTextView;
        }

        public ImageView getAnswerImageView() {
            return answerImageView;
        }

        public LinearLayout getAnswerItem() {
            return answerItem;
        }

    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View homeworkView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.answer_item, parent, false
        );
        return new AnswerViewHolder(homeworkView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        Answer answer = answerList.get(position);
        holder.getNameTextView().setText(User.getUserByUsername(answer.getStudentId()).getDisplayName());
        String answerT = answer.getAnswer();
        if (answerT.length() > 15) {
            holder.getAnswerPreviewTextView().setText(answerT.substring(0, 10) + "...");
        } else {
            holder.getAnswerPreviewTextView().setText(answerT);
        }
        holder.getAnswerImageView().setImageResource(answer.getImage());
        holder.getAnswerItem().setOnClickListener(view -> handleOnClickItem(position));
    }

    private void handleOnClickItem(int position) {
        Answer answer = answerList.get(position);
        //TODO
        Intent intent = new Intent(context, ProfessorHomeworkPage.class);;
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("username", username);
        intent.putExtra("answerId", answer.getId());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFilter(List<Answer> filtered) {
        this.answerList = filtered;
        notifyDataSetChanged();
    }
}