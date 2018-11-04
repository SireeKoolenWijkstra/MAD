package com.koolenwijkstra.siree.studentportal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class URLView {

        private String url;
        private String title;

        public URLView(String url, String title) {
            this.title = title;
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    @Override
    public String toString() {

            return title + " " + url;
    }
}
