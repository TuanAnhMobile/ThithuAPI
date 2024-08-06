package android.mobile.thithuapi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Xemay implements Serializable {
    @SerializedName("_id")
    private String id;

    @SerializedName("ten_xe")
    private String ten_xe;

    @SerializedName("mau_sac")
    private String mau_sac;

    @SerializedName("gia_ban")
    private String gia_ban;

    @SerializedName("mo_ta")
    private String mo_ta;

    @SerializedName("hinh_anh")
    private String hinh_anh;

    public Xemay() {
    }

    public Xemay(String id, String ten_xe, String mau_sac, String gia_ban, String mo_ta, String hinh_anh) {
        this.id = id;
        this.ten_xe = ten_xe;
        this.mau_sac = mau_sac;
        this.gia_ban = gia_ban;
        this.mo_ta = mo_ta;
        this.hinh_anh = hinh_anh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen_xe() {
        return ten_xe;
    }

    public void setTen_xe(String ten_xe) {
        this.ten_xe = ten_xe;
    }

    public String getMau_sac() {
        return mau_sac;
    }

    public void setMau_sac(String mau_sac) {
        this.mau_sac = mau_sac;
    }

    public String getGia_ban() {
        return gia_ban;
    }

    public void setGia_ban(String gia_ban) {
        this.gia_ban = gia_ban;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public String getHinh_anh() {
        return hinh_anh;
    }

    public void setHinh_anh(String hinh_anh) {
        this.hinh_anh = hinh_anh;
    }
}
