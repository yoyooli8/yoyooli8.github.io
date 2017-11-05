package com.am.test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.json.JSONWriter;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
/**
 * @Description: com.am.test
 * @date 2017/11/5.
 * Created by 石头 on 2017/11/5.
 */
public class TPJavaClient {
    /**
     * 生产环境，切记不要乱调
     **/
    private String TP_PRIVATEKEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMTRW1p7F/AFTtXMwxQsYzrXWhsM7yoL9C0qkQw7sSlTXjZojx6TaPVVM3eHF/QsrCk3rqCkU2scYftP5zYINrrm28ten6xVuiSaB5MQRE2HnX+muTj6RXnlA+NTKaDhYGMSs+PWmPl0IycaKaL9L21El80AcqSwO7U8OW8OZWH7AgMBAAECgYApSAgzODJTI6nfai1Yo97ETrsVhWb8mLlEZ/M4b6S/GD9ghhnOvfh7zL0YyvsZOe16T7WO+CiPBMsAXUgPqzVfK/r05YZpMJ/g6N4+FExeCMV/eV8G6A+rRctVj53rwEelDByjnUHEW4D9B+pOWS/IALkORt4a6XqKekjSwLXEEQJBAPDo6AKXsneABLzDYjBx5HtRZCDHIo8N81PYicguLfd92HN+/CzobCNnZWwchioshM3nwchIUWJVN/ouPvqcZc0CQQDRJWqOz95St2U98ErmaAaxuIePqGhOHbnKvgEGnYXxdRWiCxTYe9ExfsXfOtjlEub233DeUWbd6IBfEoGVNZ7nAkA5v7sOFAsl9jwse7Gn50VcAlC8QsAYBsITkU7F+7vtHe+rD/+nTDfP7NM1LUlEBtP2/91GA3/u0HrQsBFMxfzpAkEAtjmAf0/T6IQL/Qx5ZgjrFDuwOVXFE5LIx0IHSMlE491c7OP01K4E1bgcFWSDiAuRGzYRdFK+ashh2PQZ3XRrrwJBAIE6dRuuhlocvTAxUmLy1B7mnh4NwGhotYXDsmu6kwngzs6PoiNsWHr525QcJUEj65bXEi8pG3s/xlrum+D0Me4=";
    private String TP_OPENAPI_URL = "https://openapi.alipay.com/gateway.do";
    private String TP_APPID = "2017011004975863";

    public static void main(String[] args) throws AlipayApiException{
        TPJavaClient client = new TPJavaClient();
//        client.testRenthouseLeaseOrderSync();
             client.testState();
        //      client.testRenthouseLeaseOrderSync();
//       client.testRenthouseBillOrder();
//        client.testRenthouseLeaseOrderSync();
//        client.testRenthouseCommonImageUpload();
//        client.testRenthouseKaBaseinfoQuery();
//        client.testRenthouseLeaseOrderSync();
//        Map map = new HashMap<String ,String>();
//        map.put("a","1");
//        map.put("b","1");
//        System.out.println(map.size());
    }

    /**
     * 分散式房源同步
     *
     * @throws AlipayApiException
     */
    public void testDispersion() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(TP_OPENAPI_URL, TP_APPID, TP_PRIVATEKEY, "json", "UTF-8");
        AlipayEcoRenthouseRoomDispersionSyncRequest request = new AlipayEcoRenthouseRoomDispersionSyncRequest();
        request.setBizContent("}");
        AlipayEcoRenthouseRoomDispersionSyncResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }


    /**
     * 集中式房源同步
     * @throws AlipayApiException
     */
    public void testConcentration() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(TP_OPENAPI_URL, TP_APPID, TP_PRIVATEKEY, "json", "UTF-8");
        AlipayEcoRenthouseRoomConcentrationSyncRequest request = new AlipayEcoRenthouseRoomConcentrationSyncRequest();
        request.setBizContent("");
        AlipayEcoRenthouseRoomConcentrationSyncResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    /**
     * 上下架房源
     * @throws AlipayApiException
     */
    public void testState() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(TP_OPENAPI_URL, TP_APPID, TP_PRIVATEKEY, "json", "UTF-8");
        AlipayEcoRenthouseRoomStateSyncRequest request = new AlipayEcoRenthouseRoomStateSyncRequest();
        request.setBizContent("{" +
                "    \"flats_tag\": 2," +
                "    \"rent_status\": 1," +
                "    \"room_code\": \"173260\"," +
                "    \"room_status\": 0" +
                "}");
        AlipayEcoRenthouseRoomStateSyncResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    /**
     * 租约同步
     */
    public void testRenthouseLeaseOrderSync() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(TP_OPENAPI_URL, TP_APPID, TP_PRIVATEKEY, "json", "UTF-8");
        AlipayEcoRenthouseLeaseOrderSyncRequest request = new AlipayEcoRenthouseLeaseOrderSyncRequest();
        request.setBizContent("{\n" +
                "    \"bedroom_count\":2,\n" +
                "    \"community_code\":\"C0000185492\",\n" +
                "    \"flat_area\":98,\n" +
                "    \"flat_building\":\"12\",\n" +
                "    \"flat_unit\":\"32\",\n" +
                "    \"floor_count\":8,\n" +
                "    \"foregift_amount\":9876,\n" +
                "    \"free_begin_date\":20170101,\n" +
                "    \"free_end_date\":20170331,\n" +
                "    \"fresh_room_status\":0,\n" +
                "    \"images\":[\n" +
                "        \"http://ecopublic-dev.oss-cn-hangzhou.aliyuncs.com/eco/tpmogo/1/2017-10-23/1/1f7b9aee176241358b772111046daf3d/1_1508742222117.jpg\",\n" +
                "        \"http://ecopublic-dev.oss-cn-hangzhou.aliyuncs.com/eco/tpmogo/1/2017-10-23/1/2eaca53708c7492991fed5a64d453d62/1_1508742220841.jpg\",\n" +
                "        \"http://ecopublic-dev.oss-cn-hangzhou.aliyuncs.com/eco/tpmogo/1/2017-10-23/1/ec53a92c1ca247e9b7860d43fa1a238b/1_1508742221629.jpg\"\n" +
                "    ],\n" +
                "    \"intro\":\"21321321阿斯顿请问\",\n" +
                "    \"mogo_score\":881,\n" +
                "    \"other_amount\":[\n" +
                "\n" +
                "    ],\n" +
                "    \"owners_name\":\"蘑qoqBXE\",\n" +
                "    \"owners_tel\":\"4000606868-92126\",\n" +
                "    \"parlor_count\":1,\n" +
                "    \"pay_type\":3,\n" +
                "    \"rent_status\":1,\n" +
                "    \"rent_type\":1,\n" +
                "    \"room_amount\":9876,\n" +
                "    \"room_area\":98,\n" +
                "    \"room_code\":\"2144367\",\n" +
                "    \"room_configs\":[\n" +
                "        \"15\",\n" +
                "        \"6\",\n" +
                "        \"2\",\n" +
                "        \"3\",\n" +
                "        \"7\",\n" +
                "        \"11\",\n" +
                "        \"10\",\n" +
                "        \"14\"\n" +
                "    ],\n" +
                "    \"room_num\":\"502\",\n" +
                "    \"room_status\":1,\n" +
                "    \"toilet_count\":1,\n" +
                "    \"total_floor_count\":20\n" +
                "}");
        System.out.println((new JSONWriter()).write(request.getBizModel(), true));
        AlipayEcoRenthouseLeaseOrderSyncResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }


    /**
     * 租约状态同步
     */
    public void testRenthouseLeaseState() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(TP_OPENAPI_URL, TP_APPID, TP_PRIVATEKEY, "json", "UTF-8");
        AlipayEcoRenthouseLeaseStateSyncRequest request = new AlipayEcoRenthouseLeaseStateSyncRequest();
        request.setBizContent("{" +
                "    \"lease_code\": \"LC22270001\"," +
                "    \"lease_status\": 1" +
                "}");
        AlipayEcoRenthouseLeaseStateSyncResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    /**
     * 账单同步
     */
    public void testRenthouseBillOrder() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(TP_OPENAPI_URL, TP_APPID, TP_PRIVATEKEY, "json", "UTF-8");
        AlipayEcoRenthouseBillOrderSyncRequest request = new AlipayEcoRenthouseBillOrderSyncRequest();
        request.setBizContent("{" +
                "    \"bill_number\": \"1\"," +
                "    \"bills\": [" +
                "        {" +
                "            \"bill_amount\": \"1222.32\"," +
                "            \"bill_create_time\": \"2017-07-31 16:58:51\"," +
                "            \"bill_desc\": \"描述\"," +
                "            \"bill_no\": \"bn00007\"," +
                "            \"bill_status\": 0," +
                "            \"bill_type\": \"10001\"," +
                "            \"deadline_date\": \"2017-08-04\"," +
                "            \"discount_amount\": \"12.56\"," +
                "            \"end_date\": \"2017-07-10\"," +
                "            \"lease_no\": \"16698100\"," +
                "            \"memo\": \"测试\"," +
                "            \"paid_amount\": \"12.32\"," +
                "            \"pay_lock\": 1," +
                "            \"pay_lock_memo\": \"支付房租\"," +
                "            \"pay_status\": 0," +
                "            \"min_pay_amount\": \"123.32\"," +
                "            \"deduction_amount\": \"232.32\"," +
                //"            \"pay_time\": \"2017-07-31 16:58:51\"," +
                "            \"start_date\": \"2017-01-02\"" +
                "        }" +
                "    ]," +
                "    \"trade_id\": \"A201705020000000013\"" +
                "}");
        AlipayEcoRenthouseBillOrderSyncResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    /**
     * 文件资源上传
     */
//    public void testRenthouseCommonImageUpload() throws AlipayApiException {
//        AlipayClient alipayClient = new DefaultAlipayClient(TP_OPENAPI_URL, TP_APPID, TP_PRIVATEKEY, "json", "UTF-8");
//        AlipayEcoRenthouseCommonImageUploadRequest request = new AlipayEcoRenthouseCommonImageUploadRequest();
//
//        request.setBizContent("{" +
//                "    \"file_base\": \""+new String(Base64.encodeBase64(ImageUtil.getInputStreamByGet("http://www.phosion.cn/uploadfiles/image/201703/507.jpg")))+"\"," + //图片base64
//                "    \"file_type\": \"1\"," +
//                "    \"is_public\": true" +
//                "}");
//        AlipayEcoRenthouseCommonImageUploadResponse response = alipayClient.execute(request);
//        System.out.println(response.getBody());
//        if (response.isSuccess()) {
//            System.out.println("调用成功");
//        } else {
//            System.out.println("调用失败");
//        }
//    }

    /**
     * 公寓运营商基础信息维护
     */

    public void testRenthouseKaBaseinfoSync() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(TP_OPENAPI_URL, TP_APPID, TP_PRIVATEKEY, "json", "UTF-8");
        AlipayEcoRenthouseKaBaseinfoSyncRequest request = new AlipayEcoRenthouseKaBaseinfoSyncRequest();
        //注意该接口使用的前提是，需要开通新的ISVappid权限，目前开发环境的测试账号已被使用
        request.setBizContent("{" +
                "    \"ka_name\": \"张三公寓\"" +
                "}");
        AlipayEcoRenthouseKaBaseinfoSyncResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    /**
     * 公寓运营商基础信息获取
     */
    public void testRenthouseKaBaseinfoQuery() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(TP_OPENAPI_URL, TP_APPID, TP_PRIVATEKEY, "json", "UTF-8");
        AlipayEcoRenthouseKaBaseinfoQueryRequest request = new AlipayEcoRenthouseKaBaseinfoQueryRequest();
        request.setBizContent("{" +
                "\"ka_code\": \"1234\"" +
                "}");
        AlipayEcoRenthouseKaBaseinfoQueryResponse response = alipayClient.execute(request);
        System.out.println(response.getKaName());
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    /**
     * 公寓运营商服务地址注册
     */
    public void testRenthouseKaServiceCreate() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(TP_OPENAPI_URL, TP_APPID, TP_PRIVATEKEY, "json", "UTF-8");
        AlipayEcoRenthouseKaServiceCreateRequest request = new AlipayEcoRenthouseKaServiceCreateRequest();
        request.setBizContent("{" +
                "    \"address\": \"http://xxx/pay/pay.html\"," +
                "    \"ka_code\": \"1YqOEtXtWgsrhKjIc\"," +
                "    \"type\": 1" +
                "}");
        AlipayEcoRenthouseKaServiceCreateResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
