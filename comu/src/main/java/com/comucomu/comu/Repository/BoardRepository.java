package com.comucomu.comu.Repository;

import com.comucomu.comu.DTO.Board.BoardResponse;
import com.comucomu.comu.entity.Board;
import com.comucomu.comu.entity.Category;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Integer> {

}
