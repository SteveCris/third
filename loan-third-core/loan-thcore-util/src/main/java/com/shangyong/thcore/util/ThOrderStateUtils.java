package com.shangyong.thcore.util;

/**
 * 校验订单状态
 * Created by ybds on 2019-03-27.
 */
public class ThOrderStateUtils {

    //订单状态（0初始化10待审核20审核中30审核失败40待绑卡50绑卡中60待前置签约70待后置签约80待放款90放款中100放款失败110待还款120还款中130已结清140逾期1000订单取消）

    // 订单状态数组
    private static final int[] arrStatus = new int[]{
            0,
            10,
            30,
            40,
            60,
            70,
            80,
            100,
            110,
            130,
            140
    };

    /**
     * 状态比较
     * @param oldStatus
     * @param currentStatus
     * @return
     */
    public static int orderStatusCompare(int oldStatus, int currentStatus) {

        // 订单取消
        if (currentStatus == 1000) {
            return 1000;
        }

        // 审核失败
        if (currentStatus == 30) {
            return 1000;
        }

        // 放款失败
        if (currentStatus == 100) {
            return 1000;
        }

        // 逾期
        if (currentStatus == 140 && oldStatus == 110) {
            return 1000;
        }

        // 已结清
        if (currentStatus == 130 && oldStatus == 140) {
            return 0;
        }

        int currentStatusIndex = findIndexByStatus(currentStatus);
        if (currentStatusIndex == 1000) {
            return 1000;
        }

        int oldStatusIndex = findIndexByStatus(oldStatus);
        if (oldStatusIndex == 1000) {
            return 1000;
        }

        return currentStatusIndex - oldStatusIndex;
    }

    /**
     * 查询订单状态index
     * @param status
     * @return
     */
    private static int findIndexByStatus(int status) {
        for (int i = 0; i < arrStatus.length; i++) {
            if (arrStatus[i] == status) {
                return i;
            }
        }
        return 1000;
    }

    /**
     * 校验能否绑卡
     * @param status
     * @return
     */
    public static boolean ifCanBindCard(int status) {

        // 过滤取消状态
        if(!filterCancel(status)) {
            return false;
        }

        // 待绑卡之后的状态都可以绑卡
        if (status == 40
                || status == 50
                || status == 60
                || status == 70
                || status == 80
                || status == 90
                || status == 100
                || status == 110
                || status == 120
                || status == 130
                || status == 140) {
            return true;
        }

        return false;
    }

    /**
     * 能否确认借款
     * @param status
     * @return
     */
    public static boolean ifCanConfirmLoan(int status) {
        // 过滤取消状态
        if(!filterCancel(status)) {
            return false;
        }

        // 待前置签约之后的状态都可以确认借款
        if (status == 60
                || status == 70
                || status == 80
                || status == 90
                || status == 100
                || status == 110
                || status == 120
                || status == 130
                || status == 140) {
            return true;
        }

        return false;
    }

    /**
     * 能否还款
     * @param status
     * @return
     */
    public static boolean ifCanRepayment(int status) {
        // 过滤取消状态
        if(!filterCancel(status)) {
            return false;
        }

        // 待待还款之后的状态都可以确认借款
        if (status == 110
                || status == 120
                || status == 130
                || status == 140) {
            return true;
        }

        return false;
    }

    /**
     * 能否跳转60待前置签约
     * @param status
     * @return
     */
    public static boolean ifCanPreSignature(int status) {
        // 过滤取消状态
        if(!filterCancel(status)) {
            return false;
        }

        if (status == 60) {
            return true;
        }

        return false;
    }

    /**
     * 能否跳转70待后置签约
     * @param status
     * @return
     */
    public static boolean ifCanPendingSigning(int status) {
        // 过滤取消状态
        if(!filterCancel(status)) {
            return false;
        }

        if (status == 70) {
            return true;
        }

        return false;
    }

    /**
     * 校验是否是取消订单
     * @param status
     * @return
     */
    public static boolean filterCancel(int status) {
        if (status == 1000) {
            return false;
        }
        return true;
    }

}
