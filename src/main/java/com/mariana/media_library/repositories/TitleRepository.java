package com.mariana.media_library.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mariana.media_library.model.Title;

public interface TitleRepository extends JpaRepository<Title, Long> {

	public List<Title> findByTitleContainingIgnoreCase(String title);
}
