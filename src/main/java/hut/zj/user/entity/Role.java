package hut.zj.user.entity;

import hut.zj.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = BaseEntity.TABLE_PREFIX + "ROLE")
@Entity
public class Role extends BaseEntity{

    //角色名称//
    @Column(name = "name",length = 50,nullable = false,unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
