package com.mokhtar.redditclone.repository;

import com.mokhtar.redditclone.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
}
