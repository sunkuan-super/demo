/**
 * @Title: A
 * @Package: PACKAGE_NAME
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/8/5 - 12:32
 */
public class A extends AA{
    private static int result = 0;

    public A(){
        System.out.println("A()");
    }

    {
        System.out.println("{"+ result + "}");
        result++;
    }

}
