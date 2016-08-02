package br.com.ingred.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Class that represents a Product
 * @author igor
 *
 */
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final String id;

	private final String name;
	
	private final String description;
	
	private final String category;
	
	private final String ingredients;

	private Product(final String id, final String name, final String description, final String category,
			final String ingredients) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.ingredients = ingredients;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCategory() {
		return category;
	}

	public String getIngredients() {
		return ingredients;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description="
				+ description + ", category=" + category + ", ingredients="
				+ ingredients + "]";
	}

	public static Builder builder() {

        return new Builder();
    }
	
	public static final class Builder {
		
		private String id;
		private String name;
		private String description;
		private String category;
		private String ingredients;
		
		private Builder() {
			
		}
		
		public Builder withId(final String id) {
			this.id = id;
			return this;
		}
		
		public Builder withName(final String name) {
			this.name = name;
			return this;
		}
		
		public Builder withDescription(final String description) {
			this.description = description;
			return this;
		}
		
		public Builder withCategory(final String category) {
			this.category = category;
			return this;
		}
		
		public Builder withIngredients(final String ingredients) {
			this.ingredients = ingredients;
			return this;
		}
		
		public Product build() {
			return new Product(id, name, description, category, ingredients);
		}
		
	}

}
