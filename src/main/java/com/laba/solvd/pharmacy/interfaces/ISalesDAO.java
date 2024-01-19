package com.laba.solvd.pharmacy.interfaces;

import java.time.LocalDate;
import java.util.List;

public interface ISalesDAO<Sale> extends IBaseDAO<Sale> {

    List<Sale> getSalesByDate(LocalDate date);
}
