/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.service.product;

import javax.ejb.Stateless;
import np.info.dpshkhnl.model.product.BrandModel;
import np.info.dpshkhnl.service.core.GenericDAO;

/**
 *
 * @author Dpshkhnl
 */
@Stateless
public class BrandNameEJB extends GenericDAO<BrandModel> {
	public BrandNameEJB() {
		super(BrandModel.class);
} 
    
}
