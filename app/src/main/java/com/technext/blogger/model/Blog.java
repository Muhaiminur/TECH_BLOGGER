package com.technext.blogger.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "blog_table")
public class Blog {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "blogid")
    @SerializedName("id")
    @Expose
    private Integer id;
    @NonNull
    @ColumnInfo(name = "blogtittle")
    @SerializedName("title")
    @Expose
    private String title;
    @NonNull
    @ColumnInfo(name = "blogdescription")
    @SerializedName("description")
    @Expose
    private String description;
    @NonNull
    @ColumnInfo(name = "blogphoto")
    @SerializedName("cover_photo")
    @Expose
    private String coverPhoto;
    @NonNull
    @ColumnInfo(name = "blogcategories")
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @NonNull
    /*@ColumnInfo(name = "blogauthor")*/
    @SerializedName("author")
    @Expose
    @Embedded
    private Author author;

    /*public Blog() {
    }*/

    public Blog(@NonNull Integer id, @NonNull String title, @NonNull String description, @NonNull String coverPhoto, @NonNull List<String> categories, @NonNull Author author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.coverPhoto = coverPhoto;
        this.categories = categories;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", coverPhoto='" + coverPhoto + '\'' +
                ", categories=" + categories +
                ", author=" + author +
                '}';
    }
}