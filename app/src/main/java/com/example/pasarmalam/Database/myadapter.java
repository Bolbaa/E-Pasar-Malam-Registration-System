package com.example.pasarmalam.Database;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pasarmalam.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<UserHelperClass, myadapter.myviewholder> {

    public myadapter(@NonNull FirebaseRecyclerOptions<UserHelperClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull UserHelperClass UserHelperClass) {

        holder.name.setText(UserHelperClass.getFullName());
        holder.username.setText(UserHelperClass.getUsername());
        holder.email.setText(UserHelperClass.getEmail());
        holder.PhoneNo.setText(UserHelperClass.getPhoneNo());
        holder.Password.setText(UserHelperClass.getPassword());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Are sure to delete");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Users").child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                    }
                });
        
            } //End of OnbindviewMethod



            @NonNull
            @Override
            public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_profile, parent, false);
                return new myviewholder(view);
            }

            class myviewholder extends RecyclerView.ViewHolder {
                CircleImageView img;
                TextView name, username, email, PhoneNo, Password;
                ImageView delete;


                public myviewholder(@NonNull View itemView) {
                    super(itemView);
                    img = (CircleImageView) itemView.findViewById(R.id.img1);
                    name = (TextView) itemView.findViewById(R.id.userName);
                    username = (TextView) itemView.findViewById(R.id.userUsername);
                    email = (TextView) itemView.findViewById(R.id.userEmail);
                    PhoneNo = (TextView) itemView.findViewById(R.id.userPhoneNum);
                    Password = (TextView) itemView.findViewById(R.id.userPassword);

                    delete = (ImageView) itemView.findViewById(R.id.btn_del);

                }

            }


        }

