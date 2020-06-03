package org.htw_berlin.fb4.cocktailapp.model.ingredient;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient")
public class Ingredient implements Parcelable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "buyable_from")
    public String buyableFrom;

    @ColumnInfo(name = "picture")
    public String picture;

    public Ingredient(String name, String buyableFrom, String picture) {
        this.name = name;
        this.buyableFrom = buyableFrom;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>(){
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }

        public Ingredient createFormelParcel(Parcel in){
            return new Ingredient(in);
        }
    };

    private Ingredient(Parcel in) {
        this.name = in.readString();
        this.buyableFrom = in.readString();
        this.picture = in.readString();
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(buyableFrom);
        dest.writeString(picture);
    }
}
