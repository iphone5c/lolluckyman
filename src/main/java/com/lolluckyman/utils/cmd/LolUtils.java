package com.lolluckyman.utils.cmd;


import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.business.admin.entity.Admin;
import com.lolluckyman.utils.LolConvert;
import com.lolluckyman.utils.core.ISerialize;
import com.lolluckyman.utils.core.JsonSerialize;
import com.lolluckyman.utils.core.NameValue;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public final class LolUtils {
    private static final String UNIT = "万千佰拾亿千佰拾万千佰拾元角分";
    private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";
    private static final double MAX_VALUE = 9.99999999999999E12D;
    /** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
    public static final String US_ASCII = "US-ASCII";

    /** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
    public static final String ISO_8859_1 = "ISO-8859-1";

    /** 8 位 UCS 转换格式 */
    public static final String UTF_8 = "UTF-8";

    /** 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序 */
    public static final String UTF_16BE = "UTF-16BE";

    /** 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序 */
    public static final String UTF_16LE = "UTF-16LE";

    /** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
    public static final String UTF_16 = "UTF-16";

    /** 中文超大字符集 */
    public static final String GBK = "GBK";

    public static final String GB2312="gb2312";

    private static final String PROVINCES[] = {"安徽","澳门","北京","福建","甘肃","广东","广西","贵州","海南","河北","河南","黑龙江","湖北","湖南","吉林","江苏","江西","辽宁","内蒙古","宁夏","青海","山东","山西","陕西","上海","四川","台湾","天津","西藏","香港","新疆","云南","浙江","重庆"};

    private static final String CITY[][] = {
            {"合肥","安庆","蚌埠","亳州","巢湖","池州","滁州","阜阳","淮北","淮南","黄山","六安","马鞍山","宿州","铜陵","芜湖","宣城"},
            {"澳门"},
            {"昌平","朝阳","崇文","大兴","东城","房山","丰台","海淀","怀柔","门头沟","密云","平谷","石景山","顺义","通州","西城","宣武","延庆"},
            {"福州","龙岩","南平","宁德","莆田","泉州","三明","厦门","漳州"},
            {"兰州","白银","定西","甘南","嘉峪关","金昌","酒泉","临夏","陇南","平凉","庆阳","天水","武威","张掖"},
            {"广州","潮州","东莞","佛山","河源","惠州","江门","揭阳","茂名","梅州","清远","汕头","汕尾","韶关","深圳","阳江","云浮","湛江","肇庆","中山","珠海"},
            {"桂林","百色","北海","崇左","防城港","贵港","河池","贺州","来宾","柳州","南宁","钦州","梧州","玉林"},
            {"贵阳","安顺","毕节","六盘水","黔东南","黔南","黔西南","铜仁","遵义"},
            {"海口","白沙","保亭","昌江","澄迈","儋州","定安","东方","乐东","临高","陵水","南沙群岛","琼海","琼中","三亚","屯昌","万宁","文昌","五指山","西沙群岛","中沙群岛"},
            {"石家庄","保定","沧州","承德","邯郸","衡水","廊坊","秦皇岛","唐山","邢台","张家口"},
            {"郑州","安阳","鹤壁","焦作","开封","洛阳","漯河","南阳","平顶山","濮阳","三门峡","商丘","新乡","信阳","许昌","周口","驻马店"},
            {"哈尔滨","大庆","大兴安岭","鹤岗","黑河","鸡西","佳木斯","牡丹江","七台河","齐齐哈尔","双鸭山","绥化","伊春"},
            {"武汉","鄂州","恩施","黄冈","黄石","荆门","荆州","潜江","神农架","十堰","随州","天门","仙桃","咸宁","襄樊","孝感","宜昌"},
            {"长沙","常德","郴州","衡阳","怀化","娄底","邵阳","湘潭","湘西","益阳","永州","岳阳","张家界","株洲"},
            {"长春","白城","白山","吉林","辽源","四平","松原","通化","延边"},
            {"南京","常州","淮安","连云港","南通","苏州","宿迁","泰州","无锡","徐州","盐城","扬州","镇江"},
            {"南昌","抚州","赣州","吉安","景德镇","九江","萍乡","上饶","新余","宜春","鹰潭"},
            {"沈阳","鞍山","本溪","朝阳","大连","丹东","抚顺","阜新","葫芦岛","锦州","辽阳","盘锦","铁岭","营口"},
            {"呼和浩特","阿拉善","巴彦淖尔","包头","赤峰","鄂尔多斯","呼伦贝尔","通辽","乌海","乌兰察布","锡林郭勒","兴安"},
            {"银川","固原","石嘴山","吴忠","中卫"},
            {"西宁","果洛","海北","海东","海南","海西","黄南","玉树"},
            {"济南","滨州","德州","东营","菏泽","济宁","莱芜","聊城","临沂","青岛","日照","泰安","威海","潍坊","烟台","枣庄","淄博"},
            {"太原","长治","大同","晋城","晋中","临汾","吕梁","朔州","忻州","阳泉","运城"},
            {"西安","安康","宝鸡","汉中","商洛","铜川","渭南","咸阳","延安","榆林"},
            {"宝山","长宁","崇明","奉贤","虹口","黄浦","嘉定","金山","静安","卢湾","闵行","南汇","浦东","普陀","青浦","松江","徐汇","杨浦","闸北"},
            {"成都","阿坝","巴中","达州","德阳","甘孜","广安","广元","乐山","凉山","泸州","眉山","绵阳","内江","南充","攀枝花","遂宁","雅安","宜宾","资阳","自贡"},
            {"台北","阿莲","安定","安平","八德","八里","白河","白沙","板桥","褒忠","宝山","卑南","北斗","北港","北门","北埔","北投","补子","布袋","草屯","长宾","长治","潮州","车城","成功","城中区","池上","春日","刺桐","高雄","花莲","基隆","嘉义","苗栗","南投","屏东","台东","台南","台中","桃园","新竹","宜兰","彰化"},
            {"宝坻","北辰","大港","东丽","汉沽","和平","河北","河东","河西","红桥","蓟县","津南","静海","南开","宁河","塘沽","武清","西青"},
            {"拉萨","阿里","昌都","林芝","那曲","日喀则","山南"},
            {"北区","大埔区","东区","观塘区","黄大仙区","九龙","葵青区","离岛区","南区","荃湾区","沙田区","深水埗区","屯门区","湾仔区","西贡区","香港","新界","油尖旺区","元朗区","中西区"},
            {"乌鲁木齐","阿克苏","阿拉尔","阿勒泰","巴音郭楞","博尔塔拉","昌吉","哈密","和田","喀什","克拉玛依","克孜勒苏柯尔克孜","石河子","塔城","图木舒克","吐鲁番","五家渠","伊犁"},
            {"昆明","保山","楚雄","大理","德宏","迪庆","红河","丽江","临沧","怒江","曲靖","思茅","文山","西双版纳","玉溪","昭通"},
            {"杭州","湖州","嘉兴","金华","丽水","宁波","衢州","绍兴","台州","温州","舟山"},
            {"巴南","北碚","璧山","长寿","城口","大渡口","大足","垫江","丰都","奉节","涪陵","合川","江北","江津","九龙坡","开县","梁平","南岸","南川","彭水","綦江","黔江","荣昌","沙坪坝","石柱","双桥","铜梁","潼南","万盛","万州","巫山","巫溪","武隆","秀山","永川","酉阳","渝北","渝中","云阳","忠县"}
    };

    public LolUtils() {
    }

    public static boolean isEmptyOrNull(String str) {
        return str == null || str.isEmpty();
    }

    public static String toCNMoneyString(double money) {
        if(money >= 0.0D && money <= 9.99999999999999E12D) {
            long l = Math.round(money * 100.0D);
            if(l == 0L) {
                return "零元整";
            } else {
                String strValue = l + "";
                int i = 0;
                int j = "万千佰拾亿千佰拾万千佰拾元角分".length() - strValue.length();
                String afterMoney = "";

                for(boolean isZero = false; i < strValue.length(); ++j) {
                    char ch = strValue.charAt(i);
                    if(ch == 48) {
                        isZero = true;
                        if("万千佰拾亿千佰拾万千佰拾元角分".charAt(j) == 20159 || "万千佰拾亿千佰拾万千佰拾元角分".charAt(j) == 19975 || "万千佰拾亿千佰拾万千佰拾元角分".charAt(j) == 20803) {
                            afterMoney = afterMoney + "万千佰拾亿千佰拾万千佰拾元角分".charAt(j);
                            isZero = false;
                        }
                    } else {
                        if(isZero) {
                            afterMoney = afterMoney + "零";
                            isZero = false;
                        }

                        afterMoney = afterMoney + "零壹贰叁肆伍陆柒捌玖".charAt(ch - 48) + "万千佰拾亿千佰拾万千佰拾元角分".charAt(j);
                    }

                    ++i;
                }

                if(!afterMoney.endsWith("分")) {
                    afterMoney = afterMoney + "整";
                }

                afterMoney = afterMoney.replaceAll("亿万", "亿");
                return afterMoney;
            }
        } else {
            throw new IllegalArgumentException("money参数的超出的范围.");
        }
    }

    public static String toCNMoneyString(BigDecimal money) {
        return toCNMoneyString(money.doubleValue());
    }

    public static Date getDate(int year, int month, int day, int hour, int minute, int second, int milliSecond) {
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        cal.set(year, month - 1, day, hour, minute, second);
        cal.get(14);
        cal.set(14, milliSecond);
        return cal.getTime();
    }

    public static Date getDate(int year, int month, int day) {
        return getDate(year, month, day, 0, 0, 0, 0);
    }

    public static void writeAllByte(byte[] buffer, OutputStream stream) throws IOException {
        stream.write(buffer);
        stream.flush();
    }

    public static byte[] readAllByte(InputStream stream) throws IOException {
        int count = stream.available();
        byte[] b = new byte[count];

        for(int readCount = 0; readCount < count; readCount += stream.read(b, readCount, count - readCount)) {
            ;
        }

        return b;
    }

    public static int writeFile(InputStream stream, String fileName) throws IOException {
        File file = new File(fileName);
        FileOutputStream out = null;
        boolean result = false;

        int result1;
        try {
            byte[] buffer = readAllByte(stream);
            result1 = buffer.length;
            if(!file.getParentFile().exists() && !file.getParentFile().isDirectory()) {
                file.getParentFile().mkdirs();
            }

            out = new FileOutputStream(fileName, false);
            writeAllByte(buffer, out);
        } finally {
            if(out != null) {
                out.close();
            }

        }

        return result1;
    }

    public static InputStream readFile(String fileName) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(fileName);
        return stream;
    }

//    public static boolean isWrapClass(Class<?> cls) {
//        try {
//            return ((Class)cls.getField("TYPE").get((Object)null)).isPrimitive();
//        } catch (Exception var2) {
//            return false;
//        }
//    }

//    public static boolean isBasisClass(Class<?> cls) {
//        return cls.isPrimitive() || cls.isAssignableFrom(String.class) || cls.isAssignableFrom(Date.class) || isWrapClass(cls) || cls.isEnum();
//    }

    public static Date dateAddMinute(Date date, int minute) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(12, minute);
        return ca.getTime();
    }

    public static Date dateAddDay(Date date, int day) {
        int minute = day * 24 * 60;
        return dateAddMinute(date, minute);
    }

    public static int daysOfTwo(Date begin, Date end) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(begin);
        int day1 = aCalendar.get(6);
        aCalendar.setTime(end);
        int day2 = aCalendar.get(6);
        return day2 - day1;
    }

    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws java.text.ParseException
     */
    public static int daysBetween(Date smdate,Date bdate)
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate=sdf.parse(sdf.format(smdate));
            bdate=sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 得到格式化后的数据
     * @param eScaleMode 四舍五入、通舍、通入
     * @param info 需要格式化的数字
     * @param len 保留小数的位数
     * @return
     */
    public static String getDouble(EScaleMode eScaleMode,Double info,int len){
        BigDecimal bg = new BigDecimal(info);
        if (eScaleMode== EScaleMode.四舍五入){
            return bg.setScale(len, BigDecimal.ROUND_HALF_UP).toString();
        }else if (eScaleMode== EScaleMode.通舍){
            return bg.setScale(len, BigDecimal.ROUND_DOWN).toString();
        }else if (eScaleMode== EScaleMode.通入){
            return bg.setScale(len, BigDecimal.ROUND_CEILING).toString();
        }
        return null;
    }

    public static enum EScaleMode {
        四舍五入,
        通舍,
        通入;

        private EScaleMode() {
        }
    }

    public static List<NameValue> getProvinceList(){
        List<NameValue> result = new ArrayList<>();
        for(int i = 0; i < PROVINCES.length; i++){
            result.add(NameValue.create(PROVINCES[i],String.valueOf(i)));
        }
        return result;
    }

    public static String getProvince(int index){
        return PROVINCES[index];
    }

    public static List<NameValue> getCityList(int index){
        String citys[] = CITY[index];
        List<NameValue> result = new ArrayList<>();
        for(int i = 0; i < citys.length; i++){
            result.add(NameValue.create(citys[i],String.valueOf(i)));
        }
        return result;
    }

    public static String getCity(int provinceIndex,int cityIndex){
        return CITY[provinceIndex][cityIndex];
    }

    public static Socket socketLink(String address,int port){
        Socket socket=null;
        try {
            socket=new Socket(address,port);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
        return socket;
    }

    /**
     * 利息计算
     * @param annualyield    收益率
     * @param principal    本金
     * @param day    投资天数
     * @param interstpara    计息参数（365或者360）
     */
    public static Double liquidationInterest(Double annualyield,Double principal,int day,int interstpara){
        return principal*annualyield/interstpara*day;
    }

    /**
     * 判断当前日期是星期几<br>
     * <br>
     * @param pTime 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(LolConvert.toDate(pTime, LolConvert.DATEFORMAT_DATA_EN_LONG));
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 获取后台当前登录用户
     * @param request
     * @return
     */
    public static Admin getCurrentAdmin(HttpServletRequest request){
        Admin admin= (Admin) request.getSession().getAttribute("CURRENT_AMIN");
        return admin;
    }

    /**
     * 获取前端当前登录用户
     * @param request
     * @return
     */
    public static Account getCurrentAccount(HttpServletRequest request){
        Account account= (Account) request.getSession().getAttribute("CURRENT_ACCOUNT");
        return account;
    }

    public static List<Map> getPreviewGroup(HttpServletRequest request){
        List<Map> list= (List<Map>) request.getSession().getAttribute("PREVIEW_GROUPS");
        return list;
    }

    public static void removePreviewGroup(HttpServletRequest request){
        request.getSession().removeAttribute("PREVIEW_GROUPS");
    }

    /**
     * html 必须是格式良好的
     * @param html 转码内容
     * @return
     * @throws Exception
     */
    public static String formatHtml(String html) throws Exception {
        String info=html;
        info=info.replace("<","&lt;");
        info=info.replace(">","&gt;");
        info=info.replace("&","&amp;");
        info=info.replace("\"","&quot;");
        info=info.replace("\'","&apos;");
        return info;
    }

    /**
     * 密码加密
     * @param origPwd
     * @return
     */
    public static String encryptPassword(String origPwd){
        return MD5.MD5Encode(origPwd);
    }

    /**
     * 密码验证
     * @param encryptedPwd 加密后的密码
     * @param origPwd 加密前的密码
     * @return
     */
    public static boolean verifyPassword(String origPwd,String encryptedPwd){
        return MD5.verify(origPwd,encryptedPwd);
    }

//    /**
//     * 修改数据库中用户后，更新session中的User
//     * @param request
//     * @param latestUser
//     */
//    public static synchronized void updateSessionUser(HttpServletRequest request,User latestUser){
//        User sessionUser = getCurrentUser(request);
//        if(sessionUser!=null&&sessionUser.getUserId().equals(latestUser.getUserId())){
//            synchronized (sessionUser) {
//                sessionUser.setDeptId(latestUser.getDeptId());
//                sessionUser.setFullName(latestUser.getFullName());
//                sessionUser.setDescription(latestUser.getDescription());
//                sessionUser.setStatus(latestUser.getStatus());
//            }
//        }
//    }

    /**
     * 套接字连接
     * @param host 主机地址
     * @param port 端口
     * @param records 发送的信息
     * @return
     */
    public static Object details(String host,Integer port,String records){
        try {
            if (LolUtils.isEmptyOrNull(host))
                throw new IllegalArgumentException("连接主机的地址不能为空");
            if (host==null)
                throw new IllegalArgumentException("连接主机的端口号不能为空");
            // 创建socket对象，指定服务器的ip地址，和服务器监听的端口号
            // 客户端在new的时候，就发出了连接请求，服务器端就会进行处理，如果服务器端没有开启服务，那么
            // 这时候就会找不到服务器，并同时抛出异常==》java.net.ConnectException: Connection
            // refused: connect
            Socket s1 = new Socket(host, port);
            //打开输出流
            OutputStream os = s1.getOutputStream();
            //封装输出流
            DataOutputStream dos = new DataOutputStream(os);
            //打开输入流
            InputStream is = s1.getInputStream();
            //封装输入流
            DataInputStream dis = new DataInputStream(is);
//            //读取键盘输入流
//            InputStreamReader isr = new InputStreamReader(System.in);
//            //封装键盘输入流
//            BufferedReader br = new BufferedReader(isr);
            String info=records;
//            //客户端先读取键盘输入信息
//            info = br.readLine();
            //把他写入到服务器方
            dos.write(info.getBytes());
            //如果客户端自己说：bye，即结束对话
            //接受服务器端信息
            byte[] buff=new byte[1024];
            int i= dis.read(buff);
            //关闭相应的输入流，输出流，socket对象
            dis.close();
            dos.close();
            s1.close();
            //输出
            return new String(buff,"gb2312").trim();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 读取静态资源文件
     * @param propertiesPath 静态资源文件相对路径
     * @return 返回Properties对象
     */
    public static Properties getProperties(String propertiesPath){
        try {
            Resource resource = (Resource) new ClassPathResource(propertiesPath);
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            return props==null?null:props;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param request
     * @param response
     * @param filePath
     * @return
     */
    public static Map<String,Object> uploadFile(HttpServletRequest request,HttpServletResponse response,String filePath){
        Map<String,Object> result = new HashMap<String,Object>();
        String path = null;
        if(LolUtils.isEmptyOrNull(filePath)){
            path = request.getServletContext().getRealPath("/upload");
        }else {
            path = request.getServletContext().getRealPath(filePath);
        }
        File fPath = new File(path);
        if (!fPath.exists()) {
            fPath.mkdirs();
        }
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request ;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()){
                Map<String,Object> fileInfo = new HashMap<>();
                MultipartFile file = multiRequest.getFile((String)iter.next());
                //有上传文件的话，容量是大于0的。
                if (file != null && file.getSize() > 0){
                    String fieldName = ((CommonsMultipartFile)file).getFileItem().getFieldName();
                    String fileName = file.getOriginalFilename();
                    String ext = fileName.substring(fileName.lastIndexOf(".")) ;
                    String fileReName = UUID.randomUUID().toString() + ext ;
                    File localFile = new File(path,fileReName);
                    try {
                        file.transferTo(localFile);
                        fileInfo.put("filePath",path +"/"+fileReName);
                        fileInfo.put("fileName",fileName);
                        fileInfo.put("fileUploadTime", LolConvert.dateToString(new Date(),LolConvert.DATEFORMAT_DATETIME_EN_LONG));
                        result.put(fieldName,fileInfo);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new IllegalArgumentException("文件上传失败");
                    }
                }
            }
        }else {
            throw new IllegalArgumentException("没有上传的文件");
        }
        return result;
    }

    /**
     * 字符串编码转换的实现方法
     * @param str  待转换编码的字符串
     * @param newCharset 目标编码
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static String changeCharset(String str,String newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            //用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            //用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }

    /**
     * 字符串编码转换的实现方法
     * @param str  待转换编码的字符串
     * @param oldCharset 原编码
     * @param newCharset 目标编码
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public String changeCharset(String str, String oldCharset, String newCharset)throws UnsupportedEncodingException {
        if (str != null) {
            //用旧的字符编码解码字符串。解码可能会出现异常。
            byte[] bs = str.getBytes(oldCharset);
            //用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }

    /**
     * 对List集合对象进行去重复
     * @param list  需要处理的list集合
     * @return 去除重复之后的 list 集合
     * */

    public static List<String> removeDuplicate(List<String> list)
    {
        HashSet<String> h = new  HashSet<String>(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    public static boolean isBasisClass(Class<?> cls)
    {
        return (cls.isPrimitive()) || (cls.isAssignableFrom(String.class)) || (cls.isAssignableFrom(Date.class)) || (isWrapClass(cls)) || (cls.isEnum());
    }

    public static boolean isWrapClass(Class<?> cls)
    {
        try
        {
            return ((Class)cls.getField("TYPE").get(null)).isPrimitive(); } catch (Exception e) {
        }
        return false;
    }

    public static String getJsonSerialize(Object obj)
    {
        ISerialize serialize = new JsonSerialize();
        return serialize.serialize(obj);
    }

    public static Object getJsonDeserialize(String json, Class<?> cls)
    {
        ISerialize serialize = new JsonSerialize();
        return serialize.deserialize(json, cls);
    }

    public static <T> T getJsonDeserializeT(String json, Class<T> cls)
    {
        ISerialize serialize = new JsonSerialize();
        return serialize.deserializeT(json, cls);
    }

    public static <T> List<T> getJsonDeserializeListT(String json,Class<T> cls)
    {
        ISerialize serialize = new JsonSerialize();
        return serialize.deserializeListT(json, cls);
    }
}
