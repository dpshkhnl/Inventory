/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.service.account;

import javax.ejb.Local;
import javax.ejb.Stateless;
import np.info.dpshkhnl.model.account.AccHead;
import np.info.dpshkhnl.service.core.GenericDAO;

/**
 *
 * @author Dpshkhnl
 */
@Stateless
public class AccountHeadEJB  extends GenericDAO<AccHead> {
	public AccountHeadEJB() {
		super(AccHead.class);
	}
}
