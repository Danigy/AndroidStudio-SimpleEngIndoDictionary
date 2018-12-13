package ariefhhahha.com.thebestdictionary.models;

import android.os.Parcel;
import android.os.Parcelable;

public class IndoInggrisModel implements Parcelable {

    private int id;
    private String kata_indonesia;
    private String arti_indonesia;

    public IndoInggrisModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKata_indonesia() {
        return kata_indonesia;
    }

    public void setKata_indonesia(String kata_indonesia) {
        this.kata_indonesia = kata_indonesia;
    }

    public String getArti_indonesia() {
        return arti_indonesia;
    }

    public void setArti_indonesia(String arti_indonesia) {
        this.arti_indonesia = arti_indonesia;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.kata_indonesia);
        dest.writeString(this.arti_indonesia);
    }

    public IndoInggrisModel(String kata_indonesia, String arti_indonesia) {
        this.kata_indonesia = kata_indonesia;
        this.arti_indonesia = arti_indonesia;
    }

    protected IndoInggrisModel(Parcel in) {
        this.id = in.readInt();
        this.kata_indonesia = in.readString();
        this.arti_indonesia = in.readString();
    }

    public static final Parcelable.Creator<IndoInggrisModel> CREATOR = new Parcelable.Creator<IndoInggrisModel>() {
        @Override
        public IndoInggrisModel createFromParcel(Parcel source) {
            return new IndoInggrisModel(source);
        }

        @Override
        public IndoInggrisModel[] newArray(int size) {
            return new IndoInggrisModel[size];
        }
    };
}
