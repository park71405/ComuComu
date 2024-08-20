package com.comucomu.comu.Repository;

import com.comucomu.comu.DTO.Board.BoardResponse;
import com.comucomu.comu.entity.Board;
import com.comucomu.comu.entity.QBoard;
import com.comucomu.comu.entity.QCategory;
import com.comucomu.comu.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public List<BoardResponse> findByCategoryNo(int category_no, Pageable pageable) {

        QBoard board = QBoard.board;
        QUser user = QUser.user;

        return queryFactory
                .selectFrom(board)
                .leftJoin(board.user, user).fetchJoin()
                .where(board.category.no.eq(category_no))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream()
                .map(BoardResponse::new)
                .collect(Collectors.toList());
    }

}
