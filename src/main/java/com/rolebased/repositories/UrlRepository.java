package com.rolebased.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rolebased.entities.UrlEntity;


@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

  // @Query("SELECT u FROM _urlinfo u WHERE u.fullUrl = ?1")
    List<UrlEntity> findUrlByFullUrl(String fullUrl);
}
