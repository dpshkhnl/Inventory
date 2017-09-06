/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.service.admin;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import np.info.dpshkhnl.model.admin.UnitModel;
import np.info.dpshkhnl.service.core.GenericDAO;

/**
 *
 * @author Dpshkhnl
 */
@Stateless
public class UnitSettingEJB extends GenericDAO<UnitModel> {
	public UnitSettingEJB() {
		super(UnitModel.class);
}
    
        public int getUnitCountOfAllParent(int unitModelId) {
            UnitModel unitModel = super.find(unitModelId);
		int unitCount = unitModel.getUnitCount();
		List<UnitModel> parentLst = this.getAllParentUnit(unitModel
				.getUnitId());

		if (parentLst.size() > 0) {
			UnitModel unitObj;
			for (UnitModel comboBoxDataModel : parentLst) {
				unitObj = this.find(comboBoxDataModel.getUnitId());
				unitCount *= unitObj.getUnitCount();
			}
		}

		return unitCount;
	}
        
        public List<UnitModel> getAllParentUnit(int unitId) {
            
		UnitModel parentUnit = super.find(unitId);
		UnitModel comboModle;
                 List<UnitModel> parentUnitLst = new ArrayList<>();
		if (parentUnit== null ||parentUnit.getParentUnitId() == 0) {
			return parentUnitLst;
		} else {
			comboModle = new UnitModel();
			comboModle.setUnitId(parentUnit.getParentUnitId());
			
			parentUnitLst.add(comboModle);
			getAllParentUnit(parentUnit.getParentUnitId());
		}
		return parentUnitLst;
	}
}
