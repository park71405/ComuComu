package com.comucomu.comu.Repository;

import com.comucomu.comu.entity.Board;
import com.comucomu.comu.entity.QBoard;
import com.comucomu.comu.entity.QCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Board> findByCategoryNo(int category_no) {

        QBoard qBoard = QBoard.board;
        QCategory qCategory = QCategory.category;

        return queryFactory.selectFrom(qBoard)
                .where(qBoard.category.no.eq(category_no))
                .fetch();
    }
}
