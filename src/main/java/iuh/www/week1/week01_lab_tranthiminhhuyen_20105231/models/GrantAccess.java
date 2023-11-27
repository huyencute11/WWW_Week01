package iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.models;

public class GrantAccess {
    private Role role;
    private Account account;
    private Grant isGrant;
    private String note;

    public GrantAccess(Role role, Account account, Grant isGrant, String note) {
        this.role = role;
        this.account = account;
        this.isGrant = isGrant;
        this.note = note;
    }

    public GrantAccess() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Grant getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(Grant isGrant) {
        this.isGrant = isGrant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "role=" + role +
                ", account=" + account +
                ", isGrant=" + isGrant +
                ", note='" + note + '\'' +
                '}';
    }
}
