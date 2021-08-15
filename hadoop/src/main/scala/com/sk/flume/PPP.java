package com.sk.flume;

/**
 * @Title: PPP
 * @Package: com.sk.flume
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/9/15 - 11:31
 */
public class PPP {
    public static void main(String[] args) {
        String str = "MME组ID |MME节点名称 |国家号 |国家目的码 |SGSN移动国家码 |SGSN移动网号 |MME移动国家码 |MME移动网号 |SGSN号码 |MME编号 |MME号码 |SGSN缺省IPv4 APN |SGSN缺省IPv6 APN |SGSN缺省IPv4IPv6 APN |SGSN缺省PPP APN |国际长途字冠 |支持类型 |支持的Camel类型 |PS用户面版本 |PS用户面模式 |本局支持的LCS版本 |支持Flex功能 |NRI的长度 |全局核心网标识 |MME权重 |运营商名称 |支持用户位置信息变化上报配置 |MME支持MOCN功能 |MME缺省IPv4 APN |MME缺省IPv6 APN |MME缺省IPv4IPv6 APN |不支持Flex功能时是否为UE分配NRI |MME缺省Non-IP APN |支持基于eNodeBID位置变化上报";
        String[] split = str.split("\\|");

        int re = 0;
        for (int i = 0; i < split.length; i++) {
            if(split[i].trim().equals("NRI的长度")){
                System.out.println(i);
                re = i;
            }
        }
        System.out.println("1 = "+ split.length);
        String s = "456 HDBNJfjMME64BZX 86 139 460 00 460 00 8613748505 118 8613748505 cmnet cmnet cmnet cmnet 0 Gb 接口&Iu 接口&增强GPRS 不支持 1 透明模式 不支持 超容 7 59 255 ChinaMobile 支持 不支持 cmnet cmnet cmnet 不分配 mnonip.zte.company 不支持 ";
        String[] split1 = s.trim().split("\\s+");

        System.out.println(split1[re]);
        System.out.println(split1[re - 1]);
        System.out.println(split1[re + 1]);
        System.out.println("2 = "+split1.length);

        System.out.println("=====================================================");
        String s1 = "路由周期性更新定时器(秒) |Ready定时器时长(秒) |SGSN可达定时器时长(秒) |SGSN MM上下文删除定时器时长(分钟) |Iu延迟释放 |移动性管理NAS消息重发次数 |周期性跟踪区更新定时器T3412时长(秒) |MME可达定时器时长(秒) |MME隐式分离定时器时长(秒) |MME MM上下文删除定时器时长(分钟) |UE附着/跟踪区更新重发定时器T3402时长(秒) |MME 获取IMEI(SV) |MME支持LTE用户接入限制 |LTE用户接入EUTRAN网络失败原因 |MME支持区域码限制 |支持EPLMN组数 |由于区域编码限制用户接入拒绝原因值 |MME发起的分离携带的分离原因值 |S4 SGSN隐式分离定时器时长(秒) |MME支持NB用户接入限制 |NB用户接入EUTRAN网络失败原因值";
        System.out.println(s1.trim().split("\\|").length);

        String s2 = "3240 120 3480 1440 不支持 4 3240 3480 3480 1440 720 支持ADD功能 支持 No Suitable Cells In tracking area 不支持 不支持 Tracking Area not allowed EPS services not allowed NO No Suitable Cells In tracking area";
        System.out.println(s2.split(" ").length);
    }
}
