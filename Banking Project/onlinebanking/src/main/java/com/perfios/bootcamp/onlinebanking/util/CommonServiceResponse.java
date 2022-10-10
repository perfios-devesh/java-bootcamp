package com.perfios.bootcamp.onlinebanking.util;

import lombok.Getter;
import lombok.Setter;

public class CommonServiceResponse {

    @Getter @Setter
    private String message;
    @Getter @Setter
    private String errorMessage;
    @Getter @Setter
    private Boolean apiStatusSuccessful;

}
