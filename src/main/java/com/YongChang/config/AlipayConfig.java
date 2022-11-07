package com.YongChang.config;
import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000120617557";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPgIaefstS9jZmplWkJe2wbqsL61SxY+PfNWTbWWfwA/kOAXX6zGE5uQj5rrG61SqrLbHH+X415BaPbERfER9D1dUFztdqrGSDRQW1ODzY8AdM/euIWGNgdNqX1J2lW6eu6SiYe81qMymlU/3iykE+NdkJmzZkMVkINtKe5/lYJmSuy7VNCp9K7xS9F3K7jBUinbeeoGsHQM4HzP3iAq1C6snsFKovTr4kwQoBLgeRk9dDNeBWJ/MB/Ygpc8nihdMKYyIUFVOXtXEFR6mdw7jGEKJaF5XQTJHRBzW4YXbvfhcQHFS/iUuuCluw6YlJ1qmREysj9t4S8OMj74Na6mNHAgMBAAECggEARCeQyDoEK1TQpZQeXKW+6+rDxhdw1AdTNLHRQvSFjtL0OJitBf6HtriQ8LTtqISRjVdYdleif8nihR+woU4qgTdPw0Bnwzw5SLdBbL5W99mYPhLjCJCiMi+NmkrazKxpSYNnSnSSLPw7rU/k5w+USQ2eFRfoj1F4rZQMGHioThrFTYmynhnQv1/1PrGagoRGLkZlFAx/UP8Nm6Yrh3Hi7EhqIdjHP7bOHvE4qUFwumkSu4oaMo8b2kOzQOSN2mB3MQUvbr8yEQT1kxtFLBMXWg9ApJOFsjADU/gkrM5DWlbyZkOFy6x0cq5adeXYuVnCoxrFBSmCOHo/oLPOo6t2IQKBgQDjA0TftWYfxT5GsLk/hAD/rTL79dMqSD2epWyjmBbM1gjaBHavzsQOWQYa8tnJUpcY+U3rZOleZDD5u+viTijBlQvOBoSCE6xGm1V59L6v4QTCNgvKX8jMRExsuBtRZ0D69BQEKZtskbVsBooQ9G4hCQV0Yfo2HM62lH2FuN+rKQKBgQCh02hvT/xpmh1l2m/8/8pPIBLIu5+LrT6DPKTBaZMYz+E1vwd9uHI6XdaJ+KXp9hG9WJaiPxaOWrj8VkXv2IsMJkQxsObbnSU5JuAbK3oG1D8omeuRfbnT9Z1RuDfu4fvEaJJS3L3BpAPlz3JyiJxl4dascdNGWgJ1gUl/gRfY7wKBgEKyeC7eBgY5lmWAkLzXlM+J3JvoisU2elw9MCEUk0ZVcD5V52UZ88JfZ4rNZS1gRaKBxOHvQR2JhFYrX0+bNSBDITfD9HSfQRNxmfh97vSPwS0qYeZwo/dX7bhXkzckhhP1WFgsvUx1tv7pIPGAT0cHdoHOM4xohFdiY0rBmX2ZAoGAb+zYfqTD4AZH7vp2GnaQZsm4ND4Qy3q3ke2PxnZO1wpwqcvGUp4P426jxCnGF1uqSVvMU8tuvmbhKyZAmPMcuPp+kB/ajGrUJANPXXtkmvSR+nL7C1X6ATAP70WK6h5DFEV/bvBeDmBykbA5aB40jgL1h6/ygOYgbUVOoHGZHOECgYEApVIU7ixheiorGHI+kdRQ363XwldECGvKR1X0VPOuRYHrBcH2SumVXYJE6OGNQIq90L7X8D/UohEGfa34N6Js5yuUiUDtNOdYmsg/c6VjAKyqcwwfA29WOQyLUYo8JBY8RV48hobe02jmAuforOx6jgtEt7vYhgTYf96+VZ4wrSc=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj4CGnn7LUvY2ZqZVpCXtsG6rC+tUsWPj3zVk21ln8AP5DgF1+sxhObkI+a6xutUqqy2xx/l+NeQWj2xEXxEfQ9XVBc7Xaqxkg0UFtTg82PAHTP3riFhjYHTal9SdpVunrukomHvNajMppVP94spBPjXZCZs2ZDFZCDbSnuf5WCZkrsu1TQqfSu8UvRdyu4wVIp23nqBrB0DOB8z94gKtQurJ7BSqL06+JMEKAS4HkZPXQzXgVifzAf2IKXPJ4oXTCmMiFBVTl7VxBUepncO4xhCiWheV0EyR0Qc1uGF2734XEBxUv4lLrgpbsOmJSdapkRMrI/beEvDjI++DWupjRwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/test/return.do";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8080/test/return.do";

    // 签名方式
    public static String SIGN_TYPE = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String GATEWAYURL = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
