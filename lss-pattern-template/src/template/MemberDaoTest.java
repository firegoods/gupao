package template;

import template.dao.MemberDao;

/**
 * Created by liushaoshuai on 2018/3/19.
 */
public class MemberDaoTest {
    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao();
        memberDao.query();
    }
}
