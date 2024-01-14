package com.skydive.market.dto;

import com.skydive.market.model.enums.SportType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LoginModelDTO {
    private String email;
    private String password;
}
