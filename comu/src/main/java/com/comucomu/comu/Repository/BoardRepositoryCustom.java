package com.comucomu.comu.Repository;

import com.comucomu.comu.entity.Board;
import com.comucomu.comu.entity.QBoard;
import com.comucomu.comu.entity.QCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public List<Board> findByCategoryNo(int category_no) {

        QBoard qBoard = QBoard.board;
        QCategory qCategory = QCategory.category;

        return queryFactory.selectFrom(qBoard)
                .where(qBoard.category.no.eq(category_no))
                .fetch();
    }

}
