package com.on.spring.entity.board;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {
    public List<Board> findAllByCompanyId(Long companyId);
}
