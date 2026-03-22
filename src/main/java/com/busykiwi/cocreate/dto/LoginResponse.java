package com.busykiwi.cocreate.dto;

import com.busykiwi.cocreate.model.LoginStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private LoginStatus status;
    private String message;
}
