package com.example.bookstorephuongnam.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookstorephuongnam.DAO.TheLoaiDAO;
import com.example.bookstorephuongnam.Modal.TheLoai;
import com.example.bookstorephuongnam.R;
import java.util.List;

public class Adapter_TheLoai extends BaseAdapter {
    List<TheLoai> listTheLoai;
    TheLoaiDAO theLoaiDAO;
    public Activity context;
    private LayoutInflater inflater;

    public Adapter_TheLoai(Activity context, List<TheLoai> listTheLoai) {
        super();
        this.listTheLoai = listTheLoai;
        this.theLoaiDAO = new TheLoaiDAO(context);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listTheLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return listTheLoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView avatar;
        TextView txtMaTheLoai;
        TextView txtTenTheLoai;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_theloai, null);
            holder.avatar = (ImageView) convertView.findViewById(R.id.img_avatar);
            holder.txtMaTheLoai = (TextView) convertView.findViewById(R.id.tv_matheloai);
            holder.txtTenTheLoai = (TextView) convertView.findViewById(R.id.tv_tentheloai);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.img_delete_theloai);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theLoaiDAO.deleteTheLoaiByID(listTheLoai.get(position).getMaTheLoai());
                    listTheLoai.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        TheLoai _entry = (TheLoai) listTheLoai.get(position);
        holder.avatar.setImageResource(R.drawable.ic_bar_chart);
        holder.txtMaTheLoai.setText(_entry.getMaTheLoai());
        holder.txtTenTheLoai.setText(_entry.getTenTheLoai());

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<TheLoai> items) {
        this.listTheLoai = items;
        notifyDataSetChanged();
    }
}
