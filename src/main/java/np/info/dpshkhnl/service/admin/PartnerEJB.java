/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.service.admin;

import javax.ejb.Stateless;
import np.info.dpshkhnl.model.admin.PartnerModel;
import np.info.dpshkhnl.model.admin.RouteManagementModel;
import np.info.dpshkhnl.service.core.GenericDAO;

/**
 *
 * @author Dpshkhnl
 */

@Stateless
public class PartnerEJB  extends GenericDAO<PartnerModel> {
	public PartnerEJB() {
		super(PartnerModel.class);
	}
} 
