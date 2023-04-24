package service.impl;

import model.SieuThi;
import repository.ISieuThiRepository;
import repository.impl.SieuThiRepository;
import service.ISieuThiService;

import java.sql.SQLException;
import java.util.List;

public class SieuThiService implements ISieuThiService {
    ISieuThiRepository sieuThiRepository = new SieuThiRepository();
    @Override
    public List<SieuThi> findAll() {
        return sieuThiRepository.findAll();
    }

    @Override
    public void save(SieuThi sieuThi) {
        sieuThiRepository.save(sieuThi);
    }

    @Override
    public void delete(int id) {
        sieuThiRepository.delete(id);
    }

    @Override
    public List<SieuThi> findByName(String name) throws SQLException {
        return sieuThiRepository.findByName(name);
    }
}
