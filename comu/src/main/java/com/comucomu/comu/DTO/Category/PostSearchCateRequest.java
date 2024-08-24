package com.comucomu.comu.DTO.Category;

import com.comucomu.comu.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostSearchCateRequest {

    private String role;

    public Role toEntity(){
        return Role.builder()
                .roleName(role)
                .build();
    }

}
