package com.mokhtar.redditclone.repository;

import com.mokhtar.redditclone.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {


}
