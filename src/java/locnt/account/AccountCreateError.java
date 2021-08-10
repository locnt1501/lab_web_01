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
public class AccountCreateError implements Serializable {
    private String confirmNotMatchPassword;
    private String emailIsExisted;

    public AccountCreateError() {
    }

    public AccountCreateError(String confirmNotMatchPassword, String emailIsExisted) {
        this.confirmNotMatchPassword = confirmNotMatchPassword;
        this.emailIsExisted = emailIsExisted;
    }

    public String getConfirmNotMatchPassword() {
        return confirmNotMatchPassword;
    }

    public void setConfirmNotMatchPassword(String confirmNotMatchPassword) {
        this.confirmNotMatchPassword = confirmNotMatchPassword;
    }

    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }
    
    
}
