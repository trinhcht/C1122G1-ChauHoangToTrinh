package service;

import model.SieuThi;

import java.sql.SQLException;
import java.util.List;

public interface ISieuThiService {

    List<SieuThi> findAll();

    void save(SieuThi sieuThi);

    void delete(int id);

    List<SieuThi> findByName(String name) throws SQLException;
}
