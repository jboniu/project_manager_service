package hut.zj.dict.entity;

import hut.zj.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = BaseEntity.TABLE_PREFIX + "DATA_DICTIONARY")
public class DataDictDetail extends BaseEntity {

    @Column(name = "name",length = 50,nullable = false)
    private String name;

    @Column(name = "code",length = 50,nullable = false)
    private String code;

    //字典类型
    private String typeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

}
