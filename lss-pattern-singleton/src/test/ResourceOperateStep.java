package test;

/**
 * Created by liushaoshuai on 2018/3/13.
 */
@Deprecated
enum  ResourceOperateStep {
    GG("即将公告", "01"),
    GP("即将挂牌", "02"),
    GPONEHOUR("挂牌截止前1小时", "03"),
    XS("即将限时", "04"),
    OVER("即将结束", "99");
    private String title;
    private String code;

    ResourceOperateStep(String title, String code) {
        this.title = title;
        this.code = code;
    }

    public String getCode(String code) {
        if (code.equalsIgnoreCase(this.code)){
            return this.title;
        }else {
            return "";
        }

    }

    @Override
    public String toString() {
        return title;
    }

    public static void main(String[] args) {

        System.out.println(ResourceOperateStep.GG);
    }

}
