package com.msg.repo;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.msg.utils.SystemMessage.Hint;

@NoRepositoryBean
public interface BaseRepo<T> extends CrudRepository<T, Long> {
	public Page<T> findAll(Pageable pageable);
	public @NotNull(message=Hint.ID_HAS_NOT_EXIST) T findById(@NotNull Long id);
}
