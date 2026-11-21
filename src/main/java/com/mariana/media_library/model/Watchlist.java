package com.mariana.media_library.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "watchlist", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "title_id" }))
public class Watchlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false)
	private String userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "title_id", nullable = false)
	private Title title;

	@Column(nullable = false)
	private LocalDateTime addedAt;

	//constructors
	protected Watchlist() {
	}

	public Watchlist(String userId, Title title) {
		this.userId = userId;
		this.title = title;
		this.addedAt = LocalDateTime.now();
	}

	//gets n sets
	public Long getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public Title getTitle() {
		return title;
	}

	public LocalDateTime getAddedAt() {
		return addedAt;
	}
}