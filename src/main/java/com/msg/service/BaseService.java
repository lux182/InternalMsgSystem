package com.msg.service;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.msg.utils.SystemMessage.Hint;

public interface BaseService<T> {
	public  @NotNull(message=Hint.UNEXPECTED_EXCEPTION) T save(@NotNull T t);

	public  @NotNull(message=Hint.UNEXPECTED_EXCEPTION) T update(@NotNull T t);

	public @NotNull(message = Hint.ID_HAS_NOT_EXIST)T getById(@NotNull Long id);

	public boolean delete(@NotNull T t);

	public boolean deleteById(@NotNull Long id);

	public @NotNull Page<T> getAll(@NotNull int page, @NotNull int pageSize);

	public @NotNull Page<T> getAll(@NotNull int page, @NotNull int pageSize,	@NotNull Order order);

	public @NotNull Page<T> getAll(@NotNull int page, @NotNull int pageSize,	@NotNull Order[] orders);

	public @NotNull Page<T> getAll(@NotNull int page, @NotNull int pageSize,	@NotNull Sort sort);

	public @NotNull Page<T> getAll(@NotNull Pageable p);

	public @NotNull Iterable<T> getAll();
	
	public boolean exists(@NotNull Long id);
}
