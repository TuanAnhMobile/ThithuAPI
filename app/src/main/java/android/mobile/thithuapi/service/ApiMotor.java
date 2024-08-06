package android.mobile.thithuapi.service;

import android.mobile.thithuapi.model.Xemay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiMotor {
    Gson gson = new GsonBuilder()
            .setLenient()  // Thêm setLenient để chấp nhận JSON không chuẩn
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiMotor api = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiMotor.class);

    @GET("xemay")
    Call<List<Xemay>> getXemay();
    @POST("xemay")
    Call<Xemay> addXemay(@Body Xemay xemay);
    @DELETE("xemay/{id}")
    Call<Void> deleteMotor(@Path("id") String id);
    @PUT("xemay/{id}")
    Call<Xemay> updateSinhVien(@Path("id") String id, @Body Xemay Xemay);


}
