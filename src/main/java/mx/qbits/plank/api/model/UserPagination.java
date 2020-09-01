package mx.qbits.plank.api.model;

import java.util.List;

public class UserPagination {
    private int total;
    private List<Usuario> userList;
    public UserPagination() {
    }
    public UserPagination(int total, List<Usuario> userList) {
        this.total = total;
        this.userList = userList;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public List<Usuario> getUserList() {
        return userList;
    }
    public void setUserList(List<Usuario> userList) {
        this.userList = userList;
    }
}
