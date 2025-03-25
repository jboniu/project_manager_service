package com.javanoteany.project.dao;

import com.javanoteany.project.entity.Project;
import com.javanoteany.common.base.dao.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends BaseRepository<Project>{
    @Query("select p from Project p where p.name = :name")
    Project findByName(@Param("name") String name);
}
