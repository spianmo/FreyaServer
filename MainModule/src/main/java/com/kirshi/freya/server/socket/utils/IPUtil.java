package com.kirshi.freya.server.socket.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (c) 2023
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:IPUtil.java
 * @LastModified:2023-05-16T02:29:41.442+08:00
 */

public class IPUtil {

    private static final Integer TIME_OUT = 1000;

    public static String INTRANET_IP = getIntranetIp(); // 内网IP

    public static String INTERNET_IP = getV4IP();

    public static String getNetSegment() {
        String[] r = INTRANET_IP.split("\\.");
        return r[0] + "." + r[1];
    }

    public static List<String> getIPs() {
        List<String> list = new ArrayList<String>();
        boolean flag = false;
        int count = 0;
        Runtime r = Runtime.getRuntime();
        Process p;
        try {
            p = r.exec("arp -a");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
            String inline;
            while ((inline = br.readLine()) != null) {
                if (inline.indexOf("接口") > -1) {
                    flag = !flag;
                    if (!flag) {
                        //碰到下一个"接口"退出循环
                        break;
                    }
                }
                if (flag) {
                    count++;
                    if (count > 2) {
                        //有效IP
                        String[] str = inline.split(" {4}");
                        if (str[0].trim().contains(getNetSegment())) {
                            list.add(str[0].trim());
                        }
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getLocalIPList() {
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                        ip = inetAddress.getHostAddress();
                        ipList.add(ip);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipList;
    }


    private static String getIntranetIp() {
        try {
            //判断系统是否是windows
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                return InetAddress.getLocalHost().getHostAddress();
            }
            return getLocalIPList().get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getV4IP() {
        try {
            String path = "http://www.net.cn/static/customercare/yourip.asp";// 要获得html页面内容的地址
            URL url = new URL(path);// 创建url对象
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 打开连接
            conn.setRequestProperty("contentType", "GBK"); // 设置url中文参数编码
            conn.setConnectTimeout(5 * 1000);// 请求的时间
            conn.setRequestMethod("GET");// 请求方式
            InputStream inStream = conn.getInputStream();
            // readLesoSysXML(inStream);
            BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "GBK"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            // 读取获取到内容的最后一行,写入
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            List<String> ips = new ArrayList<String>();
            //用正则表达式提取String字符串中的IP地址
            String regEx = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
            String str = buffer.toString();
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            while (m.find()) {
                String result = m.group();
                ips.add(result);
            }
            return ips.get(0);

        } catch (Exception e) {
            System.out.println("获取公网IP连接超时");
            return INTERNET_IP;
        }
    }

}