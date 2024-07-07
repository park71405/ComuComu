package com.comucomu.comu.Repository;

import com.comucomu.comu.entity.Board;
import com.comucomu.comu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    public List<Board> findByCategory_No(int category_no);


}
