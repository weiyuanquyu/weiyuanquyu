package yuan.web.session;


import yuan.web.entity.MrOrganize;
import yuan.web.entity.MrUser;

public class SessionUser {

    private MrUser user;
    private MrOrganize mrOrganize;

    public MrUser getUser() {
        return user;
    }

    public void setUser(MrUser user) {
        this.user = user;
    }

    public MrOrganize getMrOrganize() {
        return mrOrganize;
    }

    public void setMrOrganize(MrOrganize mrOrganize) {
        this.mrOrganize = mrOrganize;
    }
}
