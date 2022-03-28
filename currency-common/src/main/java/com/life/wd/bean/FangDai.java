package com.life.wd.bean;

import java.util.ArrayList;

/**
 * 等额本息计算公式：〔贷款本金×月利率×（1＋月利率＾还款月数〕÷〔（1＋月利率＾还款月数－1〕。等额本金计算公式：每月还款金额 = （贷款本金 / 还款月数+（本金 — 已归还本金累计额×每月利率。其中＾符号表示乘方。
 *
 * 假定借款人从银行获得一笔20万元的个人住房贷款，贷款期限20年，贷款年利率4.2%，每月还本付息。按照上述公式计算，每月应偿还本息和为1233.14元。
 *
 *
 *
 */
public class FangDai {

    /**
     * 计算等额本息还款
     *
     * @param principal 贷款总额
     * @param months    贷款期限
     * @param rate      贷款利率
     * @return
     */
    public static String[] calculateEqualPrincipalAndInterest(double principal, int months, double rate) {
        ArrayList<String> data = new ArrayList<String>();
        double monthRate = rate / (100 * 12);//月利率
        double preLoan = (principal * monthRate * Math.pow((1 + monthRate), months)) / (Math.pow((1 + monthRate), months) - 1);//每月还款金额
        double totalMoney = preLoan * months;//还款总额
        double interest = totalMoney - principal;//还款总利息
        data.add(String.valueOf(totalMoney));//还款总额
        data.add(String.valueOf(principal));//贷款总额
        data.add(String.valueOf(interest));//还款总利息
        data.add(String.valueOf(preLoan));//每月还款金额
        data.add(String.valueOf(months));//还款期限
        return data.toArray(new String[data.size()]);
    }

    public static void main(String[] args) {
        System.out.println(3000*1800);
    }


}
