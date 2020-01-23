package com.example.myapplication.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.model.User;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {

    private List<User> user_arraylist = new ArrayList<>();
    private Context mContext;

    public UserRecyclerAdapter(List<User> user_arraylist, Context mContext) {
        this.user_arraylist = user_arraylist;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row, viewGroup, false);
        return new UserRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        final User user = user_arraylist.get(i);
        viewHolder.username_txtvw.setText(user.getLogin());
        viewHolder.usertype_txtvw.setText(user.getType());

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image);

        Glide.with(mContext).load(user.getAvatar_url()).apply(options).into(viewHolder.user_imgvw);

        viewHolder.mlinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(mContext,user.getAvatar_url(),i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return user_arraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView user_imgvw;
        TextView username_txtvw,usertype_txtvw;
        LinearLayout mlinearLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_imgvw = (ImageView) itemView.findViewById(R.id.user_imgvw);
            username_txtvw = (TextView) itemView.findViewById(R.id.username_txtvw);
            usertype_txtvw = (TextView) itemView.findViewById(R.id.usertype_txtvw);
            mlinearLayout = (LinearLayout) itemView.findViewById(R.id.rowindex_linlay);
        }
    }


    public void showDialog(Context context, String url, final int position){

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.imagelayout);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);


        PhotoView photoView = (PhotoView) dialog.findViewById(R.id.user_photovw);
        Glide.with(context).load(url).into(photoView);
        Button ok_dialogButton = (Button) dialog.findViewById(R.id.ok_btn);
        Button delete_dialogButton = (Button) dialog.findViewById(R.id.delete_btn);
        ok_dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        delete_dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                user_arraylist.remove(position);
                UserRecyclerAdapter.this.notifyDataSetChanged();
            }
        });

        dialog.show();

    }
}