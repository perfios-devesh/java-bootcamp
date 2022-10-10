package com.perfios.bootcamp.onlinebanking.dto;

import com.perfios.bootcamp.onlinebanking.util.CommonServiceResponse;
import lombok.Data;

@Data
public class FileResponseDTO extends CommonServiceResponse {
    private String name;
    private String url;
    private String type;
    private long size;
}
