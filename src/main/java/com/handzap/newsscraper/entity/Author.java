/**
 * 
 */
package com.handzap.newsscraper.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.handzap.newsscraper.util.IdGeneratorUtil;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@Entity
@Table(name = "author")
public class Author extends BaseEntity {

	@Id
	@GenericGenerator(name = IdGeneratorUtil.ID_GENERATOR, strategy = "com.handzap.newsscraper.util.IdGeneratorUtil")
	@GeneratedValue(generator = IdGeneratorUtil.ID_GENERATOR)
	@Column(name = "author_id")
	private String id;

	@Column(name = "author_name")
	private String authorName;

	@OneToMany(mappedBy = "author")
	private Set<Article> articles;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName
	 *            the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * @return the articles
	 */
	public Set<Article> getArticles() {
		return articles;
	}

	/**
	 * @param articles
	 *            the articles to set
	 */
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	/**
	 * @param createdBy
	 * @param createdDateTime
	 * @param id
	 * @param authorName
	 */
	public Author(String id, String authorName) {
		super();
		this.id = id;
		this.authorName = authorName;
	}

	/**
	 * @param createdBy
	 * @param createdDateTime
	 * @param authorName
	 */
	public Author(String authorName) {
		super();
		this.authorName = authorName;
	}

	/**
	 * 
	 */
	public Author() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Author)) {
			return false;
		}
		Author other = (Author) obj;
		if (authorName == null) {
			if (other.authorName != null) {
				return false;
			}
		} else if (!authorName.equals(other.authorName)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Author [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (authorName != null) {
			builder.append("authorName=");
			builder.append(authorName);
		}
		builder.append("]");
		return builder.toString();
	}

}
