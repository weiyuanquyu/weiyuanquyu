package yuan.web.base;

import org.nutz.dao.entity.annotation.Id;
import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity implements Serializable {

    @Id
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long orgid;
    private Date createtime;
    private Long createperson;
    private Date updatetime;
    private Long updateperson;
    private Date rowstamp;

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getCreateperson() {
        return createperson;
    }

    public void setCreateperson(Long createperson) {
        this.createperson = createperson;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getUpdateperson() {
        return updateperson;
    }

    public void setUpdateperson(Long updateperson) {
        this.updateperson = updateperson;
    }

    public Date getRowstamp() {
        return rowstamp;
    }

    public void setRowstamp(Date rowstamp) {
        this.rowstamp = rowstamp;
    }


}
