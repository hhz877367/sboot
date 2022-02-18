package com.hhz.model.datastruture;

import lombok.Data;

@Data
public class Lianxi {
    private String name;

    public static void main(String[] args) {
        Lianxi lianxi = new Lianxi();
        System.out.println(lianxi.getName());
    }
}
