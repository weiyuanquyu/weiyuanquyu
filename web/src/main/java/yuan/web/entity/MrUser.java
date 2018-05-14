package yuan.web.entity;


import org.nutz.dao.entity.annotation.Table;
import yuan.web.base.BaseEntity;

@Table("mr_organize")
public class MrUser extends BaseEntity {

    private static final long serialVersionUID = 4192487473315920471L;

    private String orgname;

    private long syntype;

    private String remoteurl;

    private int dbtype;

    private String username;

    private String password;

    private String dburl;

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public long getSyntype() {
        return syntype;
    }

    public void setSyntype(long syntype) {
        this.syntype = syntype;
    }

    public String getRemoteurl() {
        return remoteurl;
    }

    public void setRemoteurl(String remoteurl) {
        this.remoteurl = remoteurl;
    }

    public int getDbtype() {
        return dbtype;
    }

    public void setDbtype(int dbtype) {
        this.dbtype = dbtype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDburl() {
        return dburl;
    }

    public void setDburl(String dburl) {
        this.dburl = dburl;
    }

}
