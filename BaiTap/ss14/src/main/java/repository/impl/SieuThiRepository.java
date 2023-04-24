package repository.impl;

import model.LoaiThucPham;
import model.SieuThi;
import repository.ISieuThiRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SieuThiRepository implements ISieuThiRepository {
    @Override
    public List<SieuThi> findAll() {
        Connection connection = BaseRepository.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<SieuThi> sieuThiList = new ArrayList<>();

        if (connection != null){
            try {
            statement = connection.prepareStatement("select * from thuc_pham" +
                    " join loai_thuc_pham ltp on ltp.id_ltp = thuc_pham.id_ltp" +
                    " order by thuc_pham.id;");

                resultSet = statement.executeQuery();
                while (resultSet.next()){
                    Integer id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String NSX = resultSet.getString("NSX");
                    String HSD = resultSet.getString("HSD");
                    Integer loaiThucPhamID = resultSet.getInt("ltp.id_ltp");
                    String loaiThucPhamName = resultSet.getString("name_ltp");
                    LoaiThucPham loaiThucPham = new LoaiThucPham(loaiThucPhamID,loaiThucPhamName);
                    String moTa = resultSet.getString("mo_ta");

                    SieuThi sieuThi = new SieuThi(id,name,NSX,HSD,loaiThucPham,moTa);
                    sieuThiList.add(sieuThi);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return sieuThiList;
    }

    @Override
    public void save(SieuThi sieuThi) {
        Connection connection = BaseRepository.getConnection();
        PreparedStatement statement = null;
        if (connection != null){
            try {
                statement = connection.prepareStatement("insert into thuc_pham values (?,?,?,?,?,?);");


                statement.setInt(1,sieuThi.getId());
                statement.setString(2,sieuThi.getName());
                statement.setString(3,sieuThi.getNSX());
                statement.setString(4,sieuThi.getHSD());
                statement.setInt(5,sieuThi.getLoaiThucPham().getId());
                statement.setString(6,sieuThi.getMoTa());
                statement.executeUpdate();



            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @Override
    public void delete(int id) {
        Connection connection = BaseRepository.getConnection();
        PreparedStatement statement = null;
        if (connection != null){
            try {
                statement = connection.prepareStatement("delete from thuc_pham where id=?");
                statement.setInt(1,id);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public List<SieuThi> findByName(String name) throws SQLException {
        Connection connection = BaseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<SieuThi> sieuThiList2 = new ArrayList<>();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement("select  * from thuc_pham join loai_thuc_pham ltp on ltp.id_ltp = thuc_pham.id_ltp where name like concat('%', ? ,'%');");
                preparedStatement.setString(1, name);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    name = resultSet.getString("name");
                    String NSX = resultSet.getString("NSX");
                    String HSD = resultSet.getString("HSD");
                    Integer loaiThucPhamID = resultSet.getInt("ltp.id_ltp");
                    String loaiThucPhamName = resultSet.getString("name_ltp");
                    LoaiThucPham loaiThucPham = new LoaiThucPham(loaiThucPhamID,loaiThucPhamName);
                    String moTa = resultSet.getString("mo_ta");
                    SieuThi sieuThi = new SieuThi(id,name,NSX,HSD,loaiThucPham,moTa);
                    sieuThiList2.add(sieuThi);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sieuThiList2;
    }
}
