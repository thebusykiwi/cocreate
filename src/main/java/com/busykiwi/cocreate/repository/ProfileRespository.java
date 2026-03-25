package com.busykiwi.cocreate.repository;

import com.busykiwi.cocreate.model.Project;
import com.busykiwi.cocreate.model.Skill;
import com.busykiwi.cocreate.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRespository extends JpaRepository<UserProfile, Integer> {

    @Query("SELECT up FROM UserProfile up where up.user.id = :user_id")
    UserProfile getUserProfileByUserId(@Param("user_id") int userId);
}
