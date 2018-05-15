package com.home.news.rssfeed.network.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

@Entity(tableName = "articles")
public class Article  implements Parcelable {
    @PrimaryKey
    public Long id;
    public String author;
    public String title;
    public String description;
    public String url;
    @ColumnInfo(name = "url_to_image")
    public String urlToImage;
    @ColumnInfo(name = "published_at")
    public Date publishedAt;

    public Article(){

    }

    public Article(Parcel in) {
        publishedAt = new Date(in.readLong());
        urlToImage = in.readString();
        url = in.readString();
        description = in.readString();
        title = in.readString();
        author = in.readString();
        id = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(publishedAt.getTime());
        parcel.writeString(urlToImage);
        parcel.writeString(url);
        parcel.writeString(description);
        parcel.writeString(title);
        parcel.writeString(author);
        parcel.writeLong(id);
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
