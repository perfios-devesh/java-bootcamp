package com.perfios.bootcamp.onlinebanking.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
@EqualsAndHashCode
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;          //Branch Code
    private String bankName;
    private String branchName;
    private String micr;
    private String ifsc;
    private String address;
    private String district;
    private String state;
    private Integer pinCode;

    @OneToMany(mappedBy = "branch")
    private List<Account> accountList;
}
