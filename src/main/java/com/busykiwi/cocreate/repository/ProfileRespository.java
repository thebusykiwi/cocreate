package com.busykiwi.cocreate.repository;

import com.busykiwi.cocreate.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRespository extends JpaRepository<UserProfile, Integer> {

    @Query("SELECT up FROM user_profiles up where up.user.id = :user_id")
    UserProfile getUserProfileByUserId(@Param("user_id") int userId);
}
