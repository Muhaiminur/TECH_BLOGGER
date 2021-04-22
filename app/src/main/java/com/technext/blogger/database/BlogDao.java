package com.technext.blogger.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.technext.blogger.model.Blog;

import java.util.List;

@Dao
public interface BlogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertblog(Blog blog);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertallblog(List<Blog> blog);

    @Update
    void updateblog(Blog blog);

    /*@Query("UPDATE blog_table SET blogtittle=:b.  WHERE blogid = :old_cart")
    void updateblog(String old_id, Blog b);*/

    @Delete
    void deleteblog(Blog blog);

    @Query("DELETE FROM blog_table WHERE blogid = :b_id")
    void deleteblog_single(String b_id);

    @Query("DELETE FROM blog_table")
    void deleteAllblog();

    @Query("SELECT * from blog_table ORDER BY blogid ASC")
    LiveData<List<Blog>> getAllblog();

    @Query("SELECT * FROM blog_table WHERE blogid =:c")
    Blog findblog(String c);

    /*@Query("UPDATE blog_table SET blogid=:new_cart WHERE blogid = :old_cart")
    void updatecartdata(String old_cart, String new_cart);*/

    @Query("SELECT * FROM blog_table")
    Blog blog_profile();
}