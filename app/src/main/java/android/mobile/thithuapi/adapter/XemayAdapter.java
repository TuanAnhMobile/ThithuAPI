package android.mobile.thithuapi.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.mobile.thithuapi.R;
import android.mobile.thithuapi.databinding.BottomsheetEditBinding;
import android.mobile.thithuapi.model.Xemay;
import android.mobile.thithuapi.service.ApiMotor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XemayAdapter extends RecyclerView.Adapter<XemayAdapter.viewHolder> {
    List<Xemay> list;
    Context context;

    public XemayAdapter(List<Xemay> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_motor, parent, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        Xemay xemay = list.get(position);
        holder.txt_name.setText(list.get(position).getTen_xe());
        holder.txt_color.setText(list.get(position).getMau_sac());
        holder.txt_gia.setText(list.get(position).getGia_ban());
        Glide.with(context).load(xemay.getHinh_anh()).into(holder.img_motor);

        holder.itemView.setOnClickListener(view -> {


        });

        holder.txt_delete.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle("Xac nhan xoa")
                    .setMessage("Ban co muon xoa khong")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ApiMotor.api.deleteMotor(xemay.getId()).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.isSuccessful()) {
                                        list.remove(position);
                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(position, list.size());

                                        Toast.makeText(context, "Xoa thanh cong roi em oi", Toast.LENGTH_SHORT).show();
                                    } else {

                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Log.e("API_CALL_FAILURE444444", "Thất bại thật rồi: " + t.getMessage());
                                    Toast.makeText(context, "Xoá Api thất bại", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).setNegativeButton("No", null)
                    .show();
        });

        holder.txt_edit.setOnClickListener(view -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
            BottomsheetEditBinding binding = BottomsheetEditBinding.inflate(LayoutInflater.from(context));
            bottomSheetDialog.setContentView(binding.getRoot());
            // set up bottom sheet dialog with current item data and handle edit actions
            bottomSheetDialog.show();

            binding.editNameMotor.setText(list.get(position).getTen_xe());
            binding.editColorMotor.setText(list.get(position).getMau_sac());
            binding.editPriceMotor.setText(list.get(position).getGia_ban());
            binding.editMotaMortor.setText(list.get(position).getMo_ta());
            binding.editImgMotor.setText(list.get(position).getHinh_anh());

            binding.btnEditMotor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = binding.editNameMotor.getText().toString();
                    String color = binding.editColorMotor.getText().toString();
                    String price = binding.editPriceMotor.getText().toString();
                    String mota = binding.editMotaMortor.getText().toString();
                    String img = binding.editImgMotor.getText().toString();

                    Xemay xemay1 = new Xemay();
                    xemay1.setTen_xe(name);
                    xemay1.setMau_sac(color);
                    xemay1.setGia_ban(price);
                    xemay1.setMo_ta(mota);
                    xemay1.setHinh_anh(img);

                    ApiMotor.api.updateSinhVien(xemay1.getId(), xemay1).enqueue(new Callback<Xemay>() {
                        @Override
                        public void onResponse(Call<Xemay> call, Response<Xemay> response) {
                            if (response.isSuccessful()) {
                                list.set(position, response.body());
                                notifyItemChanged(position);
                                Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Xemay> call, Throwable t) {
                            Toast.makeText(context, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView img_motor;
        TextView txt_name, txt_gia, txt_color, txt_edit, txt_delete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img_motor = itemView.findViewById(R.id.pic_motor);
            txt_name = itemView.findViewById(R.id.txt_nameMotor);
            txt_color = itemView.findViewById(R.id.txt_colorMotor);
            txt_gia = itemView.findViewById(R.id.txt_priceMotor);
            txt_edit = itemView.findViewById(R.id.txt_edit);
            txt_delete = itemView.findViewById(R.id.txt_delete);
        }
    }
}
