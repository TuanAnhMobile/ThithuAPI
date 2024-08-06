package android.mobile.thithuapi.activity;

import android.mobile.thithuapi.R;
import android.mobile.thithuapi.adapter.XemayAdapter;
import android.mobile.thithuapi.databinding.ActivityMainBinding;
import android.mobile.thithuapi.databinding.BottomsheetAddBinding;
import android.mobile.thithuapi.model.Xemay;
import android.mobile.thithuapi.service.ApiMotor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    XemayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getXemay();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMotor();
            }
        });

    }

    private void addMotor() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        BottomsheetAddBinding bottomSheetBinding = BottomsheetAddBinding.inflate(getLayoutInflater());
        bottomSheetDialog.setContentView(bottomSheetBinding.getRoot());
        bottomSheetDialog.show();

        bottomSheetBinding.btnThemMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = bottomSheetBinding.edtNameMotor.getText().toString();
                String color = bottomSheetBinding.edtColorMotor.getText().toString();
                String price = bottomSheetBinding.edtPriceMotor.getText().toString();
                String mota = bottomSheetBinding.edtMotaMortor.getText().toString();
                String img = bottomSheetBinding.edtImgMotor.getText().toString();

                Xemay xemay = new Xemay();
                xemay.setTen_xe(name);
                xemay.setMau_sac(color);
                xemay.setGia_ban(price);
                xemay.setMo_ta(mota);
                xemay.setHinh_anh(img);

                ApiMotor.api.addXemay(xemay).enqueue(new Callback<Xemay>() {
                    @Override
                    public void onResponse(Call<Xemay> call, Response<Xemay> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            getXemay();
                            bottomSheetDialog.dismiss();
                        } else {
                            Log.e("API_CALL_FAILURE1", "Không thể thêm dữ liệu sản phẩm từ API: " + response.message());
                            Toast.makeText(MainActivity.this, "Không thể thêm dữ liệu sản phẩm từ API: " + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Xemay> call, Throwable t) {
                        Log.e("API_CALL_FAILURE2222", "Thất bại thật rồi: " + t.getMessage());
                        Toast.makeText(MainActivity.this, "Call API thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void getXemay() {
        ApiMotor.api.getXemay().enqueue(new Callback<List<Xemay>>() {
            @Override
            public void onResponse(Call<List<Xemay>> call, Response<List<Xemay>> response) {
                if (response.isSuccessful()) {
                    List<Xemay> list = response.body();
                    for (Xemay xm : list) {
                        Log.d("Du lieu nay", "onResponse: " + xm.getId() + xm.getTen_xe() + xm.getMau_sac() + xm.getGia_ban() + xm.getMo_ta() + xm.getHinh_anh());
                    }
                    adapter = new XemayAdapter(list);
                    binding.recycLer05.setAdapter(adapter);
                    binding.recycLer05.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                } else {
                    Log.e("API_CALL_FAILURE1", "Không thể nhận dữ liệu sản phẩm từ API: " + response.message());
                    Toast.makeText(MainActivity.this, "Không thể nhận dữ liệu sản phẩm từ API: " + response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Xemay>> call, Throwable t) {
                Log.e("API_CALL_FAILURE", "Thất bại thật rồi: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Call Api thất bại", Toast.LENGTH_SHORT).show();
            }
        });

    }
}