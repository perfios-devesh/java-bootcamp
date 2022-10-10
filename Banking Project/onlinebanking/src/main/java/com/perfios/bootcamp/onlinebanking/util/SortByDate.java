package com.perfios.bootcamp.onlinebanking.util;

import com.perfios.bootcamp.onlinebanking.dto.TransactionResponseDTO;

import java.util.Comparator;

public class SortByDate implements Comparator<TransactionResponseDTO> {
    @Override
    public int compare(TransactionResponseDTO o1, TransactionResponseDTO o2) {
        return o2.getDate().compareTo(o1.getDate());
    }
}
