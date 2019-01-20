package control;

import com.sobte.cqp.jcq.event.JcqAppAbstract;

public class Jcqapp extends JcqAppAbstract{

    public int startup() {
        return 0;
    }

    public int exit() {
        return 0;
    }

    public int enable() {
        return 0;
    }

    public int disable() {
        return 0;
    }

    public int privateMsg(int i, int i1, long l, String s, int i2) {
        return 0;
    }

    public int groupMsg(int i, int i1, long l, long l1, String s, String s1, int i2) {
        return 0;
    }

    public int discussMsg(int i, int i1, long l, long l1, String s, int i2) {
        return 0;
    }

    public int groupUpload(int i, int i1, long l, long l1, String s) {
        return 0;
    }

    public int groupAdmin(int i, int i1, long l, long l1) {
        return 0;
    }

    public int groupMemberDecrease(int i, int i1, long l, long l1, long l2) {
        return 0;
    }

    public int groupMemberIncrease(int i, int i1, long l, long l1, long l2) {
        return 0;
    }

    public int friendAdd(int i, int i1, long l) {
        return 0;
    }

    public int requestAddFriend(int i, int i1, long l, String s, String s1) {
        return 0;
    }

    public int requestAddGroup(int i, int i1, long l, long l1, String s, String s1) {
        return 0;
    }

    public String appInfo() {
        return null;
    }
}
