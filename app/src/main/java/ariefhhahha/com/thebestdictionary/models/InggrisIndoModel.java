package ariefhhahha.com.thebestdictionary.models;

import android.os.Parcel;
import android.os.Parcelable;

public class InggrisIndoModel implements Parcelable {

    private int id;
    private String kata_inggris;
    private String arti_inggris;

    public InggrisIndoModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKata_inggris() {
        return kata_inggris;
    }

    public void setKata_inggris(String kata_inggris) {
        this.kata_inggris = kata_inggris;
    }

    public String getArti_inggris() {
        return arti_inggris;
    }

    public void setArti_inggris(String arti_inggris) {
        this.arti_inggris = arti_inggris;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.kata_inggris);
        dest.writeString(this.arti_inggris);
    }

    public InggrisIndoModel(String kata_inggris, String arti_inggris) {
        this.kata_inggris = kata_inggris;
        this.arti_inggris = arti_inggris;
    }

    protected InggrisIndoModel(Parcel in) {
        this.id = in.readInt();
        this.kata_inggris = in.readString();
        this.arti_inggris = in.readString();
    }

    public static final Parcelable.Creator<InggrisIndoModel> CREATOR = new Parcelable.Creator<InggrisIndoModel>() {
        @Override
        public InggrisIndoModel createFromParcel(Parcel source) {
            return new InggrisIndoModel(source);
        }

        @Override
        public InggrisIndoModel[] newArray(int size) {
            return new InggrisIndoModel[size];
        }
    };
}
