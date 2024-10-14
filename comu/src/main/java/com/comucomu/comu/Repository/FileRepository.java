package com.comucomu.comu.Repository;

import com.comucomu.comu.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
}
