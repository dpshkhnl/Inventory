/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.service.product;

import javax.ejb.Stateless;
import np.info.dpshkhnl.model.product.ItemTypeModel;
import np.info.dpshkhnl.service.core.GenericDAO;

/**
 *
 * @author Dpshkhnl
 */
@Stateless
public class ItemTypeEJB extends GenericDAO<ItemTypeModel> {
	public ItemTypeEJB() {
		super(ItemTypeModel.class);
} 
    
}
