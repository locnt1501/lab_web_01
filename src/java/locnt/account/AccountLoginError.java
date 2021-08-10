/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnt.account;

import java.io.Serializable;

/**
 *
 * @author LocPC
 */
public class AccountLoginError implements Serializable {

    private String emailOrPasswordIncorrect;
    private String reCAPTCHANotChecked;

    public AccountLoginError() {
    }

    public String getEmailOrPasswordIncorrect() {
        return emailOrPasswordIncorrect;
    }

    public void setEmailOrPasswordIncorrect(String emailOrPasswordIncorrect) {
        this.emailOrPasswordIncorrect = emailOrPasswordIncorrect;
    }

    public String getReCAPTCHANotChecked() {
        return reCAPTCHANotChecked;
    }

    public void setReCAPTCHANotChecked(String reCAPTCHANotChecked) {
        this.reCAPTCHANotChecked = reCAPTCHANotChecked;
    }

}
