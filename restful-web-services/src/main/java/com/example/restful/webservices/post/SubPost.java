package com.example.restful.webservices.post;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="posttable")
public class SubPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String description;
	private String content;
	private Date postedAt= new Date();
	private Date lastUpdatedAt= new Date();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="post_tag", 
			  joinColumns = {@JoinColumn(name= "subpost_id")},
			  inverseJoinColumns = {@JoinColumn( name="tag_id")})
	@JsonIgnore
	private Set<Tag> tags=new HashSet<>();
	
	public SubPost() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SubPost(String title, String description, String content) {
		super();
		this.title = title;
		this.description = description;
		this.content = content;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostedAt() {
		return postedAt;
	}
	public void setPostedAt(Date postedAt) {
		this.postedAt = postedAt;
	}
	public Date getLastUpdatedAt() {
		return lastUpdatedAt;
	}
	public void setLastUpdatedAt(Date lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "SubPost [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content
				+ ", postedAt=" + postedAt + ", lastUpdatedAt=" + lastUpdatedAt + "]";
	}
	
}
