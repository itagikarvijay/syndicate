package com.syndicate.master.all.categories;

import java.util.List;

public interface ICategoryService<T> {

	List<T> findAll(String name, Class<?> T);
	


}
