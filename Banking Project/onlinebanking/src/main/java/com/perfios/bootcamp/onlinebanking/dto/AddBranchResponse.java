package com.perfios.bootcamp.onlinebanking.dto;

import com.perfios.bootcamp.onlinebanking.util.CommonServiceResponse;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class AddBranchResponse extends CommonServiceResponse {
    private Long branchId;
}
