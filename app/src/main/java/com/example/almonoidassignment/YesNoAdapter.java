package com.example.almonoidassignment;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

public class YesNoAdapter extends RecyclerView.Adapter<YesNoAdapter.ViewHolder> {
    private Context context;
    private String[] questions;
    public YesNoAdapter(Context context, String[] questions){
        this.context = context;
        this.questions=questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yes_no_layout, parent, false);
        YesNoAdapter.ViewHolder viewHolder = new YesNoAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.question.setText(questions[position]);
    }

    @Override
    public int getItemCount() {
        if(questions!=null){
            return questions.length;
        }
        else return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView question;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question_textView);
        }
    }
}
