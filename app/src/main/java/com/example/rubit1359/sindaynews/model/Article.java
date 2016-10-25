package com.example.rubit1359.sindaynews.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rubit1359 on 10/24/2016.
 */

public class Article implements Parcelable {
    // Model class Article
    @SerializedName("snippet")
    private String snippet;
    @SerializedName("web_url")
    private String webUrl;
    @SerializedName("multimedia")
    private List<Media> multimedia;

    protected Article(Parcel in) {
        snippet = in.readString();
        webUrl = in.readString();
        multimedia = in.createTypedArrayList(Media.CREATOR);
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(snippet);
        parcel.writeString(webUrl);
        parcel.writeTypedList(multimedia);
    }

    public String getSnippet() {
        return snippet;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public List<Media> getMultimedia() {
        return multimedia;
    }

    public static class Media implements Parcelable {
        private String url;
        private String type;
        private int width;
        private int height;


        protected Media(Parcel in) {
            url = in.readString();
            type = in.readString();
            width = in.readInt();
            height = in.readInt();
        }

        public static final Creator<Media> CREATOR = new Creator<Media>() {
            @Override
            public Media createFromParcel(Parcel in) {
                return new Media(in);
            }

            @Override
            public Media[] newArray(int size) {
                return new Media[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(url);
            parcel.writeString(type);
            parcel.writeInt(width);
            parcel.writeInt(height);
        }

        public String getUrl() {
            return url;
        }

        public String getType() {
            return type;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
