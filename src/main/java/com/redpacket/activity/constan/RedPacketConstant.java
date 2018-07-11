package com.redpacket.activity.constan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedPacketConstant {

    private static final Logger logger = LoggerFactory.getLogger(RedPacketConstant.class);
    /**
     * 红包池redis key 前缀
     **/
    private static final String RED_PACKET_POOL_PREFIX = "728_redPacket_pool_";
    /**
     * 红包记录redis key 前缀
     **/
    private static final String RED_PACKET_RECORD_PREFIX = "728_redPacket_record_";
    /**
     * 优惠券领取记录redis key 前缀
     **/
    private static final String COUPON_RECORD_PREFIX = "728_coupon_record_";
    /**
     * 用户redis key 前缀
     **/
    private static final String USER_PREFIX = "728_user_";
    /**
     * 活动场次redis key 前缀
     **/
    private static final String ACTIVITY_PREFIX = "728_activity_";
    /**
     * 每天已抢红包数据量 redis key 前缀
     **/
    public static final String REDPACKET_COUNT_PREFIX = "728_redpacketCount_";
    /**
     * 728日红包记录的key单独处理
     **/
    public static final String RED_PACKET_RECORD_PREFIX_728 = "728_day_redPacket_record";

    // 提现成功
    public static final String WC_STATUS_SUCCESS = "1";
    // 提现失败
    public static final String WC_STATUS_FAILED = "2";

    /**
     * 用户鉴权状态
     */
    // 未校验
    public static final int USER_STATUS_DEFAULT = 0;
    // 校验通过
    public static final int USER_STATUS_VALID = 1;
    // 校验不通过
    public static final int USER_STATUS_NOT_VALID = 2;

    //字符串拆分分隔符
    public static final String SEPARATOR = ",";

    //红包抢光标志
    public static final String SACK_MARK = "2";

    //普通红包
    public static final String REDPACKET_TYPE_GENERAL = "1";
    //升级红包
    public static final String REDPACKET_TYPE_UPGRADE = "2";

    //升级红包类型 任务升级
    public static final String UPGRADE_TYPE_TASK = "1";
    //升级红包类型 分享升级
    public static final String UPGRADE_TYPE_SHARE = "2";

    //红包记录场次id位置
    public static final int RP_RECORD_INDEX_ROUNDID = 0;
    //红包记录红包id位置
    public static final int RP_RECORD_INDEX_REDID = 1;
    //红包记录红包总金额位置
    public static final int RPT_RECORD_INDEX_AMOUNT = 2;
    //红包记录红包金额位置
    public static final int RP_RECORD_INDEX_REDAMOUNT = 3;
    //红包记录抢红包时间位置
    public static final int RP_RECORD_INDEX_TIME = 4;
    //红包记录dnum位置
    public static final int RP_RECORD_INDEX_TVID = 5;
    //红包记录升级金额位置
    public static final int RP_RECORD_INDEX_UPAMOUNT = 6;
    //红包记录升级类型位置
    public static final int RP_RECORD_INDEX_UPTYPE = 7;

    //红包抢光返回数组长度
    public static final int RP_RETURN_SACK_LENGTH = 9;
    //抢红包返回任务值位置
    public static final int RP_RETRUN_MV_INDEX = 7;
    //抢红包返回红包池状态位置
    public static final int RP_RETURN_STATUS_INDEX = 8;

    public static final int CP_RETURN_LENGTH = 3;
    public static final int CP_RETURN_INDEX_CPID = 0;
    public static final int CP_RETURN_INDEX_RID = 1;
    public static final int CP_RETURN_INDEX_TIME = 2;

    public static final String RP_CURRDATE_SUPPER_MIN_DEFAULT = "6600";


    /**
     * 获取红包池redis key
     *
     * @param roundId
     * @return
     */
    public static String getRedPacketPoolRedisKey(int roundId) {
        return String.format("%s%s", RED_PACKET_POOL_PREFIX, roundId);
    }

    /**
     * 获取红包记录redis key
     *
     * @param date
     * @return
     */
    public static String getRedPacketRecordRedisKey(String date) {
        return String.format("%s%s", RED_PACKET_RECORD_PREFIX, date);
    }

    /**
     * 728当天红包记录redis key
     *
     * @return
     */
    public static String get728DayRedPacketRecordRedisKey() {
        return RED_PACKET_RECORD_PREFIX_728;
    }

    /**
     * 获取优惠券领取记录 redis key
     *
     * @param date
     * @return
     */
    public static String getCouponRecordRedisKey(String date) {
        return String.format("%s%s", COUPON_RECORD_PREFIX, date);
    }

    /**
     * 获取用户redis key
     *
     * @return
     */
    public static String getUserRedisKey(String tvid) {
        return String.format("%s%s", USER_PREFIX, tvid);
    }

    /**
     * 活动场次redis key
     *
     * @param roundId
     * @return
     */
    public static String getActivityRoundRedisKey(int roundId) {
        return String.format("%s%s", ACTIVITY_PREFIX, roundId);
    }

    /**
     * 获取已抢红包数据 redis key
     *
     * @param date
     * @return
     */
    public static String getRedpacketCountRedisKey(String date) {
        return String.format("%s%s", REDPACKET_COUNT_PREFIX, date);
    }
}

