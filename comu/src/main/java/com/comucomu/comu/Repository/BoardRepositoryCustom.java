package com.comucomu.comu.Repository;

import com.comucomu.comu.entity.Board;

import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> findByCategoryNo(int category_no);

}
