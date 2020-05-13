package com.example.quanlychitieu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.ViewHoder> {
    private MainActivity context;
    private List<Deal> dealList;
    public DealAdapter(MainActivity context, List<Deal> list){
        this.context=context;
        this.dealList=list;
    }


    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.deal_iteam,null);

        return new ViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder viewHoder, int position) {
        final Deal deal=dealList.get(position);

        viewHoder.tvName.setText(deal.getName());
        viewHoder.tvNote.setText(deal.getNote());
        viewHoder.tvPrice.setText(String.valueOf(deal.getPrice()));
        viewHoder.iv.setImageResource(R.drawable.ic_launcher_background);

        viewHoder.imageviewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,updateActivity.class);
                intent.putExtra("detail",deal);
                context.startActivity(intent);
//                Toast.makeText(context, deal.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        viewHoder.imageviewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacnhanxoa(deal.getName(), (int) deal.getId());
                return;
            }
        });
    }
    private void xacnhanxoa(String ten, final int id){
        AlertDialog.Builder dialogXoa=new AlertDialog.Builder(context);
        dialogXoa.setMessage("bạn có muốn xoa " +ten+ " khong");
        dialogXoa.setPositiveButton("có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.deletemonan(id);
            }
        });
        dialogXoa.setNegativeButton("khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }
    @Override
    public int getItemCount() {
        return dealList.size();
    }

    class ViewHoder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvNote;
        TextView tvPrice;
        ImageView iv;
        ImageView imageviewEdit;
        ImageView imageviewDelete;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvname);
            tvNote=itemView.findViewById(R.id.tvNote);
            tvPrice=itemView.findViewById(R.id.price);
            iv=itemView.findViewById(R.id.im);
            imageviewEdit=itemView.findViewById(R.id.imageviewEdit);
            imageviewDelete=itemView.findViewById(R.id.imageviewDelete);
        }
    }

}
