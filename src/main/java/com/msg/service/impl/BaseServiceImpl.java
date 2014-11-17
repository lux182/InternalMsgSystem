package com.msg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.msg.repo.BaseRepo;
import com.msg.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	BaseRepo<T> baseRepo;

	public T save(T t) {
		return baseRepo.save(t);
	}

    @Override
	public T update(T t) {
		return baseRepo.save(t);
	}

	@Override
	public T getById(Long id) {
		return baseRepo.findById(id);
	}

	@Override
	public boolean delete(T t) {
		baseRepo.delete(t);
		return true;
	}

	@Override
	public boolean deleteById(Long id) {
		baseRepo.delete(id);
		return true;
	}

	@Override
	public Page<T> getAll(int page, int pageSize) {
		return getAll(new PageRequest(page,pageSize));
	}

	@Override
	public Page<T> getAll(int page, int pageSize, Order order) {
		return getAll(new PageRequest(page,pageSize,new Sort(new Order[] {order})));
	}

	@Override
	public Page<T> getAll(int page, int pageSize, Order[] orders) {
		return getAll(new PageRequest(page,pageSize,new Sort(orders)));
	}

	@Override
	public Page<T> getAll(Pageable p) {
		return baseRepo.findAll(p);
	}

	@Override
	public Iterable<T> getAll() {
		return baseRepo.findAll();
	}

	@Override
	public Page<T> getAll(int page, int pageSize, Sort sort) {
		return getAll(new PageRequest(page,pageSize,sort));
	}
	
	@Override
	public boolean exists(Long id){
		return baseRepo.exists(id);
	}
}
