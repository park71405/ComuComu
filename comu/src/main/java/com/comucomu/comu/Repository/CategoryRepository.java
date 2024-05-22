package com.comucomu.comu.Repository;

import com.comucomu.comu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE CATEGORY c " +
            "set c.category_name = :categoryName," +
                "c.path = :path," +
                "c.icon = :icon," +
                "c.component = :component" +
            "WHERE category_no = :no" , nativeQuery=true)
    void updateCategory(String categoryName, String path, String icon, String component, int no);


}
