package com.laba.solvd.jdbc.service.interfaces;

import com.laba.solvd.jdbc.model.Sale;

import java.util.List;

public interface ISalesService {
    Sale create(Sale sales);
    List<Sale> getAll();
}
