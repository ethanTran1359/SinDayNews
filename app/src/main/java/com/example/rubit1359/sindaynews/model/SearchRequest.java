package com.example.rubit1359.sindaynews.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.QueryMap;

/**
 * Created by rubit1359 on 10/24/2016.
 */

public class SearchRequest implements Parcelable{
    // Used to convert each
    private int page = 0;
    private String query;
    private String beginDate;
    private String order = "Newest";
    private boolean hasArts;
    private boolean hasFashionAndStyle;
    private boolean hasSports;

    public SearchRequest() {
    }

    public int getPage() {
        return page;
    }

    public String getQuery() {
        return query;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getOrder() {
        return order;
    }

    public boolean isHasArts() {
        return hasArts;
    }

    public boolean isHasFashionAndStyle() {
        return hasFashionAndStyle;
    }

    public boolean isHasSports() {
        return hasSports;
    }

    protected SearchRequest(Parcel in) {
        page = in.readInt();
        query = in.readString();
        beginDate = in.readString();
        order = in.readString();
        hasArts = in.readByte() != 0;
        hasFashionAndStyle = in.readByte() != 0;
        hasSports = in.readByte() != 0;
    }

    public static final Creator<SearchRequest> CREATOR = new Creator<SearchRequest>() {
        @Override
        public SearchRequest createFromParcel(Parcel in) {
            return new SearchRequest(in);
        }

        @Override
        public SearchRequest[] newArray(int size) {
            return new SearchRequest[size];
        }
    };

    public Map<String, String> toQueryMap() {
        Map<String, String> options = new HashMap<>();
        if (query != null) options.put("q", query);
        if(beginDate != null) options.put("begin_date", beginDate);
        if(order != null) options.put("sort", order.toLowerCase());
        if(getNewDesk() != null) options.put("fq", "News_desk:(" + getNewDesk() + ")");
        options.put("page", String.valueOf(page));
        return options;
    }

    private String getNewDesk() {
        if (!hasArts && !hasFashionAndStyle && !hasSports) return null;
        String value = "";
        if (hasArts) value += "\"Arts\" ";
        if (hasSports) value += "\"Sports\" ";
        if (hasFashionAndStyle) value += "\"Fashion & Style\"";
        return value.trim();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(page);
        parcel.writeString(query);
        parcel.writeString(beginDate);
        parcel.writeString(order);
        parcel.writeByte((byte) (hasArts ? 1 : 0));
        parcel.writeByte((byte) (hasFashionAndStyle ? 1 : 0));
        parcel.writeByte((byte) (hasSports ? 1 : 0));
    }
}
