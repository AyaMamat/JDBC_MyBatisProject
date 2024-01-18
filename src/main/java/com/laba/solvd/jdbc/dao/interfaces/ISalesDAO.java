package com.laba.solvd.jdbc.dao.interfaces;

import com.laba.solvd.jdbc.model.Sale;

import java.util.Date;
import java.util.List;

public interface ISalesDAO extends IBaseDAO<Sale> {

    List<Sale> getSalesByDate(Date date);
}
