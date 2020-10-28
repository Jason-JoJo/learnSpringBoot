package com.entity;

import lombok.Data;

@Data
public class ProductQueryParameter {
    private String keyword;
    private String orderBy;
    private String sortRule;
}
