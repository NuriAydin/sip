package com.nuri.proje;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedRecycler extends RecyclerView.Adapter<FeedRecycler.PostHolder> {

    private ArrayList<String> userEmailList;
    private ArrayList<String> userCommentList;
    private ArrayList<String> userImageList;

    public FeedRecycler(ArrayList<String> userEmailList, ArrayList<String> userCommentList, ArrayList<String> userImageList) {
        this.userEmailList = userEmailList;
        this.userCommentList = userCommentList;
        this.userImageList = userImageList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//oluşturulan xml ile recycler row u bağlar
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view  = layoutInflater.inflate(R.layout.chat_feed_recycler_row,parent,false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {//viewHolder oluşunca napcağımıza bakar
        holder.userEmailText.setText(userEmailList.get(position)+" paylaşımı");
        holder.commentText.setText("Açıklama : "+userCommentList.get(position));
        Picasso.get().load(userImageList.get(position)).into(holder.imageView);

    }

    @Override
    public int getItemCount() {//kaç tane row olacağını yazar
        return userEmailList.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{//nerden veri yansıtılacak

        ImageView imageView;
        TextView userEmailText,commentText;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recycler_view_row_imageView);
            userEmailText = itemView.findViewById(R.id.recycler_row_userEmail_textView);
            commentText = itemView.findViewById(R.id.recycler_row_comment_textView);
        }
    }
}
