package com.skydive.market.controller.request;

import com.skydive.market.model.enums.SportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
        private String email;
        private String password;
}
