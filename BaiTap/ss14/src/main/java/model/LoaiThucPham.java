package model;

public class LoaiThucPham {
    private Integer id;
    private String name;

    public LoaiThucPham(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public LoaiThucPham(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
