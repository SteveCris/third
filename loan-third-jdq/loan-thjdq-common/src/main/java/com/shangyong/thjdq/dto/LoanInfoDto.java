package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * Created by zbb on 2019-03-14.
 */
public class LoanInfoDto implements Serializable {

    private static final long serialVersionUID = -2486948200039077831L;
    /**
     * 贷款金额（如：5000.00）
     */
    private String loan_amount;
    /**
     * 贷款期限，若为单期按天还款产品即传天数，多期产品即传期数
     */
    private String loan_term;

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getLoan_term() {
        return loan_term;
    }

    public void setLoan_term(String loan_term) {
        this.loan_term = loan_term;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoanInfoDto{");
        sb.append("loan_amount=").append(loan_amount);
        sb.append(", loan_term=").append(loan_term);
        sb.append('}');
        return sb.toString();
    }
}
