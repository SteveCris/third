package com.shangyong.thjdq.vo;

import java.io.Serializable;

/**
 * 用户放款/还款银行卡
 * Created by ybds on 2019-03-22.
 */
public class BankCardInfoVo implements Serializable {

    private static final long serialVersionUID = 7558750919593091735L;
    /**
     *
     */
    private int card_type;
    /**
     *
     */
    private String card_no;
    /**
     *
     */
    private String bank_name;
    /**
     *
     */
    private String bank_code;

    public int getCard_type() {
        return card_type;
    }

    public void setCard_type(int card_type) {
        this.card_type = card_type;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BankCardInfo{");
        sb.append("card_type=").append(card_type);
        sb.append(", card_no='").append(card_no).append('\'');
        sb.append(", bank_name='").append(bank_name).append('\'');
        sb.append(", bank_code='").append(bank_code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
