package com.example.daoInterface;

import java.util.List;

public interface Dao<T> {
	
	public T get(long id);
	
	public List<T> getAll();
	
	public void add(T t);	

	public int update(T t);

	public int delete(long t);

}
