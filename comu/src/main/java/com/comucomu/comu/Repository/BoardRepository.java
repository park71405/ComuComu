package com.comucomu.comu.Repository;

import com.comucomu.comu.entity.Board;
import com.comucomu.comu.entity.Category;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
